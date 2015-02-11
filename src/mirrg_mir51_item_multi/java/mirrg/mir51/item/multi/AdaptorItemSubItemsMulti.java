package mirrg.mir51.item.multi;

import java.util.List;

import mirrg.mir50.item.AdaptorItemSubItemsOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemSubItemsMulti extends AdaptorItemSubItemsOverriding
{

	protected ContainerMetaItem containerMetaItem;

	public AdaptorItemSubItemsMulti(ItemMir50 itemMir50, IVirtualClass virtualClass, ContainerMetaItem containerMetaItem)
	{
		super(itemMir50, virtualClass);
		this.containerMetaItem = containerMetaItem;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTab, List<ItemStack> itemStacks)
	{
		containerMetaItem.forEach(metaItem -> {
			if (metaItem != null) {
				metaItem.accessor_IAdaptorItemSubItems.get().getSubItems(item, creativeTab, itemStacks);
			}
		});
	}

}
