package mirrg.mir50.item;

import java.util.ArrayList;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * {@link Item}クラスと独立したアイコンを持つアイコンプロバイダー。色付きマルチプルレンダー対応。
 */
public class AdaptorItemIconAutonomy extends AdaptorItemIcon
{

	@SideOnly(Side.CLIENT)
	public ArrayList<IIcon> itemIcons = new ArrayList<>();

	public ArrayList<String> iconStrings = new ArrayList<>();

	@SideOnly(Side.CLIENT)
	public ArrayList<Integer> iconColors = new ArrayList<>();

	public AdaptorItemIconAutonomy(ItemMir50 itemSample)
	{
		super(itemSample);
	}

	public AdaptorItemIconAutonomy(ItemMir50 itemSample, String iconString)
	{
		super(itemSample);
		appendIcon(iconString);
	}

	public void appendIcon(String iconString)
	{
		appendIcon(iconString, 0xffffff);
	}

	public void appendIcon(String iconString, int iconColor)
	{
		iconStrings.add(iconString);
		iconColors.add(iconColor);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		return itemIcons.get(pass);
	}

	@Override
	@Deprecated
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return getIconFromDamageForRenderPass(meta, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		while (itemIcons.size() < iconStrings.size()) {
			itemIcons.add(null);
		}
		for (int i = 0; i < iconStrings.size(); i++) {
			itemIcons.set(i, iconRegister.registerIcon(iconStrings.get(i)));
		}
	}

	public String getIconString(int pass)
	{
		return iconStrings.get(pass) == null
			? "MISSING_ICON_ITEM_" + Item.itemRegistry.getIDForObject(itemMir50) + "_" + itemMir50.getUnlocalizedName()
			: iconStrings.get(pass);
	}

	@Override
	@Deprecated
	public String getIconString()
	{
		return getIconString(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return itemIcons.size() != 1;
	}

	@Override
	public int getRenderPasses(int meta)
	{
		return itemIcons.size();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int pass)
	{
		return iconColors.get(pass);
	}

}
