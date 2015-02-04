package mirrg.p.virtualclass;

public class VirtualImplementationSlot<T> implements IVirtualImplementationAccessor<T>
{

	protected final VirtualClass virtualClass;

	public VirtualImplementationSlot(VirtualClass virtualClass, Class<T> implementationClass)
	{
		this.virtualClass = virtualClass;
		this.implementationClass = implementationClass;
	}

	public VirtualImplementationSlot(VirtualClass virtualClass, Class<T> implementationClass, T defaultImplementation)
	{
		this.virtualClass = virtualClass;
		this.implementationClass = implementationClass;
		slot = defaultImplementation;
	}

	public final Class<T> implementationClass;

	protected T slot;

	@Override
	public final T get()
	{
		if (slot == null) throw new VirtualClassAbstractMethodException(virtualClass.owner, implementationClass);
		return slot;
	}

	public final void setSlot(T t)
	{
		slot = t;
	}

	@Override
	public final Class<T> getImplementationClass()
	{
		return implementationClass;
	}

}
