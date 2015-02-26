package mirrg.mir50.instanceregistry;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * ハッシュによるIDの上限が無限長なレジストリ。IDの使用率が過疎なときに使う。
 */
public class InstanceRegistryHash<T> implements IInstanceRegistry<T>
{

	private final LinkedList<Integer> indexes = new LinkedList<>();

	private final Hashtable<String, T> name2item = new Hashtable<>();
	private final Hashtable<String, Integer> name2index = new Hashtable<>();
	private final Hashtable<Integer, T> index2item = new Hashtable<>();
	private final Hashtable<Integer, String> index2name = new Hashtable<>();
	private final Hashtable<T, String> item2name = new Hashtable<>();
	private final Hashtable<T, Integer> item2index = new Hashtable<>();

	@Override
	public T get(String name)
	{
		return name2item.get(name);
	}

	@Override
	public T get(int index)
	{
		return index2item.get(index);
	}

	@Override
	public int getIndexByName(String name)
	{
		return name2index.get(name);
	}

	@Override
	public int getIndexFromItem(T item)
	{
		return item2index.get(item);
	}

	@Override
	public String getNameByIndex(int index)
	{
		return index2name.get(index);
	}

	@Override
	public String getNameFromItem(T item)
	{
		return item2name.get(item);
	}

	@Override
	public void register(int index, String name, T item)
	{
		if (item == null) throw new NullPointerException();
		if (index2item.get(index) != null) throw new DuplicatedRegistrationException(this, index, name, item);

		if (indexes.size() == 0) {
			indexes.add(index);
		} else {
			if (index < indexes.getFirst()) {
				indexes.addFirst(index);
			} else {
				if (index > indexes.getLast()) {
					indexes.addLast(index);
				} else {

					ListIterator<Integer> iterator = indexes.listIterator();
					while (iterator.hasNext()) {
						int index2 = iterator.next();
						if (index2 > index) {
							iterator.previous();
							iterator.add(index);
						}
					}

				}
			}
		}

		name2item.put(name, item);
		name2index.put(name, index);
		index2item.put(index, item);
		index2name.put(index, name);
		item2name.put(item, name);
		item2index.put(item, index);
	}

	@Override
	public void forEach(IConsumerInstanceRegistry<T> consumer)
	{
		for (int index : indexes) {
			consumer.apply(index, index2name.get(index), index2item.get(index));
		}
	}

	@Override
	public int getMaxIndex()
	{
		return Integer.MAX_VALUE;
	}

}
