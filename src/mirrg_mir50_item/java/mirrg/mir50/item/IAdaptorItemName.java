package mirrg.mir50.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IAdaptorItemName
{

	public Item setUnlocalizedName(String unlocalizedName);

	public String getUnlocalizedNameInefficiently(ItemStack itemStack);

	public String getUnlocalizedName();

	public String getUnlocalizedName(ItemStack itemStack);

}
