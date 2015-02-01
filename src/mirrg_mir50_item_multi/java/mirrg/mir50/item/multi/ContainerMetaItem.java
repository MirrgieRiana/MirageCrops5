package mirrg.mir50.item.multi;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class ContainerMetaItem extends ArrayList<MetaItem>
{

	public ContainerMetaItem(int initialCapacity)
	{
		super(initialCapacity);

		for (int i = 0; i < initialCapacity; i++) {
			add(null);
		}
	}

	public MetaItem get(ItemStack itemStack)
	{
		return get(itemStack.getItemDamage());
	}

}
