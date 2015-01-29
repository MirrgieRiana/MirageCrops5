package mirrg.mir51.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMir50 extends Item
{

	public AdaptorItemSubItems adaptorItemSubItems = new AdaptorItemSubItems(this);

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTab, List itemStacks)
	{
		adaptorItemSubItems.getSubItems(item, creativeTab, itemStacks);
	}

	//

	public AdaptorItemIcon adaptorItemIcon = new AdaptorItemIcon(this);

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		return adaptorItemIcon.getIcon(itemStack, pass, player, usingItem, useRemaining);
	}

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass)
	{
		return adaptorItemIcon.getIcon(itemStack, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		return adaptorItemIcon.getIconFromDamageForRenderPass(meta, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return adaptorItemIcon.getIconFromDamage(meta);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getItemIcon()
	{
		return itemIcon;
	}

	@SideOnly(Side.CLIENT)
	public void setItemIcon(IIcon itemIcon)
	{
		this.itemIcon = itemIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		adaptorItemIcon.registerIcons(iconRegister);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getIconString()
	{
		return adaptorItemIcon.getIconString();
	}

	public String getField_IconString()
	{
		return iconString;
	}

	public void setField_IconString(String iconString)
	{
		this.iconString = iconString;
	}

}
