package mirrg.mir50.block.multi;

import mirrg.mir50.block.AdaptorBlockIcon;
import mirrg.mir50.block.AdaptorBlockName;
import mirrg.mir50.block.AdaptorBlockSubBlocks;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.IAdaptorBlockHarvest;

public class MetaBlock
{

	protected BlockMir50 blockMir50;
	protected int meta;

	public MetaBlock(BlockMir50 blockMir50, int meta)
	{
		this.blockMir50 = blockMir50;
		this.meta = meta;
	}

	public AdaptorBlockSubBlocks adaptorBlockSubBlocks;

	public AdaptorBlockIcon adaptorBlockIcon;

	public IAdaptorBlockHarvest adaptorBlockHarvest;

	public AdaptorBlockName adaptorBlockName;

}
