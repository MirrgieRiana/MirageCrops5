package mirrg_miragecrops5.fairytype;

import java.util.Arrays;
import java.util.List;

import mirrg.he.math.HelpersString;
import mirrg_miragecrops5.ModuleMirageCropsAbstract;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModuleFairyType extends ModuleMirageCropsAbstract
{

	@FunctionalInterface
	interface IConsumerFairyTypeInt
	{

		public void apply(FairyType fairyType, double level);

	}

	public static IConsumerFairyTypeInt fairySkillNeg(
		double ph, double in, double em, double lo, double ma, double tr)
	{
		return (fairyType, level) -> {
			level *= Math.pow(0.8, level - 1);
			fairyType.addValues(
				(int) (ph * level * 10),
				(int) (in * level * 10),
				(int) (em * level * 10),
				(int) (lo * level * 10),
				(int) (ma * level * 10),
				(int) (tr * level * 10));
		};
	}

	public static IConsumerFairyTypeInt fairySkill(
		double ph, double in, double em, double lo, double ma, double tr)
	{
		return (fairyType, level) -> {
			level *= Math.pow(1.2, level - 1);
			fairyType.addValues(
				(int) (ph * level * 10),
				(int) (in * level * 10),
				(int) (em * level * 10),
				(int) (lo * level * 10),
				(int) (ma * level * 10),
				(int) (tr * level * 10));
		};
	}

	public static IConsumerFairyTypeInt SOIL = fairySkillNeg(0, -1, 0, 0, 0, 0);
	public static IConsumerFairyTypeInt MINERAL = fairySkillNeg(-1, 0, 0, 0, 0, 0);
	public static IConsumerFairyTypeInt FARM = fairySkillNeg(0, -0.5, -0.5, 0, 0, 0);
	public static IConsumerFairyTypeInt ART = fairySkillNeg(0, 0, -1, 0, 0, 0);

	public static IConsumerFairyTypeInt MATERIAL = fairySkill(0, 1, 0, 0, 0, 0);
	public static IConsumerFairyTypeInt FOOD = fairySkill(0, 0, 1, 0, 0, 0);
	public static IConsumerFairyTypeInt SENSE = fairySkill(0, 0, 0.6, 0, 0.4, 0);
	public static IConsumerFairyTypeInt PLANT = fairySkill(0, 0, 0.8, 0, 0.2, 0);
	public static IConsumerFairyTypeInt MONEY = fairySkill(1, 0, 0, 0, 0, 0);
	public static IConsumerFairyTypeInt MYSTERY = fairySkill(0, 0, 0, 0, 1, 0);
	public static IConsumerFairyTypeInt LOGIC = fairySkill(0, 0, 0, 1, 0, 0);
	public static IConsumerFairyTypeInt MIRAGE = fairySkill(0, 0, 0, 0.8, 0.2, 0);
	public static IConsumerFairyTypeInt ORE = fairySkill(1, 0, 0, 0, 0, 0);
	public static IConsumerFairyTypeInt LAND = fairySkill(1, 0, 0, 0, 0, 0);
	public static IConsumerFairyTypeInt WEAPON = fairySkill(0, 0, 0, 0.5, 0.5, 0);
	public static IConsumerFairyTypeInt ORDER = fairySkill(0, 0, 0, 0.5, 0.4, 0.1);
	public static IConsumerFairyTypeInt FREEZE = fairySkill(0, 0.5, 0, 0, 0.5, 0);
	public static IConsumerFairyTypeInt HEAT = fairySkill(0, 0.5, 0, 0, 0.5, 0);
	public static IConsumerFairyTypeInt FUEL = fairySkill(0, 1, 0, 0, 0, 0);
	public static IConsumerFairyTypeInt ENTROPY = fairySkill(0, 0, 0.2, 0, 0.7, 0.1);

	public ModuleFairyType()
	{

		List<String> shapes = Arrays.asList("dust", "ore", "block", "ingot", "gem");

		add(new LoaderFairyType("iron", fairyType -> {
			fairyType.setColors(0xaaaaaa, 0x969696, 0xD8D8D8, 0x727272);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			MINERAL.apply(fairyType, 3);
			MATERIAL.apply(fairyType, 3);
		}));
		add(new LoaderFairyType("gold", fairyType -> {
			fairyType.setColors(0xaaaaaa, 0xDEDE00, 0xFFFF8B, 0xDC7613);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			MINERAL.apply(fairyType, 4);
			SENSE.apply(fairyType, 4);
		}));
		add(new LoaderFairyType("apatite", fairyType -> {
			fairyType.setColors(0xA4DBDB, 0x22ABFF, 0x5DC8FF, 0x107FCE);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			MINERAL.apply(fairyType, 2);
			SENSE.apply(fairyType, 1);
			PLANT.apply(fairyType, 1);
		}));
		add(new LoaderFairyType("redstone", fairyType -> {
			fairyType.setColors(0xA50000, 0xFF0000, 0x490000);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			MINERAL.apply(fairyType, 3);
			MYSTERY.apply(fairyType, 1);
			LOGIC.apply(fairyType, 2);
		}));
		add(new LoaderFairyType("fluorite", fairyType -> {
			fairyType.setColors(0xA4DBDB, 0x40CA65, 0x8947E0, 0x3A8FC);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			MINERAL.apply(fairyType, 3);
			SENSE.apply(fairyType, 3);
		}));
		add(new LoaderFairyType("calcite", fairyType -> {
			fairyType.setColors(0xA4DBDB, 0xD5D6C8, 0xD5D6C8, 0xD5D6C8);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			MINERAL.apply(fairyType, 1);
			MATERIAL.apply(fairyType, 1);
		}));
		add(new LoaderFairyType("spinachium", fairyType -> {
			fairyType.setColors(0xaaaaaa, 0x039F00, 0x1DFF00, 0x169900);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			MINERAL.apply(fairyType, 4);
			MIRAGE.apply(fairyType, 3);
			PLANT.apply(fairyType, 1);
		}));
		add(new LoaderFairyType("miragium", fairyType -> {
			fairyType.setColors(0xaaaaaa, 0xFFDBDB, 0xDAE0F6, 0xB6DBBC);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			MINERAL.apply(fairyType, 4);
			MIRAGE.apply(fairyType, 4);
		}));
		add(new LoaderFairyType("quartz", fairyType -> {
			fairyType.setColors(0xA4DBDB, 0xBCADA1, 0xD4CCC3, 0x5D4A3F);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.quartz, 1, 0));
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			MINERAL.apply(fairyType, 2);
			ORDER.apply(fairyType, 2);
		}));
		add(new LoaderFairyType("dirt", fairyType -> {
			fairyType.setColors(0x7B573B, 0x9F724E, 0x593D29);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.dirt, 1, 0));
			SOIL.apply(fairyType, 1);
			LAND.apply(fairyType, 0.5);
			PLANT.apply(fairyType, 0.5);
		}));
		add(new LoaderFairyType("sand", fairyType -> {
			fairyType.setColors(0xD2CB95, 0xE0D7A6, 0xB0AA72);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.sand, 1, 0));
			SOIL.apply(fairyType, 1);
			LAND.apply(fairyType, 1);
		}));
		add(new LoaderFairyType("gravel", fairyType -> {
			fairyType.setColors(0x918E8E, 0x7A7673, 0xAA9E98);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.gravel, 1, 0));
			SOIL.apply(fairyType, 2);
			LAND.apply(fairyType, 2);
		}));
		add(new LoaderFairyType("stone", fairyType -> {
			fairyType.setColors(0x808080, 0x8F8F8F, 0x686868);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.stone, 1, 0));
			SOIL.apply(fairyType, 3);
			ORE.apply(fairyType, 2);
			MATERIAL.apply(fairyType, 1);
		}));
		add(new LoaderFairyType("cobblestone", fairyType -> {
			fairyType.setColors(0x7F7F7F, 0xBFBFBF, 0x4C4C4C);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.cobblestone, 1, 0));
			SOIL.apply(fairyType, 2);
			MATERIAL.apply(fairyType, 2);
		}));
		add(new LoaderFairyType("log", fairyType -> {
			fairyType.setColors(0x685332, 0xB6935C, 0x372A17);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.log, 1, OreDictionary.WILDCARD_VALUE));
			FARM.apply(fairyType, 2);
			MATERIAL.apply(fairyType, 2);
		}));
		add(new LoaderFairyType("water", fairyType -> {
			fairyType.setColors(0x0407CE, 0x345FDA, 0x345FDA, 0x2749A5);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.water_bucket, 1, 0));
			SOIL.apply(fairyType, 1);
			MATERIAL.apply(fairyType, 0.5);
			FOOD.apply(fairyType, 0.5);
		}));
		add(new LoaderFairyType("lava", fairyType -> {
			fairyType.setColors(0x0407CE, 0xDC8638, 0xE4D25C, 0xCC4628);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.lava_bucket, 1, 0));
			MINERAL.apply(fairyType, 2);
			WEAPON.apply(fairyType, 2);
		}));
		add(new LoaderFairyType("milk", fairyType -> {
			fairyType.setColors(0x0407CE, 0xFFFFFF, 0xFFFFFF, 0x3D220F);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.milk_bucket, 1, 0));
			FARM.apply(fairyType, 2);
			FOOD.apply(fairyType, 2);
		}));
		add(new LoaderFairyType("ice", fairyType -> {
			fairyType.setColors(0xA4DBDB, 0x77A9FF, 0x77A9FF, 0xFFFFFF);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.ice, 1, 0));
			SOIL.apply(fairyType, 2);
			FREEZE.apply(fairyType, 2);
		}));

	}

}
