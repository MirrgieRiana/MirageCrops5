package mirrg.mir51.render.block.multiple;

import mirrg.mir50.block.AdaptorBlockRenderOverriding;
import mirrg.mir50.block.BlockMir50;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockRenderMultipleRendering extends AdaptorBlockRenderOverriding
{

	public AdaptorBlockRenderMultipleRendering(BlockMir50 owner)
	{
		super(owner);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return RenderBlockMultipleRendering.loader.get().getRenderId();
	}

}
