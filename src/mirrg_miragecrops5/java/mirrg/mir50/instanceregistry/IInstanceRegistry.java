package mirrg.mir50.instanceregistry;

public interface IInstanceRegistry<I>
{

	public I get(String name);

	public I get(int index);

	public int getIndexByName(String name);

	public int getIndexFromItem(I item);

	public String getNameByIndex(int index);

	public String getNameFromItem(I item);

	public void register(int index, String name, I item);

	public void forEach(IConsumerInstanceRegistry<I> consumer);

	/**
	 * 指定可能な最大の添え字を返します。
	 * 通常、固定長の場合は内部配列の要素数-1を返し、可変長の場合は大きな数を返します。
	 */
	public int getMaxIndex();

	@FunctionalInterface
	public static interface IConsumerInstanceRegistry<I>
	{

		public void apply(int index, String name, I item);

	}

}
