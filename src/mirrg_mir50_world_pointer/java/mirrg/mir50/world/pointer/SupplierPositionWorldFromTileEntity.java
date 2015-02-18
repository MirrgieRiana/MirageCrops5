package mirrg.mir50.world.pointer;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SupplierPositionWorldFromTileEntity extends SupplierPositionWorldIntegerAbstract
{

	public TileEntity tileEntity;

	public SupplierPositionWorldFromTileEntity(TileEntity tileEntity)
	{
		this.tileEntity = tileEntity;
	}

	@Override
	public World getWorld()
	{
		return tileEntity.getWorldObj();
	}

	@Override
	public int getXAsInt()
	{
		return tileEntity.xCoord;
	}

	@Override
	public int getYAsInt()
	{
		return tileEntity.yCoord;
	}

	@Override
	public int getZAsInt()
	{
		return tileEntity.zCoord;
	}

}
