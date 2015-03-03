package mirrg_miragecrops5.material.matrix;

import mirrg.mir50.oredictionary.HelpersOreDictionary;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class MaterialMetal extends MaterialSolid
{

	protected Shape ingot = new Shape("ingot");
	protected Shape plate = new Shape("plate");
	protected Shape rod = new Shape("rod");
	protected Shape nugget = new Shape("nugget");
	protected Shape wire = new Shape("wire");

	protected Shape craftingToolHardHammer = new Shape("craftingToolHardHammer");
	protected Shape craftingToolChisel = new Shape("craftingToolChisel");
	protected Shape craftingToolCutter = new Shape("craftingToolCutter");
	protected Shape craftingToolFile = new Shape("craftingToolFile");
	protected Shape craftingToolMortar = new Shape("craftingToolMortar");
	protected Shape craftingToolSaw = new Shape("craftingToolSaw");
	protected Shape craftingToolScythe = new Shape("craftingToolScythe");
	protected Shape craftingToolWrench = new Shape("craftingToolWrench");
	protected Shape craftingToolPlateMold = new Shape("craftingToolPlateMold");

	public MaterialMetal(String name, int tierHardness, int tierStrength)
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
				'A', ingot.getOreName()));
		};

		return super.isExistAndGetRecipeSetter(shape, block, metaId);
	}

	@Override
	public Runnable isExistAndGetRecipeSetter(ShapeMirageCrops5 shape, Item item, int metaId)
	{
		Runnable runner = null;

		if (dust.is(shape)) runner = () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"I",
				"M",
				'I', ingot.getOreName(),
				'M', "craftingToolMortar"));
		};
		if (ingot.is(shape)) runner = () -> {

			for (ItemStack ore : OreDictionary.getOres(dust.getOreName())) {
				GameRegistry.addSmelting(ore, new ItemStack(item, 1, metaId), 0);
			}

			for (ItemStack ore : OreDictionary.getOres(ore.getOreName())) {
				GameRegistry.addSmelting(ore, new ItemStack(item, 1, metaId), 0);
			}

			ItemStack itemStack = HelpersOreDictionary.copy(block.getOreName());
			if (itemStack != null) {
				GameRegistry.addRecipe(new ShapedOreRecipe(itemStack,
					"AAA",
					"AAA",
					"AAA",
					'A', ingot.getOreName()));
			}

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 9, metaId),
				"A",
				'A', block.getOreName()));
		};
		if (plate.is(shape)) runner = () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"H",
				"I",
				"I",
				'I', ingot.getOreName(),
				'H', "craftingToolHardHammer"));
		};
		if (rod.is(shape)) runner = () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"PF",
				'P', plate.getOreName(),
				'F', "craftingToolFile"));
		};
		if (nugget.is(shape)) runner = () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 9, metaId),
				"I",
				'I', ingot.getOreName()));
		};
		if (wire.is(shape)) runner = () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 2, metaId),
				"P",
				"C",
				'P', plate.getOreName(),
				'C', "craftingToolCutter"));
		};

		if (craftingToolHardHammer.is(shape)) runner = () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"IIB",
				"IIS",
				"II ",
				'B', "craftingBookMirageToolIndustrial",
				'I', ingot.getOreName(),
				'S', "stickWood"));
		};
		if (craftingToolChisel.is(shape)) runner = () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"FR",
				"BS",
				'B', "craftingBookMirageToolIndustrial",
				'R', rod.getOreName(),
				'F', "craftingToolFile",
				'S', "stickWood"));
		};
		if (craftingToolCutter.is(shape)) runner = () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"PFP",
				"BI ",
				"SHS",
				'B', "craftingBookMirageToolIndustrial",
				'I', ingot.getOreName(),
				'P', plate.getOreName(),
				'H', "craftingToolHardHammer",
				'F', "craftingToolFile",
				'S', "stickWood"));
		};
		if (craftingToolFile.is(shape)) runner = () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"HP",
				"BS",
				'B', "craftingBookMirageToolIndustrial",
				'P', plate.getOreName(),
				'H', "craftingToolHardHammer",
				'S', "stickWood"));
		};
		if (craftingToolMortar.is(shape)) runner = () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"BI ",
				"AIA",
				"AAA",
				'B', "craftingBookMirageToolIndustrial",
				'I', ingot.getOreName(),
				'A', new ItemStack(Blocks.stonebrick)));
		};
		if (craftingToolSaw.is(shape)) runner = () -> {
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
		if (craftingToolScythe.is(shape)) runner = () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"FPI",
				"PHS",
				"B S",
				'B', "craftingBookMirageToolIndustrial",
				'I', ingot.getOreName(),
				'P', plate.getOreName(),
				'H', "craftingToolHardHammer",
				'F', "craftingToolFile",
				'S', "stickWood"));
		};
		if (craftingToolWrench.is(shape)) runner = () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"IHI",
				"III",
				"BI ",
				'B', "craftingBookMirageToolIndustrial",
				'I', ingot.getOreName(),
				'H', "craftingToolHardHammer"));
		};
		if (craftingToolPlateMold.is(shape)) runner = () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"BI ",
				"ICI",
				"III",
				'B', "craftingBookMirageToolIndustrial",
				'I', ingot.getOreName(),
				'C', "craftingToolChisel"));
		};

		Runnable runner2 = runner;
		return () -> {
			if (runner2 != null) runner2.run();
			Runnable runner3 = super.isExistAndGetRecipeSetter(shape, item, metaId);
			if (runner3 != null) runner3.run();
		};
	}

}
