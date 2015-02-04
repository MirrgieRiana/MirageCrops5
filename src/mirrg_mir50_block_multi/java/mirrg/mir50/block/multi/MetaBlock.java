package mirrg.mir50.block.multi;

import mirrg.mir50.block.AdaptorBlockHarvest;
import mirrg.mir50.block.AdaptorBlockIcon;
import mirrg.mir50.block.AdaptorBlockName;
import mirrg.mir50.block.AdaptorBlockSubBlocks;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.IAdaptorBlockHarvest;
import mirrg.mir50.block.IAdaptorBlockIcon;
import mirrg.mir50.block.IAdaptorBlockName;
import mirrg.mir50.block.IAdaptorBlockSubBlocks;
import mirrg.p.virtualclass.IVirtualClass;
import mirrg.p.virtualclass.IVirtualImplementationAccessor;
import mirrg.p.virtualclass.VirtualClass;

public class MetaBlock implements IVirtualClass
{

	public final VirtualClass virtualClass = new VirtualClass(this);

	@Override
	public final VirtualClass getVirtualClass()
	{
		return virtualClass;
	}

	protected BlockMir50 blockMir50;
	protected int meta;

	public MetaBlock(BlockMir50 blockMir50, int meta)
	{
		this.blockMir50 = blockMir50;
		this.meta = meta;

		virtualClass.register(IAdaptorBlockSubBlocks.class, new AdaptorBlockSubBlocks(blockMir50));
		accessor_IAdaptorBlockSubBlocks = virtualClass.cast(IAdaptorBlockSubBlocks.class);
		virtualClass.register(IAdaptorBlockIcon.class, new AdaptorBlockIcon(blockMir50));
		accessor_IAdaptorBlockIcon = virtualClass.cast(IAdaptorBlockIcon.class);
		virtualClass.register(IAdaptorBlockHarvest.class, new AdaptorBlockHarvest(blockMir50));
		accessor_IAdaptorBlockHarvest = virtualClass.cast(IAdaptorBlockHarvest.class);
		virtualClass.register(IAdaptorBlockName.class, new AdaptorBlockName(blockMir50));
		accessor_IAdaptorBlockName = virtualClass.cast(IAdaptorBlockName.class);

	}

	public final IVirtualImplementationAccessor<IAdaptorBlockSubBlocks> accessor_IAdaptorBlockSubBlocks;

	public final IVirtualImplementationAccessor<IAdaptorBlockIcon> accessor_IAdaptorBlockIcon;

	public final IVirtualImplementationAccessor<IAdaptorBlockHarvest> accessor_IAdaptorBlockHarvest;

	public final IVirtualImplementationAccessor<IAdaptorBlockName> accessor_IAdaptorBlockName;

}
