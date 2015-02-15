package mirrg.mir50.inventory;

import net.minecraft.item.ItemStack;

public class InventoryCellDelegation implements IInventoryCell
{

	protected IInventoryCell owner;

	public InventoryCellDelegation(IInventoryCell owner)
	{
		this.owner = owner;
	}

	@Override
	public ItemStack getStackInSlot()
	{
		return owner.getStackInSlot();
	}

	@Override
	public ItemStack decrStackSize(int amount)
	{
		return owner.decrStackSize(amount);
	}

	@Override
	public ItemStack getStackInSlotOnClosing()
	{
		return owner.getStackInSlotOnClosing();
	}

	@Override
	public void setInventorySlotContents(ItemStack itemStack)
	{
		owner.setInventorySlotContents(itemStack);
	}

	@Override
	public boolean isItemValidForSlot(ItemStack itemStack)
	{
		return owner.isItemValidForSlot(itemStack);
	}

}
