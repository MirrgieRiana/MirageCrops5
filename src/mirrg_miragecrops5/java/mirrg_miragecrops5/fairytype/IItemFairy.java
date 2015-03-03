package mirrg_miragecrops5.fairytype;

import mirrg.h.struct.Tuple;
import net.minecraft.item.ItemStack;

public interface IItemFairy
{

	public Tuple<FairyType, Integer> getFairyType(ItemStack itemStack);

}
