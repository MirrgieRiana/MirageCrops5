package mirrg.mir52.gui;

import mirrg.mir50.loader.Loader;
import mirrg.mir51.loaders.LoaderMessageHandler;
import mirrg.mir51.loaders.LoaderSimpleNetworkWrapper;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class HelpersContainerMir52
{

	public static LoaderSimpleNetworkWrapper loaderSimpleNetworkWrapper = new LoaderSimpleNetworkWrapper();
	public static int loaderSimpleNetworkWrapper_counter = 0;

	public static SimpleNetworkWrapper getSimpleNetworkWrapper()
	{
		return loaderSimpleNetworkWrapper.get();
	}

	public static Loader<?>[] init()
	{
		loaderSimpleNetworkWrapper.channelName = HelpersContainerMir52.class.getName();

		return new Loader<?>[] {
			loaderSimpleNetworkWrapper,
			new LoaderMessageHandler(loaderSimpleNetworkWrapper,
				MessageNamedTagHandler.class, MessageNamedTag.class, loaderSimpleNetworkWrapper_counter++, Side.CLIENT),
		};
	}

}
