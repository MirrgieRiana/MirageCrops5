package mirrg.mir51.render.block.multiple;

import java.util.function.IntFunction;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class LoaderSimpleBlockRenderingHandler extends Loader<ISimpleBlockRenderingHandler>
{

	protected IntFunction<ISimpleBlockRenderingHandler> function;

	public LoaderSimpleBlockRenderingHandler()
	{

	}

	public LoaderSimpleBlockRenderingHandler(IntFunction<ISimpleBlockRenderingHandler> function)
	{
		init(function);
	}

	public void init(IntFunction<ISimpleBlockRenderingHandler> function)
	{
		this.function = function;
	}

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent != EnumLoadEventTiming.ClientAfterInit) return;

		ISimpleBlockRenderingHandler simpleBlockRenderingHandler;

		int renderId = RenderingRegistry.getNextAvailableRenderId();
		simpleBlockRenderingHandler = function.apply(renderId);
		RenderingRegistry.registerBlockHandler(renderId, simpleBlockRenderingHandler);

		set(simpleBlockRenderingHandler);
	}

}
