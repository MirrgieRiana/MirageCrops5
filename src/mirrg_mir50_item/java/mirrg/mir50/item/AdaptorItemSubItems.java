package mirrg.mir50.item;

import java.util.List;

import mirrg.p.adaptor.Adaptor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemSubItems extends Adaptor<ItemMir50> implements IAdaptorItemSubItems
{

	public AdaptorItemSubItems(ItemMir50 owner)
	{
		super(owner);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTab, List<ItemStack> itemStacks)
	{
		itemStacks.add(new ItemStack(item, 1, 0));
	}

}
