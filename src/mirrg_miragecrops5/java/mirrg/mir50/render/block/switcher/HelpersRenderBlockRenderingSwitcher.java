package mirrg.mir50.render.block.switcher;

import java.util.function.Consumer;

import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.loader.Loader;
import mirrg.mir51.render.block.multiple.LoaderSimpleBlockRenderingHandler;
import mirrg.p.virtualclass.IVirtualClass;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class HelpersRenderBlockRenderingSwitcher
{

	public static LoaderSimpleBlockRenderingHandler loaderShouldRender3DInInventory =
		new LoaderSimpleBlockRenderingHandler(renderId -> new RenderBlockRenderingSwitcher(renderId, true));
	public static LoaderSimpleBlockRenderingHandler loaderShouldNotRender3DInInventory =
		new LoaderSimpleBlockRenderingHandler(renderId -> new RenderBlockRenderingSwitcher(renderId, false));

	public static ISimpleBlockRenderingHandler getHandler(boolean shouldRender3DInInventory)
	{
		if (shouldRender3DInInventory) {
			return loaderShouldRender3DInInventory.get();
		} else {
			return loaderShouldNotRender3DInInventory.get();
		}
	}

	public static void make(BlockMir50 owner, IVirtualClass superObject,
		boolean shouldRender3DInInventory, boolean isOpaqueCube, boolean renderAsNormalBlock)
	{
		superObject.getVirtualClass().register(IAdaptorBlockRenderingSwitcher.class, new AdaptorBlockRenderingSwitcher());
		superObject.getVirtualClass().override(
			new AdaptorBlockRenderRenderingSwitcher(
				owner, superObject, shouldRender3DInInventory, isOpaqueCube, renderAsNormalBlock));
	}

	public static void make(BlockMir50 owner, IVirtualClass superObject)
	{
		superObject.getVirtualClass().register(IAdaptorBlockRenderingSwitcher.class, new AdaptorBlockRenderingSwitcher());
		superObject.getVirtualClass().override(
			new AdaptorBlockRenderRenderingSwitcher(owner, superObject));
	}

	public static void init(Consumer<Loader<?>> adder)
	{
		adder.accept(HelpersRenderBlockRenderingSwitcher.loaderShouldRender3DInInventory);
		adder.accept(HelpersRenderBlockRenderingSwitcher.loaderShouldNotRender3DInInventory);
	}

}
