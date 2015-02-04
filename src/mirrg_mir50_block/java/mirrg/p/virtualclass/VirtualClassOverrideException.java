package mirrg.p.virtualclass;

public class VirtualClassOverrideException extends RuntimeException
{

	public final Object owner;
	public final Object object;

	public VirtualClassOverrideException(Object owner, Object object)
	{
		this.owner = owner;
		this.object = object;
	}

	@Override
	public String getMessage()
	{
		return String.format("object '%s' has no interfaces to override %s", object, owner);
	}

}
