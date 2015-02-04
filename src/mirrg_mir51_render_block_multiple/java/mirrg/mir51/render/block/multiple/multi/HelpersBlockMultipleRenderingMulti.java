package mirrg.mir51.render.block.multiple.multi;

import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.multi.ContainerMetaBlock;
import mirrg.mir51.render.block.multiple.HelpersBlockMultipleRendering;
import mirrg.p.virtualclass.IVirtualClass;

public class HelpersBlockMultipleRenderingMulti
{

	public static void make(IVirtualClass virtualClass, BlockMir50 blockMir50, ContainerMetaBlock containerMetaBlock)
	{
		HelpersBlockMultipleRendering.make(virtualClass, blockMir50);

		virtualClass.getVirtualClass().override(new AdaptorBlockMultipleRenderingMulti(blockMir50, containerMetaBlock));
	}

}
