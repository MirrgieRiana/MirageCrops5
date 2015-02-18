package mirrg.mir50.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryCell implements IInventoryCell
{

	protected IInventory inventory;
	protected int index;

	public InventoryCell(IInventory inventory, int index)
	{
		this.inventory = inventory;
		this.index = index;
	}

	@Override
	public IInventory getInventory()
	{
		return inventory;
	}

	@Override
	public ItemStack getStackInSlot()
	{
		return inventory.getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(int amount)
	{
		return inventory.decrStackSize(index, amount);
	}

	@Override
	public ItemStack getStackInSlotOnClosing()
	{
		return inventory.getStackInSlotOnClosing(index);
	}

	@Override
	public void setInventorySlotContents(ItemStack itemStack)
	{
		inventory.setInventorySlotContents(index, itemStack);
	}

	@Override
	public boolean isItemValidForSlot(ItemStack itemStack)
	{
		return inventory.isItemValidForSlot(index, itemStack);
	}

}
