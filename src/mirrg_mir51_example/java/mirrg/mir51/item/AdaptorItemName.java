package mirrg.mir51.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class AdaptorItemName extends AdaptorItem
{

	public AdaptorItemName(ItemMir50 itemMir50)
	{
		super(itemMir50);
	}

	public Item setUnlocalizedName(String unlocalizedName)
	{
		itemMir50.setField_unlocalizedName(unlocalizedName);
		return itemMir50;
	}

	public String getUnlocalizedNameInefficiently(ItemStack itemStack)
	{
		String s = getUnlocalizedName(itemStack);
		return s == null ? "" : StatCollector.translateToLocal(s);
	}

	public String getUnlocalizedName()
	{
		return "item." + itemMir50.getField_unlocalizedName();
	}

	public String getUnlocalizedName(ItemStack itemStack)
	{
		return "item." + itemMir50.getField_unlocalizedName();
	}

}
