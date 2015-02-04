package mirrg.mir50.block;

import mirrg.p.adaptor.Adaptor;
import net.minecraft.block.Block;
import net.minecraft.util.StatCollector;

public class AdaptorBlockName extends Adaptor<BlockMir50> implements IAdaptorBlockName
{

	public AdaptorBlockName(BlockMir50 owner)
	{
		super(owner);
	}

	@Override
	public Block setBlockName(String unlocalizedName)
	{
		owner.setField_unlocalizedName(unlocalizedName);
		return owner;
	}

	@Override
	public String getLocalizedName()
	{
		return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
	}

	@Override
	public String getUnlocalizedName()
	{
		return "tile." + owner.getField_unlocalizedName();
	}

}
