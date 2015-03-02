package mirrg.mir51.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IInventoryMir51 extends IInventory
{

	@Override
	public int getSizeInventory();

	@Override
	public boolean isUseableByPlayer(EntityPlayer player);

	@Override
	public void openInventory();

	@Override
	public void closeInventory();

	public void onBroken();

	public void writeToNBT(NBTTagCompound tag);

	public void readFromNBT(NBTTagCompound tag);

	//

	public String getLocalizedName();

	@Override
	public String getInventoryName();

	public String getCustomInventoryName();

	@Override
	public boolean hasCustomInventoryName();

	public String getDefaultName();

	//

	public IInventoryCellMir51 getInventoryCell(int index);

	//

	@Deprecated
	@Override
	public int getInventoryStackLimit();

	public int getInventoryStackLimit(int index);

	public void clear();

	public void clear(int index);

	public void drop();

	public void drop(int index);

	@Override
	public void markDirty();

	public void markDirty(int index);

	//

	@Override
	public ItemStack getStackInSlot(int index);

	@Override
	public ItemStack decrStackSize(int index, int amount);

	@Override
	public ItemStack getStackInSlotOnClosing(int index);

	@Override
	public void setInventorySlotContents(int index, ItemStack itemStack);

	@Override
	public boolean isItemValidForSlot(int index, ItemStack itemStack);

	public default void reset()
	{
		clear();
	}

}
