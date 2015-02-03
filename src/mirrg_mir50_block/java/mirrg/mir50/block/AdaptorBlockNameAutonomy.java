package mirrg.mir50.block;

import net.minecraft.block.Block;
import net.minecraft.util.StatCollector;

public class AdaptorBlockNameAutonomy extends AdaptorBlockName
{

	public String unlocalizedName;

	public AdaptorBlockNameAutonomy(BlockMir50 blockMir50)
	{
		super(blockMir50);
	}

	public AdaptorBlockNameAutonomy(BlockMir50 blockMir50, String unlocalizedName)
	{
		super(blockMir50);
		this.unlocalizedName = unlocalizedName;
	}

	public Block setBlockName(String unlocalizedName)
	{
		this.unlocalizedName = unlocalizedName;
		return blockMir50;
	}

	public String getLocalizedName()
	{
		return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
	}

	public String getUnlocalizedName()
	{
		return "tile." + unlocalizedName;
	}

}
