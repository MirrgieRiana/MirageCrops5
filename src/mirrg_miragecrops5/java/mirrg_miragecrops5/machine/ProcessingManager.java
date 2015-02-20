package mirrg_miragecrops5.machine;

import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.ToIntFunction;

import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.gui.container.HelpersContainer;
import mirrg.mir51.inventory.IInventoryMir51;
import net.minecraft.item.ItemStack;

public abstract class ProcessingManager
{

	private DatamodelEnergy energyTankProcessing;
	private IInventoryMir51 inventoryInMaterialProcessing;
	private IInventoryMir51 inventoryOutProcessing;
	private IInventoryMir51 inventoryOut;

	public ProcessingManager(
		DatamodelEnergy energyTankProcessing,
		IInventoryMir51 inventoryInMaterialProcessing,
		IInventoryMir51 inventoryOutProcessing,
		IInventoryMir51 inventoryOut)
	{
		this.energyTankProcessing = energyTankProcessing;
		this.inventoryInMaterialProcessing = inventoryInMaterialProcessing;
		this.inventoryOutProcessing = inventoryOutProcessing;
		this.inventoryOut = inventoryOut;
	}

	public void tick()
	{

		if (isWaiting()) {
			tryStartProcess(duration -> {
				energyTankProcessing.setAmount(0);
				energyTankProcessing.setCapacity(duration);
			});
		}

		if (isProcessing()) {
			processTick(energyTankProcessing);
		}

		if (isOutputting()) {
			clearInventory(inventoryInMaterialProcessing);
			tryMergeInventory(inventoryOutProcessing, inventoryOut, true);
		}

		if (isOutputted()) {
			energyTankProcessing.setAmount(0);
			energyTankProcessing.setCapacity(0);
		}

	}

	public boolean isWaiting()
	{
		return energyTankProcessing.getCapacity() <= 0;
	}

	public boolean isProcessing()
	{
		return energyTankProcessing.getCapacity() > 0
			&& energyTankProcessing.getAmount() < energyTankProcessing.getCapacity();
	}

	public boolean isOutputting()
	{
		return energyTankProcessing.getCapacity() > 0
			&& energyTankProcessing.getAmount() >= energyTankProcessing.getCapacity()
			&& !isEmpty(inventoryOutProcessing);
	}

	public boolean isOutputted()
	{
		return energyTankProcessing.getCapacity() > 0
			&& energyTankProcessing.getAmount() >= energyTankProcessing.getCapacity()
			&& isEmpty(inventoryOutProcessing);
	}

	/**
	 * 実行ステート：waiting
	 */
	protected abstract void tryStartProcess(IntConsumer onStart);

	/**
	 * 実行ステート：isProcessing
	 */
	protected abstract void processTick(DatamodelEnergy energyTankProcessing);

	/**
	 * 燃料ゲージからmin～maxの量を抽出する。
	 * maxまで取れない場合、燃料を消費してゲージを増加{@link #tryBurnFuel()}してmaxまで抽出できるようにする。
	 * それでもmaxまで取れない場合、min以上残っているなら全て抽出し、min未満しか残量がない場合、何も抽出しない。
	 */
	public static long popFuel(long min, long max,
		LongConsumer onEnergyTankDecred,
		DatamodelEnergy energyTank,
		IInventoryMir51 inventoryFuel,
		ToIntFunction<ItemStack> getFuelValue)
	{
		if (energyTank.getAmount() >= max) {
			energyTank.decrAmount(max);
			onEnergyTankDecred.accept(max);
			return max;
		} else {
			// 燃料が足りない

			if (tryBurnFuel(energyTank, inventoryFuel, getFuelValue)) {
				return popFuel(min, max, onEnergyTankDecred, energyTank, inventoryFuel, getFuelValue);
			} else {
				if (energyTank.getAmount() >= min) {
					long amount = energyTank.getAmount();
					energyTank.decrAmount(amount);
					onEnergyTankDecred.accept(amount);
					return amount;
				} else {
					// 燃料がちっとも足りない

					return 0;
				}
			}
		}
	}

	/**
	 * 燃料スロットの末尾のアイテムが燃料なら、燃料を1個消費して燃料ゲージを増加させる。
	 *
	 * @return 燃料ゲージの増加が行われた場合。
	 */
	public static boolean tryBurnFuel(
		DatamodelEnergy energyTank,
		IInventoryMir51 inventoryFuel,
		ToIntFunction<ItemStack> getFuelValue)
	{
		ItemStack itemStack = getLastStack(inventoryFuel);

		int fuelValue = getFuelValue.applyAsInt(itemStack);
		if (fuelValue > 0) {

			inventoryFuel.getInventoryCell(inventoryFuel.getSizeInventory() - 1).decrStackSize(1);

			energyTank.setCapacity(energyTank.getAmount());
			energyTank.setAmount(energyTank.getAmount() + fuelValue);
			energyTank.setCapacity(energyTank.getCapacity() + fuelValue);

			return true;
		}

		return false;
	}

	/**
	 * @return 全てのアイテムがfromから移動した。
	 */
	public static boolean tryMergeInventory(IInventoryMir51 from, IInventoryMir51 to, boolean inverse)
	{
		for (int i = 0; i < from.getSizeInventory(); i++) {
			ItemStack itemStack = from.getInventoryCell(i).getStackInSlot();
			if (itemStack != null) {

				HelpersContainer.mergeItemStackIntoInventory(
					i2 -> () -> to.getInventoryCell(i2).getStackInSlot(),
					i2 -> s -> to.getInventoryCell(i2).setInventorySlotContents(s),
					i2 -> s -> to.getInventoryCell(i2).isItemValidForSlot(s),
					i2 -> to::getInventoryStackLimit,
					i2 -> to::markDirty,
					itemStack, 0, to.getSizeInventory(), inverse);

				if (itemStack.stackSize == 0) {
					from.getInventoryCell(i).setInventorySlotContents(null);
					//from.markDirty();
				} else {
					return false;
				}

			}
		}

		return true;
	}

	/**
	 * @return 全てのアイテムが右に移動完了した。
	 */
	public static boolean bringRight(IInventoryMir51 inventory)
	{
		for (int i = inventory.getSizeInventory() - 1; i >= 1; i--) {
			ItemStack right = inventory.getInventoryCell(i).getStackInSlot();
			ItemStack left = inventory.getInventoryCell(i - 1).getStackInSlot();
			if (right == null) {
				if (left != null) {
					inventory.getInventoryCell(i - 1).setInventorySlotContents(null);
					inventory.getInventoryCell(i).setInventorySlotContents(left);
					return false;
				}
			}
		}
		return true;
	}

	public static ItemStack getLastStack(IInventoryMir51 inventory)
	{
		return inventory.getInventoryCell(inventory.getSizeInventory() - 1).getStackInSlot();
	}

	public static void clearInventory(IInventoryMir51 inventory)
	{
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			inventory.getInventoryCell(i).setInventorySlotContents(null);
		}
	}

	public static boolean isEmpty(IInventoryMir51 inventory)
	{
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getInventoryCell(i).getStackInSlot() != null) return false;
		}
		return true;
	}

}
