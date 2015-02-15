package mirrg.mir52.inventories;

import mirrg.mir51.inventory.IInventoryCellMir51;
import mirrg.mir51.inventory.SimpleInventoryMir51Abstract;
import net.minecraft.tileentity.TileEntity;

public class SimpleInventoryMir51 extends SimpleInventoryMir51Abstract
{

	protected final IInventoryCellMir51[] inventoryCellMir51s;
	public String customInventoryName;

	public SimpleInventoryMir51(int size, TileEntity tileEntity)
	{
		super(tileEntity);
		inventoryCellMir51s = new IInventoryCellMir51[size];
	}

	@Override
	public int getSizeInventory()
	{
		return inventoryCellMir51s.length;
	}

	@Override
	public IInventoryCellMir51 getInventoryCell(int index)
	{
		return inventoryCellMir51s[index];
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return customInventoryName != null;
	}

	@SuppressWarnings("deprecation")
	@Deprecated
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
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

	@Override
	public String getCustomInventoryName()
	{
		return customInventoryName;
	}

	@Override
	public String getDefaultName()
	{
		return "container.mir51";
	}

}
