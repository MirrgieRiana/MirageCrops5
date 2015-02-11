package mirrg.mir51.block.multi;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

public class ContainerMetaBlock extends ArrayList<MetaBlock>
{

	public ContainerMetaBlock(int initialCapacity)
	{
		super(initialCapacity);

		for (int i = 0; i < initialCapacity; i++) {
			add(null);
		}
	}

	public MetaBlock get(ItemStack itemStack)
	{
		return get(itemStack.getItemDamage());
	}

	public MetaBlock get(IBlockAccess blockAccess, int x, int y, int z)
	{
		return get(blockAccess.getBlockMetadata(x, y, z));
	}

}
