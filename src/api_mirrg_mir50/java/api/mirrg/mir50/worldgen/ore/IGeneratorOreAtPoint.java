package api.mirrg.mir50.worldgen.ore;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

@FunctionalInterface
public interface IGeneratorOreAtPoint
{

	public boolean generate(World world, Random random, int x, int y, int z);

	public static class Helpers
	{

		public static IGeneratorOreAtPoint fromWorldGenerator(WorldGenerator worldGenerator)
		{
			return (world, random, x, y, z) -> worldGenerator.generate(world, random, x, y, z);
		}

	}

}
