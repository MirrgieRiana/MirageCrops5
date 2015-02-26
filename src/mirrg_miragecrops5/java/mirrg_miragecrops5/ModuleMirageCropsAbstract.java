package mirrg_miragecrops5;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.item.ItemMir50;
import mirrg.mir50.item.adaptors.AdaptorItemIconAutonomy;
import mirrg.mir50.item.adaptors.AdaptorItemNameAutonomy;
import mirrg.mir51.block.multi.ContainerMetaBlock;
import mirrg.mir51.block.multi.HelpersBlockMulti;
import mirrg.mir51.item.multi.AdaptorItemSubItemsMetaItem;
import mirrg.mir51.item.multi.ContainerMetaItem;
import mirrg.mir51.item.multi.HelpersItemMulti;
import mirrg.mir51.item.multi.MetaItem;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.mir51.loaders.LoaderCreativeTab;
import mirrg.mir51.loaders.LoaderItem;
import mirrg.mir51.loaders.LoaderOreDictionary;
import mirrg.mir51.modding.ModuleAbstract;
import mirrg.mir51.render.block.multiple.HelpersBlockMultipleRendering;
import mirrg.mir52.render.block.multiple.multi.HelpersBlockMultipleRenderingMulti;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public abstract class ModuleMirageCropsAbstract extends ModuleAbstract
{

	protected void setMetaItem(
		ItemMir50 itemMir50, ContainerMetaItem containerMetaItem,
		int metaId, String unlocalizedName, BiConsumer<MetaItem, AdaptorItemIconAutonomy> setter,
		boolean iconAppendCancel)
	{
		MetaItem metaItem = new MetaItem(itemMir50, metaId);

		metaItem.virtualClass.override(new AdaptorItemNameAutonomy(itemMir50, metaItem, unlocalizedName));

		metaItem.virtualClass.override(new AdaptorItemSubItemsMetaItem(itemMir50, metaItem, metaItem));
		AdaptorItemIconAutonomy a = new AdaptorItemIconAutonomy(itemMir50, metaItem);
		if (!iconAppendCancel) a.appendIcon("miragecrops5:" + unlocalizedName);
		if (setter != null) setter.accept(metaItem, a);
		metaItem.virtualClass.override(a);

		OreDictionary.registerOre(unlocalizedName, new ItemStack(itemMir50, 1, metaId));

		containerMetaItem.set(metaId, metaItem);
	}

	protected void process_loaderItem_multi(
		LoaderItem loaderItem,
		LoaderCreativeTab loaderCreativeTab,
		String unlocalizedName,
		int bufferSize,
		BiConsumer<ItemMir50, ContainerMetaItem> beforeMake,
		BiConsumer<ItemMir50, ContainerMetaItem> afterMake)
	{
		process_loaderItem(loaderItem, loaderCreativeTab, unlocalizedName, (itemMir50) -> {

			ContainerMetaItem containerMetaItem = new ContainerMetaItem(bufferSize);

			if (beforeMake != null) beforeMake.accept(itemMir50, containerMetaItem);

			HelpersItemMulti.make(itemMir50, itemMir50, containerMetaItem, true);

			if (afterMake != null) afterMake.accept(itemMir50, containerMetaItem);

		});
	}

	protected void process_loaderBlock_multi(
		LoaderBlock loaderBlock,
		LoaderCreativeTab loaderCreativeTab,
		String unlocalizedName,
		Class<? extends ItemBlock> classItemBlock,
		BiConsumer<BlockMir50, ContainerMetaBlock> beforeMake,
		BiConsumer<BlockMir50, ContainerMetaBlock> afterMake)
	{
		process_loaderBlock(loaderBlock, loaderCreativeTab, classItemBlock, unlocalizedName, (blockMir50) -> {

			ContainerMetaBlock containerMetaBlock = new ContainerMetaBlock(16);

			if (beforeMake != null) beforeMake.accept(blockMir50, containerMetaBlock);

			HelpersBlockMulti.make(blockMir50, blockMir50, containerMetaBlock);
			HelpersBlockMultipleRendering.make(blockMir50, blockMir50);
			HelpersBlockMultipleRenderingMulti.make(blockMir50, blockMir50, containerMetaBlock);

			if (afterMake != null) afterMake.accept(blockMir50, containerMetaBlock);

		});
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
