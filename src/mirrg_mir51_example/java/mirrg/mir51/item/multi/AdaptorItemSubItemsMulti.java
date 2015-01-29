package mirrg.mir51.item.multi;

import java.util.List;

import mirrg.mir51.item.AdaptorItemSubItems;
import mirrg.mir51.item.ItemMir50;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemSubItemsMulti extends AdaptorItemSubItems
{

	protected ContainerMetaItem containerMetaItem;

	public AdaptorItemSubItemsMulti(ItemMir50 itemMir50, ContainerMetaItem containerMetaItem)
	{
		super(itemMir50);
		this.containerMetaItem = containerMetaItem;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTab, List itemStacks)
	{
		containerMetaItem.forEach(metaItem -> {
			if (metaItem != null) {
				if (metaItem.adaptorItemSubItems != null) {
					metaItem.adaptorItemSubItems.getSubItems(item, creativeTab, itemStacks);
				} else {
					itemStacks.add(new ItemStack(item, 1, metaItem.meta));
				}
			}
		});
	}

}
