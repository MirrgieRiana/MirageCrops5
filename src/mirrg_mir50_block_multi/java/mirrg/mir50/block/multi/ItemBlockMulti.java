package mirrg.mir50.block.multi;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMulti extends ItemBlock
{

	public ContainerMetaBlock containerMetaBlock;

	@SuppressWarnings("unchecked")
	public ItemBlockMulti(Block block)
	{
		super(block);
		setHasSubtypes(true);
	}

	public void init(ContainerMetaBlock containerMetaBlock)
	{
		this.containerMetaBlock = containerMetaBlock;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		MetaBlock metaBlock = containerMetaBlock.get(itemStack);
		if (metaBlock == null || metaBlock.adaptorBlockName == null) return super.getUnlocalizedName(itemStack);
		if (metaBlock.adaptorBlockName instanceof AdaptorBlockNameMulti) {
			return ((AdaptorBlockNameMulti) metaBlock.adaptorBlockName).getUnlocalizedName(itemStack);
		} else {
			return metaBlock.adaptorBlockName.getUnlocalizedName();
		}
	}

	@Override
	public int getMetadata(int metaId)
	{
		return metaId;
	}

}
