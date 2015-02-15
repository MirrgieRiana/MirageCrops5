package mirrg.mir50.tile.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

 interface IInventoryMir50 extends IInventory, IInventoryName
{

	public TileEntity getTileEntity();

	@Override
	@Deprecated
	public int getInventoryStackLimit();

	public int getInventoryStackLimit(int index);

	public void clearInventoryAll();

	public void clearInventory(int index);

	public void writeToNBT(NBTTagCompound tag);

	public void readFromNBT(NBTTagCompound tag);

	public void onBroken();

	public void dropAll();

}
