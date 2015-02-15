package mirrg.mir51.loaders;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.relauncher.Side;

public class LoaderMessageHandler extends Loader<Void>
{

	public LoaderSimpleNetworkWrapper loaderSimpleNetworkWrapper;

	public int discriminator;
	public Side side;

	public Query<?, ?> query;

	public LoaderMessageHandler()
	{

	}

	public <REQ extends IMessage, REPLY extends IMessage> LoaderMessageHandler(
		LoaderSimpleNetworkWrapper loaderSimpleNetworkWrapper,
		Class<? extends IMessageHandler<REQ, REPLY>> messageHandler,
		Class<REQ> requestMessageType, int discriminator, Side side)
	{
		init(loaderSimpleNetworkWrapper, messageHandler, requestMessageType, discriminator, side);
	}

	public <REQ extends IMessage, REPLY extends IMessage> void init(
		LoaderSimpleNetworkWrapper loaderSimpleNetworkWrapper,
		Class<? extends IMessageHandler<REQ, REPLY>> messageHandler,
		Class<REQ> requestMessageType, int discriminator, Side side)
	{
		this.loaderSimpleNetworkWrapper = loaderSimpleNetworkWrapper;
		dependsOn(loaderSimpleNetworkWrapper);
		query = new Query<>(messageHandler, requestMessageType);
		this.discriminator = discriminator;
		this.side = side;
	}

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.PreInit) {
			query.load();
			loadCompleted();
		}
	}

	public class Query<REQ extends IMessage, REPLY extends IMessage>
	{

		public Class<? extends IMessageHandler<REQ, REPLY>> messageHandler;
		public Class<REQ> requestMessageType;

		public Query(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler,
			Class<REQ> requestMessageType)
		{
			this.messageHandler = messageHandler;
			this.requestMessageType = requestMessageType;
		}

		public void load()
		{
			loaderSimpleNetworkWrapper.get().registerMessage(
				messageHandler, requestMessageType, discriminator, side);
		}

	}

}
