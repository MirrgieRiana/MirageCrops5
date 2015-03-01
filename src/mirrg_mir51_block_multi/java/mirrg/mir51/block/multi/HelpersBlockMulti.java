package mirrg.mir51.block.multi;

import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.adaptors.AdaptorBlockNameExtra;
import mirrg.mir50.block.adaptors.IAdaptorBlockNameExtra;
import mirrg.p.virtualclass.IVirtualClass;

public class HelpersBlockMulti
{

	public static void make(IVirtualClass virtualClass, BlockMir50 blockMir50, ContainerMetaBlock containerMetaBlock)
	{
		virtualClass.getVirtualClass().override(new AdaptorBlockSubBlocksMulti(blockMir50, virtualClass, containerMetaBlock));
		virtualClass.getVirtualClass().override(new AdaptorBlockIconMulti(blockMir50, virtualClass, containerMetaBlock));
		virtualClass.getVirtualClass().override(new AdaptorBlockHarvestMulti(blockMir50, virtualClass, containerMetaBlock));
		virtualClass.getVirtualClass().register(IAdaptorBlockNameExtra.class, new AdaptorBlockNameExtra(blockMir50, virtualClass));
		virtualClass.getVirtualClass().override(new AdaptorBlockNameExtraMulti(blockMir50, virtualClass, containerMetaBlock));
		virtualClass.getVirtualClass().override(new AdaptorBlockTileEntityMulti(blockMir50, virtualClass, containerMetaBlock));
		virtualClass.getVirtualClass().override(new AdaptorBlockEventsMulti(blockMir50, virtualClass, containerMetaBlock));
	}

}
