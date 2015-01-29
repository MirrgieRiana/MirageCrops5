package mirrg.mir51.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemIcon extends AdaptorItem
{

	public AdaptorItemIcon(ItemMir50 itemMir50)
	{
		super(itemMir50);
	}

	public IIcon getIcon(ItemStack itemStack, int pass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		return getIcon(itemStack, pass);
	}

	public IIcon getIcon(ItemStack itemStack, int pass)
	{
		return getIconFromDamageForRenderPass(itemStack.getItemDamage(), pass);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		return getIconFromDamage(meta);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return itemMir50.getField_itemIcon();
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		itemMir50.setField_itemIcon(iconRegister.registerIcon(getIconString()));
	}

	@SideOnly(Side.CLIENT)
	public String getIconString()
	{
		return itemMir50.getField_iconString() == null
			? "MISSING_ICON_ITEM_" + Item.itemRegistry.getIDForObject(itemMir50) + "_" + itemMir50.getUnlocalizedName()
			: itemMir50.getField_iconString();
	}

}
