package mirrg.mir50.block.multi;

import mirrg.mir50.block.AdaptorBlockNameOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.item.ItemStack;

public class AdaptorBlockNameExtra extends AdaptorBlockNameOverriding implements IAdaptorBlockNameExtra
{

	public AdaptorBlockNameExtra(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	@Override
	public String getLocalizedName(ItemStack itemStack)
	{
		return this._super_IAdaptorBlockName.getLocalizedName();
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return this._super_IAdaptorBlockName.getUnlocalizedName();
	}

}
