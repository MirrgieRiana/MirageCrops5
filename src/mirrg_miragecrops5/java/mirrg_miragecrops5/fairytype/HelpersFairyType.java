package mirrg_miragecrops5.fairytype;

import java.util.function.Consumer;
import java.util.function.IntFunction;

import mirrg_miragecrops5.ModuleCore;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import api.mirrg.mir50.net.NBTTypes;

public class HelpersFairyType
{

	public static boolean isNotNegative(Consumer<int[]> increaser)
	{
		return isNotNegative(getValues(increaser));
	}

	public static boolean isNotNegative(int[] values)
	{
		for (int value : values) {
			if (value < 0) return false;
		}
		return true;
	}

	public static int[] getValues(Consumer<int[]> increaser)
	{
		int[] values = new int[6];
		increaser.accept(values);
		return values;
	}

	public static Consumer<int[]> getMultiplicative(int value, Consumer<int[]> increaser)
	{
		return values -> {
			int[] values2 = new int[6];
			increaser.accept(values2);
			for (int i = 0; i < 6; i++) {
				values[i] += values2[i] * value;
			}
		};
	}

	public static Consumer<int[]> getIncreaser(FairyType fairyType, int tier)
	{
		return fairyType.getIncreaser(tier);
	}

	public static IntFunction<Consumer<int[]>> getIncreaser(IFairySkill fairySkill)
	{
		return level -> values -> fairySkill.increase(values, level);
	}

	public static Consumer<int[]> getIncreaser(ItemStack fairy)
	{
		if (fairy == null) return values -> {};

		Item item = fairy.getItem();
		if (item != ModuleCore.loaderItem_craftingToolMirageFairy.get()) return values -> {};

		NBTTagCompound tag = fairy.getTagCompound();
		if (tag == null) return values -> {};
		if (!tag.hasKey("type", NBTTypes.STRING)) return values -> {};

		String typeName = tag.getString("type");
		FairyType fairyType = RegistryFairyType.get(typeName);
		if (fairyType == null) return values -> {};

		int tier = 1;
		if (fairy.getTagCompound().hasKey("tier", NBTTypes.INT)) {
			tier = fairy.getTagCompound().getInteger("tier");
		}

		return getMultiplicative(fairy.stackSize, fairyType.getIncreaser(tier));
	}

	public static Consumer<int[]> getIncreaser(IInventory inventory)
	{
		return values -> {
			for (int i = 0; i < inventory.getSizeInventory(); i++) {
				getIncreaser(inventory.getStackInSlot(i)).accept(values);
			}
		};
	}

	public static Consumer<int[]> getIncreaser(IInventory... inventories)
	{
		return values -> {
			for (IInventory inventory : inventories) {
				for (int i = 0; i < inventory.getSizeInventory(); i++) {
					getIncreaser(inventory.getStackInSlot(i)).accept(values);
				}
			}
		};
	}

	public static Consumer<int[]> getIncreaser(int[] source)
	{
		return values -> {
			for (int i = 0; i < 6; i++) {
				values[i] += source[i];
			}
		};
	}

}
