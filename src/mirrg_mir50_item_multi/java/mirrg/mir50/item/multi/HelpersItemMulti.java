package mirrg.mir50.item.multi;

import mirrg.mir50.item.ItemMir50;

public class HelpersItemMulti
{

	public static void makeItemMulti(ItemMir50 itemMir50, ContainerMetaItem containerMetaItem)
	{
		makeItemMulti(itemMir50, containerMetaItem, false);
	}

	public static void makeItemMulti(ItemMir50 itemMir50, ContainerMetaItem containerMetaItem, boolean requiresMultipleRenderPasses)
	{
		itemMir50.setHasSubtypes(true);

		itemMir50.adaptorItemSubItems = new AdaptorItemSubItemsMulti(itemMir50, containerMetaItem);
		itemMir50.adaptorItemIcon = new AdaptorItemIconMulti(itemMir50, containerMetaItem, requiresMultipleRenderPasses);
		itemMir50.adaptorItemName = new AdaptorItemNameMulti(itemMir50, containerMetaItem);
	}

}
