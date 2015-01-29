package mirrg.mir51.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AdaptorItemNameAutonomy extends AdaptorItemName
{

	public String unlocalizedName;

	public AdaptorItemNameAutonomy(ItemMir50 itemMir50)
	{
		super(itemMir50);
	}

	public AdaptorItemNameAutonomy(ItemMir50 itemMir50, String unlocalizedName)
	{
		super(itemMir50);
		this.unlocalizedName = unlocalizedName;
	}

	@Override
	public Item setUnlocalizedName(String unlocalizedName)
	{
		this.unlocalizedName = unlocalizedName;
		return itemMir50;
	}

	@Override
	public String getUnlocalizedName()
	{
		return "item." + unlocalizedName;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return "item." + unlocalizedName;
	}

}
