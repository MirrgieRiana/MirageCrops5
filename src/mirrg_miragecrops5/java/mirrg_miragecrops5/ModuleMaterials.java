package mirrg_miragecrops5;

import mirrg.mir51.loaders.LoaderBlock;

public class ModuleMaterials extends ModuleMirageCropsAbstract
{

	public static LoaderBlock loaderBlock_oreCalciteGroup = new LoaderBlock();
	public static LoaderBlock loaderBlock_blockCalciteGroup = new LoaderBlock();

	public static enum EnumCalciteGroup
	{
		calcite,
		magnesite,
		siderite,
		smithsonite,
		rhodochrosite,
		sphaerocobaltite,
		gaspeite,
		otavite,

		apatite,
		fluorite,

		spinachium,
		miragium,

	}

	public ModuleMaterials()
	{

		process_loaderBlock_multi(loaderBlock_oreCalciteGroup, ModuleCore.loaderCreativeTab,
			"oreCalciteGroup", "ore", a -> {
				a.appendIcon("minecraft:stone");
			});

		process_loaderBlock_multi(loaderBlock_blockCalciteGroup, ModuleCore.loaderCreativeTab,
			"blockCalciteGroup", "block", null);

	}

}
