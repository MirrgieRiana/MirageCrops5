package mirrg.mir51.render.block.multiple;

import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;

public class HelpersBlockMultipleRendering
{

	public static AdaptorBlockMultipleRenderingAutonomy make(IVirtualClass virtualClass, BlockMir50 blockMir50)
	{
		virtualClass.getVirtualClass().register(IBlockMultipleRendering.class, new AdaptorBlockMultipleRendering(blockMir50));

		AdaptorBlockMultipleRenderingAutonomy a = new AdaptorBlockMultipleRenderingAutonomy(blockMir50);
		virtualClass.getVirtualClass().override(a);
		virtualClass.getVirtualClass().override(new AdaptorBlockRenderMultipleRendering(blockMir50));

		return a;
	}

}
