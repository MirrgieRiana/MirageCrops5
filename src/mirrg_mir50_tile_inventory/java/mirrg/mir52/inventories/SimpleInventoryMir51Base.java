package mirrg.mir52.inventories;

import mirrg.mir51.inventory.IInventoryCellMir51;
import mirrg.mir51.inventory.SimpleInventoryMir51;
import net.minecraft.tileentity.TileEntity;

public class SimpleInventoryMir51Base extends SimpleInventoryMir51
{

	protected final IInventoryCellMir51[] inventoryCellMir51s;
	public String customInventoryName;

	public SimpleInventoryMir51Base(TileEntity tileEntity, int size)
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
		if (inventoryCellMir51s[index] == null) {
			inventoryCellMir51s[index] = new InventoryCellMir51Base(this::markDirty);
		}
		return inventoryCellMir51s[index];
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return customInventoryName != null;
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
