package mirrg.mir50.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IAdaptorBlockIcon
{

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side);

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta);

	@SideOnly(Side.CLIENT)
	public IIcon func_149735_b(int side, int meta);

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z);

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta);

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister);

	@SideOnly(Side.CLIENT)
	public String getTextureName();

	public Block setBlockTextureName(String textureName);

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side);

}
