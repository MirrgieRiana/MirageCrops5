package com.example.examplemod;

import mirrg.mir50.block.render.multiple.BlockMultipleRendering;
import mirrg.mir50.modding.ModuleAbstract;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.mir51.loaders.LoaderCreativeTab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ModuleMirageCropsCore extends ModuleAbstract
{

	public LoaderCreativeTab loaderCreativeTab = new LoaderCreativeTab();
	public LoaderBlock loaderBlock = new LoaderBlock();

	public ModuleMirageCropsCore()
	{

		loaderCreativeTab.init(() -> Item.getItemFromBlock(loaderBlock.get()), "MirageCrops 5");
		add(loaderCreativeTab);

		loaderBlock.init(() -> {
			BlockMultipleRendering block = new BlockMultipleRendering(Material.rock);

			block.adaptorBlockMultipleRendering.appendIcon("minecraft:stone");
			block.adaptorBlockMultipleRendering.appendIcon("miragecrops5:oreCalcite");

			return block;
		}, ItemBlock.class, "oreCalciteGroup");
		loaderBlock.setCreativeTab(loaderCreativeTab);
		add(loaderBlock);

	}

}
