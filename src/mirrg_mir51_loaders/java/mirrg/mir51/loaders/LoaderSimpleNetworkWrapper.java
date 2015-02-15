package mirrg.mir51.loaders;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class LoaderSimpleNetworkWrapper extends Loader<SimpleNetworkWrapper>
{

	public String channelName;

	public LoaderSimpleNetworkWrapper()
	{

	}

	public LoaderSimpleNetworkWrapper(String channelName)
	{
		this.channelName = channelName;
	}

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.Created) {
			set(NetworkRegistry.INSTANCE.newSimpleChannel(channelName));
		}
	}

}
