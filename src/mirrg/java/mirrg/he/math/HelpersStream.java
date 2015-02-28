package mirrg.he.math;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Test;

public class HelpersStream
{

	public static <T> Comparator<T> comparator(ToIntFunction<T> function)
	{
		return (a, b) -> {
			int a2 = function.applyAsInt(a);
			int b2 = function.applyAsInt(b);

			return a2 == b2 ? 0 : a2 > b2 ? 1 : -1;
		};
	}

	public static <T> Collector<T, ?, Optional<T>> maxByInt(ToIntFunction<? super T> function)
	{
		Hashtable<T, Integer> cache = new Hashtable<>();
		return Collectors.maxBy(HelpersStream.<T> comparator(t -> {
			if (cache.containsKey(t)) {
				return cache.get(t);
			} else {
				int i = function.applyAsInt(t);
				cache.put(t, i);
				return i;
			}
		}));
	}

	public static <T> Collector<T, ?, Optional<T>> minByInt(ToIntFunction<? super T> function)
	{
		Hashtable<T, Integer> cache = new Hashtable<>();
		return Collectors.minBy(HelpersStream.<T> comparator(t -> {
			if (cache.containsKey(t)) {
				return cache.get(t);
			} else {
				int i = function.applyAsInt(t);
				cache.put(t, i);
				return i;
			}
		}));
	}

	@Test
	public void test()
	{

		String[] array = {
			"fvewavw",
			"v",
			"",
			"ev646v63464",
			"tv3w464s",
			"432v643623",
			"t3wt3w634wqv",
			"f3w",
		};

		assertEquals("t3wt3w634wqv", Arrays.stream(array).collect(maxByInt(String::length)).get());
		assertEquals("", Arrays.stream(array).collect(minByInt(String::length)).get());

	}

}
