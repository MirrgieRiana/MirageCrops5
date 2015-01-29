package mirrg.mir51.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemIconAutonomy extends AdaptorItemIcon
{

	@SideOnly(Side.CLIENT)
	public IIcon itemIcon;

	public String iconString = null;

	public AdaptorItemIconAutonomy(ItemMir50 itemSample)
	{
		super(itemSample);
	}

	public AdaptorItemIconAutonomy(ItemMir50 itemSample, String iconString)
	{
		super(itemSample);
		this.iconString = iconString;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return itemIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(getIconString());
	}

	@Override
	public String getIconString()
	{
		return iconString == null
			? "MISSING_ICON_ITEM_" + Item.itemRegistry.getIDForObject(itemMir50) + "_" + itemMir50.getUnlocalizedName()
			: iconString;
	}

}
