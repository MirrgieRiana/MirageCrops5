package mirrg.mir50.item;

import mirrg.p.adaptor.Adaptor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemIcon extends Adaptor<ItemMir50> implements IAdaptorItemIcon
{

	public AdaptorItemIcon(ItemMir50 owner)
	{
		super(owner);
	}

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		return owner.accessor_IAdaptorItemIcon.get().getIcon(itemStack, pass);
	}

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass)
	{
		return owner.accessor_IAdaptorItemIcon.get().getIconFromDamageForRenderPass(itemStack.getItemDamage(), pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		return owner.accessor_IAdaptorItemIcon.get().getIconFromDamage(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return this.owner.getField_itemIcon();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		this.owner.setField_itemIcon(iconRegister.registerIcon(this.getIconString()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getIconString()
	{
		return this.owner.getField_iconString() == null
			? "MISSING_ICON_ITEM_" + Item.itemRegistry.getIDForObject(this.owner) + "_" + this.owner.getUnlocalizedName()
			: this.owner.getField_iconString();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return false;
	}

	@Override
	public int getRenderPasses(int meta)
	{
		return owner.accessor_IAdaptorItemIcon.get().requiresMultipleRenderPasses() ? 2 : 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int pass)
	{
		return 16777215;
	}

}
