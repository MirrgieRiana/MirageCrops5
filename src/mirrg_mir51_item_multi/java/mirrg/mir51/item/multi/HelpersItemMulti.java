package mirrg.mir51.item.multi;

import mirrg.mir50.item.ItemMir50;

public class HelpersItemMulti
{

	public static void makeItemMulti(ItemMir50 itemMir50, ContainerMetaItem metaItemContainer)
	{
		itemMir50.setHasSubtypes(true);

		itemMir50.adaptorItemSubItems = new AdaptorItemSubItemsMulti(itemMir50, metaItemContainer);
		itemMir50.adaptorItemIcon = new AdaptorItemIconMulti(itemMir50, metaItemContainer);
		itemMir50.adaptorItemName = new AdaptorItemNameMulti(itemMir50, metaItemContainer);
	}

}
