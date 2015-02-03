package mirrg.mir40.math;

public class HelpersString
{

	public static String toUpperCaseHead(String str)
	{
		if (str.length() == 0) return str;
		if (str.length() == 1) return str.toUpperCase();
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

}
