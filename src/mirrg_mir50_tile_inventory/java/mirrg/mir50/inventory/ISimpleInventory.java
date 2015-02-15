package mirrg.mir50.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface ISimpleInventory extends IInventory
{

	public IInventoryCell getInventoryCell(int index);

	@Override
	public ItemStack getStackInSlot(int index);

	@Override
	public ItemStack decrStackSize(int index, int amount);

	@Override
	public ItemStack getStackInSlotOnClosing(int index);

	@Override
	public void setInventorySlotContents(int index, ItemStack stackSize);

	@Override
	public boolean isUseableByPlayer(EntityPlayer player);

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stackSize);

}
