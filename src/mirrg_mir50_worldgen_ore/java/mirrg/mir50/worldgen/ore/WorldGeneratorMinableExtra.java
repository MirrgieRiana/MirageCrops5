package mirrg.mir50.worldgen.ore;

import java.util.ArrayList;
import java.util.Random;

import mirrg.he.math.HelpersMath;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import api.mirrg.mir50.worldgen.ore.IGeneratorOreAtPoint;
import api.mirrg.mir50.worldgen.ore.IConsumerXYZ;
import cpw.mods.fml.common.FMLLog;

public class WorldGeneratorMinableExtra extends WorldGenerator implements IGeneratorOreAtPoint
{

	protected Block block;
	protected int meta;
	protected double numberOfBlocks;
	protected Block target;
	private ArrayList<IFilterAtPoint> filterAtPoints = new ArrayList<>();

	public WorldGeneratorMinableExtra(Block block, double numberOfBlocks)
	{
		this(block, 0, numberOfBlocks, Blocks.stone);
	}

	public WorldGeneratorMinableExtra(Block block, double numberOfBlocks, Block target)
	{
		this(block, 0, numberOfBlocks, target);
	}

	public WorldGeneratorMinableExtra(Block block, int meta, double numberOfBlocks, Block target)
	{
		this.block = block;
		this.meta = meta;
		this.numberOfBlocks = numberOfBlocks;
		this.target = target;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		for (IFilterAtPoint filterAtPoint : filterAtPoints) {
			if (!filterAtPoint.isSpawnableAtCoord(world, random, x, y, z)) {
				return false;
			}
		}

		int numberOfBlocks = HelpersMath.floorRandom(this.numberOfBlocks, random);

		if (numberOfBlocks >= 4) {
			generate4(world, random, x, y, z, numberOfBlocks, (x2, y2, z2) -> {
				tryPutBlockAtCoord(world, x2, y2, z2);
			});
		} else if (numberOfBlocks == 3) {
			generate3(world, random, x, y, z, (x2, y2, z2) -> {
				tryPutBlockAtCoord(world, x2, y2, z2);
			});
		} else if (numberOfBlocks == 2) {
			generate2(world, random, x, y, z, (x2, y2, z2) -> {
				tryPutBlockAtCoord(world, x2, y2, z2);
			});
		} else if (numberOfBlocks == 1) {
			generate1(world, random, x, y, z, (x2, y2, z2) -> {
				tryPutBlockAtCoord(world, x2, y2, z2);
			});
		} else if (numberOfBlocks == 0) {
			/*
			new RuntimeException(
				getClass().getName() + "#generate: numberOfBlocks = 0, (" + x + ", " + y + ", " + z + ")")
				.printStackTrace();
			*/
		}

		return true;
	}

	protected void tryPutBlockAtCoord(World world, int x, int y, int z)
	{
		try {
			Block block = world.getBlock(x, y, z);

			if (block != null) {
				if (block.isReplaceableOreGen(world, x, y, z, target)) {
					setBlock(world, x, y, z);
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			FMLLog.warning("runtime exception in ore generation (%s, %s, %s)", x, y, z);
		}
	}

	protected void setBlock(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, block, meta, 2);
	}

	public static void generate1(World world, Random random, int x, int y, int z, IConsumerXYZ xyzConsumer)
	{
		xyzConsumer.accept(x, y, z);
	}

	public static int[] TABLE_X = {
		1, -1, 0, 0, 0, 0,
	};
	public static int[] TABLE_Y = {
		0, 0, 1, -1, 0, 0,
	};
	public static int[] TABLE_Z = {
		0, 0, 0, 0, 1, -1,
	};

	public static void generate2(World world, Random random, int x, int y, int z, IConsumerXYZ xyzConsumer)
	{
		xyzConsumer.accept(x, y, z);

		int i = random.nextInt(6);
		xyzConsumer.accept(x + TABLE_X[i], y + TABLE_Y[i], z + TABLE_Z[i]);
	}

	public static void generate3(World world, Random random, int x, int y, int z, IConsumerXYZ xyzConsumer)
	{
		xyzConsumer.accept(x, y, z);

		int i = random.nextInt(6);
		xyzConsumer.accept(x + TABLE_X[i], y + TABLE_Y[i], z + TABLE_Z[i]);

		i = random.nextInt(6);
		xyzConsumer.accept(x + TABLE_X[i], y + TABLE_Y[i], z + TABLE_Z[i]);
	}

	public static void generate4(World world, Random random, int x, int y, int z, int numberOfBlocks, IConsumerXYZ xyzConsumer)
	{
		float course = random.nextFloat() * (float) Math.PI;
		double minX = x + 8 + MathHelper.sin(course) * numberOfBlocks / 8.0F;
		double maxX = x + 8 - MathHelper.sin(course) * numberOfBlocks / 8.0F;
		double minZ = z + 8 + MathHelper.cos(course) * numberOfBlocks / 8.0F;
		double maxZ = z + 8 - MathHelper.cos(course) * numberOfBlocks / 8.0F;
		double minY = y + random.nextInt(3) - 2;
		double maxY = y + random.nextInt(3) - 2;

		for (int i = 0; i <= numberOfBlocks; ++i)
		{
			double vectX = minX + (maxX - minX) * i / numberOfBlocks;
			double vectY = minY + (maxY - minY) * i / numberOfBlocks;
			double vectZ = minZ + (maxZ - minZ) * i / numberOfBlocks;
			double d9 = random.nextDouble() * numberOfBlocks / 16.0D;
			double d10 = (MathHelper.sin(i * (float) Math.PI / numberOfBlocks) + 1.0F)
				* d9 + 1.0D;
			double d11 = (MathHelper.sin(i * (float) Math.PI / numberOfBlocks) + 1.0F)
				* d9 + 1.0D;
			int i1 = MathHelper.floor_double(vectX - d10 / 2.0D);
			int j1 = MathHelper.floor_double(vectY - d11 / 2.0D);
			int k1 = MathHelper.floor_double(vectZ - d10 / 2.0D);
			int l1 = MathHelper.floor_double(vectX + d10 / 2.0D);
			int i2 = MathHelper.floor_double(vectY + d11 / 2.0D);
			int j2 = MathHelper.floor_double(vectZ + d10 / 2.0D);

			for (int xi = i1; xi <= l1; ++xi)
			{
				double d12 = (xi + 0.5D - vectX) / (d10 / 2.0D);

				if (d12 * d12 < 1.0D)
				{
					for (int yi = j1; yi <= i2; ++yi)
					{
						double d13 = (yi + 0.5D - vectY) / (d11 / 2.0D);

						if (d12 * d12 + d13 * d13 < 1.0D)
						{
							for (int zi = k1; zi <= j2; ++zi)
							{
								double d14 = (zi + 0.5D - vectZ) / (d10 / 2.0D);

								if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D) {
									xyzConsumer.accept(xi, yi, zi);
								}

							}
						}
					}
				}
			}
		}

	}

	public void addFilter(IFilterAtPoint filterAtPoint)
	{
		filterAtPoints.add(filterAtPoint);
	}

}
