package mirrg.mir50.worldgen.ore;

import java.util.Random;

import net.minecraft.world.World;

public interface IFilterAtPoint
{

	public boolean isSpawnableAtCoord(World world, Random random, int x, int y, int z);

}
