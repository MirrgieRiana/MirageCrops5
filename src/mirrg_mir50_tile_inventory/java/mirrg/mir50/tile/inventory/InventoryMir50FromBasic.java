package mirrg.mir50.tile.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class InventoryMir50FromBasic extends InventoryMir50
{

	protected IInventory inventory;

	public InventoryMir50FromBasic(TileEntity tileEntity, IInventory inventory)
	{
		super(tileEntity);
		this.inventory = inventory;
	}

	@Override
	public ItemStack getStackInSlot(int arg0)
	{
		return inventory.getStackInSlot(arg0);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack itemStack)
	{
		inventory.setInventorySlotContents(index, itemStack);
	}

	@Override
	public int getSizeInventory()
	{
		return inventory.getSizeInventory();
	}

}
