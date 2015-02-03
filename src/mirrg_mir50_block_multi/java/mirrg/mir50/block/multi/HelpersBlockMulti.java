package mirrg.mir50.block.multi;

import mirrg.mir50.block.BlockMir50;

public class HelpersBlockMulti
{

	public static void makeBlockMulti(BlockMir50 blockMir50, ContainerMetaBlock containerMetaBlock)
	{
		blockMir50.adaptorBlockSubBlocks = new AdaptorBlockSubBlocksMulti(blockMir50, containerMetaBlock);
		blockMir50.adaptorBlockIcon = new AdaptorBlockIconMulti(blockMir50, containerMetaBlock);
		blockMir50.adaptorBlockHarvest = new AdaptorBlockHarvestMulti(blockMir50, containerMetaBlock);
	}

}
