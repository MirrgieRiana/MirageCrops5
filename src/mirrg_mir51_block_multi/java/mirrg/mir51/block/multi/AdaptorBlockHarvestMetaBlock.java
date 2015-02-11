package mirrg.mir51.block.multi;

import mirrg.mir50.block.AdaptorBlockHarvestOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;

public class AdaptorBlockHarvestMetaBlock extends AdaptorBlockHarvestOverriding
{

	public AdaptorBlockHarvestMetaBlock(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	@Override
	public int damageDropped(int damage)
	{
		return damage;
	}

}
