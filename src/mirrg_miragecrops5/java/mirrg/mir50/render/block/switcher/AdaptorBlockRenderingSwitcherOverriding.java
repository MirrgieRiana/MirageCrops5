package mirrg.mir50.render.block.switcher;

import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class AdaptorBlockRenderingSwitcherOverriding extends AdaptorBlockRenderingSwitcher
{

	protected final IAdaptorBlockRenderingSwitcher _super_IAdaptorBlockRenderingSwitcher;

	public AdaptorBlockRenderingSwitcherOverriding(IVirtualClass superObject)
	{
		this._super_IAdaptorBlockRenderingSwitcher = superObject.getVirtualClass().getCurrentImplementation(IAdaptorBlockRenderingSwitcher.class);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		_super_IAdaptorBlockRenderingSwitcher.renderInventoryBlock(block, metadata, modelId, renderer);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return _super_IAdaptorBlockRenderingSwitcher.renderWorldBlock(world, x, y, z, block, modelId, renderer);
	}

}
