package mirrg.mir51.inventory;

import mirrg.mir50.inventory.InventoryCell;

public class InventoryCellMir51 extends InventoryCell implements IInventoryCellMir51
{

	public InventoryCellMir51(IInventoryMir51 inventory, int index)
	{
		super(inventory, index);
	}

	@Override
	public IInventoryMir51 getInventory()
	{
		return (IInventoryMir51) super.getInventory();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return getInventory().getInventoryStackLimit(index);
	}

	@Override
	public void clear()
	{
		getInventory().clear(index);
	}

	@Override
	public void drop()
	{
		getInventory().drop(index);
	}

	@Override
	public void markDirty()
	{
		getInventory().markDirty(index);
	}

}
