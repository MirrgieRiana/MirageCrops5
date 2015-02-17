package mirrg.mir53.gui.container;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class HelpersContainer
{

	public static boolean mergeItemStackIntoInventory(IntFunction<Slot> inventorySlots, ItemStack stack, int start, int end, boolean inverse)
	{
		return mergeItemStackIntoInventory(
			i -> inventorySlots.apply(i)::getStack,
			i -> inventorySlots.apply(i)::putStack,
			i -> inventorySlots.apply(i)::isItemValid,
			i -> i2 -> inventorySlots.apply(i).getSlotStackLimit(),
			i -> inventorySlots.apply(i)::onSlotChanged,
			stack, start, end, inverse);
	}

	/**
	 * アイテムをグローバルスロットのstart～end-1のスロットに移動させる。<br>
	 * 移動が完了したかどうかは、stackのサイズが変更されるのでそれを見る。
	 *
	 * @return 移動が行われたか否か
	 */
	public static boolean mergeItemStackIntoInventory(
		Function<Integer, Supplier<ItemStack>> getStack,
		Function<Integer, Consumer<ItemStack>> putStack,
		Function<Integer, Predicate<ItemStack>> isItemValid,
		Function<Integer, IntUnaryOperator> getSlotStackLimit,
		Function<Integer, Runnable> onSlotChanged,
		ItemStack stack, int start, int end, boolean inverse)
	{
		boolean moved = false;

		// 既存スタックに重ねる
		if (stack.isStackable()) {
			if (stack.stackSize > 0) {
				Range range = new Range(start, end - 1, inverse);

				for (int i : range) {
					if (getStack.apply(i).get() != null) {
						if (mergeItemStackIntoSlot(
							getStack.apply(i)::get,
							putStack.apply(i)::accept,
							isItemValid.apply(i)::test,
							getSlotStackLimit.apply(i)::applyAsInt,
							onSlotChanged.apply(i)::run,
							stack, i, inverse)) moved = true;
						if (stack.stackSize <= 0) break;
					}
				}
			}
		}

		// 空スロットに移動
		if (stack.stackSize > 0) {
			Range range = new Range(start, end - 1, inverse);

			for (int i : range) {
				if (getStack.apply(i).get() == null) {
					if (mergeItemStackIntoSlot(
						getStack.apply(i)::get,
						putStack.apply(i)::accept,
						isItemValid.apply(i)::test,
						getSlotStackLimit.apply(i)::applyAsInt,
						onSlotChanged.apply(i)::run,
						stack, i, inverse)) moved = true;
					if (stack.stackSize <= 0) break;
				}
			}
		}

		return moved;
	}

	public static boolean mergeItemStackIntoSlot(
		IntFunction<Slot> inventorySlots,
		ItemStack stack, int index, boolean inverse)
	{
		Slot slot = inventorySlots.apply(index);

		return mergeItemStackIntoSlot(
			slot::getStack,
			slot::putStack,
			slot::isItemValid,
			i -> slot.getSlotStackLimit(),
			slot::onSlotChanged,
			stack, index, inverse);
	}

	/**
	 * 指定のスロットとマージする。マージできない場合はしない。
	 *
	 * @return マージが行われたか否か
	 */
	public static boolean mergeItemStackIntoSlot(
		Supplier<ItemStack> getStack,
		Consumer<ItemStack> putStack,
		Predicate<ItemStack> isItemValid,
		IntUnaryOperator getSlotStackLimit,
		Runnable onSlotChanged,
		ItemStack stack, int index, boolean inverse)
	{
		ItemStack dest = getStack.get();

		int maxStackSize;
		maxStackSize = stack.getMaxStackSize();
		maxStackSize = Math.min(maxStackSize, getSlotStackLimit.applyAsInt(index));

		if (dest != null) {
			if (dest.getItem() != stack.getItem()) return false;
			if (stack.getHasSubtypes() && stack.getItemDamage() != dest.getItemDamage()) return false;
			if (!ItemStack.areItemStackTagsEqual(stack, dest)) return false;
			if (dest.stackSize >= maxStackSize) return false;
		} else {
			if (!isItemValid.test(stack)) return false;
		}

		if (dest == null) {
			putStack.accept(stack.copy());
			dest = getStack.get();
			dest.stackSize = 0;
		}

		int transferStackSize = Math.min(maxStackSize - dest.stackSize, stack.stackSize);

		if (transferStackSize > 0) {
			stack.stackSize -= transferStackSize;
			dest.stackSize += transferStackSize;
			onSlotChanged.run();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * aからbまでの整数を返す。3と5なら、3,4,5。
	 */
	protected static class Range implements Iterable<Integer>
	{

		int a;
		int b;
		boolean bToA;

		public Range(int a, int b)
		{
			this(a, b, false);
		}

		public Range(int a, int b, boolean bToA)
		{
			if (a > b) {
				int tmp = a;
				a = b;
				b = tmp;
				bToA = !bToA;
			}

			this.a = a;
			this.b = b;
			this.bToA = bToA;
		}

		@Override
		public Iterator<Integer> iterator()
		{
			return new Iterator<Integer>() {

				int next = bToA ? b : a;

				@Override
				public boolean hasNext()
				{
					return bToA ? next >= a : next <= b;
				}

				@Override
				public Integer next()
				{
					return bToA ? next-- : next++;
				}

			};
		}
	}

}
