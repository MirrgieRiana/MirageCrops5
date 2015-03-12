package mirrg.mir52.render.block.multiple.multi;

import java.util.function.Consumer;

import mirrg.mir50.block.BlockMir50;
import mirrg.mir51.block.multi.ContainerMetaBlock;
import mirrg.mir51.block.multi.MetaBlock;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRenderingOverriding;
import mirrg.mir51.render.block.multiple.IBlockMultipleRendering;
import mirrg.mir51.render.block.multiple.IConsumerMultipleRendering;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockMultipleRenderingMulti extends AdaptorBlockMultipleRenderingOverriding
{

	protected ContainerMetaBlock containerMetaBlock;

	public AdaptorBlockMultipleRenderingMulti(BlockMir50 owner, IVirtualClass superObject, ContainerMetaBlock containerMetaBlock)
	{
		super(owner, superObject);
		this.containerMetaBlock = containerMetaBlock;
	}

	///////////////////////////////////////////////////////////

	@Override
	@SideOnly(Side.CLIENT)
	public Consumer<IConsumerMultipleRendering> getMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(blockAccess, x, y, z);
		if (metaBlock == null) return super.getMultipleRendering(blockAccess, x, y, z, side);
		return metaBlock.virtualClass.cast(IBlockMultipleRendering.class).get().getMultipleRendering(blockAccess, x, y, z, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Consumer<IConsumerMultipleRendering> getMultipleRendering(int metadata, int side)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(metadata);
		if (metaBlock == null) return super.getMultipleRendering(metadata, side);
		return metaBlock.virtualClass.cast(IBlockMultipleRendering.class).get().getMultipleRendering(metadata, side);
	}

}
