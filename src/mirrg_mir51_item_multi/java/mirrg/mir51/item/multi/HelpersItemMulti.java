package mirrg.mir51.item.multi;

import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;

public class HelpersItemMulti
{

	public static void make(IVirtualClass virtualClass, ItemMir50 itemMir50, ContainerMetaItem containerMetaItem)
	{
		make(virtualClass, itemMir50, containerMetaItem, false);
	}

	public static void make(IVirtualClass virtualClass, ItemMir50 itemMir50, ContainerMetaItem containerMetaItem, boolean requiresMultipleRenderPasses)
	{
		itemMir50.setHasSubtypes(true);

		virtualClass.getVirtualClass().override(new AdaptorItemSubItemsMulti(itemMir50, virtualClass, containerMetaItem));
		virtualClass.getVirtualClass().override(new AdaptorItemIconMulti(itemMir50, virtualClass, containerMetaItem, requiresMultipleRenderPasses));
		virtualClass.getVirtualClass().override(new AdaptorItemNameMulti(itemMir50, virtualClass, containerMetaItem));
		virtualClass.getVirtualClass().override(new AdaptorItemContainerItemMulti(itemMir50, virtualClass, containerMetaItem));
		virtualClass.getVirtualClass().override(new AdaptorItemEventsMulti(itemMir50, virtualClass, containerMetaItem));
	}

}
