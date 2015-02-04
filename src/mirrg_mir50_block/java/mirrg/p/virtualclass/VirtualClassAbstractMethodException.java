package mirrg.p.virtualclass;

public class VirtualClassAbstractMethodException extends RuntimeException
{

	public final Object owner;
	public final Class<?> classInterface;

	public VirtualClassAbstractMethodException(Object owner, Class<?> classInterface)
	{
		this.owner = owner;
		this.classInterface = classInterface;
	}

	@Override
	public String getMessage()
	{
		return String.format("%s's implementation of %s is abstract", owner, classInterface);
	}

}
