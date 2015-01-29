package mirrg.mir51.loaders;

import java.util.function.Supplier;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class LoaderCreativeTab extends Loader<CreativeTabs>
{

	protected Supplier<Item> tabIconItem;
	protected String label;

	public void init(Supplier<Item> tabIconItem, String label)
	{
		this.tabIconItem = tabIconItem;
		this.label = label;
	}

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.Created) {
			set(new CreativeTabs(label) {
				@Override
				public Item getTabIconItem()
				{
					return tabIconItem.get();
				}
			});
		}
	}

}
