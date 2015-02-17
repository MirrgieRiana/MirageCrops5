package mirrg.mir51.world;

import mirrg.mir50.world.HelpersWorld;
import mirrg.mir50.world.pointer.ISupplierPositionWorld;
import net.minecraft.item.ItemStack;

public class HelpersWorldMir51
{

	public static void dropItemStack(ISupplierPositionWorld supplierPositionWorld, ItemStack itemStack)
	{
		HelpersWorld.dropItemStack(
			supplierPositionWorld.getWorld(),
			supplierPositionWorld.getX(),
			supplierPositionWorld.getY(),
			supplierPositionWorld.getZ(),
			itemStack);
	}

}
