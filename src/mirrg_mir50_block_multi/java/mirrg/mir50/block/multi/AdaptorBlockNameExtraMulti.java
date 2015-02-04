package mirrg.mir50.block.multi;

import mirrg.mir50.block.BlockMir50;
import net.minecraft.item.ItemStack;

public class AdaptorBlockNameExtraMulti extends AdaptorBlockNameExtraOverriding
{

	protected ContainerMetaBlock containerMetaBlock;

	public AdaptorBlockNameExtraMulti(BlockMir50 owner, ContainerMetaBlock containerMetaBlock)
	{
		super(owner);
		this.containerMetaBlock = containerMetaBlock;
	}

	@Override
	public String getLocalizedName(ItemStack itemStack)
	{
		MetaBlock metaBlock = containerMetaBlock.get(itemStack);
		if (metaBlock == null) return super.getLocalizedName(itemStack);
		return metaBlock.virtualClass.cast(IAdaptorBlockNameExtra.class).get().getLocalizedName(itemStack);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		MetaBlock metaBlock = containerMetaBlock.get(itemStack);
		if (metaBlock == null) return super.getUnlocalizedName(itemStack);
		return metaBlock.virtualClass.cast(IAdaptorBlockNameExtra.class).get().getUnlocalizedName(itemStack);
	}

}
