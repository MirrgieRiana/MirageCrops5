package mirrg.mir50.guihandler;

import mirrg.p.virtualclass.IVirtualClass;
import mirrg.p.virtualclass.IVirtualImplementationAccessor;
import mirrg.p.virtualclass.VirtualClass;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiHandler extends GuiHandlerSuper
{

	public final int guiId;

	public GuiHandler(int guiId)
	{
		this.guiId = guiId;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == guiId) {
			Block block = world.getBlock(x, y, z);

			if (block instanceof IGuiProvider) {
				return ((IGuiProvider) block).createGui(player, world, x, y, z);
			}

			if (block instanceof IVirtualClass) {
				VirtualClass virtualClass = ((IVirtualClass) block).getVirtualClass();
				if (virtualClass.instanceOf(IGuiProvider.class)) {
					IVirtualImplementationAccessor<IGuiProvider> accessor = virtualClass.cast(IGuiProvider.class);
					accessor.get().createGui(player, world, x, y, z);
				}
			}

			{
				TileEntity tile = world.getTileEntity(x, y, z);

				if (tile != null && tile instanceof IGuiProvider) {
					return ((IGuiProvider) tile).createGui(player, world, x, y, z);
				}
			}
		}

		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == guiId) {
			Block block = world.getBlock(x, y, z);

			if (block instanceof IGuiProvider) {
				return ((IGuiProvider) block).createContainer(player, world, x, y, z);
			}

			if (block instanceof IVirtualClass) {
				VirtualClass virtualClass = ((IVirtualClass) block).getVirtualClass();
				if (virtualClass.instanceOf(IGuiProvider.class)) {
					IVirtualImplementationAccessor<IGuiProvider> accessor = virtualClass.cast(IGuiProvider.class);
					accessor.get().createContainer(player, world, x, y, z);
				}
			}

			{
				TileEntity tile = world.getTileEntity(x, y, z);

				if (tile != null && tile instanceof IGuiProvider) {
					return ((IGuiProvider) tile).createContainer(player, world, x, y, z);
				}
			}
		}

		return null;
	}

}

abstract class GuiHandlerSuper implements IGuiHandler
{

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

}
