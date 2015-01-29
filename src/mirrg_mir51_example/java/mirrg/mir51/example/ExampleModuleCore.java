package mirrg.mir51.example;

import mirrg.mir50.item.AdaptorItemIconAutonomy;
import mirrg.mir50.item.AdaptorItemNameAutonomy;
import mirrg.mir50.item.ItemMir50;
import mirrg.mir50.modding.ModuleAbstract;
import mirrg.mir51.item.multi.ContainerMetaItem;
import mirrg.mir51.item.multi.HelpersItemMulti;
import mirrg.mir51.item.multi.MetaItem;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.mir51.loaders.LoaderCreativeTab;
import mirrg.mir51.loaders.LoaderItem;
import mirrg.mir51.loaders.LoaderOreDictionary;
import mirrg.mir51.loaders.LoaderRecipe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ExampleModuleCore extends ModuleAbstract
{

	public static LoaderCreativeTab loaderCreativeTab = new LoaderCreativeTab();
	public static LoaderItem loaderItemSample = new LoaderItem();
	public static LoaderBlock loaderBlockSample = new LoaderBlock();

	public ExampleModuleCore()
	{

		loaderCreativeTab.init(() -> loaderItemSample.get(), "Sample");
		ExampleApiModuleCore.loaderCreativeTab = loaderCreativeTab;
		add(loaderCreativeTab);

		loaderItemSample.init(() -> {
			ItemMir50 itemSample = new ItemMir50();

			itemSample.setUnlocalizedName("sample");
			itemSample.setTextureName("minecraft:apple");

			{
				ContainerMetaItem metaItemContainer = new ContainerMetaItem(100);

				HelpersItemMulti.makeItemMulti(itemSample, metaItemContainer);

				{
					int metaId = 1;

					MetaItem metaItem = new MetaItem(itemSample, metaId);

					metaItem.adaptorItemIcon = new AdaptorItemIconAutonomy(itemSample, "minecraft:arrow");

					metaItemContainer.set(metaId, metaItem);
				}

				{
					int metaId = 2;

					MetaItem metaItem = new MetaItem(itemSample, metaId);

					metaItem.adaptorItemName = new AdaptorItemNameAutonomy(itemSample, "sample2");

					metaItemContainer.set(metaId, metaItem);
				}

			}

			return itemSample;
		}, "sampleItem", ExampleMod.MODID);
		loaderItemSample.setCreativeTab(loaderCreativeTab);
		{
			LoaderOreDictionary loaderOreDictionary = new LoaderOreDictionary();
			loaderOreDictionary.init(() -> {
				OreDictionary.registerOre("sampleItem", loaderItemSample.get());
			});
			loaderOreDictionary.dependsOn(loaderItemSample);
			add(loaderOreDictionary);
		}
		ExampleApiModuleCore.loaderItemSample = loaderItemSample;
		add(loaderItemSample);

		loaderBlockSample.init(() -> new BlockSample(), ItemBlock.class, "sampleBlock");
		loaderBlockSample.setCreativeTab(loaderCreativeTab);
		ExampleApiModuleCore.loaderBlockSample = loaderBlockSample;
		add(loaderBlockSample);

		{
			LoaderRecipe loaderRecipe = new LoaderRecipe();
			loaderRecipe.init(() -> {
				GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(loaderItemSample.get(), 1, 0),
					"X X",
					"XXX",
					'X', "ingotGold"));
				GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(loaderItemSample.get(), 1, 1),
					"X X",
					"XXX",
					'X', "sampleItem"));
			});
			loaderRecipe.dependsOn(loaderItemSample);
			add(loaderRecipe);
		}

	}

}
