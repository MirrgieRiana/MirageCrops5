package mirrg.mir50.block;

import mirrg.p.adaptor.Adaptor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AdaptorBlockTileEntity extends Adaptor<BlockMir50> implements IAdaptorBlockTileEntity
{

	public AdaptorBlockTileEntity(BlockMir50 owner)
	{
		super(owner);
	}

	@Override
	public boolean hasTileEntity()
	{
		return owner.hasTileEntity(0);
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
		return false;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return null;
	}

}
