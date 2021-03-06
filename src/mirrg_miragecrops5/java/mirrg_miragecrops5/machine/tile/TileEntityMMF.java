package mirrg_miragecrops5.machine.tile;

import mirrg.mir53.tile.TileEntityMir53Connected;
import mirrg_miragecrops5.ModMirageCrops;
import mirrg_miragecrops5.machine.ModuleMachine;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityMMF extends TileEntityMir53Connected
{

	protected int LEFT = 8;
	protected int TOP = 6;
	protected int TOP_CHEST = 16;
	protected int TOP_INVENTORY = 84;
	protected int TOP_HOLDING = 142;
	protected int SHIFT = 18;

	public boolean onActivated(World world, int x, int y, int z, EntityPlayer player, int side, float x2, float y2, float z2)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null) return false;

		if (!world.isRemote) {
			player.openGui(ModMirageCrops.instance, ModuleMachine.loaderGuiHandler.guiId, world, x, y, z);
		}
		return true;
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{

	}

}
