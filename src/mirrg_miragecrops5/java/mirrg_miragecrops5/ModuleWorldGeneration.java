package mirrg_miragecrops5;

import mirrg.mir51.loaders.LoaderBlock;
import mirrg_miragecrops5.ModuleMaterials.EnumCalciteGroup;

public class ModuleWorldGeneration extends ModuleMirageCropsAbstract
{

	public ModuleWorldGeneration()
	{

		LoaderBlock ore = ModuleMaterials.loaderBlock_oreCalciteGroup;

		aLOG(() -> cGOIC(16, 16, 0, 128, ore.get(), EnumCalciteGroup.calcite.ordinal()));
		aLOG(() -> cGOIC(8, 4, 0, 64, ore.get(), EnumCalciteGroup.magnesite.ordinal()));
		aLOG(() -> cGOIC(8, 4, 0, 56, ore.get(), EnumCalciteGroup.siderite.ordinal()));
		aLOG(() -> cGOIC(8, 4, 0, 48, ore.get(), EnumCalciteGroup.smithsonite.ordinal()));
		aLOG(() -> cGOIC(2, 2, 0, 32, ore.get(), EnumCalciteGroup.rhodochrosite.ordinal(), "ocean"));
		aLOG(() -> cGOIC(2, 2, 0, 28, ore.get(), EnumCalciteGroup.sphaerocobaltite.ordinal(), "forest"));
		aLOG(() -> cGOIC(2, 2, 0, 24, ore.get(), EnumCalciteGroup.gaspeite.ordinal(), "desert"));
		aLOG(() -> cGOIC(1, 0.5, 0, 16, ore.get(), EnumCalciteGroup.otavite.ordinal(), "extreme"));

		aLOG(() -> cGOIC(1, 20, 64, 128, ore.get(), EnumCalciteGroup.apatite.ordinal(), "extreme"));
		aLOG(() -> cGOIC(5, 4, 64, 128, ore.get(), EnumCalciteGroup.fluorite.ordinal(), "extreme"));

		aLOG(() -> cGOIC(2, 8, 0, 128, ore.get(), EnumCalciteGroup.spinachium.ordinal()));
		aLOG(() -> cGOIC(2, 8, 0, 128, ore.get(), EnumCalciteGroup.miragium.ordinal()));

	}

}
