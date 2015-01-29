package mirrg.mir51.item.multi;

import mirrg.mir51.item.AdaptorItemIcon;
import mirrg.mir51.item.ItemMir50;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemIconMulti extends AdaptorItemIcon
{

	protected MetaItemContainer metaItemContainer;

	public AdaptorItemIconMulti(ItemMir50 itemSample, MetaItemContainer metaItemContainer)
	{
		super(itemSample);
		this.metaItemContainer = metaItemContainer;
	}

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		MetaItem metaItem = metaItemContainer.get(itemStack);
		if (metaItem == null || metaItem.adaptorItemIcon == null) return super.getIcon(itemStack, pass, player, usingItem, useRemaining);
		return metaItem.adaptorItemIcon.getIcon(itemStack, pass, player, usingItem, useRemaining);
	}

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass)
	{
		MetaItem metaItem = metaItemContainer.get(itemStack);
		if (metaItem == null || metaItem.adaptorItemIcon == null) return super.getIcon(itemStack, pass);
		return metaItem.adaptorItemIcon.getIcon(itemStack, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		MetaItem metaItem = metaItemContainer.get(meta);
		if (metaItem == null || metaItem.adaptorItemIcon == null) return super.getIconFromDamageForRenderPass(meta, pass);
		return metaItem.adaptorItemIcon.getIconFromDamageForRenderPass(meta, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		MetaItem metaItem = metaItemContainer.get(meta);
		if (metaItem == null || metaItem.adaptorItemIcon == null) return super.getIconFromDamage(meta);
		return metaItem.adaptorItemIcon.getIconFromDamage(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		super.registerIcons(iconRegister);

		metaItemContainer.forEach(metaItem -> {
			if (metaItem != null && metaItem.adaptorItemIcon != null) metaItem.adaptorItemIcon.registerIcons(iconRegister);
		});

	}

}
