package mirrg.mir50.item.multi;

import mirrg.mir50.item.AdaptorItemIcon;
import mirrg.mir50.item.AdaptorItemName;
import mirrg.mir50.item.AdaptorItemSubItems;
import mirrg.mir50.item.ItemMir50;

public class MetaItem
{

	protected ItemMir50 itemMir50;
	protected int meta;

	public MetaItem(ItemMir50 itemMir50, int meta)
	{
		this.itemMir50 = itemMir50;
		this.meta = meta;
	}

	public AdaptorItemSubItems adaptorItemSubItems;

	public AdaptorItemIcon adaptorItemIcon;

	public AdaptorItemName adaptorItemName;

}
