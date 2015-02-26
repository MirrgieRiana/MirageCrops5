package mirrg_miragecrops5.material.matrix;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class MaterialGem extends MaterialSolid
{

	protected Shape gem = new Shape("gem");
	protected Shape plate = new Shape("plate");
	protected Shape rod = new Shape("rod");

	protected Shape craftingToolFile = new Shape("craftingToolFile");
	protected Shape craftingToolMortar = new Shape("craftingToolMortar");
	protected Shape craftingToolSaw = new Shape("craftingToolSaw");
	protected Shape craftingToolCutter = new Shape("craftingToolCutter");
	protected Shape craftingToolScythe = new Shape("craftingToolScythe");

	public MaterialGem(String name, int tierHardness, int tierStrength)
	{
		super(name, tierHardness, tierStrength);
	}

	@Override
	public Runnable isExistAndGetRecipeSetter(ShapeMirageCrops5 shape, Block block, int metaId)
	{
		if (this.block.is(shape)) return () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(block, 1, metaId),
				"AAA",
				"AAA",
				"AAA",
				'A', gem.getOreName()));
		};

		return super.isExistAndGetRecipeSetter(shape, block, metaId);
	}

	@Override
	public Runnable isExistAndGetRecipeSetter(ShapeMirageCrops5 shape, Item item, int metaId)
	{
		if (dust.is(shape)) return () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"H",
				"G",
				'G', gem.getOreName(),
				'H', "craftingToolHardHammer"));
		};
		if (rod.is(shape)) return () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"PF",
				'P', plate.getOreName(),
				'F', "craftingToolFile"));
		};
		if (plate.is(shape)) return () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"D ",
				"D ",
				"MW",
				'D', dust.getOreName(),
				'M', "craftingToolPlateMold",
				'W', Items.water_bucket));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"D ",
				"D ",
				"MW",
				'D', dust.getOreName(),
				'M', "craftingToolPlateMold",
				'W', "cellWater"));
		};
		if (gem.is(shape)) return () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 9, metaId),
				"A",
				'A', block.getOreName()));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"H",
				"O",
				'H', "craftingToolHardHammer",
				'O', ore.getOreName()));
		};

		if (craftingToolFile.is(shape)) return () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"HP",
				"BS",
				'B', "craftingBookMirageToolIndustrial",
				'P', plate.getOreName(),
				'H', "craftingToolHardHammer",
				'S', "stickWood"));
		};
		if (craftingToolMortar.is(shape)) return () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"BG ",
				"AGA",
				"AAA",
				'B', "craftingBookMirageToolIndustrial",
				'G', gem.getOreName(),
				'A', new ItemStack(Blocks.stonebrick)));
		};
		if (craftingToolSaw.is(shape)) return () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"SSS",
				"PPS",
				"FHB",
				'B', "craftingBookMirageToolIndustrial",
				'P', plate.getOreName(),
				'H', "craftingToolHardHammer",
				'F', "craftingToolFile",
				'S', "stickWood"));
		};
		if (craftingToolCutter.is(shape)) return () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"PFP",
				"BG ",
				"SHS",
				'B', "craftingBookMirageToolIndustrial",
				'G', gem.getOreName(),
				'P', plate.getOreName(),
				'H', "craftingToolHardHammer",
				'F', "craftingToolFile",
				'S', "stickWood"));
		};
		if (craftingToolScythe.is(shape)) return () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"FPG",
				"PHS",
				"B S",
				'B', "craftingBookMirageToolIndustrial",
				'G', gem.getOreName(),
				'P', plate.getOreName(),
				'H', "craftingToolHardHammer",
				'F', "craftingToolFile",
				'S', "stickWood"));
		};

		return super.isExistAndGetRecipeSetter(shape, item, metaId);
	}

}
