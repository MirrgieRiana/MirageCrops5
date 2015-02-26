package mirrg.mir50.instanceregistry;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * 配列によるIDの上限が固定長なレジストリ。IDの使用率が高密度なときに使う。
 */
public class InstanceRegistryArray<T> implements IInstanceRegistry<T>
{

	private final Hashtable<String, T> name2item = new Hashtable<>();
	private final Hashtable<String, Integer> name2index = new Hashtable<>();
	private final T[] index2item;
	private final String[] index2name;
	private final Hashtable<T, String> item2name = new Hashtable<>();
	private final Hashtable<T, Integer> item2index = new Hashtable<>();

	public InstanceRegistryArray(T[] index2item)
	{
		this.index2item = index2item.clone();
		Arrays.fill(index2item, null);
		index2name = new String[index2item.length];
	}

	@Override
	public T get(String name)
	{
		return name2item.get(name);
	}

	@Override
	public T get(int index)
	{
		return index2item[index];
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
		return index2name[index];
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
		if (index2item[index] != null) throw new DuplicatedRegistrationException(this, index, name, item);

		name2item.put(name, item);
		name2index.put(name, index);
		index2item[index] = item;
		index2name[index] = name;
		item2name.put(item, name);
		item2index.put(item, index);
	}

	@Override
	public void forEach(IConsumerInstanceRegistry<T> consumer)
	{
		for (int i = 0; i < index2item.length; i++) {
			if (index2item[i] != null) consumer.apply(i, index2name[i], index2item[i]);
		}
	}

	@Override
	public int getMaxIndex()
	{
		return index2item.length - 1;
	}

}
