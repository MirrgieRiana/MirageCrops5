package mirrg.mir50.world.pointer;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class SupplierPositionWorldFromEntity extends SupplierPositionWorldDoubleAbstract
{

	public Entity entity;

	public SupplierPositionWorldFromEntity(Entity entity)
	{
		this.entity = entity;
	}

	@Override
	public World getWorld()
	{
		return entity.worldObj;
	}

	@Override
	public double getX()
	{
		return entity.posX;
	}

	@Override
	public double getY()
	{
		return entity.posY;
	}

	@Override
	public double getZ()
	{
		return entity.posZ;
	}

}
