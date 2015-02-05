package mirrg.mir50.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IAdaptorItemIcon
{

	public IIcon getIcon(ItemStack itemStack, int pass, EntityPlayer player, ItemStack usingItem, int useRemaining);

	public IIcon getIcon(ItemStack itemStack, int pass);

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass);

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta);

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister);

	@SideOnly(Side.CLIENT)
	public String getIconString();

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses();

	public int getRenderPasses(int meta);

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int pass);

}
