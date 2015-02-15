package mirrg.mir51.inventory;

import mirrg.mir50.inventory.ISimpleInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public interface ISimpleInventoryMir51 extends ISimpleInventory, IInventoryName
{

	@Deprecated
	@Override
	public int getInventoryStackLimit();

	@Override
	public IInventoryCellMir51 getInventoryCell(int index);

	public TileEntity getTileEntity();

	public void clearInventoryAll();

	public void writeToNBT(NBTTagCompound tag);

	public void readFromNBT(NBTTagCompound tag);

	public void onBroken();

	public void dropAll();

}
