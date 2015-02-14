package mirrg_miragecrops5.machine;

import mirrg_miragecrops5.ModuleCore;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFairy extends Slot
{

	public SlotFairy(IInventory inventory, int index, int x, int y)
	{
		super(inventory, index, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack itemStack)
	{
		return itemStack.getItem() == ModuleCore.loaderItem_craftingToolMirageFairy.get();
	}

	@Override
	public int getSlotStackLimit()
	{
		return 10;
	}

}
