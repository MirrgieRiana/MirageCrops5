package mirrg.mir51.inventory;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import api.mirrg.mir50.net.NBTTypes;

public abstract class InventoryMir51Abstract implements IInventoryMir51
{

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		for (int i = 0; i < getSizeInventory(); i++) {
			if (getStackInSlot(i) != null) {
				NBTTagCompound tag2 = new NBTTagCompound();
				getStackInSlot(i).writeToNBT(tag2);
				tag.setTag("Slot_" + i, tag2);
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		clear();
		for (int i = 0; i < getSizeInventory(); i++) {
			if (tag.hasKey("Slot_" + i, NBTTypes.COMPOUND)) {
				NBTTagCompound tag2 = tag.getCompoundTag("Slot_" + i);
				setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(tag2));
			}
		}
	}

	@Override
	public String getLocalizedName()
	{
		if (hasCustomInventoryName()) {
			return getInventoryName();
		} else {
			return I18n.format(getInventoryName(), new Object[0]);
		}
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < getSizeInventory(); i++) {
			clear(i);
		}
	}

	@Override
	public void clear(int index)
	{
		setInventorySlotContents(index, null);
	}

	@Override
	public void onBroken()
	{
		drop();
	}

	@Override
	public void drop()
	{
		for (int i = 0; i < getSizeInventory(); i++) {
			drop(i);
		}
	}

}
