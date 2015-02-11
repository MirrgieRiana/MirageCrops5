package mirrg_miragecrops5.machine;

import java.util.function.Supplier;

import mirrg.mir50.guihandler.GuiHandler;
import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import cpw.mods.fml.common.network.NetworkRegistry;

public class LoaderGuiHandler extends Loader<GuiHandler>
{

	public Supplier<Object> supplierMod = null;
	public int guiId = 0;

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.Init) {
			set(new GuiHandler(guiId));
			NetworkRegistry.INSTANCE.registerGuiHandler(supplierMod.get(), get());
		}
	}

}
