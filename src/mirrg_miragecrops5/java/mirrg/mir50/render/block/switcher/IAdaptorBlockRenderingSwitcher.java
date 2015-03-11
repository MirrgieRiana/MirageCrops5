package mirrg.mir50.render.block.switcher;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public interface IAdaptorBlockRenderingSwitcher
{

	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer);

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer);

}
