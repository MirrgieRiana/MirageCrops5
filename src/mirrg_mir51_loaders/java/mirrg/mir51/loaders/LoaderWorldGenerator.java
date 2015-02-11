package mirrg.mir51.loaders;

import java.util.function.Supplier;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class LoaderWorldGenerator extends Loader<IWorldGenerator>
{

	protected Supplier<IWorldGenerator> supplier;

	protected int modGenerationWeight;

	public LoaderWorldGenerator()
	{

	}

	public LoaderWorldGenerator(Supplier<IWorldGenerator> supplier, int modGenerationWeight)
	{
		init(supplier, modGenerationWeight);
	}

	public void init(Supplier<IWorldGenerator> supplier, int modGenerationWeight)
	{
		this.supplier = supplier;
		this.modGenerationWeight = modGenerationWeight;
	}

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.PreInit) {

			IWorldGenerator worldGenerator = supplier.get();

			GameRegistry.registerWorldGenerator(worldGenerator, modGenerationWeight);

			set(worldGenerator);
		}
	}

}
