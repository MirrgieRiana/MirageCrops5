package mirrg.p.virtualclass;

public class VirtualClassRegistrationException extends RuntimeException
{

	public final Object owner;
	public final Class<?> classInterface;

	public VirtualClassRegistrationException(Object owner, Class<?> classInterface)
	{
		this.owner = owner;
		this.classInterface = classInterface;
	}

	@Override
	public String getMessage()
	{
		return String.format("%s, %s", owner, classInterface);
	}

}
