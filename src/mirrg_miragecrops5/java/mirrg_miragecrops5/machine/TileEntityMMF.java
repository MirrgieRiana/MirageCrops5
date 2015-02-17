package mirrg_miragecrops5.machine;

import mirrg.mir50.world.pointer.SupplierPositionWorldFromTileEntity;
import mirrg.mir52.tile.TileEntityMir53Connected;
import mirrg_miragecrops5.ModMirageCrops;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityMMF extends TileEntityMir53Connected
{

	protected int LEFT = 8;
	protected int TOP_CHEST = 16;
	protected int TOP_INVENTORY = 84;
	protected int TOP_HOLDING = 142;
	protected int SHIFT = 18;

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float x2, float y2, float z2)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null) return false;

		if (true) {
			if (!world.isRemote) {
				player.openGui(ModMirageCrops.instance, ModuleMachine.loaderGuiHandler.guiId, world, x, y, z);
			}
			return true;
		}

		return false;
	}

	private final SupplierPositionWorldFromTileEntity supplierPosition = new SupplierPositionWorldFromTileEntity(this);

	protected SupplierPositionWorldFromTileEntity getSupplierPosition()
	{
		return supplierPosition;
	}

}
