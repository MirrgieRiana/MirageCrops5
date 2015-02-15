package mirrg.mir51.inventory;

import mirrg.mir50.inventory.InventoryCell;
import net.minecraft.item.ItemStack;

public abstract class InventoryCellMir51 extends InventoryCell implements IInventoryCellMir51
{

	protected Runnable runnableMarkDirty;

	public InventoryCellMir51(Runnable runnableMarkDirty)
	{
		this.runnableMarkDirty = runnableMarkDirty;
	}

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

	@Override
	public void markDirty()
	{
		runnableMarkDirty.run();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

}
