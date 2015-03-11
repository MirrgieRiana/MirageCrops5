package mirrg.mir50.render.block.switcher;

import java.util.Optional;

import mirrg.mir51.block.multi.ContainerMetaBlock;
import mirrg.mir51.block.multi.MetaBlock;
import mirrg.p.virtualclass.HelpersVirtualClass;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class AdaptorBlockRenderingSwitcherMulti extends AdaptorBlockRenderingSwitcherOverriding
{

	public ContainerMetaBlock containerMetaBlock;

	public AdaptorBlockRenderingSwitcherMulti(IVirtualClass superObject, ContainerMetaBlock containerMetaBlock)
	{
		super(superObject);
		this.containerMetaBlock = containerMetaBlock;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		MetaBlock metaBlock = containerMetaBlock.get(metadata);
		if (metaBlock != null) {
			Optional<IAdaptorBlockRenderingSwitcher> optionalAccessor_IAdaptorBlockRenderingSwitcher =
				HelpersVirtualClass.cast(metaBlock, IAdaptorBlockRenderingSwitcher.class);
			if (optionalAccessor_IAdaptorBlockRenderingSwitcher.isPresent()) {
				optionalAccessor_IAdaptorBlockRenderingSwitcher.get().renderInventoryBlock(
					block, metadata, modelId, renderer);
				return;
			}
		}
		super.renderInventoryBlock(block, metadata, modelId, renderer);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		MetaBlock metaBlock = containerMetaBlock.get(world, x, y, z);
		if (metaBlock != null) {
			Optional<IAdaptorBlockRenderingSwitcher> optionalAccessor_IAdaptorBlockRenderingSwitcher =
				HelpersVirtualClass.cast(metaBlock, IAdaptorBlockRenderingSwitcher.class);
			if (optionalAccessor_IAdaptorBlockRenderingSwitcher.isPresent()) {
				return optionalAccessor_IAdaptorBlockRenderingSwitcher.get().renderWorldBlock(
					world, x, y, z, block, modelId, renderer);
			}
		}
		return super.renderWorldBlock(world, x, y, z, block, modelId, renderer);
	}

}
