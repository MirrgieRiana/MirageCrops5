package mirrg.numberreave;

import java.util.function.BiPredicate;
import java.util.function.IntUnaryOperator;

public class Storage<T>
{

	@FunctionalInterface
	public static interface BiIntFunction<T>
	{

		public T apply(int a, int b);

	}

	private BiIntFunction<T> table;
	private IntUnaryOperator recordLength;
	private int tableSize;
	private BiPredicate<T, T> comparator;

	private int[] positions;

	public static Storage<Character> valueOf(String[] strings)
	{
		return new Storage<Character>(
			(a, b) -> strings[a].charAt(b),
			a -> strings[a].length(),
			strings.length,
			(a, b) -> a.equals(b));
	}

	public Storage(BiIntFunction<T> table, IntUnaryOperator recordLength, int tableSize, BiPredicate<T, T> comparator)
	{
		this.table = table;
		this.recordLength = recordLength;
		this.tableSize = tableSize;
		this.comparator = comparator;

		positions = new int[tableSize];
	}

	public int getLength()
	{
		return tableSize;
	}

	public boolean isEmpty(int index)
	{
		return positions[index] >= recordLength.applyAsInt(index);
	}

	public boolean isEmpty()
	{
		for (int i = 0; i < tableSize; i++) {
			if (!isEmpty(i)) return false;
		}
		return true;
	}

	public T at(int index, int index2)
	{
		return table.apply(index, index2 + positions[index]);
	}

	public T first(int index)
	{
		return table.apply(index, positions[index]);
	}

	public boolean[] reaveAll(T value)
	{
		boolean[] mask = new boolean[tableSize];

		for (int i = 0; i < getLength(); i++) {
			if (!isEmpty(i)) {
				if (comparator.test(first(i), value)) {
					reave(i);
					mask[i] = true;
				}
			}
		}

		return mask;
	}

	public void reave(int index)
	{
		positions[index]++;
	}

	public int indexOf(int index, T value)
	{
		for (int i = positions[index]; i < recordLength.applyAsInt(index); i++) {
			if (comparator.test(table.apply(index, i), value)) return i - positions[index];
		}

		return -1;
	}

}
