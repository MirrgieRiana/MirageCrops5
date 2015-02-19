package mirrg_miragecrops5.fairytype;

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

	private int[] values = new int[6];

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

	public void setValues(int ph, int in, int em, int lo, int ma, int tr)
	{
		this.values[0] = ph;
		this.values[1] = in;
		this.values[2] = em;
		this.values[3] = lo;
		this.values[4] = ma;
		this.values[5] = tr;
	}

	public void addValues(int ph, int in, int em, int lo, int ma, int tr)
	{
		this.values[0] += ph;
		this.values[1] += in;
		this.values[2] += em;
		this.values[3] += lo;
		this.values[4] += ma;
		this.values[5] += tr;
	}

	public int getValue(int index)
	{
		if (index < 6) return values[index];
		return 0;
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
