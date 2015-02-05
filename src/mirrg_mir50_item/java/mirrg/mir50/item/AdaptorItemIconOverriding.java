package mirrg.mir50.item;

import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemIconOverriding extends AdaptorItemIcon
{

	protected final IAdaptorItemIcon _super_IAdaptorItemIcon;

	public AdaptorItemIconOverriding(ItemMir50 owner, IVirtualClass superObject)
	{
		super(owner);
		this._super_IAdaptorItemIcon = superObject.getVirtualClass().getCurrentImplementation(IAdaptorItemIcon.class);
	}

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		return _super_IAdaptorItemIcon.getIcon(itemStack, pass, player, usingItem, useRemaining);
	}

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass)
	{
		return _super_IAdaptorItemIcon.getIcon(itemStack, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		return _super_IAdaptorItemIcon.getIconFromDamageForRenderPass(meta, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return _super_IAdaptorItemIcon.getIconFromDamage(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		_super_IAdaptorItemIcon.registerIcons(iconRegister);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getIconString()
	{
		return _super_IAdaptorItemIcon.getIconString();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return _super_IAdaptorItemIcon.requiresMultipleRenderPasses();
	}

	@Override
	public int getRenderPasses(int meta)
	{
		return _super_IAdaptorItemIcon.getRenderPasses(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int pass)
	{
		return _super_IAdaptorItemIcon.getColorFromItemStack(itemStack, pass);
	}

}
