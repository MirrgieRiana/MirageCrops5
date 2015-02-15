package mirrg.mir50.tile.inventory;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import api.mirrg.mir50.net.NBTTypes;

public abstract class InventoryMir50 implements IInventoryMir50
{

	private final TileEntity tileEntity;

	public InventoryMir50(TileEntity tileEntity)
	{
		this.tileEntity = tileEntity;
	}

	@Override
	public final TileEntity getTileEntity()
	{
		return tileEntity;
	}

	//

	@Override
	public String getInventoryName()
	{
		return getDefaultName();
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public String getDefaultName()
	{
		return "container.inventory";
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

	//

	@SuppressWarnings("deprecation")
	@Deprecated
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public int getInventoryStackLimit(int index)
	{
		return getInventoryStackLimit();
	}

	//

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		TileEntity te = getTileEntity();

		if (te.getWorldObj().getTileEntity(te.xCoord, te.yCoord, te.zCoord) != te) {
			return false;
		} else {
			return player.getDistanceSq(te.xCoord + 0.5D, te.yCoord + 0.5D, te.zCoord + 0.5D) <= 64.0D;
		}
	}

	@Override
	public void markDirty()
	{
		getTileEntity().markDirty();
	}

	@Override
	public void openInventory()
	{

	}

	@Override
	public void closeInventory()
	{

	}

	//

	@Override
	public abstract ItemStack getStackInSlot(int arg0);

	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
		if (getStackInSlot(index) != null) {
			ItemStack itemStack = getStackInSlot(index);
			clearInventory(index);
			return itemStack;
		} else {
			return null;
		}
	}

	@Override
	public abstract void setInventorySlotContents(int index, ItemStack itemStack);

	@Override
	public ItemStack decrStackSize(int index, int amount)
	{
		if (getStackInSlot(index) != null) {
			if (getStackInSlot(index).stackSize <= amount) {
				ItemStack itemStack = getStackInSlot(index);
				clearInventory(index);
				markDirty();
				return itemStack;
			} else {
				ItemStack itemStack = getStackInSlot(index).splitStack(amount);
				if (getStackInSlot(index).stackSize == 0) clearInventory(index);
				markDirty();
				return itemStack;
			}
		} else {
			return null;
		}
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		return true;
	}

	@Override
	public void clearInventory(int index)
	{
		setInventorySlotContents(index, null);
	}

	//

	@Override
	public void clearInventoryAll()
	{
		for (int i = 0; i < getSizeInventory(); i++) {
			clearInventory(i);
		}
	}

	@Override
	public abstract int getSizeInventory();

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
		for (int i = 0; i < getSizeInventory(); i++) {
			if (tag.hasKey("Slot_" + i, NBTTypes.COMPOUND)) {
				NBTTagCompound tag2 = tag.getCompoundTag("Slot_" + i);
				setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(tag2));
			} else {
				clearInventory(i);
			}
		}
	}

	@Override
	public void onBroken()
	{
		dropAll();
	}

	@Override
	public void dropAll()
	{
		for (int i = 0; i < getSizeInventory(); i++) {
			ItemStack itemStack = getStackInSlot(i);
			if (itemStack != null) {
				dropItemStack(getTileEntity().getWorldObj(),
					getTileEntity().xCoord, getTileEntity().yCoord, getTileEntity().zCoord, itemStack);
			}
		}
	}

	public static void dropItemStack(World worldObj, float xCoord, float yCoord, float zCoord, ItemStack itemStack)
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

}
