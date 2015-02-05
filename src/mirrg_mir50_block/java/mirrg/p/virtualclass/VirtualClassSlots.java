package mirrg.p.virtualclass;

import java.util.Hashtable;

public class VirtualClassSlots extends Hashtable<Class<?>, VirtualImplementationSlot<?>>
{

	@SuppressWarnings("unchecked")
	public synchronized <T> VirtualImplementationSlot<T> get(Class<T> key)
	{
		return (VirtualImplementationSlot<T>) super.get(key);
	}

	@SuppressWarnings("unchecked")
	public synchronized <T> VirtualImplementationSlot<T> put(Class<T> key, VirtualImplementationSlot<T> value)
	{
		return (VirtualImplementationSlot<T>) super.put(key, value);
	}

}
