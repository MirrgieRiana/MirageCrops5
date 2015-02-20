package mirrg_miragecrops5.fairytype;

import java.util.ArrayList;

import mirrg.h.struct.Tuple;
import mirrg_miragecrops5.recipefairy.OreMatcher;
import net.minecraft.item.ItemStack;

public class FairyType
{

	public final String typeName;

	public FairyType(String typeName)
	{
		this.typeName = typeName;
	}

	protected OreMatcher oreMatcher = new OreMatcher();

	public OreMatcher getOreMatcher()
	{
		return oreMatcher;
	}

	public boolean matches(ItemStack itemStack)
	{
		return oreMatcher.matches(itemStack);
	}

	public static int DEFAULT_SKIN_COLOR = 0xFFC9D1;

	/**
	 * 肌の色
	 */
	public int colorS = DEFAULT_SKIN_COLOR;

	/**
	 * 奥翅の色
	 */
	public int colorA = 0xffffff;

	/**
	 * 手前翅の色
	 */

	public int colorB = 0xffffff;
	/**
	 * 触角の色
	 */
	public int colorC = 0xffffff;

	public void setColors(int s, int a, int b, int c)
	{
		colorS = s;
		setColors(a, b, c);
	}

	public void setColors(int a, int b, int c)
	{
		colorA = a;
		colorB = b;
		colorC = c;
	}

	private ArrayList<Tuple<IFairySkill, Double>> skillEntries = new ArrayList<>();

	private double maxSkillLevel = 0;
	private double sumSkillLevelPositive = 0;
	private double sumSkillLevelNegative = 0;

	public void addSkill(IFairySkill fairySkill, double level)
	{
		skillEntries.add(new Tuple<>(fairySkill, level));
		maxSkillLevel = Math.max(maxSkillLevel, level);
		if (fairySkill.isPositive()) {
			sumSkillLevelPositive += level;
		} else {
			sumSkillLevelNegative += level;
		}
	}

	public int[] getValues()
	{
		return getValues((int) Math.ceil(maxSkillLevel));
	}

	public int[] getValues(int tier)
	{
		int[] values = new int[6];
		double rate = sumSkillLevelPositive > 0
			? Math.min(1, tier / sumSkillLevelPositive)
			: 0;
		double rateN = sumSkillLevelNegative > 0
			? Math.min(1, (((tier / sumSkillLevelNegative) - 1) / 2) + 1)
			: 0;

		for (Tuple<IFairySkill, Double> skillEntry : skillEntries) {
			if (skillEntry.getX().isPositive()) {
				skillEntry.getX().increase(values, skillEntry.getY() * rate);
			} else {
				skillEntry.getX().increase(values, skillEntry.getY() * rateN);
			}
		}

		return values;
	}

	public static String getLabel(int index)
	{
		if (index == 0) return "Tr";
		if (index == 1) return "Lo";
		if (index == 2) return "Ma";
		if (index == 3) return "In";
		if (index == 4) return "Em";
		if (index == 5) return "Ph";
		return "ER";
	}

}
