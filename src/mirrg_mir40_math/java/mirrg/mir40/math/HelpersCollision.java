package mirrg.mir40.math;

public class HelpersCollision
{

	/**
	 * valueがminかmaxに等しい時に0、範囲内の時に1、範囲外の時に-1を返す。<br>
	 * minがmaxより大きい場合、この2つを逆に解釈して判定を行う。
	 */
	public static int isHit(int value, int min, int max)
	{
		if (min > max) return isHit(value, max, min);
		if (value < min) return -1;
		if (value > max) return -1;
		if (value == min) return 0;
		if (value == max) return 0;
		return 1;
	}

	/**
	 * {@link #isHit(int, int, int)}
	 */
	public static int isHit(int x, int y, int xMin, int xMax, int yMin, int yMax)
	{
		return Math.min(isHit(y, yMin, yMax), isHit(x, xMin, xMax));
	}

	/**
	 * valueがminより大きい場合に1、maxより小さい場合に2をorした値を返す。<br>
	 * borderがtrueである場合、この判定が等値を含む。
	 */
	public static int getHit(int value, int min, int max, boolean border)
	{
		if (border) {
			return ((value >= min) ? 1 : 0)
				| ((value <= max) ? 2 : 0);
		} else {
			return ((value > min) ? 1 : 0)
				| ((value < max) ? 2 : 0);
		}
	}

	/**
	 * {@link #getHit(int, int, int, boolean)}と同じだが、ビットは下位からxmin, xmax, ymin,
	 * ymaxになっている。
	 */
	public static int getHit(int x, int y, int xMin, int xMax, int yMin, int yMax, boolean border)
	{
		return getHit(x, xMin, xMax, border) | (getHit(y, yMin, yMax, border) << 2);
	}

}
