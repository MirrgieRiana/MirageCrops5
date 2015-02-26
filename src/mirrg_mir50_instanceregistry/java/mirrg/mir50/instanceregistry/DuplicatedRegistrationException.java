package mirrg.mir50.instanceregistry;

public class DuplicatedRegistrationException extends RuntimeException
{

	private IInstanceRegistry<?> instanceRegistry;
	private int index;
	private String name;
	private Object item;

	public <T> DuplicatedRegistrationException(IInstanceRegistry<T> instanceRegistry, int index, String name, T item)
	{
		this.instanceRegistry = instanceRegistry;
		this.index = index;
		this.name = name;
		this.item = item;
	}

	@Override
	public String getMessage()
	{
		return String.format("%s[%s] (%s: %s) <- (%s: %s)",
			instanceRegistry,
			index,
			instanceRegistry.getNameByIndex(index),
			instanceRegistry.get(index),
			name,
			item);
	}

}
