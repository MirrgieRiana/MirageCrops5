package mirrg.mir52.example;

import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.item.ItemMir50;
import mirrg.mir50.item.adaptors.AdaptorItemIconAutonomy;
import mirrg.mir50.item.adaptors.AdaptorItemNameAutonomy;
import mirrg.mir51.item.multi.AdaptorItemSubItemsMetaItem;
import mirrg.mir51.item.multi.ContainerMetaItem;
import mirrg.mir51.item.multi.HelpersItemMulti;
import mirrg.mir51.item.multi.MetaItem;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.mir51.loaders.LoaderCreativeTab;
import mirrg.mir51.loaders.LoaderItem;
import mirrg.mir51.loaders.LoaderOreDictionary;
import mirrg.mir51.loaders.LoaderRecipe;
import mirrg.mir51.modding.ModuleAbstract;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRenderingAutonomy;
import mirrg.mir51.render.block.multiple.HelpersBlockMultipleRendering;
import mirrg.mir51.render.block.multiple.RenderBlockMultipleRendering;
import net.minecraft.block.material.Material;
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
	public static LoaderBlock loaderBlockSample2 = new LoaderBlock();

	public ExampleModuleCore()
	{

		loaderCreativeTab.init(() -> loaderItemSample.get(), "Sample");
		ExampleApiModuleCore.loaderCreativeTab = loaderCreativeTab;
		add(loaderCreativeTab);

		loaderItemSample.init(() -> {
			ItemMir50 itemMir50 = new ItemMir50();

			itemMir50.setUnlocalizedName("sample");
			itemMir50.setTextureName("minecraft:apple");

			{
				ContainerMetaItem metaItemContainer = new ContainerMetaItem(100);
				HelpersItemMulti.make(itemMir50, itemMir50, metaItemContainer, true);

				{
					int metaId = 0;

					MetaItem metaItem = new MetaItem(itemMir50, metaId);

					metaItemContainer.set(metaId, metaItem);
				}

				{
					int metaId = 1;

					MetaItem metaItem = new MetaItem(itemMir50, metaId);

					metaItem.virtualClass.override(new AdaptorItemSubItemsMetaItem(itemMir50, metaItem, metaItem));

					metaItem.virtualClass.override(new AdaptorItemIconAutonomy(itemMir50, metaItem, "minecraft:arrow"));

					metaItemContainer.set(metaId, metaItem);
				}

				{
					int metaId = 2;

					MetaItem metaItem = new MetaItem(itemMir50, metaId);

					metaItem.virtualClass.override(new AdaptorItemSubItemsMetaItem(itemMir50, metaItem, metaItem));

					metaItem.virtualClass.override(new AdaptorItemNameAutonomy(itemMir50, metaItem, "sample2"));

					metaItemContainer.set(metaId, metaItem);
				}

				{
					int metaId = 3;
				}

				{
					int metaId = 4;

					MetaItem metaItem = new MetaItem(itemMir50, metaId);

					metaItem.virtualClass.override(new AdaptorItemSubItemsMetaItem(itemMir50, metaItem, metaItem));

					metaItem.virtualClass.override(new AdaptorItemNameAutonomy(itemMir50, metaItem, "sample4"));

					{
						AdaptorItemIconAutonomy adaptorItemIconAutonomy = new AdaptorItemIconAutonomy(itemMir50, metaItem, "minecraft:gold_ingot");
						adaptorItemIconAutonomy.appendIcon("minecraft:fish_cod_raw");
						metaItem.virtualClass.override(adaptorItemIconAutonomy);
					}

					metaItemContainer.set(metaId, metaItem);
				}

				{
					int metaId = 5;

					MetaItem metaItem = new MetaItem(itemMir50, metaId);

					metaItem.virtualClass.override(new AdaptorItemSubItemsMetaItem(itemMir50, metaItem, metaItem));

					metaItem.virtualClass.override(new AdaptorItemNameAutonomy(itemMir50, metaItem, "sample4"));

					{
						AdaptorItemIconAutonomy adaptorItemIconAutonomy = new AdaptorItemIconAutonomy(itemMir50, metaItem);
						adaptorItemIconAutonomy.appendIcon("minecraft:iron_ingot", 0x22cc00);
						metaItem.virtualClass.override(adaptorItemIconAutonomy);
					}

					metaItemContainer.set(metaId, metaItem);
				}

			}

			return itemMir50;
		}, "sampleItem", ExampleMod.MODID);
		loaderItemSample.setCreativeTab(loaderCreativeTab);
		ExampleApiModuleCore.loaderItemSample = loaderItemSample;
		add(loaderItemSample);

		{
			LoaderOreDictionary loaderOreDictionary = new LoaderOreDictionary();
			loaderOreDictionary.init(() -> {
				OreDictionary.registerOre("sampleItem", loaderItemSample.get());
			});
			loaderOreDictionary.dependsOn(loaderItemSample);
			add(loaderOreDictionary);
		}

		loaderBlockSample.init(() -> {
			BlockMir50 blockMir50 = new BlockMir50(Material.rock);

			blockMir50.setBlockName("sampleBlock");
			//setBlockTextureName("minecraft:grass_top");
			blockMir50.setBlockTextureName("minecraft:double_plant_sunflower_front");

			return blockMir50;
		}, ItemBlock.class, "sampleBlock");
		loaderBlockSample.setCreativeTab(loaderCreativeTab);
		ExampleApiModuleCore.loaderBlockSample = loaderBlockSample;
		add(loaderBlockSample);

		loaderBlockSample2.init(() -> {
			BlockMir50 blockMir50 = new BlockMir50(Material.rock);

			blockMir50.setBlockName("sampleBlock2");
			blockMir50.setBlockTextureName("minecraft:stone");

			AdaptorBlockMultipleRenderingAutonomy a = HelpersBlockMultipleRendering.makeAutonomy(blockMir50, blockMir50);

			a.appendIcon("minecraft:grass_top", 0x739627);
			a.appendIcon("minecraft:double_plant_sunflower_front", 0x893472);

			//setBlockTextureName("minecraft:grass_top");
			//blockMir50.setBlockTextureName("minecraft:double_plant_sunflower_front");

			return blockMir50;
		}, ItemBlock.class, "sampleBlock2");
		loaderBlockSample2.setCreativeTab(loaderCreativeTab);
		//ExampleApiModuleCore.loaderBlockSample = loaderBlockSample;
		add(loaderBlockSample2);

		add(RenderBlockMultipleRendering.loader);

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
