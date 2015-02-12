package mirrg_miragecrops5.fairytype;

import java.util.Arrays;
import java.util.List;

import mirrg.he.math.HelpersString;
import mirrg_miragecrops5.ModuleMirageCropsAbstract;

public class ModuleFairyType extends ModuleMirageCropsAbstract
{

	public ModuleFairyType()
	{

		List<String> shapes = Arrays.asList("dust", "ore", "block", "ingot", "gem");

		add(new LoaderFairyType("iron", fairyType -> {
			fairyType.setColors(0x969696, 0xD8D8D8, 0x727272);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(-12, 8, 0, 1, 0, 0);
		}));
		add(new LoaderFairyType("gold", fairyType -> {
			fairyType.setColors(0xDEDE00, 0xFFFF8B, 0xDC7613);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(-22, 0, 12, 0, 1, 0);
		}));
		add(new LoaderFairyType("apatite", fairyType -> {
			fairyType.setColors(0x22ABFF, 0x5DC8FF, 0x107FCE);
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
			fairyType.setColors(0x40CA65, 0x8947E0, 0x3A8FC);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(-11, 0, 7, 0, 0, 0);
		}));
		add(new LoaderFairyType("calcite", fairyType -> {
			fairyType.setColors(0xD5D6C8, 0xD5D6C8, 0xD5D6C8);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(-6, 0, 4, 0, 0, 0);
		}));
		add(new LoaderFairyType("spinachium", fairyType -> {
			fairyType.setColors(0x039F00, 0x1DFF00, 0x169900);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(-24, 0, 0, 0, 2, 0);
		}));
		add(new LoaderFairyType("miragium", fairyType -> {
			fairyType.setColors(0xFFDBDB, 0xDAE0F6, 0xB6DBBC);
			shapes.forEach(shape -> {
				fairyType.getOreMatcher().addMatcher(shape + HelpersString.toUpperCaseHead(fairyType.typeName));
			});
			fairyType.setValues(-30, 0, 0, 0, 3, 0);
		}));

	}

}
