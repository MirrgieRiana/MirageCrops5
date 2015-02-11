package mirrg.mir50.block;

import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AdaptorBlockTileEntityOverriding extends AdaptorBlockTileEntity
{

	protected final IAdaptorBlockTileEntity _super_IAdaptorBlockTileEntity;

	public AdaptorBlockTileEntityOverriding(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner);
		this._super_IAdaptorBlockTileEntity = superObject.getVirtualClass().getCurrentImplementation(IAdaptorBlockTileEntity.class);
	}

	@Override
	public boolean hasTileEntity()
	{
		return _super_IAdaptorBlockTileEntity.hasTileEntity();
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
		return _super_IAdaptorBlockTileEntity.hasTileEntity(metadata);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return _super_IAdaptorBlockTileEntity.createTileEntity(world, metadata);
	}

}
