package mirrg.mir52.inventories;

import mirrg.mir51.inventory.IInventoryCellMir51;
import mirrg.mir51.inventory.ISimpleInventoryMir51;
import mirrg.mir51.inventory.SimpleInventoryMir51Abstract;
import net.minecraft.tileentity.TileEntity;

/**
 * インベントリの部分を分離し、新たな通し番号によるスロットへの参照を与えます。
 */
public class SimpleInventoryTrimmer extends SimpleInventoryMir51Abstract
{

	protected ISimpleInventoryMir51 inventory;
	protected int start;
	protected int length;

	public SimpleInventoryTrimmer(TileEntity tileEntity, ISimpleInventoryMir51 inventory, int start, int length)
	{
		super(tileEntity);
		this.inventory = inventory;
		this.start = start;
		this.length = length;
	}

	@SuppressWarnings("deprecation")
	@Deprecated
	@Override
	public int getInventoryStackLimit()
	{
		return inventory.getInventoryStackLimit();
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return inventory.hasCustomInventoryName();
	}

	@Override
	public String getCustomInventoryName()
	{
		return inventory.getCustomInventoryName();
	}

	@Override
	public String getDefaultName()
	{
		return inventory.getDefaultName();
	}

	@Override
	public void markDirty()
	{
		inventory.markDirty();
	}

	@Override
	public void openInventory()
	{
		inventory.openInventory();
	}

	@Override
	public void closeInventory()
	{
		inventory.closeInventory();
	}

	@Override
	public int getSizeInventory()
	{
		return length;
	}

	////////////////////////////////////////////////////////////

	@Override
	public IInventoryCellMir51 getInventoryCell(int index)
	{
		return inventory.getInventoryCell(start + index);
	}

}
