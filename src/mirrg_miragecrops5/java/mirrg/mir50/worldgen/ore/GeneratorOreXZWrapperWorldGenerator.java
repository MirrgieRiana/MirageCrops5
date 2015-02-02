package mirrg.mir50.worldgen.ore;

import java.util.Random;
import java.util.function.ToIntFunction;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent.Post;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreXZ;

public class GeneratorOreXZWrapperWorldGenerator implements IGeneratorOreXZ
{

	public static GeneratorOreXZWrapperWorldGenerator createFromMinMax(
		int density, WorldGenerator worldGenerator, int yMin, int yMax)
	{
		return new GeneratorOreXZWrapperWorldGenerator(density, worldGenerator,
			random -> random.nextInt(yMax - yMin) + yMin);
	}

	public static GeneratorOreXZWrapperWorldGenerator createFromCone(
		int density, WorldGenerator worldGenerator, int center, int radius)
	{
		return new GeneratorOreXZWrapperWorldGenerator(density, worldGenerator,
			random -> random.nextInt(radius) + random.nextInt(radius) + center - radius);
	}

	protected int density;

	protected WorldGenerator worldGenerator;

	protected ToIntFunction<Random> yProvider;

	public GeneratorOreXZWrapperWorldGenerator(
		int density, WorldGenerator worldGenerator, ToIntFunction<Random> yProvider)
	{
		this.density = density;
		this.worldGenerator = worldGenerator;
		this.yProvider = yProvider;
	}

	public void generate(World world, Random random, int chunkX, int chunkZ, Post event)
	{
		for (int l = 0; l < density; ++l)
		{
			int i1 = chunkX + random.nextInt(16);
			int j1 = yProvider.applyAsInt(random);
			int k1 = chunkZ + random.nextInt(16);
			worldGenerator.generate(world, random, i1, j1, k1);
		}
	}

}
