package mirrg_miragecrops5.machine;

import static org.junit.Assert.*;
import net.minecraftforge.common.util.ForgeDirection;

import org.junit.Test;

public class HelpersDirection
{

	public static final EnumDirection DOWN = EnumDirection.DOWN;
	public static final EnumDirection UP = EnumDirection.UP;
	public static final EnumDirection FRONT = EnumDirection.FRONT;
	public static final EnumDirection RIGHT = EnumDirection.RIGHT;
	public static final EnumDirection BACK = EnumDirection.BACK;
	public static final EnumDirection LEFT = EnumDirection.LEFT;

	@SuppressWarnings("hiding")
	public static enum EnumDirection
	{
		DOWN, UP, FRONT, RIGHT, BACK, LEFT, ;

		static EnumDirection[] values = values();
	}

	private static final int[] DIRECTION_TO_ROTATE = {
		-1, -1, 3, 1, 2, 0,
	};

	/**
	 * @param a
	 *            any
	 * @param b
	 *            Only {2~5}
	 * @return
	 */
	public static EnumDirection subtract(int a, int b)
	{
		if (b == 1) throw new IllegalArgumentException("b == " + b + " (expected: 2~5)");
		if (b == 0) throw new IllegalArgumentException("b == " + b + " (expected: 2~5)");
		if (a == 1) return UP;
		if (a == 0) return DOWN;

		return EnumDirection.values[(4 + DIRECTION_TO_ROTATE[a] - DIRECTION_TO_ROTATE[b]) % 4 + 2];
	}

	/**
	 * @param a
	 *            any
	 * @param b
	 *            Only {east west north south}
	 * @return
	 */
	public static EnumDirection subtract(ForgeDirection a, ForgeDirection b)
	{
		if (b == ForgeDirection.UP) throw new IllegalArgumentException("b == " + b + " (expected: east west north south)");
		if (b == ForgeDirection.DOWN) throw new IllegalArgumentException("b == " + b + " (expected: east west north south)");
		if (a == ForgeDirection.UP) return UP;
		if (a == ForgeDirection.DOWN) return DOWN;

		return EnumDirection.values[(4 + DIRECTION_TO_ROTATE[a.ordinal()] - DIRECTION_TO_ROTATE[b.ordinal()]) % 4 + 2];
	}

	@Test
	public void test()
	{
		assertEquals(FRONT, subtract(ForgeDirection.NORTH, ForgeDirection.NORTH));
		assertEquals(FRONT, subtract(ForgeDirection.SOUTH, ForgeDirection.SOUTH));
		assertEquals(FRONT, subtract(ForgeDirection.EAST, ForgeDirection.EAST));
		assertEquals(FRONT, subtract(ForgeDirection.WEST, ForgeDirection.WEST));

		assertEquals(BACK, subtract(ForgeDirection.NORTH, ForgeDirection.SOUTH));
		assertEquals(BACK, subtract(ForgeDirection.SOUTH, ForgeDirection.NORTH));
		assertEquals(BACK, subtract(ForgeDirection.EAST, ForgeDirection.WEST));
		assertEquals(BACK, subtract(ForgeDirection.WEST, ForgeDirection.EAST));

		assertEquals(LEFT, subtract(ForgeDirection.NORTH, ForgeDirection.EAST));
		assertEquals(LEFT, subtract(ForgeDirection.EAST, ForgeDirection.SOUTH));
		assertEquals(LEFT, subtract(ForgeDirection.SOUTH, ForgeDirection.WEST));
		assertEquals(LEFT, subtract(ForgeDirection.WEST, ForgeDirection.NORTH));

		assertEquals(RIGHT, subtract(ForgeDirection.NORTH, ForgeDirection.WEST));
		assertEquals(RIGHT, subtract(ForgeDirection.WEST, ForgeDirection.SOUTH));
		assertEquals(RIGHT, subtract(ForgeDirection.SOUTH, ForgeDirection.EAST));
		assertEquals(RIGHT, subtract(ForgeDirection.EAST, ForgeDirection.NORTH));
	}

}
