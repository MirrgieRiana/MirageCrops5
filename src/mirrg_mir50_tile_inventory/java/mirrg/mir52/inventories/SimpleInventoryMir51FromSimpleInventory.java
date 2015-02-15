package mirrg.mir52.inventories;

import mirrg.mir50.inventory.ISimpleInventory;
import mirrg.mir51.inventory.IInventoryCellMir51;
import mirrg.mir51.inventory.SimpleInventoryMir51;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class SimpleInventoryMir51FromSimpleInventory extends SimpleInventoryMir51
{

	protected ISimpleInventory owner;
	public String customInventoryName;

	public SimpleInventoryMir51FromSimpleInventory(TileEntity tileEntity, ISimpleInventory owner)
	{
		super(tileEntity);
		this.owner = owner;
	}

	@Override
	public int getSizeInventory()
	{
		return owner.getSizeInventory();
	}

	protected IInventoryCellMir51[] inventoryCells;

	@Override
	public IInventoryCellMir51 getInventoryCell(int index)
	{
		if (inventoryCells == null) {
			inventoryCells = new IInventoryCellMir51[getSizeInventory()];
		}

		if (inventoryCells[index] == null) {
			inventoryCells[index] = new InventoryCellMir51FromInventoryCell(
				this::markDirty, owner.getInventoryCell(index));
		}

		return inventoryCells[index];
	}

	@Override
	public String getInventoryName()
	{
		return owner.getInventoryName();
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return owner.hasCustomInventoryName();
	}

	@SuppressWarnings("deprecation")
	@Deprecated
	@Override
	public int getInventoryStackLimit()
	{
		return owner.getInventoryStackLimit();
	}

	@Override
	public void markDirty()
	{
		owner.markDirty();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return owner.isUseableByPlayer(player);
	}

	@Override
	public void openInventory()
	{
		owner.openInventory();
	}

	@Override
	public void closeInventory()
	{
		owner.closeInventory();
	}

	@Override
	public String getCustomInventoryName()
	{
		return owner.hasCustomInventoryName() ? owner.getInventoryName() : null;
	}

	@Override
	public String getDefaultName()
	{
		return owner.hasCustomInventoryName() ? "container.mir51" : owner.getInventoryName();
	}

}
