package mirrg.mir50.item.multi;

import mirrg.mir50.item.AdaptorItemName;
import mirrg.mir50.item.ItemMir50;
import net.minecraft.item.ItemStack;

public class AdaptorItemNameMulti extends AdaptorItemName
{

	protected ContainerMetaItem containerMetaItem;

	public AdaptorItemNameMulti(ItemMir50 itemSample, ContainerMetaItem containerMetaItem)
	{
		super(itemSample);
		this.containerMetaItem = containerMetaItem;
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack itemStack)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null || metaItem.adaptorItemName == null) return super.getUnlocalizedNameInefficiently(itemStack);
		return metaItem.adaptorItemName.getUnlocalizedNameInefficiently(itemStack);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null || metaItem.adaptorItemName == null) return super.getUnlocalizedName(itemStack);
		return metaItem.adaptorItemName.getUnlocalizedName(itemStack);
	}

}
