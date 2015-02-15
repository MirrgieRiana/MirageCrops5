package mirrg.mir50.inventory;

import net.minecraft.entity.player.EntityPlayer;

public class SimpleInventoryDelegation extends SimpleInventory
{

	@Handmade
	public SimpleInventoryDelegation(ISimpleInventory owner)
	{
		this.owner = owner;
	}

	/////////////////////////////////////////////////////////////////////

	@Handmade
	protected ISimpleInventory owner;

	@Override
	public int getSizeInventory()
	{
		return owner.getSizeInventory();
	}

	@Override
	public IInventoryCell getInventoryCell(int index)
	{
		return owner.getInventoryCell(index);
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return owner.isUseableByPlayer(player);
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
	public void openInventory()
	{
		owner.openInventory();
	}

	@Override
	public void closeInventory()
	{
		owner.closeInventory();
	}

}
