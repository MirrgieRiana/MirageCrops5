package mirrg.mir51.block.multi;

import mirrg.mir50.block.AdaptorBlockTileEntityOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AdaptorBlockTileEntityMulti extends AdaptorBlockTileEntityOverriding
{

	protected ContainerMetaBlock containerMetaBlock;

	public AdaptorBlockTileEntityMulti(BlockMir50 owner, IVirtualClass superObject, ContainerMetaBlock containerMetaBlock)
	{
		super(owner, superObject);
		this.containerMetaBlock = containerMetaBlock;
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(metadata);
		if (metaBlock == null) return super.hasTileEntity(metadata);
		return metaBlock.accessor_IAdaptorBlockTileEntity.get().hasTileEntity(metadata);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(metadata);
		if (metaBlock == null) return super.createTileEntity(world, metadata);
		return metaBlock.accessor_IAdaptorBlockTileEntity.get().createTileEntity(world, metadata);
	}

}
