package mirrg_miragecrops5.recipefairy;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

@FunctionalInterface
public interface IRecipeFairyMatching
{

	public ItemStack create(InventoryCrafting inventoryCrafting, Integer[] slotIndexes);

}
