package mirrg.mir50.inventory;

import net.minecraft.item.ItemStack;

public interface IInventoryCell
{

	public ItemStack getStackInSlot();

	public ItemStack decrStackSize(int amount);

	public ItemStack getStackInSlotOnClosing();

	public void setInventorySlotContents(ItemStack itemStack);

	public boolean isItemValidForSlot(ItemStack itemStack);

}
