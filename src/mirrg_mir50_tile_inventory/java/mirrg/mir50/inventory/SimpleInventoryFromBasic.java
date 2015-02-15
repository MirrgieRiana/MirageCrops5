package mirrg.mir50.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class SimpleInventoryFromBasic implements ISimpleInventory
{

	protected IInventory inventory;

	public SimpleInventoryFromBasic(IInventory inventory)
	{
		this.inventory = inventory;
	}

	@Override
	public void openInventory()
	{
		inventory.openInventory();
	}

	@Override
	public void markDirty()
	{
		inventory.markDirty();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return inventory.isUseableByPlayer(player);
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return inventory.hasCustomInventoryName();
	}

	@Override
	public int getSizeInventory()
	{
		return inventory.getSizeInventory();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return inventory.getInventoryStackLimit();
	}

	@Override
	public String getInventoryName()
	{
		return inventory.getInventoryName();
	}

	protected IInventoryCell[] inventoryCells;

	@Override
	public IInventoryCell getInventoryCell(int index)
	{
		if (inventoryCells == null) {
			inventoryCells = new IInventoryCell[getSizeInventory()];
		}

		if (inventoryCells[index] == null) {
			inventoryCells[index] = new InventoryCellFromBasic(inventory, index);
		}

		return inventoryCells[index];
	}

	@Override
	public void closeInventory()
	{
		inventory.closeInventory();
	}

}
