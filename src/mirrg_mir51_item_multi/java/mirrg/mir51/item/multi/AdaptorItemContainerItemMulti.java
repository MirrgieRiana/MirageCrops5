package mirrg.mir51.item.multi;

import mirrg.mir50.item.AdaptorItemContainerItemOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.item.ItemStack;

public class AdaptorItemContainerItemMulti extends AdaptorItemContainerItemOverriding
{

	protected ContainerMetaItem containerMetaItem;

	public AdaptorItemContainerItemMulti(ItemMir50 itemSample, IVirtualClass virtualClass, ContainerMetaItem containerMetaItem)
	{
		super(itemSample, virtualClass);
		this.containerMetaItem = containerMetaItem;
	}

	@Override
	public boolean hasContainerItem(ItemStack itemStack)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.hasContainerItem(itemStack);
		return metaItem.accessor_IAdaptorItemContainerItem.get().hasContainerItem(itemStack);
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.doesContainerItemLeaveCraftingGrid(itemStack);
		return metaItem.accessor_IAdaptorItemContainerItem.get().doesContainerItemLeaveCraftingGrid(itemStack);
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.getContainerItem(itemStack);
		return metaItem.accessor_IAdaptorItemContainerItem.get().getContainerItem(itemStack);
	}

}
