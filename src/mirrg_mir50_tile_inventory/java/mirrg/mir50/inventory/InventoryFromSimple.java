package mirrg.mir50.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryFromSimple implements IInventory
{

	protected ISimpleInventory simpleInventory;

	public InventoryFromSimple(ISimpleInventory simpleInventory)
	{
		this.simpleInventory = simpleInventory;
	}

	@Override
	public int getSizeInventory()
	{
		return simpleInventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return simpleInventory.getInventoryCell(index).getStackInSlot();
	}

	@Override
	public ItemStack decrStackSize(int index, int amount)
	{
		return simpleInventory.getInventoryCell(index).decrStackSize(amount);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
		return simpleInventory.getInventoryCell(index).getStackInSlotOnClosing();
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack itemStack)
	{
		simpleInventory.getInventoryCell(index).setInventorySlotContents(itemStack);
	}

	@Override
	public String getInventoryName()
	{
		return simpleInventory.getInventoryName();
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return simpleInventory.hasCustomInventoryName();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return simpleInventory.getInventoryStackLimit();
	}

	@Override
	public void markDirty()
	{
		simpleInventory.markDirty();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return simpleInventory.isUseableByPlayer(player);
	}

	@Override
	public void openInventory()
	{
		simpleInventory.openInventory();
	}

	@Override
	public void closeInventory()
	{
		simpleInventory.closeInventory();
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack itemStack)
	{
		return simpleInventory.getInventoryCell(index).isItemValidForSlot(itemStack);
	}

}
