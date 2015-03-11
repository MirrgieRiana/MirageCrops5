package mirrg.mir50.render.block.switcher;

import java.util.Optional;

import mirrg.p.virtualclass.HelpersVirtualClass;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderBlockRenderingSwitcher implements ISimpleBlockRenderingHandler
{

	private final int renderId;
	private final boolean shouldRender3DInInventory;

	public RenderBlockRenderingSwitcher(int renderId, boolean shouldRender3DInInventory)
	{
		this.renderId = renderId;
		this.shouldRender3DInInventory = shouldRender3DInInventory;
	}

	@Override
	public int getRenderId()
	{
		return renderId;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return shouldRender3DInInventory;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		Optional<IAdaptorBlockRenderingSwitcher> cast = HelpersVirtualClass.cast(block, IAdaptorBlockRenderingSwitcher.class);
		if (!cast.isPresent()) throw new RuntimeException(String.format("Block(%s, metadata: %s) is not IRenderBlockSwitching", block, metadata));
		cast.get().renderInventoryBlock(block, metadata, modelId, renderer);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		Optional<IAdaptorBlockRenderingSwitcher> cast = HelpersVirtualClass.cast(block, IAdaptorBlockRenderingSwitcher.class);
		if (!cast.isPresent()) throw new RuntimeException(String.format("Block(%s, (%s, %s, %s)) is not IRenderBlockSwitching", block, x, y, z));
		return cast.get().renderWorldBlock(world, x, y, z, block, modelId, renderer);
	}

}
