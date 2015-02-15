package mirrg.mir52.inventories;

import mirrg.mir50.inventory.Handmade;
import mirrg.mir50.inventory.IInventoryCell;
import mirrg.mir51.inventory.InventoryCellMir51;
import net.minecraft.item.ItemStack;

public class InventoryCellMir51FromInventoryCell extends InventoryCellMir51
{

	@Handmade
	public InventoryCellMir51FromInventoryCell(Runnable runnableMarkDirty, IInventoryCell owner)
	{
		super(runnableMarkDirty);
		this.owner = owner;
	}

	@Handmade
	protected IInventoryCell owner;

	/////////////////////////////////////////////////////////////////

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
