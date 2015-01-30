package mirrg.mir51.item.multi;

import mirrg.mir50.item.AdaptorItemIcon;
import mirrg.mir50.item.ItemMir50;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemIconMulti extends AdaptorItemIcon
{

	protected ContainerMetaItem containerMetaItem;

	@SideOnly(Side.CLIENT)
	protected boolean requiresMultipleRenderPasses;

	public AdaptorItemIconMulti(ItemMir50 itemSample, ContainerMetaItem containerMetaItem, boolean requiresMultipleRenderPasses)
	{
		super(itemSample);
		this.containerMetaItem = containerMetaItem;
		this.requiresMultipleRenderPasses = requiresMultipleRenderPasses;
	}

	public AdaptorItemIconMulti(ItemMir50 itemSample, ContainerMetaItem containerMetaItem)
	{
		this(itemSample, containerMetaItem, false);
	}

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null || metaItem.adaptorItemIcon == null) return super.getIcon(itemStack, pass, player, usingItem, useRemaining);
		return metaItem.adaptorItemIcon.getIcon(itemStack, pass, player, usingItem, useRemaining);
	}

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null || metaItem.adaptorItemIcon == null) return super.getIcon(itemStack, pass);
		return metaItem.adaptorItemIcon.getIcon(itemStack, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		MetaItem metaItem = containerMetaItem.get(meta);
		if (metaItem == null || metaItem.adaptorItemIcon == null) return super.getIconFromDamageForRenderPass(meta, pass);
		return metaItem.adaptorItemIcon.getIconFromDamageForRenderPass(meta, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		MetaItem metaItem = containerMetaItem.get(meta);
		if (metaItem == null || metaItem.adaptorItemIcon == null) return super.getIconFromDamage(meta);
		return metaItem.adaptorItemIcon.getIconFromDamage(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		super.registerIcons(iconRegister);

		containerMetaItem.forEach(metaItem -> {
			if (metaItem != null && metaItem.adaptorItemIcon != null) metaItem.adaptorItemIcon.registerIcons(iconRegister);
		});

	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return requiresMultipleRenderPasses;
	}

	@Override
	public int getRenderPasses(int meta)
	{
		if (!requiresMultipleRenderPasses()) return 1;

		MetaItem metaItem = containerMetaItem.get(meta);
		if (metaItem == null || metaItem.adaptorItemIcon == null) return super.getRenderPasses(meta);
		return metaItem.adaptorItemIcon.getRenderPasses(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int pass)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null || metaItem.adaptorItemIcon == null) return super.getColorFromItemStack(itemStack, pass);
		return metaItem.adaptorItemIcon.getColorFromItemStack(itemStack, pass);
	}

}
