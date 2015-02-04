package mirrg.mir50.block;

import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.block.Block;

public class AdaptorBlockNameOverriding extends AdaptorBlockName
{

	protected final IAdaptorBlockName _super_IAdaptorBlockName;

	public AdaptorBlockNameOverriding(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner);
		this._super_IAdaptorBlockName = superObject.getVirtualClass().getCurrentImplementation(IAdaptorBlockName.class);
	}

	@Override
	public Block setBlockName(String unlocalizedName)
	{
		return this._super_IAdaptorBlockName.setBlockName(unlocalizedName);
	}

	@Override
	public String getLocalizedName()
	{
		return this._super_IAdaptorBlockName.getLocalizedName();
	}

	@Override
	public String getUnlocalizedName()
	{
		return this._super_IAdaptorBlockName.getUnlocalizedName();
	}

}
