package mirrg.mir51.tile.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * インベントリの部分を分離し、新たな通し番号によるスロットへの参照を与えます。
 */
public class InventoryTrimmer implements IInventory
{

	protected IInventory inventory;
	protected int start;
	protected int length;

	public InventoryTrimmer(IInventory inventory, int start, int length)
	{
		this.inventory = inventory;
		this.start = start;
		this.length = length;
	}

	@Override
	public void closeInventory()
	{
		inventory.closeInventory();
	}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1)
	{
		return inventory.decrStackSize(start + arg0, arg1);
	}

	@Override
	public String getInventoryName()
	{
		return inventory.getInventoryName();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return inventory.getInventoryStackLimit();
	}

	@Override
	public int getSizeInventory()
	{
		return length;
	}

	@Override
	public ItemStack getStackInSlot(int arg0)
	{
		return inventory.getStackInSlot(start + arg0);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int arg0)
	{
		return inventory.getStackInSlotOnClosing(start + arg0);
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return inventory.hasCustomInventoryName();
	}

	@Override
	public boolean isItemValidForSlot(int arg0, ItemStack arg1)
	{
		return inventory.isItemValidForSlot(start + arg0, arg1);
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0)
	{
		return inventory.isUseableByPlayer(arg0);
	}

	@Override
	public void markDirty()
	{
		inventory.markDirty();
	}

	@Override
	public void openInventory()
	{
		inventory.openInventory();
	}

	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1)
	{
		inventory.setInventorySlotContents(start + arg0, arg1);
	}

}
