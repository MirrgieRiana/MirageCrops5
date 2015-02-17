package mirrg.mir50.world.pointer;

import net.minecraft.world.World;

public class SupplierPositionWorldDouble extends SupplierPositionWorldDoubleAbstract
{

	public World world;
	public double x;
	public double y;
	public double z;

	public SupplierPositionWorldDouble(World world, double x, double y, double z)
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
	public double getX()
	{
		return x;
	}

	@Override
	public double getY()
	{
		return y;
	}

	@Override
	public double getZ()
	{
		return z;
	}

}
