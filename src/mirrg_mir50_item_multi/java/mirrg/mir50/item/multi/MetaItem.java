package mirrg.mir50.item.multi;

import mirrg.mir50.item.AdaptorItemContainerItem;
import mirrg.mir50.item.AdaptorItemIcon;
import mirrg.mir50.item.AdaptorItemName;
import mirrg.mir50.item.AdaptorItemSubItems;
import mirrg.mir50.item.IAdaptorItemContainerItem;
import mirrg.mir50.item.IAdaptorItemIcon;
import mirrg.mir50.item.IAdaptorItemName;
import mirrg.mir50.item.IAdaptorItemSubItems;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import mirrg.p.virtualclass.IVirtualImplementationAccessor;
import mirrg.p.virtualclass.VirtualClass;

public class MetaItem implements IVirtualClass
{

	public final VirtualClass virtualClass = new VirtualClass(this);

	@Override
	public final VirtualClass getVirtualClass()
	{
		return virtualClass;
	}

	protected ItemMir50 itemMir50;
	protected int meta;

	public MetaItem(ItemMir50 itemMir50, int meta)
	{
		this.itemMir50 = itemMir50;
		this.meta = meta;

		virtualClass.register(IAdaptorItemSubItems.class, new AdaptorItemSubItems(itemMir50));
		accessor_IAdaptorItemSubItems = virtualClass.cast(IAdaptorItemSubItems.class);
		virtualClass.register(IAdaptorItemIcon.class, new AdaptorItemIcon(itemMir50));
		accessor_IAdaptorItemIcon = virtualClass.cast(IAdaptorItemIcon.class);
		virtualClass.register(IAdaptorItemName.class, new AdaptorItemName(itemMir50));
		accessor_IAdaptorItemName = virtualClass.cast(IAdaptorItemName.class);
		virtualClass.register(IAdaptorItemContainerItem.class, new AdaptorItemContainerItem(itemMir50));
		accessor_IAdaptorItemContainerItem = virtualClass.cast(IAdaptorItemContainerItem.class);

	}

	public final IVirtualImplementationAccessor<IAdaptorItemSubItems> accessor_IAdaptorItemSubItems;

	public final IVirtualImplementationAccessor<IAdaptorItemIcon> accessor_IAdaptorItemIcon;

	public final IVirtualImplementationAccessor<IAdaptorItemName> accessor_IAdaptorItemName;

	public final IVirtualImplementationAccessor<IAdaptorItemContainerItem> accessor_IAdaptorItemContainerItem;

}
