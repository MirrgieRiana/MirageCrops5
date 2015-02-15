package mirrg.mir52.inventories;

import mirrg.mir51.inventory.InventoryCellMir51Abstract;
import net.minecraft.item.ItemStack;

public class InventoryCellMir51 extends InventoryCellMir51Abstract
{

	protected ItemStack itemStack;
	protected Runnable runnableMarkDirty;

	public InventoryCellMir51(Runnable runnableMarkDirty)
	{
		this.runnableMarkDirty = runnableMarkDirty;
	}

	@Override
	public ItemStack getStackInSlot()
	{
		return itemStack;
	}

	@Override
	public void setInventorySlotContents(ItemStack itemStack)
	{
		this.itemStack = itemStack;
		markDirty();
	}

	@Override
	public boolean isItemValidForSlot(ItemStack itemStack)
	{
		return true;
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
