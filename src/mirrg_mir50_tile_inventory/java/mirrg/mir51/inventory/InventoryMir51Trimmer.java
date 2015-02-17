package mirrg.mir51.inventory;

import net.minecraft.entity.player.EntityPlayer;

/**
 * インベントリの部分を分離し、新たな通し番号によるスロットへの参照を与えます。
 */
public class InventoryMir51Trimmer extends InventoryMir51SimpleAbstract
{

	protected int start;
	protected int length;

	public InventoryMir51Trimmer(IInventoryMir51 inventory, int start, int length)
	{
		this.inventory = inventory;
		this.start = start;
		this.length = length;
	}

	@Override
	public int getSizeInventory()
	{
		return length;
	}

	@Override
	public IInventoryCellMir51 getInventoryCell(int index)
	{
		return inventory.getInventoryCell(start + index);
	}

	///////////////////////////////////////////////////////

	protected IInventoryMir51 inventory;

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return inventory.isUseableByPlayer(player);
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
	public String getLocalizedName()
	{
		return inventory.getLocalizedName();
	}

	@Override
	public String getInventoryName()
	{
		return inventory.getInventoryName();
	}

	@Override
	public String getCustomInventoryName()
	{
		return inventory.getCustomInventoryName();
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return inventory.hasCustomInventoryName();
	}

	@Override
	public String getDefaultName()
	{
		return inventory.getDefaultName();
	}

	@SuppressWarnings("deprecation")
	@Deprecated
	@Override
	public int getInventoryStackLimit()
	{
		return inventory.getInventoryStackLimit();
	}

	@Override
	public void markDirty()
	{
		inventory.markDirty();
	}

}
