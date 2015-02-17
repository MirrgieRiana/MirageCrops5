package mirrg_miragecrops5.machine;

import mirrg.mir52.tile.IContainerExtraSlot;
import mirrg.mir53.gui.container.ContainerMir53;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import cpw.mods.fml.common.FMLLog;
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

				IContainerExtraSlot containerExtraSlot = containerMir53.getContainerExtraSlot(message.name);
				if (containerExtraSlot != null) {
					containerExtraSlot.readFromNBT(message.tag);
				} else {
					FMLLog.warning("illegal named nbt tag packet was caught: %s", message.name);
				}
			}

		}

		return null;
	}

}
