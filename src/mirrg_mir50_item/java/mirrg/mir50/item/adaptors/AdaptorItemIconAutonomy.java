package mirrg.mir50.item.adaptors;

import java.util.ArrayList;

import mirrg.mir50.item.AdaptorItemIconOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * {@link Item}クラスと独立したアイコンを持つアイコンプロバイダー。色付きマルチプルレンダー対応。
 */
public class AdaptorItemIconAutonomy extends AdaptorItemIconOverriding
{

	@SideOnly(Side.CLIENT)
	public ArrayList<IIcon> itemIcons = new ArrayList<>();

	public ArrayList<String> iconStrings = new ArrayList<>();

	@SideOnly(Side.CLIENT)
	public ArrayList<Integer> iconColors = new ArrayList<>();

	public AdaptorItemIconAutonomy(ItemMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	public AdaptorItemIconAutonomy(ItemMir50 owner, IVirtualClass superObject, String iconString)
	{
		super(owner, superObject);
		this.appendIcon(iconString);
	}

	public void appendIcon(String iconString)
	{
		this.appendIcon(iconString, 0xffffff);
	}

	public void appendIcon(String iconString, int iconColor)
	{
		this.iconStrings.add(iconString);
		this.iconColors.add(iconColor);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		return this.itemIcons.get(pass);
	}

	@Override
	@Deprecated
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return owner.accessor_IAdaptorItemIcon.get().getIconFromDamageForRenderPass(meta, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		while (this.itemIcons.size() < this.iconStrings.size()) {
			this.itemIcons.add(null);
		}
		for (int i = 0; i < this.iconStrings.size(); i++) {
			this.itemIcons.set(i, iconRegister.registerIcon(this.iconStrings.get(i)));
		}
	}

	public String getIconString(int pass)
	{
		return this.iconStrings.get(pass) == null
			? "MISSING_ICON_ITEM_" + Item.itemRegistry.getIDForObject(this.owner) + "_" + this.owner.getUnlocalizedName()
			: this.iconStrings.get(pass);
	}

	@Override
	@Deprecated
	public String getIconString()
	{
		return this.getIconString(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return this.itemIcons.size() != 1;
	}

	@Override
	public int getRenderPasses(int meta)
	{
		return this.itemIcons.size();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int pass)
	{
		return this.iconColors.get(pass);
	}

}
