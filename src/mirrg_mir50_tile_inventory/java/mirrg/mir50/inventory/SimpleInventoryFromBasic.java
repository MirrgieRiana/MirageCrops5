package mirrg.mir50.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class SimpleInventoryFromBasic extends SimpleInventory
{

	@Handmade
	public SimpleInventoryFromBasic(IInventory inventory)
	{
		this.inventory = inventory;
	}

	@Handmade
	protected IInventoryCell[] inventoryCells;

	@Override
	@Handmade
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

	/////////////////////////////////////////////////////////////////////

	@Handmade
	protected IInventory inventory;

	@Override
	public int getSizeInventory()
	{
		return inventory.getSizeInventory();
	}

	@Override
	public String getInventoryName()
	{
		return inventory.getInventoryName();
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return inventory.hasCustomInventoryName();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return inventory.getInventoryStackLimit();
	}

	@Override
	public void markDirty()
	{
		inventory.markDirty();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
	{
		return inventory.isUseableByPlayer(p_70300_1_);
	}

	@Override
	public void openInventory()
	{
		inventory.openInventory();
	}

	@Override
	public void closeInventory()
	{
		inventory.closeInventory();
	}

}
