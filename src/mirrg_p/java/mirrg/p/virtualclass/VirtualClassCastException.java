package mirrg.p.virtualclass;

public class VirtualClassCastException extends RuntimeException
{

	public final Object owner;
	public final Class<?> classInterface;

	public VirtualClassCastException(Object owner, Class<?> classInterface)
	{
		this.owner = owner;
		this.classInterface = classInterface;
	}

	@Override
	public String getMessage()
	{
		return String.format("%s is not %s", owner, classInterface);
	}

}
