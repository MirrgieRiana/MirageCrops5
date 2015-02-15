package mirrg.mir51.inventory;

import mirrg.mir50.inventory.SimpleInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import api.mirrg.mir50.net.NBTTypes;

public abstract class SimpleInventoryMir51 extends SimpleInventory implements ISimpleInventoryMir51
{

	private final TileEntity tileEntity;

	public SimpleInventoryMir51(TileEntity tileEntity)
	{
		this.tileEntity = tileEntity;
	}

	@Override
	public TileEntity getTileEntity()
	{
		return tileEntity;
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
	public String getInventoryName()
	{
		return hasCustomInventoryName() ? getCustomInventoryName() : getDefaultName();
	}

	@Override
	public void clearInventoryAll()
	{
		for (int i = 0; i < getSizeInventory(); i++) {
			clearInventory(i);
		}
	}

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

	@Override
	public final int getInventoryStackLimit(int index)
	{
		return getInventoryCell(index).getInventoryStackLimit();
	}

	@Override
	public final void clearInventory(int index)
	{
		getInventoryCell(index).clearInventory();
	}

	@Override
	public final void markDirty(int index)
	{
		getInventoryCell(index).markDirty();
	}

	@SuppressWarnings("deprecation")
	@Deprecated
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

}
