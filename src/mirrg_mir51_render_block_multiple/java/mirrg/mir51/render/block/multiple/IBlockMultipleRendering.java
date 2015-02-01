package mirrg.mir51.render.block.multiple;

import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IBlockMultipleRendering
{

	@SideOnly(Side.CLIENT)
	public void setMultipleRendering(boolean isInMultipleRendering);

	@SideOnly(Side.CLIENT)
	public void setMultipleRenderPass(int pass);

	//

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(IBlockAccess blockAccess, int x, int y, int z);

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(int metadata);

	@SideOnly(Side.CLIENT)
	public IIcon getMultipleRenderIcon(IBlockAccess blockAccess, int x, int y, int z, int side, int pass);

	@SideOnly(Side.CLIENT)
	public IIcon getMultipleRenderIcon(int side, int metadata, int pass);

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderColor(IBlockAccess blockAccess, int x, int y, int z, int pass);

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderColor(int metadata, int pass);

}
