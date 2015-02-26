package mirrg.mir50.item;

import mirrg.p.adaptor.Adaptor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AdaptorItemContainerItem extends Adaptor<ItemMir50> implements IAdaptorItemContainerItem
{

	public AdaptorItemContainerItem(ItemMir50 owner)
	{
		super(owner);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean hasContainerItem(ItemStack itemStack)
	{
		return this.owner.hasContainerItem();
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
	{
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		if (!owner.accessor_IAdaptorItemContainerItem.get().hasContainerItem(itemStack)) {
			return null;
		}
		return new ItemStack(this.owner.getContainerItem());
	}

	@Override
	public double getDurabilityForDisplay(ItemStack itemStack)
	{
		return (double) itemStack.getItemDamageForDisplay() / (double) itemStack.getMaxDamage();
	}

	@Override
	public boolean showDurabilityBar(ItemStack itemStack)
	{
		return itemStack.isItemDamaged();
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getItemStackLimit(ItemStack itemStack)
	{
		return owner.getItemStackLimit();
	}

	@SuppressWarnings("deprecation")
	@Deprecated
	@Override
	public int getItemStackLimit()
	{
		return owner.getField_maxStackSize();
	}

	@Override
	public Item setMaxStackSize(int maxStackSize)
	{
		owner.setField_maxStackSize(maxStackSize);
		return owner;
	}

}
