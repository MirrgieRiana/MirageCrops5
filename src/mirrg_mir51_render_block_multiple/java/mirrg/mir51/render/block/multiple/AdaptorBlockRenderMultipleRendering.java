package mirrg.mir51.render.block.multiple;

import mirrg.mir50.block.AdaptorBlockRenderOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockRenderMultipleRendering extends AdaptorBlockRenderOverriding
{

	public AdaptorBlockRenderMultipleRendering(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return HelpersBlockMultipleRendering.loader.get().getRenderId();
	}

}
