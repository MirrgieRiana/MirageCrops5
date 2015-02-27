package mirrg.he.math;

public class HelpersColor
{

	public static int ib3(int r, int g, int b)
	{
		return (HelpersMath.trim(r, 0, 255) << 16)
			| (HelpersMath.trim(g, 0, 255) << 8)
			| HelpersMath.trim(b, 0, 255);
	}

	public static int id3(double r, double g, double b)
	{
		return ib3((int) (r * 255), (int) (g * 255), (int) (b * 255));
	}

	public static int multiplicate(int color, double rate)
	{
		int r = (color >> 16) & 0xFF;
		int g = (color >> 8) & 0xFF;
		int b = color & 0xFF;
		return ib3((int) Math.round(r * rate), (int) Math.round(g * rate), (int) Math.round(b * rate));
	}

}
