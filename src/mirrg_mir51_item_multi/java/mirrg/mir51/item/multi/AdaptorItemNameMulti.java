package mirrg.mir51.item.multi;

import java.util.List;

import mirrg.mir50.item.AdaptorItemNameOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemNameMulti extends AdaptorItemNameOverriding
{

	protected ContainerMetaItem containerMetaItem;

	public AdaptorItemNameMulti(ItemMir50 itemSample, IVirtualClass virtualClass, ContainerMetaItem containerMetaItem)
	{
		super(itemSample, virtualClass);
		this.containerMetaItem = containerMetaItem;
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack itemStack)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.getUnlocalizedNameInefficiently(itemStack);
		return metaItem.accessor_IAdaptorItemName.get().getUnlocalizedNameInefficiently(itemStack);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.getUnlocalizedName(itemStack);
		return metaItem.accessor_IAdaptorItemName.get().getUnlocalizedName(itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> strings, boolean shift)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) {
			super.addInformation(itemStack, player, strings, shift);
			return;
		}
		metaItem.accessor_IAdaptorItemName.get().addInformation(itemStack, player, strings, shift);
	}

}
