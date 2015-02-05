package mirrg.mir50.item.adaptors;

import mirrg.mir50.item.AdaptorItemContainerItemOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.item.ItemStack;

public class AdaptorItemContainerItemCraftingTool extends AdaptorItemContainerItemOverriding
{

	public AdaptorItemContainerItemCraftingTool(ItemMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
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
