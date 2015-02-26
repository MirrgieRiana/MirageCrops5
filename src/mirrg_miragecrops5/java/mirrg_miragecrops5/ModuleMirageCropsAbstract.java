package mirrg_miragecrops5;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import mirrg.he.math.HelpersString;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.adaptors.AdaptorBlockNameAutonomy;
import mirrg.mir50.block.adaptors.AdaptorBlockNameExtra;
import mirrg.mir50.block.adaptors.IAdaptorBlockNameExtra;
import mirrg.mir50.item.ItemMir50;
import mirrg.mir50.item.adaptors.AdaptorItemIconAutonomy;
import mirrg.mir50.worldgen.ore.FilterBiome;
import mirrg.mir50.worldgen.ore.GeneratorOreInChunkBridge;
import mirrg.mir50.worldgen.ore.WorldGeneratorMinableExtra;
import mirrg.mir51.block.multi.AdaptorBlockHarvestMetaBlock;
import mirrg.mir51.block.multi.AdaptorBlockSubBlocksMetaBlock;
import mirrg.mir51.block.multi.ContainerMetaBlock;
import mirrg.mir51.block.multi.HelpersBlockMulti;
import mirrg.mir51.block.multi.ItemBlockMulti;
import mirrg.mir51.block.multi.MetaBlock;
import mirrg.mir51.item.multi.AdaptorItemSubItemsMetaItem;
import mirrg.mir51.item.multi.ContainerMetaItem;
import mirrg.mir51.item.multi.HelpersItemMulti;
import mirrg.mir51.item.multi.MetaItem;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.mir51.loaders.LoaderCreativeTab;
import mirrg.mir51.loaders.LoaderItem;
import mirrg.mir51.loaders.LoaderOreDictionary;
import mirrg.mir51.loaders.LoaderOreGenerator;
import mirrg.mir51.modding.ModuleAbstract;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRenderingAutonomy;
import mirrg.mir51.render.block.multiple.HelpersBlockMultipleRendering;
import mirrg.mir52.render.block.multiple.multi.HelpersBlockMultipleRenderingMulti;
import mirrg_miragecrops5.ModuleMaterials.EnumCalciteGroup;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreAtPoint;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreInChunk;

public abstract class ModuleMirageCropsAbstract extends ModuleAbstract
{

	protected void setMetaItem(
		ItemMir50 itemMir50, ContainerMetaItem containerMetaItem,
		int metaId, String unlocalizedName, BiConsumer<MetaItem, AdaptorItemIconAutonomy> setter,
		boolean iconAppendCancel)
	{
		MetaItem metaItem = new MetaItem(itemMir50, metaId);

		metaItem.virtualClass.override(new AdaptorItemSubItemsMetaItem(itemMir50, metaItem, metaItem));
		AdaptorItemIconAutonomy a = new AdaptorItemIconAutonomy(itemMir50, metaItem);
		if (!iconAppendCancel) a.appendIcon("miragecrops5:" + unlocalizedName);
		metaItem.virtualClass.override(a);

		OreDictionary.registerOre(unlocalizedName, new ItemStack(itemMir50, 1, metaId));

		if (setter != null) setter.accept(metaItem, a);

		containerMetaItem.set(metaId, metaItem);
	}

	protected void process_loaderItem_multi(LoaderItem loaderItem, LoaderCreativeTab loaderCreativeTab,
		String unlocalizedName, BiConsumer<ItemMir50, ContainerMetaItem> processor)
	{
		process_loaderItem(loaderItem, loaderCreativeTab, unlocalizedName, (itemMir50) -> {
			ContainerMetaItem metaItemContainer = new ContainerMetaItem(100);
			HelpersItemMulti.make(itemMir50, itemMir50, metaItemContainer, true);

			processor.accept(itemMir50, metaItemContainer);
		});
	}

	protected void process_loaderItem_unstackable(LoaderItem loaderItem, LoaderCreativeTab loaderCreativeTab,
		String unlocalizedName, Consumer<ItemMir50> processor)
	{
		process_loaderItem(loaderItem, loaderCreativeTab, unlocalizedName, (itemMir50) -> {
			itemMir50.setMaxStackSize(1);

			processor.accept(itemMir50);
		});
	}

	protected void process_loaderBlock_multi(LoaderBlock loaderBlock, LoaderCreativeTab loaderCreativeTab,
		String unlocalizedName, String shape, Consumer<AdaptorBlockMultipleRenderingAutonomy> iconSetter)
	{
		ArrayList<String> oreNames = new ArrayList<>();
		ArrayList<Block> blocks = new ArrayList<>();
		ArrayList<Integer> metaIds = new ArrayList<>();

		process_loaderBlock(loaderBlock, loaderCreativeTab, ItemBlockMulti.class, unlocalizedName, (blockMir50) -> {

			blockMir50.setBlockName(unlocalizedName);
			blockMir50.setBlockTextureName("minecraft:stone");

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

						if (iconSetter != null) iconSetter.accept(a);
						a.appendIcon("miragecrops5:" + shape + HelpersString.toUpperCaseHead(value.name()));
					}

					{
						AdaptorBlockNameAutonomy a = new AdaptorBlockNameAutonomy(blockMir50, metaBlock);

						a.setBlockName(shape + HelpersString.toUpperCaseHead(value.name()));

						metaBlock.virtualClass.override(a);
					}

					metaBlock.virtualClass.register(IAdaptorBlockNameExtra.class, new AdaptorBlockNameExtra(blockMir50, metaBlock));

					oreNames.add(shape + HelpersString.toUpperCaseHead(value.name()));
					blocks.add(blockMir50);
					metaIds.add(metaId);

					containerMetaBlock.set(metaId, metaBlock);
				}

			}

		});

		add(new LoaderOreDictionary(() -> {

			for (int i = 0; i < oreNames.size(); i++) {
				OreDictionary.registerOre(oreNames.get(i), new ItemStack(blocks.get(i), 1, metaIds.get(i)));
			}
		}).dependsOn(loaderBlock));
	}

	protected void process_loaderItem(LoaderItem loaderItem, LoaderCreativeTab loaderCreativeTab,
		String unlocalizedName, Consumer<ItemMir50> setter)
	{
		ItemMir50[] slot = new ItemMir50[1];

		loaderItem.init(() -> {
			ItemMir50 itemMir50 = new ItemMir50();

			itemMir50.setUnlocalizedName(unlocalizedName);
			itemMir50.setTextureName("miragecrops5:" + unlocalizedName);

			if (setter != null) setter.accept(itemMir50);

			slot[0] = itemMir50;
			return itemMir50;
		}, unlocalizedName, ModMirageCrops.MODID);
		loaderItem.setCreativeTab(loaderCreativeTab);
		add(loaderItem);

		add(new LoaderOreDictionary(() -> {
			OreDictionary.registerOre(unlocalizedName, slot[0]);
		}).dependsOn(loaderItem));
	}

	protected void process_loaderBlock(LoaderBlock loaderBlock, LoaderCreativeTab loaderCreativeTab,
		Class<? extends ItemBlock> classItemBlock, String unlocalizedName, Consumer<BlockMir50> setter)
	{
		BlockMir50[] slot = new BlockMir50[1];

		loaderBlock.init(() -> {
			BlockMir50 blockMir50 = new BlockMir50(Material.rock);

			blockMir50.setBlockName(unlocalizedName);
			blockMir50.setBlockTextureName("miragecrops5:" + unlocalizedName);

			if (setter != null) setter.accept(blockMir50);

			slot[0] = blockMir50;
			return blockMir50;
		}, classItemBlock, unlocalizedName);
		loaderBlock.setCreativeTab(loaderCreativeTab);
		add(loaderBlock);

		add(new LoaderOreDictionary(() -> {
			OreDictionary.registerOre(unlocalizedName, slot[0]);
		}).dependsOn(loaderBlock));
	}

}
