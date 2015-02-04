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
		this.owner.setField_unlocalizedName(unlocalizedName);
		return this.owner;
	}

	@Override
	public String getLocalizedName()
	{
		return StatCollector.translateToLocal(owner.accessor_IAdaptorBlockName.get().getUnlocalizedName() + ".name");
	}

	@Override
	public String getUnlocalizedName()
	{
		return "tile." + this.owner.getField_unlocalizedName();
	}

}
