package mirrg.mir50.instanceregistry;

public interface IInstanceRegistry<I>
{

	/**
	 * 複数適合する場合、最も遅く代入された項目を返す。
	 */
	public I get(String name);

	public I get(int index);

	/**
	 * 複数適合する場合、最も遅く代入された項目を返す。
	 */
	public int getIndexByName(String name);

	/**
	 * 複数適合する場合、最も遅く代入された項目を返す。
	 */
	public int getIndexFromItem(I item);

	public String getNameByIndex(int index);

	/**
	 * 複数適合する場合、最も遅く代入された項目を返す。
	 */
	public String getNameFromItem(I item);

	/**
	 * @param index
	 *            一意でなければならない。
	 * @param name
	 *            一意である必要はない。
	 * @param item
	 *            一意である必要はない。nullは禁止。
	 */
	public void register(int index, String name, I item);

	/**
	 * 登録されている番号のみが与えられる。
	 */
	public void forEach(IConsumerInstanceRegistry<I> consumer);

	/**
	 * 指定可能な最大の添え字を返す。
	 * 通常、固定長の場合は内部配列の要素数-1を返し、可変長の場合は大きな数を返す。
	 */
	public int getMaxIndex();

	@FunctionalInterface
	public static interface IConsumerInstanceRegistry<I>
	{

		public void apply(int index, String name, I item);

	}

}
