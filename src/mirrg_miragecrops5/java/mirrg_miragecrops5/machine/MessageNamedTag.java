package mirrg_miragecrops5.machine;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageNamedTag implements IMessage
{

	public String name;
	public NBTTagCompound tag;

	public MessageNamedTag()
	{

	}

	public MessageNamedTag(String name, NBTTagCompound tag)
	{
		this.name = name;
		this.tag = tag;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		name = ByteBufUtils.readUTF8String(buf);
		tag = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, name);
		ByteBufUtils.writeTag(buf, tag);
	}

}
