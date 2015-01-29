package mirrg.mir51.example;

import mirrg.mir50.modding.ModuleAbstract;
import mirrg.mir51.loaders.LoaderCreativeTab;
import mirrg.mir51.loaders.LoaderItem;
import net.minecraft.item.Item;

public class ExampleModuleCore extends ModuleAbstract
{

	public static LoaderCreativeTab loaderCreativeTab = new LoaderCreativeTab();
	public static LoaderItem loaderItemSample = new LoaderItem();

	public ExampleModuleCore()
	{

		loaderCreativeTab.init(() -> loaderItemSample.get(), "Sample");
		ExampleApiModuleCore.loaderCreativeTab = loaderCreativeTab;
		add(loaderCreativeTab);

		loaderItemSample.init(() -> {
			Item item = new Item();
			item.setTextureName("minecraft:apple");
			return item;
		}, "sample", ExampleMod.MODID);
		loaderItemSample.setCreativeTab(loaderCreativeTab);
		ExampleApiModuleCore.loaderItemSample = loaderItemSample;
		add(loaderItemSample);

	}

}
