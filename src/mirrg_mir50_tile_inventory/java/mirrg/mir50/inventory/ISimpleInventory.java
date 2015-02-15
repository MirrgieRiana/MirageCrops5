package mirrg.mir50.inventory;

import net.minecraft.entity.player.EntityPlayer;

public interface ISimpleInventory
{

	public int getSizeInventory();

	public IInventoryCell getInventoryCell(int index);

	public String getInventoryName();

	public boolean hasCustomInventoryName();

	public int getInventoryStackLimit();

	public void markDirty();

	public boolean isUseableByPlayer(EntityPlayer player);

	public void openInventory();

	public void closeInventory();

}
