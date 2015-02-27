package mirrg_miragecrops5.machine.container;

import mirrg_miragecrops5.machine.RegistryFairyFuel;
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
		return RegistryFairyFuel.getFairyFuelValue(itemStack) > 0;
	}

}
