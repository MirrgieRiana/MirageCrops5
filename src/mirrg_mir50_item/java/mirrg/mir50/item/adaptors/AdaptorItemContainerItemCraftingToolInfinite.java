package mirrg.mir50.item.adaptors;

import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.item.ItemStack;

public class AdaptorItemContainerItemCraftingToolInfinite extends AdaptorItemContainerItemCraftingTool
{

	public AdaptorItemContainerItemCraftingToolInfinite(ItemMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		return itemStack;
	}

}
