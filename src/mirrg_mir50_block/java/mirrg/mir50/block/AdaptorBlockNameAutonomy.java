package mirrg.mir50.block;

import net.minecraft.block.Block;

public class AdaptorBlockNameAutonomy extends AdaptorBlockNameOverriding
{

	public String unlocalizedName;

	public AdaptorBlockNameAutonomy(BlockMir50 owner)
	{
		super(owner);
	}

	public AdaptorBlockNameAutonomy(BlockMir50 owner, String unlocalizedName)
	{
		super(owner);
		this.unlocalizedName = unlocalizedName;
	}

	@Override
	public Block setBlockName(String unlocalizedName)
	{
		this.unlocalizedName = unlocalizedName;
		return owner;
	}

	@Override
	public String getUnlocalizedName()
	{
		return "tile." + unlocalizedName;
	}

}
