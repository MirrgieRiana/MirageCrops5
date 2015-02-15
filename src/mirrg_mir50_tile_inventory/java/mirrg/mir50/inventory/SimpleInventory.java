package mirrg.mir50.inventory;

import net.minecraft.item.ItemStack;

public abstract class SimpleInventory implements ISimpleInventory
{

	@Override
	public final ItemStack getStackInSlot(int index)
	{
		return getInventoryCell(index).getStackInSlot();
	}

	@Override
	public final ItemStack decrStackSize(int index, int amount)
	{
		return getInventoryCell(index).decrStackSize(amount);
	}

	@Override
	public final ItemStack getStackInSlotOnClosing(int index)
	{
		return getInventoryCell(index).getStackInSlotOnClosing();
	}

	@Override
	public final void setInventorySlotContents(int index, ItemStack itemStack)
	{
		getInventoryCell(index).setInventorySlotContents(itemStack);
	}

	@Override
	public final boolean isItemValidForSlot(int index, ItemStack itemStack)
	{
		return getInventoryCell(index).isItemValidForSlot(itemStack);
	}

}
