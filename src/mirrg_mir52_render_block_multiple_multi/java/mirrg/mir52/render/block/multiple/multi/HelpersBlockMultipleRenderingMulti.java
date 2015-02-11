package mirrg.mir52.render.block.multiple.multi;

import mirrg.mir50.block.BlockMir50;
import mirrg.mir51.block.multi.ContainerMetaBlock;
import mirrg.p.virtualclass.IVirtualClass;

public class HelpersBlockMultipleRenderingMulti
{

	public static void make(IVirtualClass virtualClass, BlockMir50 blockMir50, ContainerMetaBlock containerMetaBlock)
	{
		virtualClass.getVirtualClass().override(new AdaptorBlockMultipleRenderingMulti(blockMir50, virtualClass, containerMetaBlock));
	}

}
