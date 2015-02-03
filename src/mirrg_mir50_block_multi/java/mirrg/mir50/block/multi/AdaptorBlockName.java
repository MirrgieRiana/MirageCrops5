package mirrg.mir50.block.multi;

import mirrg.mir50.block.AdaptorBlock;
import mirrg.mir50.block.BlockMir50;
import net.minecraft.item.ItemStack;

public class AdaptorBlockName extends AdaptorBlock
{

	public AdaptorBlockName(BlockMir50 blockMir50)
	{
		super(blockMir50);
	}

	public String getUnlocalizedName(ItemStack itemStack)
	{
		return blockMir50.getUnlocalizedName();
	}

}
