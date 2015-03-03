package mirrg.p.virtualclass;

import java.util.Optional;

public class HelpersVirtualClass
{

	public static boolean instanceOf(Object object, Class<?> clazz)
	{
		if (clazz.isInstance(object)) return true;

		if (object instanceof IVirtualClass) {
			if (((IVirtualClass) object).getVirtualClass().instanceOf(clazz)) return true;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public static <T> Optional<T> cast(Object object, Class<T> clazz)
	{
		if (clazz.isInstance(object)) return Optional.of((T) object);

		if (object instanceof IVirtualClass) {
			if (((IVirtualClass) object).getVirtualClass().instanceOf(clazz)) {
				return Optional.of(((IVirtualClass) object).getVirtualClass().cast(clazz).get());
			}
		}

		return Optional.empty();
	}

}
