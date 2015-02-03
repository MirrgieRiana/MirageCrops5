package mirrg_miragecrops5;

import mirrg.mir40.math.HelpersString;
import mirrg.mir50.block.AdaptorBlockName;
import mirrg.mir50.block.multi.AdaptorBlockHarvestMulti;
import mirrg.mir50.block.multi.AdaptorBlockNameMulti;
import mirrg.mir50.block.multi.AdaptorBlockSubBlocksMulti;
import mirrg.mir50.block.multi.ItemBlockMulti;
import mirrg.mir50.item.AdaptorItemContainerItemCraftingTool;
import mirrg.mir50.item.ItemMir50;
import mirrg.mir50.loaders.LoaderBlock;
import mirrg.mir50.loaders.LoaderCreativeTab;
import mirrg.mir50.loaders.LoaderItem;
import mirrg.mir50.loaders.LoaderOreGenerator;
import mirrg.mir50.loaders.LoaderRecipe;
import mirrg.mir50.modding.ModuleAbstract;
import mirrg.mir50.worldgen.ore.GeneratorOreInChunkBridge;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRendering;
import mirrg.mir51.render.block.multiple.BlockMultipleRendering;
import mirrg.mir51.render.block.multiple.multi.AdaptorBlockMultipleRenderingMulti;
import mirrg.mir51.render.block.multiple.multi.ContainerMetaBlockMultipleRendering;
import mirrg.mir51.render.block.multiple.multi.MetaBlockMultipleRendering;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreAtPoint;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModuleMirageCropsCore extends ModuleAbstract
{

	public LoaderCreativeTab loaderCreativeTab = new LoaderCreativeTab();
	public LoaderBlock loaderBlock = new LoaderBlock();
	public LoaderBlock loaderBlock2 = new LoaderBlock();
	public LoaderBlock loaderBlock3 = new LoaderBlock();
	public LoaderItem loaderItem = new LoaderItem();

	public ContainerMetaBlockMultipleRendering container1;
	public ContainerMetaBlockMultipleRendering container2;

	public static enum EnumCalciteGroup
	{
		calcite,
		magnesite,
		siderite,
		rhodochrosite,
		smithsonite,
		sphaerocobaltite,
		gaspeite,
		otavite,
	}

	public ModuleMirageCropsCore()
	{

		loaderCreativeTab.init(() -> Item.getItemFromBlock(loaderBlock.get()), "MirageCrops 5");
		add(loaderCreativeTab);

		loaderBlock.init(() -> {
			BlockMultipleRendering block = new BlockMultipleRendering(Material.rock);

			block.adaptorBlockMultipleRendering.appendIcon("minecraft:stone");
			block.adaptorBlockMultipleRendering.appendIcon("miragecrops5:oreCalcite", color(255, 205, 59));

			return block;
		}, ItemBlock.class, "blockTest");
		loaderBlock.setCreativeTab(loaderCreativeTab);
		add(loaderBlock);

		loaderBlock2.init(() -> {
			BlockMultipleRendering block = new BlockMultipleRendering(Material.rock);

			{
				ContainerMetaBlockMultipleRendering container = new ContainerMetaBlockMultipleRendering(16);

				block.setAdaptorBlockMultipleRendering(new AdaptorBlockMultipleRenderingMulti(block, container));
				block.adaptorBlockHarvest = new AdaptorBlockHarvestMulti(block, container);
				block.adaptorBlockSubBlocks = new AdaptorBlockSubBlocksMulti(block, container);
				block.adaptorBlockName = new AdaptorBlockNameMulti(block, container);

				for (EnumCalciteGroup value : EnumCalciteGroup.values()) {
					int metaId = value.ordinal();
					MetaBlockMultipleRendering metaBlockMultipleRendering = new MetaBlockMultipleRendering(block, metaId);

					{
						AdaptorBlockMultipleRendering adaptorBlockMultipleRendering = new AdaptorBlockMultipleRendering(block);

						adaptorBlockMultipleRendering.appendIcon("minecraft:stone");
						adaptorBlockMultipleRendering.appendIcon("miragecrops5:ore" + HelpersString.toUpperCaseHead(value.name()));

						metaBlockMultipleRendering.setAdaptorBlockMultipleRendering(adaptorBlockMultipleRendering);
					}

					{
						AdaptorBlockNameMulti adaptorBlockNameMulti = new AdaptorBlockNameMulti(block, container);

						adaptorBlockNameMulti.setBlockName("ore" + HelpersString.toUpperCaseHead(value.name()));

						metaBlockMultipleRendering.adaptorBlockName = adaptorBlockNameMulti;
					}

					container.set(metaId, metaBlockMultipleRendering);
				}

				container1 = container;
			}

			return block;
		}, ItemBlockMulti.class, "oreCalciteGroup");
		loaderBlock2.setItemBlockIniter(item -> {
			((ItemBlockMulti) item).init(container1);
		});
		loaderBlock2.setCreativeTab(loaderCreativeTab);
		add(loaderBlock2);

		loaderBlock3.init(() -> {
			BlockMultipleRendering block = new BlockMultipleRendering(Material.rock);

			{
				ContainerMetaBlockMultipleRendering container = new ContainerMetaBlockMultipleRendering(16);

				block.setAdaptorBlockMultipleRendering(new AdaptorBlockMultipleRenderingMulti(block, container));
				block.adaptorBlockHarvest = new AdaptorBlockHarvestMulti(block, container);
				block.adaptorBlockSubBlocks = new AdaptorBlockSubBlocksMulti(block, container);
				block.adaptorBlockName = new AdaptorBlockNameMulti(block, container);

				for (EnumCalciteGroup value : EnumCalciteGroup.values()) {
					int metaId = value.ordinal();
					MetaBlockMultipleRendering metaBlockMultipleRendering = new MetaBlockMultipleRendering(block, metaId);

					{
						AdaptorBlockMultipleRendering adaptorBlockMultipleRendering = new AdaptorBlockMultipleRendering(block);

						adaptorBlockMultipleRendering.appendIcon("miragecrops5:block" + HelpersString.toUpperCaseHead(value.name()));

						metaBlockMultipleRendering.setAdaptorBlockMultipleRendering(adaptorBlockMultipleRendering);
					}

					{
						AdaptorBlockName adaptorBlockName = new AdaptorBlockName(block);

						adaptorBlockName.setBlockName("block" + HelpersString.toUpperCaseHead(value.name()));

						metaBlockMultipleRendering.adaptorBlockName = adaptorBlockName;
					}

					container.set(metaId, metaBlockMultipleRendering);
				}

				container2 = container;
			}

			return block;
		}, ItemBlockMulti.class, "blockCalciteGroup");
		loaderBlock3.setItemBlockIniter(item -> {
			((ItemBlockMulti) item).init(container2);
		});
		loaderBlock3.setCreativeTab(loaderCreativeTab);
		add(loaderBlock3);

		loaderItem.init(() -> {
			ItemMir50 itemMir50 = new ItemMir50();

			itemMir50.setMaxStackSize(1);
			itemMir50.setTextureName("minecraft:iron_pickaxe");

			itemMir50.setMaxDamage(20 - 1);
			itemMir50.adaptorItemContainerItem = new AdaptorItemContainerItemCraftingTool(itemMir50);

			return itemMir50;
		}, "craftingToolHammerIron", ModMirageCrops.MODID);
		loaderItem.setCreativeTab(loaderCreativeTab);
		add(loaderItem);

		add(new LoaderRecipe(() -> {

			GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(Items.iron_ingot, 1, 0),
				"X",
				"Y",
				'X', new ItemStack(loaderItem.get(), 1, OreDictionary.WILDCARD_VALUE),
				'Y', Blocks.iron_ore));

		})
			.dependsOn(loaderItem));

		add(new LoaderOreGenerator(loader, () -> GeneratorOreInChunkBridge.createFromMinMax(
			20, IGeneratorOreAtPoint.Helpers.fromWorldGenerator(new WorldGenMinable(loaderBlock.get(), 16)), 0, 128)));

	}

	private int color(int r, int g, int b)
	{
		return (r << 16) | (g << 8) | b;
	}

}
