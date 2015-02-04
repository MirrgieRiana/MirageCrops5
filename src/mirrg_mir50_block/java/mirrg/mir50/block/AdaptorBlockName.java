package mirrg.mir50.block;

import net.minecraft.block.Block;
import net.minecraft.util.StatCollector;

public class AdaptorBlockName extends AdaptorBlock implements IAdaptorBlockName
{

	public AdaptorBlockName(BlockMir50 blockMir50)
	{
		super(blockMir50);
	}

	public Block setBlockName(String unlocalizedName)
	{
		blockMir50.setField_unlocalizedName(unlocalizedName);
		return blockMir50;
	}

	public String getLocalizedName()
	{
		return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
	}

	public String getUnlocalizedName()
	{
		return "tile." + blockMir50.getField_unlocalizedName();
	}

}
