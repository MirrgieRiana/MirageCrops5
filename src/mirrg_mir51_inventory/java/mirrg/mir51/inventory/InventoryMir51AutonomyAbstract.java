package mirrg.mir51.inventory;

import net.minecraft.item.ItemStack;

public abstract class InventoryMir51AutonomyAbstract extends InventoryMir51Abstract
{

	@Override
	public IInventoryCellMir51 getInventoryCell(int index)
	{
		return new InventoryCellMir51(this, index);
	}

	@Override
	public ItemStack decrStackSize(int index, int amount)
	{
		if (getStackInSlot(index) == null) return null;

		ItemStack itemStack;

		if (getStackInSlot(index).stackSize <= amount) {
			itemStack = getStackInSlot(index);
			clear(index);
		} else {
			itemStack = getStackInSlot(index).splitStack(amount);
			if (getStackInSlot(index).stackSize == 0) clear(index);
		}

		markDirty(index);
		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
		ItemStack tmp = getStackInSlot(index);
		clear(index);
		return tmp;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return getCustomInventoryName() != null;
	}

	@Override
	public String getInventoryName()
	{
		return hasCustomInventoryName() ? getCustomInventoryName() : getDefaultName();
	}

}
