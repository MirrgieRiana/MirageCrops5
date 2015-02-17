package mirrg.mir50.world.pointer;

import net.minecraft.world.World;

public class SupplierPositionWorldInteger extends SupplierPositionWorldIntegerAbstract
{

	public World world;
	public int x;
	public int y;
	public int z;

	public SupplierPositionWorldInteger(World world, int x, int y, int z)
	{
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public World getWorld()
	{
		return world;
	}

	@Override
	public int getXAsInt()
	{
		return x;
	}

	@Override
	public int getYAsInt()
	{
		return y;
	}

	@Override
	public int getZAsInt()
	{
		return z;
	}

}
