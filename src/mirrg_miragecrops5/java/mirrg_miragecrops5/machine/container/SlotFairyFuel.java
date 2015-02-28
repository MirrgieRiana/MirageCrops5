package mirrg_miragecrops5.machine.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import api.mirrg_miragecrops5.recipes.APIRegistryRecipe;

public class SlotFairyFuel extends Slot
{

	public SlotFairyFuel(IInventory inventory, int index, int x, int y)
	{
		super(inventory, index, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack itemStack)
	{
		return APIRegistryRecipe.registryRecipeFairyFuel.matcher(itemStack).isPresent();
	}

}
