package mirrg_miragecrops5.material;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import mirrg.h.struct.Tuple;
import mirrg.he.math.HelpersString;
import mirrg.mir50.block.AdaptorBlockEventsOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.adaptors.AdaptorBlockNameAutonomy;
import mirrg.mir50.block.adaptors.AdaptorBlockNameExtra;
import mirrg.mir50.block.adaptors.IAdaptorBlockNameExtra;
import mirrg.mir50.instanceregistry.IInstanceRegistry;
import mirrg.mir50.instanceregistry.InstanceRegistryArray;
import mirrg.mir50.item.ItemMir50;
import mirrg.mir50.item.adaptors.AdaptorItemEventsFertilizer;
import mirrg.mir50.item.adaptors.AdaptorItemNameAutonomy;
import mirrg.mir50.item.adaptors.AdaptorItemNameInformation;
import mirrg.mir50.material.HelpersMaterial;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import mirrg.mir51.block.multi.AdaptorBlockHarvestMetaBlock;
import mirrg.mir51.block.multi.AdaptorBlockSubBlocksMetaBlock;
import mirrg.mir51.block.multi.ContainerMetaBlock;
import mirrg.mir51.block.multi.ItemBlockMulti;
import mirrg.mir51.block.multi.MetaBlock;
import mirrg.mir51.icon.multi.MultipleIcon;
import mirrg.mir51.icon.multi.MultipleIconShapes;
import mirrg.mir51.item.multi.AdaptorItemSubItemsMetaItem;
import mirrg.mir51.item.multi.ContainerMetaItem;
import mirrg.mir51.item.multi.MetaItem;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.mir51.loaders.LoaderCreativeTab;
import mirrg.mir51.loaders.LoaderItem;
import mirrg.mir51.loaders.LoaderOreDictionary;
import mirrg.mir51.loaders.LoaderRecipe;
import mirrg_miragecrops5.ItemCraftingPickaxeApatite;
import mirrg_miragecrops5.ModuleMirageCropsAbstract;
import mirrg_miragecrops5.material.matrix.MaterialGem;
import mirrg_miragecrops5.material.matrix.MaterialMetal;
import mirrg_miragecrops5.material.matrix.MaterialMirageCrops5;
import mirrg_miragecrops5.material.matrix.MaterialSolid;
import mirrg_miragecrops5.material.matrix.ShapeBlockBlock;
import mirrg_miragecrops5.material.matrix.ShapeBlockMirageCrops5;
import mirrg_miragecrops5.material.matrix.ShapeBlockOre;
import mirrg_miragecrops5.material.matrix.ShapeCraftingTool;
import mirrg_miragecrops5.material.matrix.ShapeItemMirageCrops5;
import mirrg_miragecrops5.material.matrix.ShapeMaterial;
import mirrg_miragecrops5.material.matrix.ShapeMirageCrops5;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ModuleMaterials extends ModuleMirageCropsAbstract
{

	public static LoaderCreativeTab loaderCreativeTab = new LoaderCreativeTab();

	public static LoaderBlock loaderBlock_oreCalciteGroup = new LoaderBlock();
	public static LoaderBlock loaderBlock_blockCalciteGroup = new LoaderBlock();

	public static LoaderItem loaderItem_materials = new LoaderItem();
	public static ContainerMetaItem containerMetaItem_materials;
	public static LoaderItem loaderItem_craftingTools = new LoaderItem();
	public static ContainerMetaItem containerMetaItem_craftingTools;

	public static Hashtable<String, ContainerMetaBlock> containerMetaBlocks = new Hashtable<>();

	//

	protected ArrayList<Tuple<String, Supplier<ItemStack>>> scheduleRegisterOreDictionary = new ArrayList<>();
	protected ArrayList<Runnable> scheduleRegisterRecipe = new ArrayList<>();

	public ModuleMaterials()
	{

		{
			HelpersMaterial.initDefaultMaterials(HelpersModuleMaterial.registryMaterialProperty);
			HelpersModuleMaterial.registryMaterialProperty.set("spinachium", 0x00C610, "Ma");
			HelpersModuleMaterial.registryMaterialProperty.set("miragium", 0x597ABA, "Ma");
		}

		{
			registerMaterial(new MaterialGem("calcite", 1, 0));
			registerMaterial(new MaterialGem("magnesite", 1, 0));
			registerMaterial(new MaterialGem("siderite", 1, 0));
			registerMaterial(new MaterialGem("rhodochrosite", 1, 0));
			registerMaterial(new MaterialGem("smithsonite", 1, 0));
			registerMaterial(new MaterialGem("sphaerocobaltite", 1, 0));
			registerMaterial(new MaterialGem("gaspeite", 1, 0));
			registerMaterial(new MaterialGem("otavite", 1, 0));

			registerMaterial(new MaterialGem("talc", 1, 0));
			registerMaterial(new MaterialGem("gypsum", 1, 0));
			registerMaterial(new MaterialGem("calcite", 1, 0));
			registerMaterial(new MaterialGem("fluorite", 1, 0));
			registerMaterial(new MaterialGem("apatite", 2, 0));
			registerMaterial(new MaterialGem("orthoclase", 2, 0));
			registerMaterial(new MaterialGem("quartz", 3, 0));
			registerMaterial(new MaterialGem("topaz", 3, 0));
			registerMaterial(new MaterialGem("ruby", 4, 0));
			registerMaterial(new MaterialGem("diamond", 5, 0));

			registerMaterial(new MaterialGem("glass", 2, 0));

			registerMaterial(new MaterialMirageCrops5("calcium"));
			registerMaterial(new MaterialMirageCrops5("magnesium"));
			registerMaterial(new MaterialMetal("iron", 1, 2));
			registerMaterial(new MaterialMetal("manganese", 0, 1));
			registerMaterial(new MaterialMetal("zinc", 0, 1));
			registerMaterial(new MaterialMetal("cobalt", 1, 1));
			registerMaterial(new MaterialMetal("nickel", 1, 2));
			registerMaterial(new MaterialMetal("cadmium", 1, 1));

			registerMaterial(new MaterialMetal("copper", 0, 1));
			registerMaterial(new MaterialMetal("tin", 0, 0));
			registerMaterial(new MaterialMetal("gold", 0, 0));
			registerMaterial(new MaterialMetal("silver", 0, 0));
			registerMaterial(new MaterialMetal("lead", 0, 1));
			registerMaterial(new MaterialMirageCrops5("mercury"));
			registerMaterial(new MaterialMetal("titanium", 2, 4));
			registerMaterial(new MaterialMetal("chrome", 3, 4));
			registerMaterial(new MaterialMetal("iridium", 2, 5));
			registerMaterial(new MaterialMetal("osmium", 3, 5));
			registerMaterial(new MaterialMetal("tungsten", 3, 3));
			registerMaterial(new MaterialMetal("platinum", 1, 1));
			registerMaterial(new MaterialMetal("aluminium", 0, 2));
			registerMaterial(new MaterialMetal("bismuth", 0, 1));

			registerMaterial(new MaterialMetal("bronze", 0, 2));
			registerMaterial(new MaterialMetal("brass", 1, 2));
			registerMaterial(new MaterialMetal("electrum", 0, 1));
			registerMaterial(new MaterialMetal("steel", 1, 3));
			registerMaterial(new MaterialMetal("stainlessSteel", 1, 3));
			registerMaterial(new MaterialMetal("tungstenSteel", 2, 4));

			registerMaterial(new MaterialSolid("sulfur", 0, 0));

			registerMaterial(new MaterialMetal("spinachium", 0, 0));
			registerMaterial(new MaterialMetal("miragium", 1, 1));
		}

		//

		loaderCreativeTab.init(() -> loaderItem_materials.get(), "miragecrops5_materials");
		add(loaderCreativeTab);

		// #########################################################################################################

		{
			IInstanceRegistry<MaterialMirageCrops5> registryMaterialItem = new InstanceRegistryArray<>(new MaterialMirageCrops5[1000]);
			HelpersModuleMaterial.registryMaterialItem = registryMaterialItem;
			{
				registerMaterial(registryMaterialItem, 0, "calcite");
				registerMaterial(registryMaterialItem, 1, "magnesite");
				registerMaterial(registryMaterialItem, 2, "siderite");
				registerMaterial(registryMaterialItem, 3, "rhodochrosite");
				registerMaterial(registryMaterialItem, 4, "smithsonite");
				registerMaterial(registryMaterialItem, 5, "sphaerocobaltite");
				registerMaterial(registryMaterialItem, 6, "gaspeite");
				registerMaterial(registryMaterialItem, 7, "otavite");

				registerMaterial(registryMaterialItem, 10, "talc");
				registerMaterial(registryMaterialItem, 11, "gypsum");
				registerMaterial(registryMaterialItem, 12, "calcite");
				registerMaterial(registryMaterialItem, 13, "fluorite");
				registerMaterial(registryMaterialItem, 14, "apatite");
				registerMaterial(registryMaterialItem, 15, "orthoclase");
				registerMaterial(registryMaterialItem, 16, "quartz");
				registerMaterial(registryMaterialItem, 17, "topaz");
				registerMaterial(registryMaterialItem, 18, "ruby");
				registerMaterial(registryMaterialItem, 19, "diamond");

				registerMaterial(registryMaterialItem, 20, "glass");

				registerMaterial(registryMaterialItem, 30, "calcium");
				registerMaterial(registryMaterialItem, 31, "magnesium");
				registerMaterial(registryMaterialItem, 32, "iron");
				registerMaterial(registryMaterialItem, 33, "manganese");
				registerMaterial(registryMaterialItem, 34, "zinc");
				registerMaterial(registryMaterialItem, 35, "cobalt");
				registerMaterial(registryMaterialItem, 36, "nickel");
				registerMaterial(registryMaterialItem, 37, "cadmium");

				registerMaterial(registryMaterialItem, 40, "copper");
				registerMaterial(registryMaterialItem, 41, "tin");
				registerMaterial(registryMaterialItem, 42, "gold");
				registerMaterial(registryMaterialItem, 43, "silver");
				registerMaterial(registryMaterialItem, 44, "lead");
				registerMaterial(registryMaterialItem, 45, "mercury");
				registerMaterial(registryMaterialItem, 46, "titanium");
				registerMaterial(registryMaterialItem, 47, "chrome");
				registerMaterial(registryMaterialItem, 48, "iridium");
				registerMaterial(registryMaterialItem, 49, "osmium");
				registerMaterial(registryMaterialItem, 50, "tungsten");
				registerMaterial(registryMaterialItem, 51, "platinum");
				registerMaterial(registryMaterialItem, 52, "aluminium");
				registerMaterial(registryMaterialItem, 53, "bismuth");

				registerMaterial(registryMaterialItem, 60, "bronze");
				registerMaterial(registryMaterialItem, 61, "brass");
				registerMaterial(registryMaterialItem, 62, "electrum");
				registerMaterial(registryMaterialItem, 63, "steel");
				registerMaterial(registryMaterialItem, 64, "stainlessSteel");
				registerMaterial(registryMaterialItem, 65, "tungstenSteel");

				registerMaterial(registryMaterialItem, 70, "sulfur");

				registerMaterial(registryMaterialItem, 80, "spinachium");
				registerMaterial(registryMaterialItem, 81, "miragium");
			}

			{
				IInstanceRegistry<ShapeItemMirageCrops5> registryShape = new InstanceRegistryArray<>(new ShapeItemMirageCrops5[32]);
				HelpersModuleMaterial.registryShapeMaterial = registryShape;
				{
					registerShape(registryShape, 0, new ShapeMaterial("ingot", MultipleIconShapes.INGOT));
					registerShape(registryShape, 1, new ShapeMaterial("dust", MultipleIconShapes.DUST));
					registerShape(registryShape, 2, new ShapeMaterial("rod", MultipleIconShapes.ROD));
					registerShape(registryShape, 3, new ShapeMaterial("plate", MultipleIconShapes.PLATE));
					registerShape(registryShape, 4, new ShapeMaterial("gem", MultipleIconShapes.GEM));

					registerShape(registryShape, 10, new ShapeMaterial("nugget", MultipleIconShapes.NUGGET));
					registerShape(registryShape, 11, new ShapeMaterial("dustSmall", MultipleIconShapes.DUST_SMALL));
					registerShape(registryShape, 12, new ShapeMaterial("dustTiny", MultipleIconShapes.DUST_TINY));

					registerShape(registryShape, 20, new ShapeMaterial("wire", MultipleIconShapes.WIRE));
					//registryShape.registerShape(21, new A("foil", MultipleIconShapes.FOIL));
				}

				process_loaderItem_materialMatrix(loaderItem_materials, loaderCreativeTab, "materials",
					registryShape, registryMaterialItem,
					(itemMir50, containerMetaItem) -> {

						containerMetaItem_materials = containerMetaItem;

						// クリエイティブタブの看板用にデフォルトアイコンをセット
					HelpersModuleMaterial.applyMultipleIcon(itemMir50, itemMir50,
						new MultipleIcon(MultipleIconShapes.SAW, HelpersModuleMaterial.registryMaterialProperty.getColor("apatite"), 0x896727));

				}, null);
			}

			{
				IInstanceRegistry<ShapeItemMirageCrops5> registryShapeTool = new InstanceRegistryArray<>(new ShapeItemMirageCrops5[32]);
				HelpersModuleMaterial.registryShapeTool = registryShapeTool;
				{
					registerShape(registryShapeTool, 0, new ShapeCraftingTool("craftingToolHardHammer", MultipleIconShapes.HAMMER, 64, 1.2, 2));
					registerShape(registryShapeTool, 1, new ShapeCraftingTool("craftingToolSoftHammer", MultipleIconShapes.HAMMER, 64, 0.9, 1.5));
					registerShape(registryShapeTool, 2, new ShapeCraftingTool("craftingToolChisel", MultipleIconShapes.CHISEL, 10, 1.8, 2.2));
					registerShape(registryShapeTool, 3, new ShapeCraftingTool("craftingToolCutter", MultipleIconShapes.CUTTER, 64, 1, 2.2));
					registerShape(registryShapeTool, 4, new ShapeCraftingTool("craftingToolFile", MultipleIconShapes.FILE, 32, 2, 1.2));
					registerShape(registryShapeTool, 5, new ShapeCraftingTool("craftingToolMortar", MultipleIconShapes.MORTAR, 64, 1.8, 1.5));
					registerShape(registryShapeTool, 6, new ShapeCraftingTool("craftingToolSaw", MultipleIconShapes.SAW, 64, 1.5, 2));
					registerShape(registryShapeTool, 7, new ShapeCraftingTool("craftingToolScythe", MultipleIconShapes.SCYTHE, 128, 1.2, 1.5));
					registerShape(registryShapeTool, 8, new ShapeCraftingTool("craftingToolWrench", MultipleIconShapes.WRENCH, 64, 1, 2));
					registerShape(registryShapeTool, 9, new ShapeCraftingTool("craftingToolPlateMold", MultipleIconShapes.PLATE_MOLD, 64, 1.2, 2));
				}

				process_loaderItem_materialMatrix(loaderItem_craftingTools, loaderCreativeTab, "craftingTools",
					registryShapeTool, registryMaterialItem,
					null, (itemMir50, containerMetaItem) -> {

						containerMetaItem_craftingTools = containerMetaItem;

						itemMir50.setTextureName("minecraft:apple");
						itemMir50.setFull3D();
						itemMir50.setMaxStackSize(1);

					});
			}
		}

		{
			IInstanceRegistry<MaterialMirageCrops5> registryMaterialBlock = new InstanceRegistryArray<>(new MaterialMirageCrops5[1000]);
			HelpersModuleMaterial.registryMaterialBlock = registryMaterialBlock;
			{
				registerMaterial(registryMaterialBlock, 0, "calcite");
				registerMaterial(registryMaterialBlock, 1, "magnesite");
				registerMaterial(registryMaterialBlock, 2, "siderite");
				registerMaterial(registryMaterialBlock, 3, "rhodochrosite");
				registerMaterial(registryMaterialBlock, 4, "smithsonite");
				registerMaterial(registryMaterialBlock, 5, "sphaerocobaltite");
				registerMaterial(registryMaterialBlock, 6, "gaspeite");
				registerMaterial(registryMaterialBlock, 7, "otavite");

				registerMaterial(registryMaterialBlock, 8, "apatite");
				registerMaterial(registryMaterialBlock, 9, "fluorite");

				registerMaterial(registryMaterialBlock, 10, "spinachium");
				registerMaterial(registryMaterialBlock, 11, "miragium");
			}

			{
				IInstanceRegistry<ShapeBlockMirageCrops5> registryShapeBlock = new InstanceRegistryArray<>(new ShapeBlockMirageCrops5[4]);
				{
					registerShape(registryShapeBlock, 0, new ShapeBlockOre("ore", loaderBlock_oreCalciteGroup));
					registerShape(registryShapeBlock, 1, new ShapeBlockBlock("block", loaderBlock_blockCalciteGroup));
				}

				registryShapeBlock.forEach((indexShape, nameShape, shape) -> {
					process_loaderBlock_materialMatrix(shape.loaderBlock, loaderCreativeTab,
						shape.getName() + "CalciteGroup", shape, registryMaterialBlock, (blockMir50, containerMetaBlock) -> {

							containerMetaBlocks.put(shape.getName(), containerMetaBlock);

						}, null);
				});
			}
		}

		add(new LoaderRecipe(() -> {

			MetaItem metaItem = containerMetaItem_materials.get(
				HelpersModuleMaterial.registryShapeMaterial.getIndexByName("dust")
					* (HelpersModuleMaterial.registryMaterialItem.getMaxIndex() + 1)
					+ HelpersModuleMaterial.registryMaterialItem.getIndexByName("apatite"));

			metaItem.virtualClass.override(new AdaptorItemEventsFertilizer((ItemMir50) loaderItem_materials.get(), metaItem));

		}));

		add(new LoaderRecipe(() -> {

			addRecipeOreBreakingAmount("calcite", (random, fortune) -> 4 + random.nextInt(1 + 4 + 4 * fortune));

			addRecipeOreBreakingAmount("magnesite", (random, fortune) -> 2 + random.nextInt(1 + 2 + 2 * fortune));
			addRecipeOreBreakingAmount("siderite", (random, fortune) -> 2 + random.nextInt(1 + 2 + 2 * fortune));
			addRecipeOreBreakingAmount("rhodochrosite", (random, fortune) -> 2 + random.nextInt(1 + 2 + 2 * fortune));

			addRecipeOreBreakingAmount("smithsonite", (random, fortune) -> 1 + random.nextInt(1 + fortune));
			addRecipeOreBreakingAmount("sphaerocobaltite", (random, fortune) -> 1 + random.nextInt(1 + fortune));
			addRecipeOreBreakingAmount("gaspeite", (random, fortune) -> 1 + random.nextInt(1 + fortune));

			addRecipeOreBreakingAmount("otavite", (random, fortune) -> 1 + random.nextInt(1 + fortune));

			addRecipeOreBreakingAmount("apatite", (random, fortune) -> 1 + random.nextInt(1 + fortune));
			addRecipeOreBreakingAmount("fluorite", (random, fortune) -> 1 + random.nextInt(1 + fortune));

			addRecipeOreBreakingItemStack("miragium",
				(random, fortune) -> HelpersOreDictionary.copyOrThrow("dustMiragium", 1 + random.nextInt(1 + fortune)));

			{
				BlockMir50 blockMir50 = (BlockMir50) loaderBlock_oreCalciteGroup.get();
				MetaBlock metaBlock = containerMetaBlocks.get("ore").get(HelpersModuleMaterial.registryMaterialBlock.getIndexByName("miragium"));
				metaBlock.getVirtualClass().override(new AdaptorBlockEventsOverriding(blockMir50, metaBlock) {

					@SuppressWarnings("all")
					@Override
					@Deprecated
					public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z)
					{
						ItemStack itemStack = player.getHeldItem();
						if (itemStack != null) {
							Item item = itemStack.getItem();
							if (item instanceof ItemCraftingPickaxeApatite) {

								itemStack.damageItem(itemStack.getMaxDamage() - itemStack.getItemDamage() + 1, player);

							}
						}

						return super.removedByPlayer(world, player, x, y, z);
					}

					@Override
					public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata)
					{
						ItemStack itemStack = player.getHeldItem();
						if (itemStack != null) {
							Item item = itemStack.getItem();
							if (item instanceof ItemCraftingPickaxeApatite) {

								player.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(owner)], 1);
								player.addExhaustion(0.025F);

								owner.dropBlockAsItem(world, x, y, z,
									HelpersOreDictionary.copyOrThrow("craftingStoneMirageFairy"));

								if (itemStack.stackSize == 0) {
									player.destroyCurrentEquippedItem();
								}

								return;
							}
						}

						super.harvestBlock(world, player, x, y, z, metadata);
					}

				});
			}

		}));

		add(new LoaderOreDictionary(() -> {
			for (Tuple<String, Supplier<ItemStack>> entry : scheduleRegisterOreDictionary) {
				OreDictionary.registerOre(entry.getX(), entry.getY().get());
			}
		}));

		add(new LoaderRecipe(() -> {
			scheduleRegisterRecipe.forEach(r -> r.run());
		}));

	}

	private void addRecipeOreBreakingAmount(String nameMaterial, BiFunction<Random, Integer, Integer> functionAmount)
	{
		HelpersModuleMaterial.recipesOreBreaking.add(
			"ore" + HelpersString.toUpperCaseHead(nameMaterial), (fortune, random) -> {
				ArrayList<ItemStack> outputs = new ArrayList<>();

				outputs.add(HelpersOreDictionary.copyOrThrow(
					"gem" + HelpersString.toUpperCaseHead(nameMaterial),
					functionAmount.apply(random, fortune)));

				return outputs;
			});
	}

	private void addRecipeOreBreakingItemStack(String nameMaterial, BiFunction<Random, Integer, ItemStack> functionItemStack)
	{
		HelpersModuleMaterial.recipesOreBreaking.add(
			"ore" + HelpersString.toUpperCaseHead(nameMaterial), (fortune, random) -> {
				ArrayList<ItemStack> outputs = new ArrayList<>();

				outputs.add(functionItemStack.apply(random, fortune));

				return outputs;
			});
	}

	private static void registerMaterial(MaterialMirageCrops5 material)
	{
		HelpersModuleMaterial.registryMaterial.put(material.getName(), material);
	}

	private static void registerMaterial(IInstanceRegistry<MaterialMirageCrops5> dest, int metaId, String name)
	{
		MaterialMirageCrops5 material = HelpersModuleMaterial.registryMaterial.get(name);
		if (material == null) throw new RuntimeException("Unknown Material: " + name);
		dest.register(metaId, name, material);
	}

	private static <I extends ShapeMirageCrops5> void registerShape(IInstanceRegistry<I> dest, int index, I shape)
	{
		dest.register(index, shape.getName(), shape);
	}

	private void process_loaderItem_materialMatrix(
		LoaderItem loaderItem,
		LoaderCreativeTab loaderCreativeTab,
		String unlocalizedName,
		IInstanceRegistry<ShapeItemMirageCrops5> shapes,
		IInstanceRegistry<MaterialMirageCrops5> materials,
		BiConsumer<ItemMir50, ContainerMetaItem> beforeMake,
		BiConsumer<ItemMir50, ContainerMetaItem> afterMake)
	{
		if (beforeMake == null) beforeMake = (itemMir50, containerMetaItem) -> {};
		if (afterMake == null) afterMake = (itemMir50, containerMetaItem) -> {};

		process_loaderItem_multi(loaderItem, loaderCreativeTab, unlocalizedName,
			(shapes.getMaxIndex() + 1) * (materials.getMaxIndex() + 1),
			beforeMake, afterMake.andThen((itemMir50, containerMetaItem) -> {

				shapes.forEach((indexShape, nameShape, shape) -> {
					materials.forEach((indexMaterial, nameMaterial, material) -> {

						int metaId = indexShape * (materials.getMaxIndex() + 1) + indexMaterial;

						Runnable recipeSetter = material.isExistAndGetRecipeSetter(shape, itemMir50, metaId);
						if (recipeSetter != null) {
							scheduleRegisterRecipe.add(recipeSetter);

							MetaItem metaItem = createMetaItemMaterialMatrix(itemMir50, metaId, shape.getName(), material.getName());
							containerMetaItem.set(metaId, metaItem);

							shape.onAppliedMetaItem(material, itemMir50, metaItem, metaId,
								scheduleRegisterOreDictionary, scheduleRegisterRecipe);

						}

					});
				});

			}));
	}

	private void process_loaderBlock_materialMatrix(
		LoaderBlock loaderBlock,
		LoaderCreativeTab loaderCreativeTab,
		String unlocalizedName,
		ShapeBlockMirageCrops5 shape,
		IInstanceRegistry<MaterialMirageCrops5> materials,
		BiConsumer<BlockMir50, ContainerMetaBlock> beforeMake,
		BiConsumer<BlockMir50, ContainerMetaBlock> afterMake)
	{
		if (beforeMake == null) beforeMake = (blockMir50, containerMetaBlock) -> {};
		if (afterMake == null) afterMake = (blockMir50, containerMetaBlock) -> {};

		process_loaderBlock_multi(loaderBlock, loaderCreativeTab, unlocalizedName, ItemBlockMulti.class,
			beforeMake.andThen((blockMir50, containerMetaBlock) -> {

				blockMir50.setHardness(1.5F);
				blockMir50.setResistance(10.0F);

				blockMir50.setBlockName(unlocalizedName);
				blockMir50.setBlockTextureName("minecraft:stone");

			}), afterMake.andThen((blockMir50, containerMetaBlock) -> {

				materials.forEach((metaId, nameMaterial, material) -> {

					Runnable recipeSetter = material.isExistAndGetRecipeSetter(shape, blockMir50, metaId);
					if (recipeSetter != null) {
						scheduleRegisterRecipe.add(recipeSetter);

						MetaBlock metaBlock = createMetaBlockMaterialMatrix(blockMir50, metaId, shape.getName(), material.getName());
						containerMetaBlock.set(metaId, metaBlock);

						shape.onAppliedMetaBlock(material, blockMir50, metaBlock, metaId,
							scheduleRegisterOreDictionary, scheduleRegisterRecipe);

					}

				});

			}));
	}

	private MetaItem createMetaItemMaterialMatrix(
		ItemMir50 itemMir50,
		int metaId,
		String nameShape,
		String nameMaterial)
	{
		MetaItem metaItem = new MetaItem(itemMir50, metaId);

		String unlocalizedName = nameShape + HelpersString.toUpperCaseHead(nameMaterial);

		// 素材マトリクスじゃなくても行う初期化
		metaItem.virtualClass.override(new AdaptorItemNameAutonomy(itemMir50, metaItem, unlocalizedName));
		metaItem.virtualClass.override(new AdaptorItemSubItemsMetaItem(itemMir50, metaItem, metaItem));

		// マトリクス用のアルゴリズムのアイテム名の設定
		metaItem.virtualClass.override(new AdaptorItemNameMaterial(itemMir50, metaItem, nameShape, nameMaterial));

		// 分子式の表示
		metaItem.virtualClass.override(new AdaptorItemNameInformation(itemMir50, metaItem,
			(itemStack, player, strings, shift) -> {
				if (HelpersModuleMaterial.registryMaterialProperty.hasChemicalFormula(nameMaterial)) {
					strings.add(HelpersModuleMaterial.registryMaterialProperty.getChemicalFormula(nameMaterial));
				}
			}));

		// 鉱石辞書に登録（素材全般での初期化）
		scheduleRegisterOreDictionary.add(new Tuple<>(unlocalizedName, () -> new ItemStack(itemMir50, 1, metaId)));

		return metaItem;
	}

	private MetaBlock createMetaBlockMaterialMatrix(
		BlockMir50 blockMir50,
		int metaId,
		String nameShape,
		String nameMaterial)
	{
		MetaBlock metaBlock = new MetaBlock(blockMir50, metaId);

		String unlocalizedName = nameShape + HelpersString.toUpperCaseHead(nameMaterial);

		// 素材マトリクスじゃなくても行う初期化
		metaBlock.virtualClass.override(new AdaptorBlockSubBlocksMetaBlock(blockMir50, metaBlock, metaBlock));
		metaBlock.virtualClass.override(new AdaptorBlockHarvestMetaBlock(blockMir50, metaBlock));

		// 名前の設定
		metaBlock.virtualClass.override(new AdaptorBlockNameAutonomy(blockMir50, metaBlock, unlocalizedName));
		metaBlock.virtualClass.register(IAdaptorBlockNameExtra.class, new AdaptorBlockNameExtra(blockMir50, metaBlock));

		// 鉱石辞書に登録（素材全般での初期化）
		scheduleRegisterOreDictionary.add(new Tuple<>(unlocalizedName, () -> new ItemStack(blockMir50, 1, metaId)));

		return metaBlock;
	}

}
