package mirrg.mir51.render.block.multiple.multi;

import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.multi.MetaBlock;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRendering;

public class MetaBlockMultipleRendering extends MetaBlock
{

	protected BlockMir50 blockMir50;
	protected int meta;

	public MetaBlockMultipleRendering(BlockMir50 blockMir50, int meta)
	{
		super(blockMir50, meta);
		adaptorBlockIcon = adaptorBlockMultipleRendering;
	}

	public AdaptorBlockMultipleRendering adaptorBlockMultipleRendering;

	public void setAdaptorBlockMultipleRendering(AdaptorBlockMultipleRendering adaptorBlockMultipleRendering)
	{
		this.adaptorBlockMultipleRendering = adaptorBlockMultipleRendering;
		adaptorBlockIcon = adaptorBlockMultipleRendering;
	}

}
