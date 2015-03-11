package mirrg.mir50.render.block.switcher;

import java.util.function.Supplier;

import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class AdaptorBlockRenderingSwitcherFromHandler extends AdaptorBlockRenderingSwitcherOverriding
{

	public Supplier<ISimpleBlockRenderingHandler> supplierSimpleBlockRenderingHandler;

	public AdaptorBlockRenderingSwitcherFromHandler(
		IVirtualClass superObject,
		Supplier<ISimpleBlockRenderingHandler> supplierSimpleBlockRenderingHandler)
	{
		super(superObject);
		this.supplierSimpleBlockRenderingHandler = supplierSimpleBlockRenderingHandler;
	}

	public AdaptorBlockRenderingSwitcherFromHandler(
		IVirtualClass superObject,
		ISimpleBlockRenderingHandler simpleBlockRenderingHandler)
	{
		this(superObject, () -> simpleBlockRenderingHandler);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		supplierSimpleBlockRenderingHandler.get().renderInventoryBlock(block, metadata, modelId, renderer);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return supplierSimpleBlockRenderingHandler.get().renderWorldBlock(world, x, y, z, block, modelId, renderer);
	}

}
