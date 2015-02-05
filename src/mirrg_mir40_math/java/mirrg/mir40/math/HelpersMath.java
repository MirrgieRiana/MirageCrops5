package mirrg.mir40.math;

import java.util.Random;

public class HelpersMath
{

	public static final Random random = new Random();

	/**
	 * {@link #floorRandom(double, Random)}<br>
	 * ランダムは{@link #random}を参照する。
	 */
	public static int floorRandom(double value)
	{
		return floorRandom(value, random);
	}

	/**
	 * 複数回呼び出すと平均値がvalueに収束するように、floor(value)以上ceil(value)以下の値を返す。
	 * 
	 * @param value
	 *            0以上の数値
	 */
	public static int floorRandom(double value, Random random)
	{
		int integer = (int) Math.floor(value);
		double addition = value - integer;

		if (random.nextDouble() < addition) {
			integer++;
		}

		return integer;
	}

	public static int trim(int value, int min, int max)
	{
		if (value < min) return min;
		if (value > max) return max;
		return value;
	}

}
