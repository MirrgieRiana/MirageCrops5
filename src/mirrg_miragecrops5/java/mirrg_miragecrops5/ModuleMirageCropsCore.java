package mirrg_miragecrops5;

import mirrg.mir50.block.render.multiple.BlockMultipleRendering;
import mirrg.mir50.item.AdaptorItemContainerItemCraftingTool;
import mirrg.mir50.item.ItemMir50;
import mirrg.mir50.modding.ModuleAbstract;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.mir51.loaders.LoaderCreativeTab;
import mirrg.mir51.loaders.LoaderItem;
import mirrg.mir51.loaders.LoaderRecipe;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModuleMirageCropsCore extends ModuleAbstract
{

	public LoaderCreativeTab loaderCreativeTab = new LoaderCreativeTab();
	public LoaderBlock loaderBlock = new LoaderBlock();
	public LoaderItem loaderItem = new LoaderItem();

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

		}) {
			{
				dependsOn(loaderItem);
			}
		});

	}

	private int color(int r, int g, int b)
	{
		return (r << 16) | (g << 8) | b;
	}

}
