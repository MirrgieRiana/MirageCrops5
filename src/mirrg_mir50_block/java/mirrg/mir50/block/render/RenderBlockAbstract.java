package mirrg.mir50.block.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class RenderBlockAbstract implements ISimpleBlockRenderingHandler
{

	private final int renderId;

	public RenderBlockAbstract(int renderId)
	{
		this.renderId = renderId;
	}

	@Override
	public int getRenderId()
	{
		return renderId;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public abstract void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer);

	@Override
	public abstract boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z,
		Block block, int modelId, RenderBlocks renderer);

}
