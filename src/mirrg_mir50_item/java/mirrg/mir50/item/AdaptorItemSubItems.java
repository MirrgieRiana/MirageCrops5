package mirrg.mir50.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemSubItems extends AdaptorItem
{

	public AdaptorItemSubItems(ItemMir50 itemMir50)
	{
		super(itemMir50);
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTab, List<ItemStack> itemStacks)
	{
		itemStacks.add(new ItemStack(item, 1, 0));
	}

}
