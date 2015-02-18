package mirrg.mir51.inventory;

import mirrg.mir50.inventory.IInventoryCell;

public interface IInventoryCellMir51 extends IInventoryCell
{

	@Override
	public IInventoryMir51 getInventory();

	public int getInventoryStackLimit();

	public void clear();

	public void drop();

	public void markDirty();

}
