package mirrg.mir50.block;

import net.minecraft.block.Block;

public interface IAdaptorBlockName
{

	public Block setBlockName(String unlocalizedName);

	public String getLocalizedName();

	public String getUnlocalizedName();

}
