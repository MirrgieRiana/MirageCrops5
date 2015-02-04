package mirrg_miragecrops5;

import java.util.function.Supplier;

import mirrg.mir40.math.HelpersString;
import mirrg.mir50.block.AdaptorBlockNameAutonomy;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.multi.AdaptorBlockHarvestMetaBlock;
import mirrg.mir50.block.multi.AdaptorBlockNameExtra;
import mirrg.mir50.block.multi.AdaptorBlockSubBlocksMetaBlock;
import mirrg.mir50.block.multi.ContainerMetaBlock;
import mirrg.mir50.block.multi.HelpersBlockMulti;
import mirrg.mir50.block.multi.IAdaptorBlockNameExtra;
import mirrg.mir50.block.multi.ItemBlockMulti;
import mirrg.mir50.block.multi.MetaBlock;
import mirrg.mir50.item.AdaptorItemContainerItemCraftingTool;
import mirrg.mir50.item.ItemMir50;
import mirrg.mir50.loaders.LoaderBlock;
import mirrg.mir50.loaders.LoaderCreativeTab;
import mirrg.mir50.loaders.LoaderItem;
import mirrg.mir50.loaders.LoaderOreGenerator;
import mirrg.mir50.loaders.LoaderRecipe;
import mirrg.mir50.modding.ModuleAbstract;
import mirrg.mir50.worldgen.ore.FilterBiome;
import mirrg.mir50.worldgen.ore.GeneratorOreInChunkBridge;
import mirrg.mir50.worldgen.ore.WorldGeneratorMinableExtra;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRenderingAutonomy;
import mirrg.mir51.render.block.multiple.HelpersBlockMultipleRendering;
import mirrg.mir51.render.block.multiple.multi.HelpersBlockMultipleRenderingMulti;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreAtPoint;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreInChunk;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModuleMirageCropsCore extends ModuleAbstract
{

	public LoaderCreativeTab loaderCreativeTab = new LoaderCreativeTab();
	//public LoaderBlock loaderBlock_blockTest = new LoaderBlock();
	public LoaderBlock loaderBlock_oreCalciteGroup = new LoaderBlock();
	public LoaderBlock loaderBlock_blockCalciteGroup = new LoaderBlock();
	public LoaderItem loaderItem_craftingToolHammerIron = new LoaderItem();

	public ContainerMetaBlock container1;
	public ContainerMetaBlock container2;

	public static enum EnumCalciteGroup
	{
		calcite,
		magnesite,
		siderite,
		smithsonite,
		rhodochrosite,
		sphaerocobaltite,
		gaspeite,
		otavite,

		apatite,
		fluorite,

		spinachium,
		miragium,

	}

	public ModuleMirageCropsCore()
	{

		loaderCreativeTab.init(() -> Item.getItemFromBlock(loaderBlock_oreCalciteGroup.get()), "miragecrops5_core");
		add(loaderCreativeTab);

		/*
		loaderBlock_blockTest.init(() -> {
			BlockMir50 block = new BlockMir50(Material.rock);

			block.adaptorBlockMultipleRendering.appendIcon("minecraft:stone");
			block.adaptorBlockMultipleRendering.appendIcon("miragecrops5:oreCalcite", color(255, 205, 59));

			return block;
		}, ItemBlock.class, "blockTest");
		loaderBlock_blockTest.setCreativeTab(loaderCreativeTab);
		add(loaderBlock_blockTest);
		*/

		loaderBlock_oreCalciteGroup.init(() -> {
			BlockMir50 blockMir50 = new BlockMir50(Material.rock);

			{
				ContainerMetaBlock containerMetaBlock = new ContainerMetaBlock(16);
				HelpersBlockMulti.make(blockMir50, blockMir50, containerMetaBlock);
				HelpersBlockMultipleRendering.make(blockMir50, blockMir50);
				HelpersBlockMultipleRenderingMulti.make(blockMir50, blockMir50, containerMetaBlock);

				for (EnumCalciteGroup value : EnumCalciteGroup.values()) {
					int metaId = value.ordinal();
					MetaBlock metaBlock = new MetaBlock(blockMir50, metaId);

					metaBlock.virtualClass.override(new AdaptorBlockSubBlocksMetaBlock(blockMir50, metaBlock, metaBlock));

					metaBlock.virtualClass.override(new AdaptorBlockHarvestMetaBlock(blockMir50, metaBlock));

					{
						AdaptorBlockMultipleRenderingAutonomy a = HelpersBlockMultipleRendering.makeAutonomy(metaBlock, blockMir50);

						a.appendIcon("minecraft:stone");
						a.appendIcon("miragecrops5:ore" + HelpersString.toUpperCaseHead(value.name()));
					}

					{
						AdaptorBlockNameAutonomy a = new AdaptorBlockNameAutonomy(blockMir50, metaBlock);

						a.setBlockName("ore" + HelpersString.toUpperCaseHead(value.name()));

						metaBlock.virtualClass.override(a);
					}

					metaBlock.virtualClass.register(IAdaptorBlockNameExtra.class, new AdaptorBlockNameExtra(blockMir50, metaBlock));

					containerMetaBlock.set(metaId, metaBlock);
				}

				container1 = containerMetaBlock;
			}

			return blockMir50;
		}, ItemBlockMulti.class, "oreCalciteGroup");
		loaderBlock_oreCalciteGroup.setCreativeTab(loaderCreativeTab);
		add(loaderBlock_oreCalciteGroup);

		loaderBlock_blockCalciteGroup.init(() -> {
			BlockMir50 blockMir50 = new BlockMir50(Material.rock);

			{
				ContainerMetaBlock containerMetaBlock = new ContainerMetaBlock(16);
				HelpersBlockMulti.make(blockMir50, blockMir50, containerMetaBlock);
				HelpersBlockMultipleRendering.make(blockMir50, blockMir50);
				HelpersBlockMultipleRenderingMulti.make(blockMir50, blockMir50, containerMetaBlock);

				for (EnumCalciteGroup value : EnumCalciteGroup.values()) {
					int metaId = value.ordinal();
					MetaBlock metaBlock = new MetaBlock(blockMir50, metaId);

					metaBlock.virtualClass.override(new AdaptorBlockSubBlocksMetaBlock(blockMir50, metaBlock, metaBlock));

					metaBlock.virtualClass.override(new AdaptorBlockHarvestMetaBlock(blockMir50, metaBlock));

					{
						AdaptorBlockMultipleRenderingAutonomy a = HelpersBlockMultipleRendering.makeAutonomy(metaBlock, blockMir50);

						a.appendIcon("miragecrops5:block" + HelpersString.toUpperCaseHead(value.name()));
					}

					{
						AdaptorBlockNameAutonomy a = new AdaptorBlockNameAutonomy(blockMir50, metaBlock);

						a.setBlockName("block" + HelpersString.toUpperCaseHead(value.name()));

						metaBlock.virtualClass.override(a);
					}

					metaBlock.virtualClass.register(IAdaptorBlockNameExtra.class, new AdaptorBlockNameExtra(blockMir50, metaBlock));

					containerMetaBlock.set(metaId, metaBlock);
				}

				container2 = containerMetaBlock;
			}

			return blockMir50;
		}, ItemBlockMulti.class, "blockCalciteGroup");
		loaderBlock_blockCalciteGroup.setCreativeTab(loaderCreativeTab);
		add(loaderBlock_blockCalciteGroup);

		loaderItem_craftingToolHammerIron.init(() -> {
			ItemMir50 itemMir50 = new ItemMir50();

			itemMir50.setMaxStackSize(1);
			itemMir50.setTextureName("minecraft:iron_pickaxe");

			itemMir50.setMaxDamage(20 - 1);
			itemMir50.adaptorItemContainerItem = new AdaptorItemContainerItemCraftingTool(itemMir50);

			return itemMir50;
		}, "craftingToolHammerIron", ModMirageCrops.MODID);
		loaderItem_craftingToolHammerIron.setCreativeTab(loaderCreativeTab);
		add(loaderItem_craftingToolHammerIron);

		add(new LoaderRecipe(() -> {

			GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(Items.iron_ingot, 1, 0),
				"X",
				"Y",
				'X', new ItemStack(loaderItem_craftingToolHammerIron.get(), 1, OreDictionary.WILDCARD_VALUE),
				'Y', Blocks.iron_ore));

		})
			.dependsOn(loaderItem_craftingToolHammerIron));

		aLOG(() -> cGOIC(16, 16, 0, 128, loaderBlock_oreCalciteGroup.get(), EnumCalciteGroup.calcite.ordinal()));
		aLOG(() -> cGOIC(8, 4, 0, 64, loaderBlock_oreCalciteGroup.get(), EnumCalciteGroup.magnesite.ordinal()));
		aLOG(() -> cGOIC(8, 4, 0, 56, loaderBlock_oreCalciteGroup.get(), EnumCalciteGroup.siderite.ordinal()));
		aLOG(() -> cGOIC(8, 4, 0, 48, loaderBlock_oreCalciteGroup.get(), EnumCalciteGroup.smithsonite.ordinal()));
		aLOG(() -> cGOIC(2, 2, 0, 32, loaderBlock_oreCalciteGroup.get(), EnumCalciteGroup.rhodochrosite.ordinal(), "ocean"));
		aLOG(() -> cGOIC(2, 2, 0, 28, loaderBlock_oreCalciteGroup.get(), EnumCalciteGroup.sphaerocobaltite.ordinal(), "forest"));
		aLOG(() -> cGOIC(2, 2, 0, 24, loaderBlock_oreCalciteGroup.get(), EnumCalciteGroup.gaspeite.ordinal(), "desert"));
		aLOG(() -> cGOIC(1, 0.5, 0, 16, loaderBlock_oreCalciteGroup.get(), EnumCalciteGroup.otavite.ordinal(), "extreme"));

		aLOG(() -> cGOIC(1, 20, 64, 128, loaderBlock_oreCalciteGroup.get(), EnumCalciteGroup.apatite.ordinal(), "extreme"));
		aLOG(() -> cGOIC(5, 4, 64, 128, loaderBlock_oreCalciteGroup.get(), EnumCalciteGroup.fluorite.ordinal(), "extreme"));

		aLOG(() -> cGOIC(2, 8, 0, 128, loaderBlock_oreCalciteGroup.get(), EnumCalciteGroup.spinachium.ordinal()));
		aLOG(() -> cGOIC(2, 8, 0, 128, loaderBlock_oreCalciteGroup.get(), EnumCalciteGroup.miragium.ordinal()));

	}

	private void aLOG(Supplier<IGeneratorOreInChunk> supplier)
	{
		add(new LoaderOreGenerator(loaderModule, supplier));
	}

	private IGeneratorOreInChunk cGOIC(
		int density, double numberOfBlocks, int heightMin, int heightMax, Block block, int meta, String... filterBiomeNames)
	{
		WorldGeneratorMinableExtra worldGenerator = new WorldGeneratorMinableExtra(block, meta, numberOfBlocks, Blocks.stone);

		for (String filterBiomeName : filterBiomeNames) {
			worldGenerator.addFilter(new FilterBiome(filterBiomeName));
		}

		return GeneratorOreInChunkBridge.createFromMinMax(
			density, IGeneratorOreAtPoint.Helpers.fromWorldGenerator(worldGenerator), heightMin, heightMax);
	}

	private int color(int r, int g, int b)
	{
		return (r << 16) | (g << 8) | b;
	}

}
