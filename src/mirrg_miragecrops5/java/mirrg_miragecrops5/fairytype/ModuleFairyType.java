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

	public static abstract class FairySkillAbstract implements IFairySkill
	{

		protected String name;
		protected double[] values;

		public FairySkillAbstract(String name, double ph, double in, double em, double lo, double ma, double tr)
		{
			this.name = name;
			values = new double[] {
				ph, in, em, lo, ma, tr,
			};
		}

		@Override
		public String getName()
		{
			return name;
		}

		@Override
		public boolean isPositive()
		{
			return false;
		}

	}

	public static class FairySkillNegative extends FairySkillAbstract
	{

		public FairySkillNegative(String name, double ph, double in, double em, double lo, double ma, double tr)
		{
			super(name, ph, in, em, lo, ma, tr);
		}

		@Override
		public void increase(int[] values, double level)
		{
			level *= Math.pow(0.8, level - 1);
			level *= 0.7;
			for (int i = 0; i < 6; i++) {
				values[i] += (int) (this.values[i] * level * 10);
			}
		}

	}

	public static class FairySkillPositive extends FairySkillAbstract
	{

		public FairySkillPositive(String name, double ph, double in, double em, double lo, double ma, double tr)
		{
			super(name, ph, in, em, lo, ma, tr);
		}

		@Override
		public void increase(int[] values, double level)
		{
			level *= Math.pow(1.1, level - 1);
			level *= 1.0;
			for (int i = 0; i < 6; i++) {
				values[i] += (int) (this.values[i] * level * 10);
			}
		}

	}

	public static IFairySkill SOIL = new FairySkillNegative("soil", 0, -1, 0, 0, 0, 0);
	public static IFairySkill MINERAL = new FairySkillNegative("mineral", -1, 0, 0, 0, 0, 0);
	public static IFairySkill FARM = new FairySkillNegative("farm", 0, -0.5, -0.5, 0, 0, 0);
	public static IFairySkill ART = new FairySkillNegative("art", 0, 0, -1, 0, 0, 0);

	public static IFairySkill MATERIAL = new FairySkillPositive("material", 0, 1, 0, 0, 0, 0);
	public static IFairySkill FOOD = new FairySkillPositive("food", 0, 0, 1, 0, 0, 0);
	public static IFairySkill SENSE = new FairySkillPositive("sense", 0, 0, 0.6, 0, 0.4, 0);
	public static IFairySkill PLANT = new FairySkillPositive("plant", 0, 0, 0.8, 0, 0.2, 0);
	public static IFairySkill MONEY = new FairySkillPositive("money", 1, 0, 0, 0, 0, 0);
	public static IFairySkill MYSTERY = new FairySkillPositive("mystery", 0, 0, 0, 0, 1, 0);
	public static IFairySkill LOGIC = new FairySkillPositive("logic", 0, 0, 0, 1, 0, 0);
	public static IFairySkill MIRAGE = new FairySkillPositive("mirage", 0, 0, 0, 0.8, 0.2, 0);
	public static IFairySkill ORE = new FairySkillPositive("ore", 1, 0, 0, 0, 0, 0);
	public static IFairySkill LAND = new FairySkillPositive("land", 1, 0, 0, 0, 0, 0);
	public static IFairySkill WEAPON = new FairySkillPositive("weapon", 0, 0, 0, 0.5, 0.5, 0);
	public static IFairySkill ORDER = new FairySkillPositive("order", 0, 0, 0, 0.5, 0.4, 0.1);
	public static IFairySkill FREEZE = new FairySkillPositive("freeze", 0, 0.5, 0, 0, 0.5, 0);
	public static IFairySkill HEAT = new FairySkillPositive("heat", 0, 0.5, 0, 0, 0.5, 0);
	public static IFairySkill FUEL = new FairySkillPositive("fuel", 0, 1, 0, 0, 0, 0);
	public static IFairySkill ENTROPY = new FairySkillPositive("entropy", 0, 0, 0.2, 0, 0.7, 0.1);

	public ModuleFairyType()
	{

		List<String> shapes = Arrays.asList("dust", "ore", "block", "ingot", "gem");

		add(new LoaderFairyType("iron", fairyType -> {
			fairyType.setColors(0xaaaaaa, 0x969696, 0xD8D8D8, 0x727272);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 3);
			fairyType.addSkill(MATERIAL, 3);
		}));
		add(new LoaderFairyType("gold", fairyType -> {
			fairyType.setColors(0xaaaaaa, 0xDEDE00, 0xFFFF8B, 0xDC7613);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 4);
			fairyType.addSkill(SENSE, 4);
		}));
		add(new LoaderFairyType("apatite", fairyType -> {
			fairyType.setColors(0xA4DBDB, 0x22ABFF, 0x5DC8FF, 0x107FCE);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 2);
			fairyType.addSkill(SENSE, 1);
			fairyType.addSkill(PLANT, 1);
		}));
		add(new LoaderFairyType("redstone", fairyType -> {
			fairyType.setColors(0xA50000, 0xFF0000, 0x490000);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 3);
			fairyType.addSkill(MYSTERY, 1);
			fairyType.addSkill(LOGIC, 2);
		}));
		add(new LoaderFairyType("coal", fairyType -> {
			fairyType.setColors(0x444444, 0x555555, 0x333333);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 1);
			fairyType.addSkill(FUEL, 1);
		}));
		add(new LoaderFairyType("fluorite", fairyType -> {
			fairyType.setColors(0xA4DBDB, 0x40CA65, 0x8947E0, 0x3A8FC);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 3);
			fairyType.addSkill(SENSE, 3);
		}));
		add(new LoaderFairyType("calcite", fairyType -> {
			fairyType.setColors(0xA4DBDB, 0xD5D6C8, 0xD5D6C8, 0xD5D6C8);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 1);
			fairyType.addSkill(MATERIAL, 1);
		}));
		add(new LoaderFairyType("spinachium", fairyType -> {
			fairyType.setColors(0xaaaaaa, 0x039F00, 0x1DFF00, 0x169900);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 4);
			fairyType.addSkill(MIRAGE, 3);
			fairyType.addSkill(PLANT, 1);
		}));
		add(new LoaderFairyType("miragium", fairyType -> {
			fairyType.setColors(0xaaaaaa, 0xFFDBDB, 0xDAE0F6, 0xB6DBBC);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 4);
			fairyType.addSkill(MIRAGE, 4);
		}));
		add(new LoaderFairyType("quartz", fairyType -> {
			fairyType.setColors(0xA4DBDB, 0xBCADA1, 0xD4CCC3, 0x5D4A3F);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.quartz, 1, 0));
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.addSkill(MINERAL, 2);
			fairyType.addSkill(ORDER, 2);
		}));
		add(new LoaderFairyType("dirt", fairyType -> {
			fairyType.setColors(0x7B573B, 0x9F724E, 0x593D29);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.dirt, 1, 0));
			fairyType.addSkill(SOIL, 1);
			fairyType.addSkill(LAND, 0.5);
			fairyType.addSkill(PLANT, 0.5);
		}));
		add(new LoaderFairyType("sand", fairyType -> {
			fairyType.setColors(0xD2CB95, 0xE0D7A6, 0xB0AA72);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.sand, 1, 0));
			fairyType.addSkill(SOIL, 1);
			fairyType.addSkill(LAND, 1);
		}));
		add(new LoaderFairyType("gravel", fairyType -> {
			fairyType.setColors(0x7A7673, 0xAA9E98, 0x918E8E);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.gravel, 1, 0));
			fairyType.addSkill(SOIL, 2);
			fairyType.addSkill(LAND, 2);
		}));
		add(new LoaderFairyType("stone", fairyType -> {
			fairyType.setColors(0x808080, 0x8F8F8F, 0x686868);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.stone, 1, 0));
			fairyType.addSkill(SOIL, 3);
			fairyType.addSkill(ORE, 2);
			fairyType.addSkill(MATERIAL, 1);
		}));
		add(new LoaderFairyType("cobblestone", fairyType -> {
			fairyType.setColors(0x7F7F7F, 0xBFBFBF, 0x4C4C4C);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.cobblestone, 1, 0));
			fairyType.addSkill(SOIL, 2);
			fairyType.addSkill(MATERIAL, 2);
		}));
		add(new LoaderFairyType("log", fairyType -> {
			fairyType.setColors(0x685332, 0xB6935C, 0x372A17);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.log, 1, OreDictionary.WILDCARD_VALUE));
			fairyType.addSkill(FARM, 1);
			fairyType.addSkill(MATERIAL, 1);
		}));
		add(new LoaderFairyType("water", fairyType -> {
			fairyType.setColors(0x0407CE, 0x345FDA, 0x345FDA, 0x2749A5);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.water_bucket, 1, 0));
			fairyType.addSkill(SOIL, 1);
			fairyType.addSkill(MATERIAL, 0.5);
			fairyType.addSkill(FOOD, 0.5);
		}));
		add(new LoaderFairyType("lava", fairyType -> {
			fairyType.setColors(0x0407CE, 0xDC8638, 0xE4D25C, 0xCC4628);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.lava_bucket, 1, 0));
			fairyType.addSkill(MINERAL, 2);
			fairyType.addSkill(HEAT, 2);
		}));
		add(new LoaderFairyType("milk", fairyType -> {
			fairyType.setColors(0x0407CE, 0xFFFFFF, 0xFFFFFF, 0x3D220F);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.milk_bucket, 1, 0));
			fairyType.addSkill(FARM, 2);
			fairyType.addSkill(FOOD, 2);
		}));
		add(new LoaderFairyType("ice", fairyType -> {
			fairyType.setColors(0xA4DBDB, 0x77A9FF, 0x77A9FF, 0xFFFFFF);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.ice, 1, 0));
			fairyType.addSkill(SOIL, 2);
			fairyType.addSkill(FREEZE, 2);
		}));

	}

}
