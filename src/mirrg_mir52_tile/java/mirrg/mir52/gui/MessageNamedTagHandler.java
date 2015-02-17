package mirrg.mir52.gui;

import api.mirrg.mir50.gui.containerextraslot.IContainerExtraSlot;
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

			if (entityclientplayermp.openContainer instanceof ContainerMir52) {
				ContainerMir52 containerMir53 = (ContainerMir52) entityclientplayermp.openContainer;

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
