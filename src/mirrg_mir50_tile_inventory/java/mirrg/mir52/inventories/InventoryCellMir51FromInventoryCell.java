package mirrg.mir52.inventories;

import mirrg.mir50.inventory.IInventoryCell;
import mirrg.mir51.inventory.InventoryCellMir51Abstract;
import net.minecraft.item.ItemStack;

public class InventoryCellMir51FromInventoryCell extends InventoryCellMir51Abstract
{

	protected IInventoryCell owner;
	protected Runnable runnableMarkDirty;

	public InventoryCellMir51FromInventoryCell(IInventoryCell owner, Runnable runnableMarkDirty)
	{
		this.owner = owner;
		this.runnableMarkDirty = runnableMarkDirty;
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

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void markDirty()
	{
		runnableMarkDirty.run();
	}

}
