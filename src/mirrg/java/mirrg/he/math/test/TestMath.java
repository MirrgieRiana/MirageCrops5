package mirrg.he.math.test;

import static org.junit.Assert.*;
import mirrg.he.math.HelpersMath;
import mirrg.he.math.HelpersString;

import org.junit.Test;

public class TestMath
{

	@Test
	public void testMath()
	{
		assertEquals(0, HelpersMath.log2(1));
		assertEquals(1, HelpersMath.log2(2));
		assertEquals(1, HelpersMath.log2(3));
		assertEquals(2, HelpersMath.log2(4));
		assertEquals(7, HelpersMath.log2(255));
		assertEquals(8, HelpersMath.log2(256));
		assertEquals(-9999, HelpersMath.log2(-1));
	}

	@Test
	public void testString()
	{
		assertEquals("", HelpersString.rept("", 6));
		assertEquals("aaaaaa", HelpersString.rept("a", 6));
		assertEquals("ab ab ", HelpersString.rept("ab ", 2));
		assertEquals("", HelpersString.rept("a", 0));
		assertEquals("", HelpersString.rept("", 0));
	}

}
