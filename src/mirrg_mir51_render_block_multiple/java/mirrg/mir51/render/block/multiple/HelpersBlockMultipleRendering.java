package mirrg.mir51.render.block.multiple;

import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;

public class HelpersBlockMultipleRendering
{

	public static void make(IVirtualClass virtualClass, BlockMir50 blockMir50)
	{
		virtualClass.getVirtualClass().override(new AdaptorBlockRenderMultipleRendering(blockMir50, virtualClass));
		virtualClass.getVirtualClass().register(IBlockMultipleRendering.class, new AdaptorBlockMultipleRendering(blockMir50));
	}

	public static AdaptorBlockMultipleRenderingAutonomy makeAutonomy(IVirtualClass virtualClass, BlockMir50 blockMir50)
	{
		make(virtualClass, blockMir50);

		AdaptorBlockMultipleRenderingAutonomy a = new AdaptorBlockMultipleRenderingAutonomy(blockMir50, virtualClass);
		virtualClass.getVirtualClass().override(a);
		return a;
	}

}