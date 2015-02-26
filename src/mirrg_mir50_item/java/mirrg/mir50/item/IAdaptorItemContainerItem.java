package mirrg.mir50.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IAdaptorItemContainerItem
{

	public boolean hasContainerItem(ItemStack itemStack);

	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack);

	public ItemStack getContainerItem(ItemStack itemStack);

	public double getDurabilityForDisplay(ItemStack itemStack);

	public boolean showDurabilityBar(ItemStack itemStack);

	public int getItemStackLimit(ItemStack itemStack);

	@Deprecated
	public int getItemStackLimit();

	public Item setMaxStackSize(int maxStackSize);

}
