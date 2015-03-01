package mirrg_miragecrops5.machine;

import mirrg.mir50.guihandler.IGuiProvider;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.GuiMir52;
import mirrg.mir53.tile.TileEntityMir53;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiProviderTileEntityMir53 implements IGuiProvider
{

	@Override
	public GuiMir52 createGui(EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null) return null;
		if (tileEntity instanceof TileEntityMir53) {
			return ((TileEntityMir53) tileEntity).createGui(player, world, x, y, z);
		}
		return null;
	}

	@Override
	public ContainerMir52 createContainer(EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null) return null;
		if (tileEntity instanceof TileEntityMir53) {
			return ((TileEntityMir53) tileEntity).createContainer(player, world, x, y, z);
		}
		return null;
	}

}