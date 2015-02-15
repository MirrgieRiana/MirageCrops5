package mirrg.mir50.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class SimpleInventoryFromBasic extends SimpleInventory
{

	@Handmade
	public SimpleInventoryFromBasic(IInventory owner)
	{
		this.owner = owner;
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
			inventoryCells[index] = new InventoryCellFromBasic(owner, index);
		}

		return inventoryCells[index];
	}

	@Override
	public boolean equals(Object obj)
	{
		return owner.equals(obj);
	}

	@Override
	public int hashCode()
	{
		return owner.hashCode();
	}

	/////////////////////////////////////////////////////////////////////

	@Handmade
	protected IInventory owner;

	@Override
	public int getSizeInventory()
	{
		return owner.getSizeInventory();
	}

	@Override
	public String getInventoryName()
	{
		return owner.getInventoryName();
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return owner.hasCustomInventoryName();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return owner.getInventoryStackLimit();
	}

	@Override
	public void markDirty()
	{
		owner.markDirty();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
	{
		return owner.isUseableByPlayer(p_70300_1_);
	}

	@Override
	public void openInventory()
	{
		owner.openInventory();
	}

	@Override
	public void closeInventory()
	{
		owner.closeInventory();
	}

}
