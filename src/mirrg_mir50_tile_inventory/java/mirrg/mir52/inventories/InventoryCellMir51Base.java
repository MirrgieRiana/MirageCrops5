package mirrg.mir52.inventories;

import mirrg.mir51.inventory.InventoryCellMir51;
import net.minecraft.item.ItemStack;

public class InventoryCellMir51Base extends InventoryCellMir51
{

	protected ItemStack itemStack;

	public InventoryCellMir51Base(Runnable runnableMarkDirty)
	{
		super(runnableMarkDirty);
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

}
