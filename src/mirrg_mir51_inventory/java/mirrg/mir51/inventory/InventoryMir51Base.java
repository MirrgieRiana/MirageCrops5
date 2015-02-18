package mirrg.mir51.inventory;

import api.mirrg.mir50.world.pointer.ISupplierPositionWorld;
import mirrg.mir50.world.HelpersWorld;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class InventoryMir51Base extends InventoryMir51AutonomyAbstract
{

	protected Runnable runnableMarkDirty;
	protected ISupplierPositionWorld supplierPositionWorld;

	protected final ItemStack[] itemStacks;
	protected final IInventoryCellMir51[] inventoryCellMir51s;
	public String customInventoryName = null;
	public int inventoryStackLimit = 64;

	public InventoryMir51Base(
		Runnable runnableMarkDirty,
		ISupplierPositionWorld supplierPositionWorld,
		int size)
	{
		this.runnableMarkDirty = runnableMarkDirty;
		this.supplierPositionWorld = supplierPositionWorld;

		itemStacks = new ItemStack[size];
		inventoryCellMir51s = new IInventoryCellMir51[size];
	}

	@Override
	public void markDirty()
	{
		runnableMarkDirty.run();
	}

	@Override
	public void markDirty(int index)
	{
		markDirty();
	}

	@Override
	public void drop(int index)
	{
		HelpersWorld.dropItemStack(supplierPositionWorld, getStackInSlot(index));
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		if (supplierPositionWorld.getWorld().getTileEntity(
			supplierPositionWorld.getXAsInt(),
			supplierPositionWorld.getYAsInt(),
			supplierPositionWorld.getZAsInt()) != null) {
			return player.getDistanceSq(
				supplierPositionWorld.getX(),
				supplierPositionWorld.getY(),
				supplierPositionWorld.getZ()) <= 64.0D;
		}
		return false;
	}

	@Override
	public int getSizeInventory()
	{
		return itemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return itemStacks[index];
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack itemStack)
	{
		itemStacks[index] = itemStack;
		markDirty();
	}

	@Override
	public IInventoryCellMir51 getInventoryCell(int index)
	{
		if (inventoryCellMir51s[index] == null) {
			inventoryCellMir51s[index] = new InventoryCellMir51(this, index);
		}
		return inventoryCellMir51s[index];
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

	@SuppressWarnings("deprecation")
	@Deprecated
	@Override
	public int getInventoryStackLimit()
	{
		return inventoryStackLimit;
	}

	@Override
	public int getInventoryStackLimit(int index)
	{
		return getInventoryStackLimit();
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
	public boolean isItemValidForSlot(int index, ItemStack itemStack)
	{
		return true;
	}

}
