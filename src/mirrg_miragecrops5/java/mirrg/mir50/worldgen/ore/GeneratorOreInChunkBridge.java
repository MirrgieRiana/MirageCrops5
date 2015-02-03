package mirrg.mir50.worldgen.ore;

import java.util.Random;
import java.util.function.ToIntFunction;

import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.OreGenEvent.Post;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreAtPoint;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreInChunk;

public class GeneratorOreInChunkBridge implements IGeneratorOreInChunk
{

	public static enum EnumMinableSample
	{
		DIRT(32, 20, 0, 256, 1),
		GRAVEL(32, 10, 0, 256, 1),
		COAL(16, 20, 0, 128, 1),
		IRON(8, 20, 0, 64, 1),
		GOLD(8, 2, 0, 32, 1),
		REDSTONE(7, 8, 0, 16, 1),
		DIAMOND(7, 1, 0, 16, 1),
		LAPIS(6, 1, 16, 16, 2),
		SILVERFISH(8, 7, 0, 64, 1),
		NETHER_QUARTZ(13, 16, 10, 118, 1), ;

		public final int numberOfBlocks;
		public final int density;
		public final int argument1;
		public final int argument2;
		public final int type;

		public static final int TYPE_MIN_MAX = 1;
		public static final int TYPE_CONE = 2;

		private EnumMinableSample(int numberOfBlocks, int density, int argument1, int argument2, int type)
		{
			this.numberOfBlocks = numberOfBlocks;
			this.density = density;
			this.argument1 = argument1;
			this.argument2 = argument2;
			this.type = type;
		}

	}

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
