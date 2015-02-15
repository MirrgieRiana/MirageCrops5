package mirrg.mir50.tile.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class InventoryTileEntity extends InventoryMir50
{

	protected ItemStack[] itemStacks;

	public InventoryTileEntity(TileEntity tileEntity, int size)
	{
		super(tileEntity);
		itemStacks = new ItemStack[size];
	}

	@Override
	public ItemStack getStackInSlot(int arg0)
	{
		return itemStacks[arg0];
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack itemStack)
	{
		itemStacks[index] = itemStack;
		if (itemStack != null && itemStack.stackSize > getInventoryStackLimit(index)) {
			itemStack.stackSize = getInventoryStackLimit(index);
		}
		markDirty();
	}

	@Override
	public int getSizeInventory()
	{
		return itemStacks.length;
	}

}
