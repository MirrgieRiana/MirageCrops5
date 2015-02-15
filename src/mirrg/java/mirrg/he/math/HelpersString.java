package mirrg.he.math;

import java.text.NumberFormat;

public class HelpersString
{

	public static NumberFormat numberInstance = NumberFormat.getNumberInstance();

	public static String toUpperCaseHead(String str)
	{
		if (str.length() == 0) return str;
		if (str.length() == 1) return str.toUpperCase();
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String rept(String string, int count)
	{
		StringBuffer sb = new StringBuffer(string.length() * count);
		for (; count > 0; count--) {
			sb.append(string);
		}
		return sb.toString();
	}

	public static String getSeparatedString(long value)
	{
		return numberInstance.format(value);
	}

}
