package mirrg.mir50.inventory;

import net.minecraft.inventory.IInventory;

public class HelpersSimpleInventory
{

	public static ISimpleInventory make(IInventory inventory)
	{
		return new SimpleInventoryFromBasic(inventory);
	}

}
