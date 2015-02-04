package mirrg.mir50.block.multi;

import mirrg.mir50.block.BlockMir50;
import net.minecraft.item.ItemStack;

public class AdaptorBlockNameExtraOverriding extends AdaptorBlockNameExtra
{

	protected final IAdaptorBlockNameExtra _super_IAdaptorBlockNameExtra;

	public AdaptorBlockNameExtraOverriding(BlockMir50 owner)
	{
		super(owner);
		_super_IAdaptorBlockNameExtra = owner.getVirtualClass().getCurrentImplementation(IAdaptorBlockNameExtra.class);
	}

	@Override
	public String getLocalizedName(ItemStack itemStack)
	{
		return _super_IAdaptorBlockNameExtra.getLocalizedName(itemStack);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return _super_IAdaptorBlockNameExtra.getUnlocalizedName(itemStack);
	}

}
