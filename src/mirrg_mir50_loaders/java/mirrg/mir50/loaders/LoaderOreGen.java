package mirrg.mir50.loaders;

import java.util.function.Supplier;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import mirrg.mir50.loader.LoaderRoot;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreXZ;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class LoaderOreGen extends Loader<IGeneratorOreXZ>
{

	protected Supplier<IGeneratorOreXZ> supplier;

	public LoaderOreGen(LoaderRoot loaderRoot)
	{
		subscribeEvent(loaderRoot);
	}

	public LoaderOreGen(LoaderRoot loaderRoot, Supplier<IGeneratorOreXZ> supplier)
	{
		this(loaderRoot);
		init(supplier);
	}

	public void init(Supplier<IGeneratorOreXZ> supplier)
	{
		this.supplier = supplier;
	}

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.Init) {
			set(supplier.get());
		}
	}

	//

	protected static Handler handler;

	protected void subscribeEvent(LoaderRoot loaderRoot)
	{
		if (handler == null) {
			handler = new Handler(loaderRoot);
			MinecraftForge.ORE_GEN_BUS.register(handler);
		}
	}

	protected static class Handler
	{

		protected LoaderRoot loaderRoot;

		protected Handler(LoaderRoot loaderRoot)
		{
			this.loaderRoot = loaderRoot;
		}

		@SubscribeEvent
		public void handle(OreGenEvent.Post event)
		{
			loaderRoot.forEach(loader -> {
				if (loader instanceof LoaderOreGen) {
					((LoaderOreGen) loader).get().generate(
						event.world, event.rand, event.worldX, event.worldZ, event);
				}
			});
		}

	}

}
