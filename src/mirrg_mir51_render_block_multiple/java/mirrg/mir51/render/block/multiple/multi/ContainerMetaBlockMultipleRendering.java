package mirrg.mir51.render.block.multiple.multi;

import mirrg.mir50.block.multi.ContainerMetaBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

public class ContainerMetaBlockMultipleRendering extends ContainerMetaBlock
{

	public ContainerMetaBlockMultipleRendering(int initialCapacity)
	{
		super(initialCapacity);

		for (int i = 0; i < initialCapacity; i++) {
			add(null);
		}
	}

	public MetaBlockMultipleRendering get(int index)
	{
		return (MetaBlockMultipleRendering) super.get(index);
	}

	public MetaBlockMultipleRendering get(ItemStack itemStack)
	{
		return get(itemStack.getItemDamage());
	}

	public MetaBlockMultipleRendering get(IBlockAccess blockAccess, int x, int y, int z)
	{
		return get(blockAccess.getBlockMetadata(x, y, z));
	}

}
