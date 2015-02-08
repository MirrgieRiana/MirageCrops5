package mirrg.mir50.item.adaptors;

import java.util.function.Function;

import mirrg.mir50.item.AdaptorItemContainerItemOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.item.ItemStack;

public class AdaptorItemContainerItemAutonomy extends AdaptorItemContainerItemOverriding
{

	public Function<ItemStack, ItemStack> functionContainerItem = null;

	public boolean doesContainerItemLeaveCraftingGrid = true;

	public AdaptorItemContainerItemAutonomy(ItemMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	public AdaptorItemContainerItemAutonomy(ItemMir50 owner, IVirtualClass superObject,
		Function<ItemStack, ItemStack> functionContainerItem, boolean doesContainerItemLeaveCraftingGrid)
	{
		super(owner, superObject);
		this.functionContainerItem = functionContainerItem;
		this.doesContainerItemLeaveCraftingGrid = doesContainerItemLeaveCraftingGrid;
	}

	@Override
	public boolean hasContainerItem(ItemStack itemStack)
	{
		return functionContainerItem != null;
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
	{
		return doesContainerItemLeaveCraftingGrid;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		return functionContainerItem.apply(itemStack);
	}

}
