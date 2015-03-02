package mirrg.numberreave;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class Storage
{

	private IntBinaryOperator table;
	private IntUnaryOperator recordLength;
	private int tableSize;

	private int[] positions;

	public static Storage valueOf(String[] strings)
	{
		return new Storage((a, b) -> strings[a].charAt(b), a -> strings[a].length(), strings.length);
	}

	public Storage(IntBinaryOperator table, IntUnaryOperator recordLength, int tableSize)
	{
		this.table = table;
		this.recordLength = recordLength;
		this.tableSize = tableSize;

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

	public int at(int index, int index2)
	{
		return table.applyAsInt(index, index2 + positions[index]);
	}

	public int first(int index)
	{
		return table.applyAsInt(index, positions[index]);
	}

	public boolean[] reaveAll(int value)
	{
		boolean[] mask = new boolean[tableSize];

		for (int i = 0; i < getLength(); i++) {
			if (!isEmpty(i)) {
				if (first(i) == value) {
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

	public int indexOf(int index, int value)
	{
		for (int i = positions[index]; i < recordLength.applyAsInt(index); i++) {
			if (table.applyAsInt(index, i) == value) return i - positions[index];
		}

		return -1;
	}

}
