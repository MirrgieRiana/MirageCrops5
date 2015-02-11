package mirrg.p.virtualclass;

public interface IVirtualImplementationAccessor<T>
{

	public Class<T> getImplementationClass();

	public T get();

}
