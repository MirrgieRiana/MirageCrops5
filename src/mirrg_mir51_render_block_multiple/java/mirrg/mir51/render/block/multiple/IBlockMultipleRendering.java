package mirrg.mir51.render.block.multiple;

import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IBlockMultipleRendering
{

	@SideOnly(Side.CLIENT)
	public void setMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, boolean isInMultipleRendering);

	@SideOnly(Side.CLIENT)
	public void setMultipleRendering(int metadata, boolean isInMultipleRendering);

	@SideOnly(Side.CLIENT)
	public void setMultipleRenderPass(IBlockAccess blockAccess, int x, int y, int z, int pass);

	@SideOnly(Side.CLIENT)
	public void setMultipleRenderPass(int metadata, int pass);

	//

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(IBlockAccess blockAccess, int x, int y, int z);

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(int metadata);

	@SideOnly(Side.CLIENT)
	public IIcon getMultipleRenderIcon(IBlockAccess blockAccess, int x, int y, int z, int side, int pass);

	@SideOnly(Side.CLIENT)
	public IIcon getMultipleRenderIcon(int metadata, int side, int pass);

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderColor(IBlockAccess blockAccess, int x, int y, int z, int pass);

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderColor(int metadata, int pass);

}
