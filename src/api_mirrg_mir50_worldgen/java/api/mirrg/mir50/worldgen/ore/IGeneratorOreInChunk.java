package api.mirrg.mir50.worldgen.ore;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.OreGenEvent;

public interface IGeneratorOreInChunk
{

	public void generate(World world, Random random, int chunkX, int chunkZ, OreGenEvent.Post event);

}
