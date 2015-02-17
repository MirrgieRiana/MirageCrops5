package mirrg.mir50.world.pointer;

import api.mirrg.mir50.world.pointer.ISupplierPositionWorld;

public abstract class SupplierPositionWorldIntegerAbstract implements ISupplierPositionWorld
{

	@Override
	public double getX()
	{
		return getXAsInt() + 0.5;
	}

	@Override
	public double getY()
	{
		return getYAsInt() + 0.5;
	}

	@Override
	public double getZ()
	{
		return getZAsInt() + 0.5;
	}

}
