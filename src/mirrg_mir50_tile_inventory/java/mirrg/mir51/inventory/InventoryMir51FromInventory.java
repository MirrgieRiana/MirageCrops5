package mirrg.mir51.inventory;

import mirrg.mir50.world.pointer.ISupplierPositionWorld;
import mirrg.mir51.world.HelpersWorldMir51;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryMir51FromInventory extends InventoryMir51Abstract
{

	protected ISupplierPositionWorld supplierPositionWorld;

	public InventoryMir51FromInventory(IInventory owner, ISupplierPositionWorld supplierPositionWorld)
	{
		this.owner = owner;
		this.supplierPositionWorld = supplierPositionWorld;
	}

	@Override
	public String getCustomInventoryName()
	{
		return owner.hasCustomInventoryName() ? owner.getInventoryName() : null;
	}

	@Override
	public String getDefaultName()
	{
		return "unknown";
	}

	@Override
	public void drop(int index)
	{
		HelpersWorldMir51.dropItemStack(supplierPositionWorld, getStackInSlot(index));
	}

	@Override
	public void markDirty(int index)
	{
		markDirty();
	}

	@Override
	public int getInventoryStackLimit(int index)
	{
		return getInventoryStackLimit();
	}

	@Override
	public IInventoryCellMir51 getInventoryCell(int index)
	{
		return new InventoryCellMir51(this, index);
	}

	/////////////////////////////////////////////////

	protected IInventory owner;

	@Override
	public int getSizeInventory()
	{
		return owner.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int p_70301_1_)
	{
		return owner.getStackInSlot(p_70301_1_);
	}

	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_)
	{
		return owner.decrStackSize(p_70298_1_, p_70298_2_);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_)
	{
		return owner.getStackInSlotOnClosing(p_70304_1_);
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_)
	{
		owner.setInventorySlotContents(p_70299_1_, p_70299_2_);
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
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
	{
		return owner.isUseableByPlayer(p_70300_1_);
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
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		return owner.isItemValidForSlot(p_94041_1_, p_94041_2_);
	}

}
