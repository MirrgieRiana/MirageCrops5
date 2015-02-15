package mirrg.mir51.inventory;

import net.minecraft.item.ItemStack;

public abstract class InventoryCellMir51Abstract implements IInventoryCellMir51
{

	@Override
	public ItemStack decrStackSize(int amount)
	{
		if (getStackInSlot() == null) return null;

		ItemStack itemStack;

		if (getStackInSlot().stackSize <= amount) {
			itemStack = getStackInSlot();
			clearInventory();
		} else {
			itemStack = getStackInSlot().splitStack(amount);
			if (getStackInSlot().stackSize == 0) clearInventory();
		}

		markDirty();
		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing()
	{
		ItemStack tmp = getStackInSlot();
		clearInventory();
		return tmp;
	}

	@Override
	public void clearInventory()
	{
		setInventorySlotContents(null);
	}

}
