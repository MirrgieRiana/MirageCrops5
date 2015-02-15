package mirrg_miragecrops5.machine;

import mirrg_miragecrops5.HelpersOreDictionary;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFairyFuel extends Slot
{

	public SlotFairyFuel(IInventory inventory, int index, int x, int y)
	{
		super(inventory, index, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack itemStack)
	{
		return HelpersOreDictionary.isOre(itemStack, "dustMirage");
	}

}
