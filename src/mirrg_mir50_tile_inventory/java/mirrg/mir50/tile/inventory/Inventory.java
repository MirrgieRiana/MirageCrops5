package mirrg.mir50.tile.inventory;

import java.util.function.Predicate;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import api.mirrg.mir50.net.NBTTypes;

public class Inventory implements IInventory
{

	protected ItemStack[] itemStacks;
	protected Runnable runnableMarkDirty;
	protected Predicate<EntityPlayer> predicateIsUseableByPlayer;

	public Inventory(Runnable runnableMarkDirty, int size)
	{
		this.runnableMarkDirty = runnableMarkDirty;
		this.predicateIsUseableByPlayer = p -> true;
		itemStacks = new ItemStack[size];
	}

	@Override
	public void openInventory()
	{

	}

	@Override
	public void closeInventory()
	{

	}

	@Override
	public ItemStack decrStackSize(int index, int amount)
	{
		if (itemStacks[index] != null) {
			if (itemStacks[index].stackSize <= amount) {
				ItemStack itemStack = itemStacks[index];
				itemStacks[index] = null;
				markDirty();
				return itemStack;
			} else {
				ItemStack itemStack = itemStacks[index].splitStack(amount);
				if (itemStacks[index].stackSize == 0) itemStacks[index] = null;
				markDirty();
				return itemStack;
			}
		} else {
			return null;
		}
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public int getSizeInventory()
	{
		return itemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int arg0)
	{
		return itemStacks[arg0];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
		if (itemStacks[index] != null) {
			ItemStack itemStack = itemStacks[index];
			itemStacks[index] = null;
			return itemStack;
		} else {
			return null;
		}
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public String getInventoryName()
	{
		return "container.inventory";
	}

	@Override
	public boolean isItemValidForSlot(int arg0, ItemStack arg1)
	{
		return true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return predicateIsUseableByPlayer.test(player);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack itemStack)
	{
		itemStacks[index] = itemStack;
		if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
			itemStack.stackSize = getInventoryStackLimit();
		}
		markDirty();
	}

	public void dropAll(World worldObj, float xCoord, float yCoord, float zCoord)
	{
		for (int i = 0; i < itemStacks.length; i++) {
			ItemStack itemStack = itemStacks[i];
			if (itemStack != null) {
				dropItemStack(worldObj, xCoord, yCoord, zCoord, itemStack);
			}
		}
	}

	protected static void dropItemStack(World worldObj, float xCoord, float yCoord, float zCoord, ItemStack itemStack)
	{
		float f1 = worldObj.rand.nextFloat() * 0.8F + 0.1F;
		float f2 = worldObj.rand.nextFloat() * 0.8F + 0.1F;
		float f3 = worldObj.rand.nextFloat() * 0.8F + 0.1F;

		while (itemStack.stackSize > 0) {
			int j = worldObj.rand.nextInt(21) + 10;
			if (j > itemStack.stackSize) j = itemStack.stackSize;
			itemStack.stackSize -= j;

			EntityItem entityItem = new EntityItem(worldObj, xCoord + f1, yCoord + f2, zCoord + f3,
				new ItemStack(itemStack.getItem(), j, itemStack.getItemDamage()));

			if (itemStack.hasTagCompound()) {
				entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
			}

			float f4 = 0.05F;
			entityItem.motionX = ((float) worldObj.rand.nextGaussian() * f4);
			entityItem.motionY = ((float) worldObj.rand.nextGaussian() * f4 + 0.2F);
			entityItem.motionZ = ((float) worldObj.rand.nextGaussian() * f4);
			worldObj.spawnEntityInWorld(entityItem);
		}

	}

	public void writeToNBT(NBTTagCompound tag)
	{
		for (int i = 0; i < itemStacks.length; i++) {
			if (itemStacks[i] != null) {
				NBTTagCompound tag2 = new NBTTagCompound();
				itemStacks[i].writeToNBT(tag2);
				tag.setTag("Slot_" + i, tag2);
			}
		}
	}

	public void readFromNBT(NBTTagCompound tag)
	{
		for (int i = 0; i < itemStacks.length; i++) {
			if (tag.hasKey("Slot_" + i, NBTTypes.COMPOUND)) {
				NBTTagCompound tag2 = tag.getCompoundTag("Slot_" + i);
				itemStacks[i] = ItemStack.loadItemStackFromNBT(tag2);
			} else {
				itemStacks[i] = null;
			}
		}
	}

	@Override
	public void markDirty()
	{
		runnableMarkDirty.run();
	}

}
