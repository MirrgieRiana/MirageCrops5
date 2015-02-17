package mirrg.mir51.inventory;

import net.minecraft.item.ItemStack;

public abstract class InventoryMir51SimpleAbstract extends InventoryMir51Abstract
{

	@Override
	public void markDirty(int index)
	{
		getInventoryCell(index).markDirty();
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return getInventoryCell(index).getStackInSlot();
	}

	@Override
	public int getInventoryStackLimit(int index)
	{
		return getInventoryCell(index).getInventoryStackLimit();
	}

	@Override
	public ItemStack decrStackSize(int index, int amount)
	{
		return getInventoryCell(index).decrStackSize(amount);
	}

	@Override
	public void clear(int index)
	{
		getInventoryCell(index).clear();
	}

	@Override
	public void drop(int index)
	{
		getInventoryCell(index).drop();
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
		return getInventoryCell(index).getStackInSlotOnClosing();
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack itemStack)
	{
		getInventoryCell(index).setInventorySlotContents(itemStack);
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack itemStack)
	{
		return getInventoryCell(index).isItemValidForSlot(itemStack);
	}

}
