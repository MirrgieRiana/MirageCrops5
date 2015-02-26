package mirrg.mir50.item.adaptors;

import mirrg.mir50.item.AdaptorItemContainerItemOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AdaptorItemContainerItemMaxStackSize extends AdaptorItemContainerItemOverriding
{

	public AdaptorItemContainerItemMaxStackSize(ItemMir50 owner, IVirtualClass superObject, int maxStackSize)
	{
		super(owner, superObject);
		this.maxStackSize = maxStackSize;
	}

	public AdaptorItemContainerItemMaxStackSize(ItemMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	public int maxStackSize = 64;

	@Override
	public int getItemStackLimit(ItemStack itemStack)
	{
		return this.getItemStackLimit();
	}

	@SuppressWarnings("deprecation")
	@Deprecated
	@Override
	public int getItemStackLimit()
	{
		return maxStackSize;
	}

	@Override
	public Item setMaxStackSize(int maxStackSize)
	{
		this.maxStackSize = maxStackSize;
		return owner;
	}

}
