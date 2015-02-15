package mirrg.mir51.inventory;

import mirrg.mir50.inventory.IInventoryCell;

public interface IInventoryCellMir51 extends IInventoryCell
{

	public int getInventoryStackLimit();

	public void clearInventory();

	public void markDirty();

}
