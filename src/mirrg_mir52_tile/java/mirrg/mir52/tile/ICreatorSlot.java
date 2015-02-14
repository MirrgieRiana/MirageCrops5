package mirrg.mir52.tile;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

@FunctionalInterface
public interface ICreatorSlot
{

	public Slot create(IInventory inventory, int index, int x, int y);

}
