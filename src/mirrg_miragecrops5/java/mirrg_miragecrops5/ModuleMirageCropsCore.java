package mirrg_miragecrops5;

import java.util.function.Supplier;

import mirrg.mir40.math.HelpersString;
import mirrg.mir50.block.AdaptorBlockName;
import mirrg.mir50.block.AdaptorBlockNameAutonomy;
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
import mirrg.mir50.worldgen.ore.WorldGeneratorMinableExtra;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRendering;
import mirrg.mir51.render.block.multiple.BlockMultipleRendering;
import mirrg.mir51.render.block.multiple.multi.AdaptorBlockMultipleRenderingMulti;
import mirrg.mir51.render.block.multiple.multi.ContainerMetaBlockMultipleRendering;
import mirrg.mir51.render.block.multiple.multi.MetaBlockMultipleRendering;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreAtPoint;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreInChunk;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModuleMirageCropsCore extends ModuleAbstract
{

	public LoaderCreativeTab loaderCreativeTab = new LoaderCreativeTab();
	public LoaderBlock loaderBlock_blockTest = new LoaderBlock();
	public LoaderBlock loaderBlock_oreCalciteGroup = new LoaderBlock();
	public LoaderBlock loaderBlock_blockCalciteGroup = new LoaderBlock();
	public LoaderItem loaderItem_craftingToolHammerIron = new LoaderItem();

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

		loaderCreativeTab.init(() -> Item.getItemFromBlock(loaderBlock_blockTest.get()), "MirageCrops 5");
		add(loaderCreativeTab);

		loaderBlock_blockTest.init(() -> {
			BlockMultipleRendering block = new BlockMultipleRendering(Material.rock);

			block.adaptorBlockMultipleRendering.appendIcon("minecraft:stone");
			block.adaptorBlockMultipleRendering.appendIcon("miragecrops5:oreCalcite", color(255, 205, 59));

			return block;
		}, ItemBlock.class, "blockTest");
		loaderBlock_blockTest.setCreativeTab(loaderCreativeTab);
		add(loaderBlock_blockTest);

		loaderBlock_oreCalciteGroup.init(() -> {
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
						AdaptorBlockName adaptorBlockName = new AdaptorBlockNameAutonomy(block);

						adaptorBlockName.setBlockName("ore" + HelpersString.toUpperCaseHead(value.name()));

						metaBlockMultipleRendering.adaptorBlockName = adaptorBlockName;
					}

					container.set(metaId, metaBlockMultipleRendering);
				}

				container1 = container;
			}

			return block;
		}, ItemBlockMulti.class, "oreCalciteGroup");
		loaderBlock_oreCalciteGroup.setItemBlockIniter(item -> {
			((ItemBlockMulti) item).init(container1);
		});
		loaderBlock_oreCalciteGroup.setCreativeTab(loaderCreativeTab);
		add(loaderBlock_oreCalciteGroup);

		loaderBlock_blockCalciteGroup.init(() -> {
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
						AdaptorBlockName adaptorBlockName = new AdaptorBlockNameAutonomy(block);

						adaptorBlockName.setBlockName("block" + HelpersString.toUpperCaseHead(value.name()));

						metaBlockMultipleRendering.adaptorBlockName = adaptorBlockName;
					}

					container.set(metaId, metaBlockMultipleRendering);
				}

				container2 = container;
			}

			return block;
		}, ItemBlockMulti.class, "blockCalciteGroup");
		loaderBlock_blockCalciteGroup.setItemBlockIniter(item -> {
			((ItemBlockMulti) item).init(container2);
		});
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

		aLOG(() -> cGOIC(15, 16, 0, 128, loaderBlock_oreCalciteGroup.get(), 0));
		aLOG(() -> cGOIC(10, 8, 0, 64, loaderBlock_oreCalciteGroup.get(), 1));
		aLOG(() -> cGOIC(10, 8, 0, 56, loaderBlock_oreCalciteGroup.get(), 2));
		aLOG(() -> cGOIC(10, 8, 0, 48, loaderBlock_oreCalciteGroup.get(), 3));
		aLOG(() -> cGOIC(2, 4, 0, 32, loaderBlock_oreCalciteGroup.get(), 4));
		aLOG(() -> cGOIC(2, 4, 0, 28, loaderBlock_oreCalciteGroup.get(), 5));
		aLOG(() -> cGOIC(2, 4, 0, 24, loaderBlock_oreCalciteGroup.get(), 6));
		aLOG(() -> cGOIC(1, 1, 0, 16, loaderBlock_oreCalciteGroup.get(), 7));

	}

	private void aLOG(Supplier<IGeneratorOreInChunk> supplier)
	{
		add(new LoaderOreGenerator(loaderModule, supplier));
	}

	private IGeneratorOreInChunk cGOIC(
		int density, int numberOfBlocks, int heightMin, int heightMax, Block block, int meta)
	{
		return GeneratorOreInChunkBridge.createFromMinMax(
			density, IGeneratorOreAtPoint.Helpers.fromWorldGenerator(
				new WorldGeneratorMinableExtra(block, meta, numberOfBlocks, Blocks.stone)), heightMin, heightMax);
	}

	private int color(int r, int g, int b)
	{
		return (r << 16) | (g << 8) | b;
	}

}
