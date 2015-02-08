package mirrg_miragecrops5.recipefairy;

import net.minecraft.item.ItemStack;

@FunctionalInterface
public interface IRecipeFairyInput
{

	public boolean matches(ItemStack itemStack);

}
