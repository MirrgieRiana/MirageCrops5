package mirrg.mir51.item.multi;

import mirrg.mir51.item.AdaptorItemIcon;
import mirrg.mir51.item.AdaptorItemName;
import mirrg.mir51.item.AdaptorItemSubItems;
import mirrg.mir51.item.ItemMir50;

public class MetaItem
{

	protected ItemMir50 itemSample;
	protected int meta;

	public MetaItem(ItemMir50 itemSample, int meta)
	{
		this.itemSample = itemSample;
		this.meta = meta;
	}

	public AdaptorItemSubItems adaptorItemSubItems;

	public AdaptorItemIcon adaptorItemIcon;

	public AdaptorItemName adaptorItemName;

}
