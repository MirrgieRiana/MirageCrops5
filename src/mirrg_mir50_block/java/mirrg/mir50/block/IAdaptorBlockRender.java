package mirrg.mir50.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IAdaptorBlockRender
{

	@SideOnly(Side.CLIENT)
	public int getRenderType();

	public boolean isOpaqueCube();

	public boolean renderAsNormalBlock();

}
