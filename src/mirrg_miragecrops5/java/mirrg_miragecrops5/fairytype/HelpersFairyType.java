package mirrg_miragecrops5.fairytype;

import static net.minecraft.util.EnumChatFormatting.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntFunction;

import mirrg.he.math.HelpersMath;
import mirrg.he.math.HelpersString;
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

	private static String makeGauge(int index, int value)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(value < 0 ? RED : value > 0 ? GREEN : DARK_GRAY);
		sb.append(HelpersFairyType.getLabel(index));
		sb.append(" ");
		sb.append(value < 0 ? "-" : value > 0 ? "+" : " ");
		sb.append(Math.abs(value));
		sb.append(" ");

		int gauge = HelpersMath.log2(Math.abs(value)) + 1;
		if (value == 0) gauge = 0;
		sb.append(HelpersString.rept("|", gauge));
		sb.append(BLACK);
		sb.append(HelpersString.rept("|", 10 - gauge));

		return sb.toString();
	}

	public static void addInformation(List<String> strings, int[] values)
	{
		strings.add("       " + GRAY + makeGauge(5, values[5]));
		strings.add(GRAY + makeGauge(3, values[3]) +
			" " + GRAY + makeGauge(4, values[4]));
		strings.add(GRAY + makeGauge(1, values[1]) +
			" " + GRAY + makeGauge(2, values[2]));
		strings.add("       " + GRAY + makeGauge(0, values[0]));
	}

	public static String getLabel(int index)
	{
		if (index == 5) return "Tr";
		if (index == 3) return "Lo";
		if (index == 4) return "Ma";
		if (index == 1) return "In";
		if (index == 2) return "Em";
		if (index == 0) return "Ph";
		return "ER";
	}

}
