package mirrg.mir51.loaders;

import java.util.function.Supplier;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class LoaderItem extends Loader<Item>
{

	protected Supplier<Item> supplierItem;
	protected String name;
	protected String modId;
	protected LoaderCreativeTab loaderCreativeTab;

	public void init(Supplier<Item> supplierItem, String name, String modId)
	{
		this.supplierItem = supplierItem;
		this.name = name;
		this.modId = modId;
	}

	public void setCreativeTab(LoaderCreativeTab loaderCreativeTab)
	{
		this.loaderCreativeTab = loaderCreativeTab;
	}

	@Override
	protected void loadDependancies(EnumLoadEventTiming loadEvent)
	{
		super.loadDependancies(loadEvent);
		if (loaderCreativeTab != null) loaderCreativeTab.load(loadEvent);
	}

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.PreInit) {
			Item item = supplierItem.get();
			if (loaderCreativeTab != null) item.setCreativeTab(loaderCreativeTab.get());
			GameRegistry.registerItem(item, name, modId);
			set(item);
		}
	}

}
