package mirrg.p.adaptor;

import mirrg.p.virtualclass.IVirtualClass;

public class Adaptor<T extends IVirtualClass>
{

	protected final T owner;

	public Adaptor(T owner)
	{
		this.owner = owner;
	}

}
