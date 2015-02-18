package mirrg.mir51.loaders;

import java.util.function.IntFunction;
import java.util.function.Supplier;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class LoaderGuiHandler extends Loader<IGuiHandler>
{

	public Supplier<Object> supplierMod = null;
	public IntFunction<IGuiHandler> supplierGuiHandler = null;
	public int guiId = 0;

	public LoaderGuiHandler(IntFunction<IGuiHandler> supplierGuiHandler)
	{
		this.supplierGuiHandler = supplierGuiHandler;
	}

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.Init) {
			set(supplierGuiHandler.apply(guiId));
			NetworkRegistry.INSTANCE.registerGuiHandler(supplierMod.get(), get());
		}
	}

}
