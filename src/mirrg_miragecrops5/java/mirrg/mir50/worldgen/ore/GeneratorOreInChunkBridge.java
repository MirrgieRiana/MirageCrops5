package mirrg.mir50.worldgen.ore;

import java.util.Random;
import java.util.function.ToIntFunction;

import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.OreGenEvent.Post;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreAtPoint;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreInChunk;

public class GeneratorOreInChunkBridge implements IGeneratorOreInChunk
{

	public static GeneratorOreInChunkBridge createFromMinMax(
		int density, IGeneratorOreAtPoint generatorOreAtPoint, int yMin, int yMax)
	{
		return new GeneratorOreInChunkBridge(density, generatorOreAtPoint,
			random -> random.nextInt(yMax - yMin) + yMin);
	}

	public static GeneratorOreInChunkBridge createFromCone(
		int density, IGeneratorOreAtPoint generatorOreAtPoint, int center, int radius)
	{
		return new GeneratorOreInChunkBridge(density, generatorOreAtPoint,
			random -> random.nextInt(radius) + random.nextInt(radius) + center - radius);
	}

	protected int density;

	protected IGeneratorOreAtPoint generatorOreAtPoint;

	protected ToIntFunction<Random> yProvider;

	public GeneratorOreInChunkBridge(
		int density, IGeneratorOreAtPoint generatorOreAtPoint, ToIntFunction<Random> yProvider)
	{
		this.density = density;
		this.generatorOreAtPoint = generatorOreAtPoint;
		this.yProvider = yProvider;
	}

	public void generate(World world, Random random, int chunkX, int chunkZ, Post event)
	{
		for (int l = 0; l < density; ++l)
		{
			int i1 = chunkX + random.nextInt(16);
			int j1 = yProvider.applyAsInt(random);
			int k1 = chunkZ + random.nextInt(16);
			generatorOreAtPoint.generate(world, random, i1, j1, k1);
		}
	}

}
