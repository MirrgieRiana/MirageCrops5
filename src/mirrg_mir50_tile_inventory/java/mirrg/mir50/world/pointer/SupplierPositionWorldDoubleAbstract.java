package mirrg.mir50.world.pointer;

import api.mirrg.mir50.world.pointer.ISupplierPositionWorld;

public abstract class SupplierPositionWorldDoubleAbstract implements ISupplierPositionWorld
{

	@Override
	public int getXAsInt()
	{
		return (int) getX();
	}

	@Override
	public int getYAsInt()
	{
		return (int) getY();
	}

	@Override
	public int getZAsInt()
	{
		return (int) getZ();
	}

}
