package mirrg_miragecrops5.machine;

import mirrg.mir52.tile.ContainerMir53;
import mirrg.mir52.tile.ITileEntityMir53;
import mirrg.mir52.tile.TileEntityMir53Connected;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageNamedTagHandler implements IMessageHandler<MessageNamedTag, IMessage>
{

	@Override
	public IMessage onMessage(MessageNamedTag message, MessageContext ctx)
	{
		EntityClientPlayerMP entityclientplayermp = Minecraft.getMinecraft().thePlayer;

		if (entityclientplayermp.openContainer != null) {

			if (entityclientplayermp.openContainer instanceof ContainerMir53) {
				ContainerMir53 containerMir53 = (ContainerMir53) entityclientplayermp.openContainer;
				ITileEntityMir53 tileEntity = containerMir53.getTileEntity();

				if (tileEntity instanceof TileEntityMir53Connected) {
					((TileEntityMir53Connected) tileEntity).readFromNBT(message.name, message.tag);
				}
			}

		}

		return null;
	}

}
