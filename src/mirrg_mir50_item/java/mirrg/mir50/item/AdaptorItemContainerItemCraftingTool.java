package mirrg.mir50.item;

import net.minecraft.item.ItemStack;

public class AdaptorItemContainerItemCraftingTool extends AdaptorItemContainerItem
{

	public AdaptorItemContainerItemCraftingTool(ItemMir50 itemMir50)
	{
		super(itemMir50);
	}

	@Override
	public boolean hasContainerItem(ItemStack itemStack)
	{
		return true;
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
	{
		return false;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		ItemStack itemStack2 = itemStack.copy();

		itemStack2.setItemDamage(itemStack2.getItemDamage() + 1);

		if (itemStack2.getMaxDamage() < itemStack2.getItemDamage()) {
			return null;
		}

		return itemStack2;
	}

}
