package mirrg.mir52.inventories;

import mirrg.mir50.inventory.HelpersSimpleInventory;
import mirrg.mir50.inventory.ISimpleInventory;
import mirrg.mir51.inventory.ISimpleInventoryMir51;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;

public class HelpersSimpleInventoryMir51
{

	public static ISimpleInventoryMir51 make(IInventory inventory, TileEntity tileEntity)
	{
		return make(HelpersSimpleInventory.make(inventory), tileEntity);
	}

	public static ISimpleInventoryMir51 make(ISimpleInventory simpleInventory, TileEntity tileEntity)
	{
		return new SimpleInventoryMir51FromSimpleInventory(tileEntity, simpleInventory);
	}

}
