package mirrg_miragecrops5;

import java.util.function.Supplier;

import mirrg.mir50.worldgen.ore.FilterBiome;
import mirrg.mir50.worldgen.ore.GeneratorOreInChunkBridge;
import mirrg.mir50.worldgen.ore.WorldGeneratorMinableExtra;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.mir51.loaders.LoaderOreGenerator;
import mirrg_miragecrops5.material.HelpersModuleMaterial;
import mirrg_miragecrops5.material.ModuleMaterials;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreAtPoint;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreInChunk;

public class ModuleWorldGeneration extends ModuleMirageCropsAbstract
{

	public ModuleWorldGeneration()
	{

		LoaderBlock l = ModuleMaterials.loaderBlock_oreCalciteGroup;

		aLOG(() -> cGOIC(16, 16, 0, 128, l.get(), gIB("calcite")));
		aLOG(() -> cGOIC(8, 4, 0, 64, l.get(), gIB("magnesite")));
		aLOG(() -> cGOIC(8, 4, 0, 56, l.get(), gIB("siderite")));
		aLOG(() -> cGOIC(8, 4, 0, 48, l.get(), gIB("smithsonite")));
		aLOG(() -> cGOIC(2, 2, 0, 32, l.get(), gIB("rhodochrosite"), "ocean"));
		aLOG(() -> cGOIC(2, 2, 0, 28, l.get(), gIB("sphaerocobaltite"), "forest"));
		aLOG(() -> cGOIC(2, 2, 0, 24, l.get(), gIB("gaspeite"), "desert"));
		aLOG(() -> cGOIC(1, 0.5, 0, 16, l.get(), gIB("otavite"), "extreme"));

		aLOG(() -> cGOIC(1, 20, 64, 128, l.get(), gIB("apatite"), "extreme"));
		aLOG(() -> cGOIC(5, 4, 64, 128, l.get(), gIB("fluorite"), "extreme"));

		aLOG(() -> cGOIC(2, 8, 0, 128, l.get(), gIB("spinachium")));
		aLOG(() -> cGOIC(2, 8, 0, 128, l.get(), gIB("miragium")));

	}

	private int gIB(String nameMaterial)
	{
		return HelpersModuleMaterial.registryMaterialBlock.getIndexByName(nameMaterial);
	}

	private void aLOG(Supplier<IGeneratorOreInChunk> supplier)
	{
		add(new LoaderOreGenerator(loaderModule, supplier));
	}

	private IGeneratorOreInChunk cGOIC(
		int density, double numberOfBlocks, int heightMin, int heightMax, Block block, int meta, String... filterBiomeNames)
	{
		WorldGeneratorMinableExtra worldGenerator = new WorldGeneratorMinableExtra(block, meta, numberOfBlocks, Blocks.stone);

		for (String filterBiomeName : filterBiomeNames) {
			worldGenerator.addFilter(new FilterBiome(filterBiomeName));
		}

		return GeneratorOreInChunkBridge.createFromMinMax(
			density, IGeneratorOreAtPoint.Helpers.fromWorldGenerator(worldGenerator), heightMin, heightMax);
	}

}
