package mirrg.mir50.item.multi;

import java.util.List;

import mirrg.mir50.item.AdaptorItemSubItemsOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemSubItemsMetaItem extends AdaptorItemSubItemsOverriding
{

	protected final MetaItem metaItem;

	public AdaptorItemSubItemsMetaItem(ItemMir50 owner, IVirtualClass superObject, MetaItem metaItem)
	{
		super(owner, superObject);
		this.metaItem = metaItem;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTab, List<ItemStack> itemStacks)
	{
		itemStacks.add(new ItemStack(this.metaItem.itemMir50, 1, this.metaItem.meta));
	}

}
