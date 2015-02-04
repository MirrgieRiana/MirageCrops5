package mirrg.mir50.block.multi;

import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.item.ItemStack;

public class AdaptorBlockNameExtraOverriding extends AdaptorBlockNameExtra
{

	protected final IAdaptorBlockNameExtra _super_IAdaptorBlockNameExtra;

	public AdaptorBlockNameExtraOverriding(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
		this._super_IAdaptorBlockNameExtra = superObject.getVirtualClass().getCurrentImplementation(IAdaptorBlockNameExtra.class);
	}

	@Override
	public String getLocalizedName(ItemStack itemStack)
	{
		return this._super_IAdaptorBlockNameExtra.getLocalizedName(itemStack);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return this._super_IAdaptorBlockNameExtra.getUnlocalizedName(itemStack);
	}

}
