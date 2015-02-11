package mirrg.mir52.tile;

import mirrg.mir50.tile.inventory.IInventoryName;
import net.minecraft.tileentity.TileEntity;

public interface ITileEntityMir53 extends IInventoryName
{

	public TileEntity getTileEntity();

	public void onBroken();

}
