package mirrg.mir50.block.multi;

import mirrg.mir50.block.AdaptorBlockName;
import mirrg.mir50.block.BlockMir50;
import net.minecraft.item.ItemStack;

public class AdaptorBlockNameMulti extends AdaptorBlockName
{

	protected ContainerMetaBlock containerMetaBlock;

	public AdaptorBlockNameMulti(BlockMir50 blockMir50, ContainerMetaBlock containerMetaBlock)
	{
		super(blockMir50);
		this.containerMetaBlock = containerMetaBlock;
	}

	public String getLocalizedName(ItemStack itemStack)
	{
		MetaBlock metaBlock = containerMetaBlock.get(itemStack);
		if (metaBlock == null || metaBlock.adaptorBlockName == null) return super.getLocalizedName();
		return metaBlock.adaptorBlockName.getLocalizedName();
	}

	public String getUnlocalizedName(ItemStack itemStack)
	{
		MetaBlock metaBlock = containerMetaBlock.get(itemStack);
		if (metaBlock == null || metaBlock.adaptorBlockName == null) return super.getUnlocalizedName();
		return metaBlock.adaptorBlockName.getUnlocalizedName();
	}

}
