package mirrg.p.virtualclass;

public class VirtualImplementationSlot<T> implements IVirtualImplementationAccessor<T>
{

	public VirtualImplementationSlot(Class<T> implementationClass, T defaultImplementation)
	{
		this.implementationClass = implementationClass;
		slot = defaultImplementation;
	}

	public final Class<T> implementationClass;

	protected T slot;

	public final T get()
	{
		return slot;
	}

	public final void setSlot(T t)
	{
		slot = t;
	}

	public final Class<T> getImplementationClass()
	{
		return implementationClass;
	}

}
