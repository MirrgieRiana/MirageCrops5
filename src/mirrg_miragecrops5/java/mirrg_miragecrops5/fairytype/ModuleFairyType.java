package mirrg_miragecrops5.fairytype;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntUnaryOperator;

import mirrg.he.math.HelpersMath;
import mirrg.he.math.HelpersString;
import mirrg_miragecrops5.ModuleMirageCropsAbstract;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModuleFairyType extends ModuleMirageCropsAbstract
{

	public static IFairySkill SOIL = new FairySkillNegative("soil", -1, 0, 0, 0, 0, 0);
	public static IFairySkill MINERAL = new FairySkillNegative("mineral", 0, -1, 0, 0, 0, 0);
	public static IFairySkill FARM = new FairySkillNegative("farm", 0, 0, -1, 0, 0, 0);
	public static IFairySkill FACTORY = new FairySkillNegative("factory", 0, -1, 0, 0, 0, 0);
	public static IFairySkill ART = new FairySkillNegative("art", 0, 0, -1, 0, 0, 0);
	public static IFairySkill LABOR = new FairySkillNegative("labor", -1, 0, 0, 0, 0, 0);
	public static IFairySkill ANIMAL = new FairySkillNegative("animal", 0, 0, -1, 0, 0, 0);
	public static IFairySkill ADVENTURE = new FairySkillNegative("adventure", -1, 0, 0, 0, 0, 0);
	public static IFairySkill INTELLIGENCE = new FairySkillNegative("intelligence", 0, 0, 0, -1, 0, 0);
	public static IFairySkill THAUMATURGY = new FairySkillNegative("thaumaturgy", 0, 0, 0, 0, -1, 0);
	public static IFairySkill PHENOMENON = new FairySkillNegative("phenomenon", -1, 0, 0, 0, 0, 0);

	public static IFairySkill LAND = new FairySkillPositive("land", 2, 0, 0, 0, 0, 0);

	public static IFairySkill MATERIAL = new FairySkillPositive("material", 0, 1, 0, 0, 0, 0);
	public static IFairySkill FOOD = new FairySkillPositive("food", 0, 0, 1, 0, 0, 0);
	public static IFairySkill SENSE = new FairySkillPositive("sense", 0, 0, 0.8, 0, 0.2, 0);
	public static IFairySkill PLANT = new FairySkillPositive("plant", 0, 0, 1, 0, 0, 0);
	public static IFairySkill MONEY = new FairySkillPositive("money", 1, 0, 0, 0, 0, 0);
	public static IFairySkill MYSTERY = new FairySkillPositive("mystery", 0, 0, 0, 0, 1, 0);
	public static IFairySkill LOGIC = new FairySkillPositive("logic", 0, 0, 0, 1, 0, 0);
	public static IFairySkill MIRAGE = new FairySkillPositive("mirage", 0, 0, 0, 0.8, 0.2, 0);
	public static IFairySkill ORE = new FairySkillPositive("ore", 0, 1, 0, 0, 0, 0);
	public static IFairySkill WEAPON = new FairySkillPositive("weapon", 0, 0, 0, 0.6, 0.4, 0);
	public static IFairySkill ORDER = new FairySkillPositive("order", 0, 0, 0, 0.5, 0.4, 0.1);
	public static IFairySkill FREEZE = new FairySkillPositive("freeze", 0, 0.5, 0, 0, 0.5, 0);
	public static IFairySkill HEAT = new FairySkillPositive("heat", 0, 0.5, 0, 0, 0.5, 0);
	public static IFairySkill FUEL = new FairySkillPositive("fuel", 0, 1, 0, 0, 0, 0);
	public static IFairySkill ENTROPY = new FairySkillPositive("entropy", 0, 0, 0.2, 0, 0.7, 0.1);
	public static IFairySkill ARMER = new FairySkillPositive("armer", 0.5, 0, 0.5, 0, 0, 0);
	public static IFairySkill MOVE = new FairySkillPositive("move", 1, 0, 0, 0, 0, 0);
	public static IFairySkill HELL = new FairySkillPositive("hell", 0, 0, 0.3, 0.2, 0.5, 0);
	public static IFairySkill ENDER = new FairySkillPositive("ender", 0, 0, 0, 0, 0.5, 0.5);

	private static List<String> shapes = Arrays.asList("dust", "ore", "block", "ingot", "gem");

	private void addLoaderMetal()
	{
		IntUnaryOperator toFairyIndex = i -> {
			int start = 0;
			int length = 300;
			if (i + start != HelpersMath.trim(i + start, start, start + length - 1)) throw new RuntimeException();
			return i + start;
		};
		int bodyColor = 0xAAAAAA;

		// null: 0
		add(new LoaderFairyType(toFairyIndex.applyAsInt(1), "iron", fairyType -> {
			fairyType.setColors(bodyColor, 0x969696, 0xD8D8D8, 0x727272);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 3);
			fairyType.addSkill(MATERIAL, 3);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(2), "gold", fairyType -> {
			fairyType.setColors(bodyColor, 0xDEDE00, 0xFFFF8B, 0xDC7613);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 4);
			fairyType.addSkill(SENSE, 2);
			fairyType.addSkill(MONEY, 2);
		}));
	}

	private void addLoaderMirageCrops()
	{
		IntUnaryOperator toFairyIndex = i -> {
			int start = 1400;
			int length = 100;
			if (i + start != HelpersMath.trim(i + start, start, start + length - 1)) throw new RuntimeException();
			return i + start;
		};
		int bodyColor = 0xAAAAAA;

		add(new LoaderFairyType(toFairyIndex.applyAsInt(0), "spinachium", fairyType -> {
			fairyType.setColors(bodyColor, 0x039F00, 0x1DFF00, 0x169900);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 4);
			fairyType.addSkill(MIRAGE, 3);
			fairyType.addSkill(PLANT, 1);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(1), "miragium", fairyType -> {
			fairyType.setColors(bodyColor, 0xFFDBDB, 0xDAE0F6, 0xB6DBBC);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 4);
			fairyType.addSkill(MIRAGE, 4);
		}));

	}

	private void addLoaderMineral()
	{
		IntUnaryOperator toFairyIndex = i -> {
			int start = 300;
			int length = 300;
			if (i + start != HelpersMath.trim(i + start, start, start + length - 1)) throw new RuntimeException();
			return i + start;
		};
		int bodyColor = 0xA4DBDB;

		add(new LoaderFairyType(toFairyIndex.applyAsInt(0), "apatite", fairyType -> {
			fairyType.setColors(bodyColor, 0x22ABFF, 0x5DC8FF, 0x107FCE);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 2);
			fairyType.addSkill(SENSE, 1);
			fairyType.addSkill(PLANT, 1);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(1), "fluorite", fairyType -> {
			fairyType.setColors(bodyColor, 0x40CA65, 0x8947E0, 0x3A8FC);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 3);
			fairyType.addSkill(SENSE, 3);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(2), "calcite", fairyType -> {
			fairyType.setColors(bodyColor, 0xD5D6C8, 0xD5D6C8, 0xD5D6C8);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 1);
			fairyType.addSkill(MATERIAL, 1);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(3), "quartz", fairyType -> {
			fairyType.setColors(bodyColor, 0xBCADA1, 0xD4CCC3, 0x5D4A3F);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.quartz, 1, 0));
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 2);
			fairyType.addSkill(ORDER, 2);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(4), "ice", fairyType -> {
			fairyType.setColors(bodyColor, 0x77A9FF, 0x77A9FF, 0xFFFFFF);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.ice, 1, 0));
			fairyType.addSkill(SOIL, 2);
			fairyType.addSkill(FREEZE, 2);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(5), "emerald", fairyType -> {
			fairyType.setColors(bodyColor, 0x2CC14C, 0x6EEB91, 0x2FB34B);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 4);
			fairyType.addSkill(MONEY, 4);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(6), "diamond", fairyType -> {
			fairyType.setColors(bodyColor, 0x2CCDB1, 0xA2F6E7, 0x1A7A6A);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 5);
			fairyType.addSkill(MATERIAL, 4);
			fairyType.addSkill(MONEY, 1);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(7), "lapis", fairyType -> {
			fairyType.setColors(bodyColor, 0x224BAF, 0x5A82E2, 0x0A2B7A);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 4);
			fairyType.addSkill(SENSE, 4);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(8), "glass", fairyType -> {
			fairyType.setColors(bodyColor, 0xeeeeee, 0xffffff, 0xdddddd);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.glass, 1, 0));
			fairyType.addSkill(FACTORY, 2);
			fairyType.addSkill(SENSE, 2);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(9), "obsidian", fairyType -> {
			fairyType.setColors(bodyColor, 0x433468, 0x482D87, 0x09090E);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.obsidian, 1, 0));
			fairyType.addSkill(MINERAL, 3);
			fairyType.addSkill(ARMER, 3);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(10), "redstone", fairyType -> {
			fairyType.setColors(bodyColor, 0xA50000, 0xFF0000, 0x490000);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 3);
			fairyType.addSkill(MYSTERY, 1);
			fairyType.addSkill(LOGIC, 2);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(11), "coal", fairyType -> {
			fairyType.setColors(bodyColor, 0x444444, 0x555555, 0x333333);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 1);
			fairyType.addSkill(FUEL, 1);
		}));

	}

	private void addLoaderLiquid()
	{
		IntUnaryOperator toFairyIndex = i -> {
			int start = 700;
			int length = 100;
			if (i + start != HelpersMath.trim(i + start, start, start + length - 1)) throw new RuntimeException();
			return i + start;
		};
		int bodyColor = 0x8C8CFF;

		add(new LoaderFairyType(toFairyIndex.applyAsInt(0), "water", fairyType -> {
			fairyType.setColors(bodyColor, 0x345FDA, 0x345FDA, 0x2749A5);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.water_bucket, 1, 0));
			fairyType.addSkill(SOIL, 1);
			fairyType.addSkill(MATERIAL, 0.5);
			fairyType.addSkill(FOOD, 0.5);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(1), "lava", fairyType -> {
			fairyType.setColors(bodyColor, 0xDC8638, 0xE4D25C, 0xCC4628);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.lava_bucket, 1, 0));
			fairyType.addSkill(MINERAL, 2);
			fairyType.addSkill(HEAT, 2);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(2), "milk", fairyType -> {
			fairyType.setColors(bodyColor, 0xFFFFFF, 0xFFFFFF, 0x3D220F);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.milk_bucket, 1, 0));
			fairyType.addSkill(FARM, 2);
			fairyType.addSkill(FOOD, 2);
		}));

	}

	private void addLoaderPlant()
	{
		IntUnaryOperator toFairyIndex = i -> {
			int start = 800;
			int length = 100;
			if (i + start != HelpersMath.trim(i + start, start, start + length - 1)) throw new RuntimeException();
			return i + start;
		};
		int bodyColor = 0x60FF60;

		add(new LoaderFairyType(toFairyIndex.applyAsInt(0), "wood", fairyType -> {
			fairyType.setColors(bodyColor, 0x685332, 0xB6935C, 0x372A17);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.log, 1, OreDictionary.WILDCARD_VALUE));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.planks, 1, OreDictionary.WILDCARD_VALUE));
			fairyType.addSkill(FARM, 1);
			fairyType.addSkill(MATERIAL, 1);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(1), "carrot", fairyType -> {
			fairyType.setColors(bodyColor, 0xD36A0D, 0xFFC177, 0xAC3900);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.carrot, 1, 0));
			fairyType.addSkill(FARM, 3);
			fairyType.addSkill(FOOD, 2);
			fairyType.addSkill(SENSE, 1);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(2), "apple", fairyType -> {
			fairyType.setColors(bodyColor, 0xD31623, 0xFFAAAF, 0x54090E);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.apple, 1, 0));
			fairyType.addSkill(FARM, 4);
			fairyType.addSkill(FOOD, 3);
			fairyType.addSkill(SENSE, 1);
		}));

	}

	private void addLoaderAnimal()
	{
		IntUnaryOperator toFairyIndex = i -> {
			int start = 900;
			int length = 100;
			if (i + start != HelpersMath.trim(i + start, start, start + length - 1)) throw new RuntimeException();
			return i + start;
		};
		int bodyColor = 0xFFC9D1;

		add(new LoaderFairyType(toFairyIndex.applyAsInt(0), "slimeball", fairyType -> {
			fairyType.setColors(bodyColor, 0x508049, 0x7DC873, 0x34532F);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.slime_ball, 1, 0));
			fairyType.addSkill(ANIMAL, 3);
			fairyType.addSkill(MATERIAL, 3);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(1), "bone", fairyType -> {
			fairyType.setColors(bodyColor, 0xEDEBCA, 0xffffff, 0xBCBBAF);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.bone, 1, 0));
			fairyType.addSkill(ANIMAL, 2);
			fairyType.addSkill(MATERIAL, 2);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(2), "meat", fairyType -> {
			fairyType.setColors(bodyColor, 0xFF7777, 0xFFE1D4, 0xE24940);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.beef, 1, 0));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.porkchop, 1, 0));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.chicken, 1, 0));
			fairyType.addSkill(ANIMAL, 3);
			fairyType.addSkill(FOOD, 3);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(3), "egg", fairyType -> {
			fairyType.setColors(bodyColor, 0xffffff, 0xFFAAAF, 0xDFCE9B);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.egg, 1, 0));
			fairyType.addSkill(ANIMAL, 2);
			fairyType.addSkill(FOOD, 2);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(4), "enderpearl", fairyType -> {
			fairyType.setColors(bodyColor, 0x349988, 0x8CF4E2, 0x032620);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.ender_pearl, 1, 0));
			fairyType.addSkill(ANIMAL, 4);
			fairyType.addSkill(MYSTERY, 1);
			fairyType.addSkill(ENDER, 2);
			fairyType.addSkill(MOVE, 1);
		}));

	}

	private void addLoaderSoil()
	{
		IntUnaryOperator toFairyIndex = i -> {
			int start = 1000;
			int length = 100;
			if (i + start != HelpersMath.trim(i + start, start, start + length - 1)) throw new RuntimeException();
			return i + start;
		};
		int bodyColor = 0xC17C43;

		add(new LoaderFairyType(toFairyIndex.applyAsInt(0), "dirt", fairyType -> {
			fairyType.setColors(bodyColor, 0x7B573B, 0x9F724E, 0x593D29);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.dirt, 1, 0));
			fairyType.addSkill(SOIL, 1);
			fairyType.addSkill(LAND, 0.5);
			fairyType.addSkill(PLANT, 0.5);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(1), "sand", fairyType -> {
			fairyType.setColors(bodyColor, 0xD2CB95, 0xE0D7A6, 0xB0AA72);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.sand, 1, 0));
			fairyType.addSkill(SOIL, 2);
			fairyType.addSkill(LAND, 2);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(2), "gravel", fairyType -> {
			fairyType.setColors(bodyColor, 0x7A7673, 0xAA9E98, 0x918E8E);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.gravel, 1, 0));
			fairyType.addSkill(SOIL, 2);
			fairyType.addSkill(LAND, 1);
			fairyType.addSkill(ORE, 1);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(3), "stone", fairyType -> {
			fairyType.setColors(bodyColor, 0x808080, 0x8F8F8F, 0x686868);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.stone, 1, 0));
			fairyType.addSkill(SOIL, 1);
			fairyType.addSkill(LAND, 1);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(4), "cobblestone", fairyType -> {
			fairyType.setColors(bodyColor, 0x7F7F7F, 0xBFBFBF, 0x4C4C4C);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.cobblestone, 1, 0));
			fairyType.addSkill(SOIL, 1);
			fairyType.addSkill(MATERIAL, 1);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(5), "snow", fairyType -> {
			fairyType.setColors(bodyColor, 0xffffff, 0xffffff, 0xffffff);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.snow, 1, 0));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.snowball, 1, 0));
			fairyType.addSkill(SOIL, 1);
			fairyType.addSkill(FREEZE, 1);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(6), "clay", fairyType -> {
			fairyType.setColors(bodyColor, 0x666B7F, 0xA5A9B9, 0x373944);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.clay_ball, 1, 0));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.clay, 1, 0));
			fairyType.addSkill(ADVENTURE, 3);
			fairyType.addSkill(MATERIAL, 3);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(7), "netherrack", fairyType -> {
			fairyType.setColors(bodyColor, 0x772828, 0x86413B, 0x482525);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.netherrack, 1, 0));
			fairyType.addSkill(SOIL, 3);
			fairyType.addSkill(HELL, 2);
			fairyType.addSkill(LAND, 1);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(8), "endstone", fairyType -> {
			fairyType.setColors(bodyColor, 0xBCBC89, 0xE9EAB5, 0xC3BD89);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.end_stone, 1, 0));
			fairyType.addSkill(SOIL, 4);
			fairyType.addSkill(ENDER, 2);
			fairyType.addSkill(LAND, 2);
		}));

	}

	private void addLoaderArtifact()
	{
		IntUnaryOperator toFairyIndex = i -> {
			int start = 1100;
			int length = 300;
			if (i + start != HelpersMath.trim(i + start, start, start + length - 1)) throw new RuntimeException();
			return i + start;
		};

		add(new LoaderFairyType(toFairyIndex.applyAsInt(0), "sugar", fairyType -> {
			fairyType.setColors(0xffffff, 0xffffff, 0xffffff);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.sugar, 1, 0));
			fairyType.addSkill(FACTORY, 2);
			fairyType.addSkill(FOOD, 2);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(1), "pickaxe", fairyType -> {
			fairyType.setColors(0x896727, 0x7F7F7F, 0x898989, 0x494949);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.wooden_pickaxe, 1, 0));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.stone_pickaxe, 1, 0));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.iron_pickaxe, 1, 0));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.golden_pickaxe, 1, 0));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.diamond_pickaxe, 1, 0));
			fairyType.addSkill(LABOR, 2);
			fairyType.addSkill(ORE, 2);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(2), "chestplate", fairyType -> {
			fairyType.setColors(0x896727, 0x7F7F7F, 0x898989, 0x494949);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.leather_chestplate, 1, 0));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.iron_chestplate, 1, 0));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.golden_chestplate, 1, 0));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.diamond_chestplate, 1, 0));
			fairyType.addSkill(FACTORY, 3);
			fairyType.addSkill(ARMER, 3);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(3), "book", fairyType -> {
			fairyType.setColors(0xD6D6D6, 0x38280C, 0x654B17, 0x261B08);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.book, 1, 0));
			fairyType.addSkill(FACTORY, 5);
			fairyType.addSkill(ORDER, 5);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(4), "writtenbook", fairyType -> {
			fairyType.setColors(0x111111, 0x38280C, 0x654B17, 0x261B08);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.written_book, 1, 0));
			fairyType.addSkill(INTELLIGENCE, 5);
			fairyType.addSkill(LOGIC, 2);
			fairyType.addSkill(MYSTERY, 2);
			fairyType.addSkill(SENSE, 1);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(5), "enchantedbook", fairyType -> {
			fairyType.setColors(0xAA8CFF, 0x38280C, 0x654B17, 0x261B08);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.enchanted_book, 1, 0));
			fairyType.addSkill(THAUMATURGY, 5);
			fairyType.addSkill(LOGIC, 2);
			fairyType.addSkill(MYSTERY, 2);
			fairyType.addSkill(SENSE, 1);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(6), "brick", fairyType -> {
			fairyType.setColors(0xffffff, 0x7F3E2C, 0xB75A40, 0x3A1C14);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.brick, 1, 0));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.brick_block, 1, 0));
			fairyType.addSkill(FACTORY, 3);
			fairyType.addSkill(ARMER, 2);
			fairyType.addSkill(SENSE, 1);
		}));
		add(new LoaderFairyType(toFairyIndex.applyAsInt(7), "fire", fairyType -> {
			fairyType.setColors(0xFF1F00, 0xBD5400, 0xD17F07, 0xF5E28C);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.fire_charge, 1, 0));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.flint_and_steel, 1, 0));
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.fire, 1, 0));
			fairyType.addSkill(PHENOMENON, 2);
			fairyType.addSkill(HEAT, 2);
		}));

	}

	public ModuleFairyType()
	{
		addLoaderMetal();
		addLoaderMirageCrops();
		addLoaderMineral();
		addLoaderLiquid();
		addLoaderPlant();
		addLoaderAnimal();
		addLoaderSoil();
		addLoaderArtifact();
	}

}
