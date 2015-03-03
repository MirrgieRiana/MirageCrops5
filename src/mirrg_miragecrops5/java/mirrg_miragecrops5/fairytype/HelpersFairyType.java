package mirrg_miragecrops5.fairytype;

import static net.minecraft.util.EnumChatFormatting.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import mirrg.h.struct.Tuple;
import mirrg.he.math.HelpersMath;
import mirrg.he.math.HelpersString;
import mirrg.p.virtualclass.HelpersVirtualClass;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

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

	public static Consumer<int[]> getIncreaser(ItemStack fairy)
	{
		if (fairy == null) return values -> {};

		Tuple<FairyType, Integer> fairyType = HelpersFairyType.getFairyType(fairy);
		if (fairyType == null) return values -> {};

		return getMultiplicative(fairy.stackSize, fairyType.getX().getIncreaser(fairyType.getY()));
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
		sb.append(HelpersString.rept("|", Math.max(0, 10 - gauge)));

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

	public static String getLocalizedName(FairyType fairyType)
	{
		return StatCollector.translateToLocal(
			"fairytype." + (fairyType != null ? fairyType.typeName : "null") + ".name").trim();
	}

	public static String getLocalizedName(IFairySkill fairySkill)
	{
		return StatCollector.translateToLocal(
			"fairyskill." + (fairySkill != null ? fairySkill.getName() : "null") + ".name").trim();
	}

	public static Tuple<FairyType, Integer> getFairyType(ItemStack itemStack)
	{
		if (itemStack == null) return null;

		Optional<IItemFairy> itemFairy = HelpersVirtualClass.cast(itemStack.getItem(), IItemFairy.class);
		if (!itemFairy.isPresent()) return null;

		return itemFairy.get().getFairyType(itemStack);
	}

	private static final int[] RISE_TO_CYCLE = {
		0, 1, 5, 2, 4, 3,
	}; // ph in em lo ma tr -> ph in lo tr ma em

	public static int getDistance(int i, int j)
	{
		return 3 - Math.abs(3 - Math.abs(RISE_TO_CYCLE[i] - RISE_TO_CYCLE[j]));
	}

	public void test_getDistance()
	{
		a(0, 0, 0);
		a(0, 1, 1);
		a(0, 2, 2);
		a(0, 3, 3);
		a(0, 4, 4);
		a(0, 5, 5);
		a(0, 0, 0);

		a(1, 0, 1);
		a(1, 1, 3);
		a(1, 3, 5);
		a(1, 5, 4);
		a(1, 4, 2);
		a(1, 2, 0);

		a(2, 0, 3);
		a(2, 3, 4);
		a(2, 4, 0);
		a(2, 1, 2);
		a(2, 2, 5);
		a(2, 5, 1);

		a(3, 0, 5);
		a(3, 1, 4);
		a(3, 3, 2);
	}

	private void a(int a, int b, int c)
	{
		assertEquals(a, getDistance(b, c));
		assertEquals(a, getDistance(c, b));
	}

}
