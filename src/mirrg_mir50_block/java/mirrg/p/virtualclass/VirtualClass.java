package mirrg.p.virtualclass;

import java.util.Enumeration;
import java.util.Hashtable;

public class VirtualClass
{

	public final Object owner;

	protected Hashtable<Class<?>, VirtualImplementationSlot<?>> slots = new Hashtable<>();

	public VirtualClass(Object owner)
	{
		this.owner = owner;
	}

	public <T> T getCurrentImplementation(Class<T> classInterface)
	{
		return cast(classInterface).get();
	}

	public <T> IVirtualImplementationAccessor<T> cast(Class<T> classInterface)
	{
		if (!slots.containsKey(classInterface)) throw new VirtualClassCastException(owner, classInterface);
		return (IVirtualImplementationAccessor<T>) slots.get(classInterface);
	}

	public boolean instanceOf(Class<?> classInterface)
	{
		return slots.containsKey(classInterface);
	}

	public <T> void register(Class<T> classInterface)
	{
		if (slots.containsKey(classInterface)) throw new VirtualClassRegistrationException(owner, classInterface);
		slots.put(classInterface, new VirtualImplementationSlot<T>(this, classInterface));
	}

	public <T> void register(Class<T> classInterface, T defaultImplementation)
	{
		if (slots.containsKey(classInterface)) throw new VirtualClassRegistrationException(owner, classInterface);
		slots.put(classInterface, new VirtualImplementationSlot<T>(this, classInterface, defaultImplementation));
	}

	public int override(Object object)
	{
		return override(object, true);
	}

	public int override(Object object, boolean raiseException)
	{
		int count = 0;

		Enumeration<Class<?>> keys = slots.keys();
		while (keys.hasMoreElements()) {
			Class<?> classInterface = keys.nextElement();

			if (classInterface.isInstance(object)) {
				setSlot(slots.get(classInterface), object);
				count++;
			}
		}

		if (raiseException && count == 0) {
			throw new VirtualClassOverrideException(owner, object);
		}

		return count;
	}

	protected <T> void setSlot(VirtualImplementationSlot<T> slot, Object object)
	{
		slot.setSlot((T) object);
	}

}
