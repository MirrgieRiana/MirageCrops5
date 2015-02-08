package mirrg_miragecrops5;

import mirrg_miragecrops5.recipefairy.OreMatcher;

public class FairyType
{

	public final String typeName;

	public FairyType(String typeName)
	{
		this.typeName = typeName;
	}

	protected OreMatcher oreMatcher;

	public OreMatcher getOreMatcher()
	{
		return oreMatcher;
	}

	public int color = 0xffffff;

}
