package mirrg.mir50.block;

import net.minecraft.block.Block;

public class AdaptorBlockNameOverriding extends AdaptorBlockName
{

	protected final IAdaptorBlockName _super_IAdaptorBlockName;

	public AdaptorBlockNameOverriding(BlockMir50 owner)
	{
		super(owner);
		_super_IAdaptorBlockName = owner.getVirtualClass().getCurrentImplementation(IAdaptorBlockName.class);
	}

	@Override
	public Block setBlockName(String unlocalizedName)
	{
		return _super_IAdaptorBlockName.setBlockName(unlocalizedName);
	}

	@Override
	public String getLocalizedName()
	{
		return _super_IAdaptorBlockName.getLocalizedName();
	}

	@Override
	public String getUnlocalizedName()
	{
		return _super_IAdaptorBlockName.getUnlocalizedName();
	}

}
