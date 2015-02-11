package mirrg.mir51.item.multi;

import mirrg.mir50.item.AdaptorItemIconOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemIconMulti extends AdaptorItemIconOverriding
{

	protected ContainerMetaItem containerMetaItem;

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses;

	public AdaptorItemIconMulti(ItemMir50 itemSample, IVirtualClass virtualClass, ContainerMetaItem containerMetaItem, boolean requiresMultipleRenderPasses)
	{
		super(itemSample, virtualClass);
		this.containerMetaItem = containerMetaItem;
		this.requiresMultipleRenderPasses = requiresMultipleRenderPasses;
	}

	public AdaptorItemIconMulti(ItemMir50 itemSample, IVirtualClass virtualClass, ContainerMetaItem containerMetaItem)
	{
		this(itemSample, virtualClass, containerMetaItem, false);
	}

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.getIcon(itemStack, pass, player, usingItem, useRemaining);
		return metaItem.accessor_IAdaptorItemIcon.get().getIcon(itemStack, pass, player, usingItem, useRemaining);
	}

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.getIcon(itemStack, pass);
		return metaItem.accessor_IAdaptorItemIcon.get().getIcon(itemStack, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		MetaItem metaItem = containerMetaItem.get(meta);
		if (metaItem == null) return super.getIconFromDamageForRenderPass(meta, pass);
		return metaItem.accessor_IAdaptorItemIcon.get().getIconFromDamageForRenderPass(meta, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		MetaItem metaItem = containerMetaItem.get(meta);
		if (metaItem == null) return super.getIconFromDamage(meta);
		return metaItem.accessor_IAdaptorItemIcon.get().getIconFromDamage(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		super.registerIcons(iconRegister);

		containerMetaItem.forEach(metaItem -> {
			if (metaItem != null) metaItem.accessor_IAdaptorItemIcon.get().registerIcons(iconRegister);
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
		if (metaItem == null) return super.getRenderPasses(meta);
		return metaItem.accessor_IAdaptorItemIcon.get().getRenderPasses(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int pass)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.getColorFromItemStack(itemStack, pass);
		return metaItem.accessor_IAdaptorItemIcon.get().getColorFromItemStack(itemStack, pass);
	}

}
