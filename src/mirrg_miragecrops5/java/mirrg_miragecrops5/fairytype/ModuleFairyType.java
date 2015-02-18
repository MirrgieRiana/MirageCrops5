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

	public ModuleFairyType()
	{

		List<String> shapes = Arrays.asList("dust", "ore", "block", "ingot", "gem");

		add(new LoaderFairyType("iron", fairyType -> {
			fairyType.setColors(0xaaaaaa, 0x969696, 0xD8D8D8, 0x727272);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(-12, 8, 0, 1, 0, 0);
		}));
		add(new LoaderFairyType("gold", fairyType -> {
			fairyType.setColors(0xaaaaaa, 0xDEDE00, 0xFFFF8B, 0xDC7613);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(-22, 0, 12, 0, 1, 0);
		}));
		add(new LoaderFairyType("apatite", fairyType -> {
			fairyType.setColors(0xA4DBDB, 0x22ABFF, 0x5DC8FF, 0x107FCE);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(-6, 0, 9, 0, 0, 0);
		}));
		add(new LoaderFairyType("redstone", fairyType -> {
			fairyType.setColors(0xA50000, 0xFF0000, 0x490000);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(-10, 6, 0, 1, 0, 0);
		}));
		add(new LoaderFairyType("fluorite", fairyType -> {
			fairyType.setColors(0xA4DBDB, 0x40CA65, 0x8947E0, 0x3A8FC);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(-11, 0, 7, 0, 0, 0);
		}));
		add(new LoaderFairyType("calcite", fairyType -> {
			fairyType.setColors(0xA4DBDB, 0xD5D6C8, 0xD5D6C8, 0xD5D6C8);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(-6, 0, 4, 0, 0, 0);
		}));
		add(new LoaderFairyType("spinachium", fairyType -> {
			fairyType.setColors(0xaaaaaa, 0x039F00, 0x1DFF00, 0x169900);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(-24, 0, 0, 0, 2, 0);
		}));
		add(new LoaderFairyType("miragium", fairyType -> {
			fairyType.setColors(0xaaaaaa, 0xFFDBDB, 0xDAE0F6, 0xB6DBBC);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(-30, 0, 0, 0, 3, 0);
		}));
		add(new LoaderFairyType("dirt", fairyType -> {
			fairyType.setColors(0x7B573B, 0x9F724E, 0x593D29);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.dirt, 1, 0));
			fairyType.setValues(5, 0, 0, 0, 0, 0);
		}));
		add(new LoaderFairyType("sand", fairyType -> {
			fairyType.setColors(0xD2CB95, 0xE0D7A6, 0xB0AA72);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.sand, 1, 0));
			fairyType.setValues(10, -2, 0, 0, 0, 0);
		}));
		add(new LoaderFairyType("gravel", fairyType -> {
			fairyType.setColors(0x918E8E, 0x7A7673, 0xAA9E98);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.gravel, 1, 0));
			fairyType.setValues(8, -1, 0, 0, 0, 0);
		}));
		add(new LoaderFairyType("log", fairyType -> {
			fairyType.setColors(0x685332, 0xB6935C, 0x372A17);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Blocks.log, 1, OreDictionary.WILDCARD_VALUE));
			fairyType.setValues(10, 0, -2, 0, 0, 0);
		}));
		add(new LoaderFairyType("quartz", fairyType -> {
			fairyType.setColors(0xA4DBDB, 0xBCADA1, 0xD4CCC3, 0x5D4A3F);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.quartz, 1, 0));
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(0, -7, 0, 1, 0, 0);
		}));
		add(new LoaderFairyType("water", fairyType -> {
			fairyType.setColors(0x0407CE, 0x345FDA, 0x345FDA, 0x2749A5);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.water_bucket, 1, 0));
			fairyType.setValues(15, -5, 0, 0, 0, 0);
		}));
		add(new LoaderFairyType("lava", fairyType -> {
			fairyType.setColors(0x0407CE, 0xDC8638, 0xE4D25C, 0xCC4628);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.lava_bucket, 1, 0));
			fairyType.setValues(0, 0, -5, 0, 1, 0);
		}));
		add(new LoaderFairyType("milk", fairyType -> {
			fairyType.setColors(0x0407CE, 0xFFFFFF, 0xFFFFFF, 0x3D220F);
			fairyType.getOreMatcher().addMatcher(new ItemStack(Items.milk_bucket, 1, 0));
			fairyType.setValues(0, 0, 2, 0, 0, 0);
		}));

	}

}
