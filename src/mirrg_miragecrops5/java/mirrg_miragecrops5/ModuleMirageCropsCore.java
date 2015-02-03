package mirrg_miragecrops5;

import mirrg.mir50.block.multi.AdaptorBlockHarvestMulti;
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
import cpw.mods.fml.common.registry.GameRegistry;

public class ModuleMirageCropsCore extends ModuleAbstract
{

	public LoaderCreativeTab loaderCreativeTab = new LoaderCreativeTab();
	public LoaderBlock loaderBlock = new LoaderBlock();
	public LoaderBlock loaderBlock2 = new LoaderBlock();
	public LoaderItem loaderItem = new LoaderItem();

	public ContainerMetaBlockMultipleRendering container1;

	public ModuleMirageCropsCore()
	{

		loaderCreativeTab.init(() -> Item.getItemFromBlock(loaderBlock.get()), "MirageCrops 5");
		add(loaderCreativeTab);

		loaderBlock.init(() -> {
			BlockMultipleRendering block = new BlockMultipleRendering(Material.rock);

			block.adaptorBlockMultipleRendering.appendIcon("minecraft:stone");
			block.adaptorBlockMultipleRendering.appendIcon("miragecrops5:oreCalcite", color(255, 205, 59));

			return block;
		}, ItemBlock.class, "oreCalciteGroup");
		loaderBlock.setCreativeTab(loaderCreativeTab);
		add(loaderBlock);

		loaderBlock2.init(() -> {
			BlockMultipleRendering block = new BlockMultipleRendering(Material.rock);

			{
				ContainerMetaBlockMultipleRendering container = new ContainerMetaBlockMultipleRendering(16);

				block.setAdaptorBlockMultipleRendering(new AdaptorBlockMultipleRenderingMulti(block, container));
				block.adaptorBlockHarvest = new AdaptorBlockHarvestMulti(block, container);
				block.adaptorBlockSubBlocks = new AdaptorBlockSubBlocksMulti(block, container);

				{
					int metaId = 0;

					MetaBlockMultipleRendering metaBlockMultipleRendering = new MetaBlockMultipleRendering(block, metaId);

					{
						AdaptorBlockMultipleRendering adaptorBlockMultipleRendering = new AdaptorBlockMultipleRendering(block);

						adaptorBlockMultipleRendering.appendIcon("minecraft:stone");
						adaptorBlockMultipleRendering.appendIcon("miragecrops5:oreCalcite", color(255, 205, 59));

						metaBlockMultipleRendering.setAdaptorBlockMultipleRendering(adaptorBlockMultipleRendering);
					}

					container.set(metaId, metaBlockMultipleRendering);
				}

				{
					int metaId = 1;

					MetaBlockMultipleRendering metaBlockMultipleRendering = new MetaBlockMultipleRendering(block, metaId);

					{
						AdaptorBlockMultipleRendering adaptorBlockMultipleRendering = new AdaptorBlockMultipleRendering(block);

						adaptorBlockMultipleRendering.appendIcon("minecraft:stone");
						adaptorBlockMultipleRendering.appendIcon("miragecrops5:oreOtavite");

						metaBlockMultipleRendering.setAdaptorBlockMultipleRendering(adaptorBlockMultipleRendering);
					}

					container.set(metaId, metaBlockMultipleRendering);
				}

				container1 = container;
			}

			block.adaptorBlockMultipleRendering.appendIcon("minecraft:stone");
			block.adaptorBlockMultipleRendering.appendIcon("miragecrops5:oreCalcite", color(255, 205, 59));

			return block;
		}, ItemBlockMulti.class, "blockCalciteGroup");
		loaderBlock2.setItemBlockIniter(item -> {
			((ItemBlockMulti) item).init(container1);
		});
		loaderBlock2.setCreativeTab(loaderCreativeTab);
		add(loaderBlock2);

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
			20, new WorldGenMinable(loaderBlock.get(), 16), 0, 128)));

	}

	private int color(int r, int g, int b)
	{
		return (r << 16) | (g << 8) | b;
	}

}
