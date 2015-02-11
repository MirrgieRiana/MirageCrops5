package mirrg.mir50.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface IAdaptorBlockTileEntity
{

	public boolean hasTileEntity();

	public boolean hasTileEntity(int metadata);

	public TileEntity createTileEntity(World world, int metadata);

}
