package mirrg.mir53.tile;

import mirrg.mir51.tile.inventory.IInventoryName;
import net.minecraft.tileentity.TileEntity;

public interface ITileEntityMir53 extends IInventoryName
{

	public TileEntity getTileEntity();

	public void onBroken();

}
