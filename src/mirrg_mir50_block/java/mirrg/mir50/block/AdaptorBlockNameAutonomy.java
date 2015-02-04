package mirrg.mir50.block;

import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.block.Block;

public class AdaptorBlockNameAutonomy extends AdaptorBlockNameOverriding
{

	public String unlocalizedName;

	public AdaptorBlockNameAutonomy(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	public AdaptorBlockNameAutonomy(BlockMir50 owner, IVirtualClass superObject, String unlocalizedName)
	{
		super(owner, superObject);
		this.unlocalizedName = unlocalizedName;
	}

	@Override
	public Block setBlockName(String unlocalizedName)
	{
		this.unlocalizedName = unlocalizedName;
		return this.owner;
	}

	@Override
	public String getUnlocalizedName()
	{
		return "tile." + this.unlocalizedName;
	}

}
