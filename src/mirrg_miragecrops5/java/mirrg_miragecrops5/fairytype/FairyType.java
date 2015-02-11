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

	public int ph, in, em, lo, ma, tr;

	public void setColors(int a, int b, int c)
	{
		colorA = a;
		colorB = b;
		colorC = c;
	}

	public void setValues(int ph, int in, int em, int lo, int ma, int tr)
	{
		this.ph = ph;
		this.in = in;
		this.em = em;
		this.lo = lo;
		this.ma = ma;
		this.tr = tr;
	}

}
