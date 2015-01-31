package mirrg.mir50.item;

import net.minecraft.item.ItemStack;

public class AdaptorItemContainerItem extends AdaptorItem
{

	public AdaptorItemContainerItem(ItemMir50 itemMir50)
	{
		super(itemMir50);
	}

	public boolean hasContainerItem(ItemStack itemStack)
	{
		return itemMir50.hasContainerItem();
	}

	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
	{
		return true;
	}

	public ItemStack getContainerItem(ItemStack itemStack)
	{
		if (!hasContainerItem(itemStack)) {
			return null;
		}
		return new ItemStack(itemMir50.getContainerItem());
	}

}
