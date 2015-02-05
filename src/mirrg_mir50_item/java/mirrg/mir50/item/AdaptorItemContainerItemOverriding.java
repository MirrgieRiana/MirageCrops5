package mirrg.mir50.item;

import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.item.ItemStack;

public class AdaptorItemContainerItemOverriding extends AdaptorItemContainerItem
{

	protected final IAdaptorItemContainerItem _super_IAdaptorItemContainerItem;

	public AdaptorItemContainerItemOverriding(ItemMir50 owner, IVirtualClass superObject)
	{
		super(owner);
		this._super_IAdaptorItemContainerItem = superObject.getVirtualClass().getCurrentImplementation(IAdaptorItemContainerItem.class);
	}

	@Override
	public boolean hasContainerItem(ItemStack itemStack)
	{
		return _super_IAdaptorItemContainerItem.hasContainerItem(itemStack);
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
	{
		return _super_IAdaptorItemContainerItem.doesContainerItemLeaveCraftingGrid(itemStack);
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		return _super_IAdaptorItemContainerItem.getContainerItem(itemStack);
	}

}
