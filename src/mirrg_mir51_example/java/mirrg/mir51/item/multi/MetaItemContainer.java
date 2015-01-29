package mirrg.mir51.item.multi;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class MetaItemContainer extends ArrayList<MetaItem>
{

	public MetaItemContainer(int initialCapacity)
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
