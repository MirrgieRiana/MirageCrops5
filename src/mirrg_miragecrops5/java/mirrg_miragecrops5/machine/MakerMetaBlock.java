package mirrg_miragecrops5.machine;

import java.util.function.Consumer;
import java.util.function.Function;

import mirrg.mir50.block.BlockMir50;
import mirrg.mir51.block.multi.MetaBlock;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRenderingOverriding;
import mirrg.mir51.render.block.multiple.IBlockMultipleRendering;
import mirrg.mir51.render.block.multiple.IConsumerMultipleRendering;
import mirrg.p.virtualclass.IVirtualClass;
import mirrg_miragecrops5.machine.tile.ITileEntityHasDirection;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MakerMetaBlock
{

	protected static interface BiIntFunction<T>
	{

		public T apply(int a, int b);

	}

	protected static class BlockMultipleRenderingFromConsumer implements IBlockMultipleRendering
	{

		public Function<IEventMultipleRendering, Consumer<IConsumerMultipleRendering>> consumer;

		public BlockMultipleRenderingFromConsumer(Function<IEventMultipleRendering, Consumer<IConsumerMultipleRendering>> consumer)
		{
			this.consumer = consumer;
		}

		@Override
		public Consumer<IConsumerMultipleRendering> getMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, int side)
		{
			int direction = ForgeDirection.EAST.ordinal();
			{
				TileEntity tileEntity = blockAccess.getTileEntity(x, y, z);
				if (tileEntity instanceof ITileEntityHasDirection) {
					direction = ((ITileEntityHasDirection) tileEntity).getDirection();
				}
			}

			int final_direction = direction;
			return consumer.apply(new IEventMultipleRendering() {

				@Override
				public int getDirection()
				{
					return final_direction;
				}

				@Override
				public int getSide()
				{
					return side;
				}

				@Override
				public boolean isOnWorld()
				{
					return true;
				}

				@Override
				public IBlockAccess getBlockAccess()
				{
					return blockAccess;
				}

				@Override
				public int getX()
				{
					return x;
				}

				@Override
				public int getY()
				{
					return y;
				}

				@Override
				public int getZ()
				{
					return z;
				}

				@Override
				public int getMetadata()
				{
					return blockAccess.getBlockMetadata(x, y, z);
				}

			});
		}

		@Override
		public Consumer<IConsumerMultipleRendering> getMultipleRendering(int metadata, int side)
		{
			return consumer.apply(new IEventMultipleRendering() {

				@Override
				public int getDirection()
				{
					return ForgeDirection.EAST.ordinal();
				}

				@Override
				public int getSide()
				{
					return side;
				}

				@Override
				public boolean isOnWorld()
				{
					return false;
				}

				@Override
				public IBlockAccess getBlockAccess()
				{
					throw new UnsupportedOperationException();
				}

				@Override
				public int getX()
				{
					throw new UnsupportedOperationException();
				}

				@Override
				public int getY()
				{
					throw new UnsupportedOperationException();
				}

				@Override
				public int getZ()
				{
					throw new UnsupportedOperationException();
				}

				@Override
				public int getMetadata()
				{
					return metadata;
				}

			});
		}

	}

	public static interface IEventMultipleRendering
	{

		/**
		 * Block's direction
		 */
		public int getDirection();

		/**
		 * Side of block to render
		 */
		public int getSide();

		public boolean isOnWorld();

		public IBlockAccess getBlockAccess();

		public int getX();

		public int getY();

		public int getZ();

		public int getMetadata();

	}

	protected static class AdaptorBlockMultipleRenderingFromConsumer extends AdaptorBlockMultipleRenderingOverriding
	{

		public Function<IEventMultipleRendering, Consumer<IConsumerMultipleRendering>> consumer;

		public AdaptorBlockMultipleRenderingFromConsumer(BlockMir50 owner, IVirtualClass superObject)
		{
			super(owner, superObject);
		}

		public AdaptorBlockMultipleRenderingFromConsumer(BlockMir50 owner, MetaBlock superObject,
			Function<IEventMultipleRendering, Consumer<IConsumerMultipleRendering>> consumer)
		{
			this(owner, superObject);
			this.consumer = consumer;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public Consumer<IConsumerMultipleRendering> getMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, int side)
		{
			int direction = ForgeDirection.EAST.ordinal();
			{
				TileEntity tileEntity = blockAccess.getTileEntity(x, y, z);
				if (tileEntity instanceof ITileEntityHasDirection) {
					direction = ((ITileEntityHasDirection) tileEntity).getDirection();
				}
			}

			int final_direction = direction;
			return consumer.apply(new IEventMultipleRendering() {

				@Override
				public int getDirection()
				{
					return final_direction;
				}

				@Override
				public int getSide()
				{
					return side;
				}

				@Override
				public boolean isOnWorld()
				{
					return true;
				}

				@Override
				public IBlockAccess getBlockAccess()
				{
					return blockAccess;
				}

				@Override
				public int getX()
				{
					return x;
				}

				@Override
				public int getY()
				{
					return y;
				}

				@Override
				public int getZ()
				{
					return z;
				}

				@Override
				public int getMetadata()
				{
					return blockAccess.getBlockMetadata(x, y, z);
				}

			});
		}

		@Override
		@SideOnly(Side.CLIENT)
		public Consumer<IConsumerMultipleRendering> getMultipleRendering(int metadata, int side)
		{
			return consumer.apply(new IEventMultipleRendering() {

				@Override
				public int getDirection()
				{
					return ForgeDirection.EAST.ordinal();
				}

				@Override
				public int getSide()
				{
					return side;
				}

				@Override
				public boolean isOnWorld()
				{
					return false;
				}

				@Override
				public IBlockAccess getBlockAccess()
				{
					throw new UnsupportedOperationException();
				}

				@Override
				public int getX()
				{
					throw new UnsupportedOperationException();
				}

				@Override
				public int getY()
				{
					throw new UnsupportedOperationException();
				}

				@Override
				public int getZ()
				{
					throw new UnsupportedOperationException();
				}

				@Override
				public int getMetadata()
				{
					return metadata;
				}

			});
		}

	}

	protected static interface IConsumerVertexWithUV
	{

		public void addVertexWithUV(double xi, double yi, double zi, double u, double v);

	}

	protected static double currentXCenter;
	protected static double currentY;
	protected static double currentZCenter;

	protected static final IConsumerVertexWithUV[] CONSUMER_VERTEX_WITH_UVS = {
		(xi, yi, zi, u, v) -> {
			Tessellator.instance.addVertexWithUV(currentXCenter - xi, currentY + yi, currentZCenter - zi, u, v);
		},
		(xi, yi, zi, u, v) -> {
			Tessellator.instance.addVertexWithUV(currentXCenter + xi, currentY + yi, currentZCenter + zi, u, v);
		},
		(xi, yi, zi, u, v) -> {
			Tessellator.instance.addVertexWithUV(currentXCenter - zi, currentY + yi, currentZCenter + xi, u, v);
		},
		(xi, yi, zi, u, v) -> {
			Tessellator.instance.addVertexWithUV(currentXCenter + zi, currentY + yi, currentZCenter - xi, u, v);
		},
	};

	protected static void renderFeather(RenderBlocks renderer, IIcon iicon, int rotate, double x, double y, double z)
	{
		if (rotate > 4) return;
		IConsumerVertexWithUV consumerVertexWithUV = CONSUMER_VERTEX_WITH_UVS[rotate];
		if (renderer.hasOverrideBlockTexture()) iicon = renderer.overrideBlockTexture;
		Tessellator t = Tessellator.instance;

		currentXCenter = x + 0.5;
		currentY = y;
		currentZCenter = z + 0.5;

		double u1 = iicon.getMinU();
		double v1 = iicon.getMinV();
		double u2 = iicon.getMaxU();
		double v2 = iicon.getMaxV();

		double x1 = -0.001 - 0.5;
		double x2 = 1.001 - 0.5;
		double y1 = 0;
		double y2 = 2;
		double z1 = -1 - 0.5;
		double z2 = 1 - 0.5;

		t.setNormal(-1.0F, 0.0F, 0.0F);
		consumerVertexWithUV.addVertexWithUV(x1, y2, z1, u1, v1);
		consumerVertexWithUV.addVertexWithUV(x1, y1, z1, u1, v2);
		consumerVertexWithUV.addVertexWithUV(x1, y1, z2, u2, v2);
		consumerVertexWithUV.addVertexWithUV(x1, y2, z2, u2, v1);

		t.setNormal(1.0F, 0.0F, 0.0F);
		consumerVertexWithUV.addVertexWithUV(x1, y2, z2, u2, v1);
		consumerVertexWithUV.addVertexWithUV(x1, y1, z2, u2, v2);
		consumerVertexWithUV.addVertexWithUV(x1, y1, z1, u1, v2);
		consumerVertexWithUV.addVertexWithUV(x1, y2, z1, u1, v1);

		t.setNormal(1.0F, 0.0F, 0.0F);
		consumerVertexWithUV.addVertexWithUV(x2, y2, z2, u2, v1);
		consumerVertexWithUV.addVertexWithUV(x2, y1, z2, u2, v2);
		consumerVertexWithUV.addVertexWithUV(x2, y1, z1, u1, v2);
		consumerVertexWithUV.addVertexWithUV(x2, y2, z1, u1, v1);

		t.setNormal(-1.0F, 0.0F, 0.0F);
		consumerVertexWithUV.addVertexWithUV(x2, y2, z1, u1, v1);
		consumerVertexWithUV.addVertexWithUV(x2, y1, z1, u1, v2);
		consumerVertexWithUV.addVertexWithUV(x2, y1, z2, u2, v2);
		consumerVertexWithUV.addVertexWithUV(x2, y2, z2, u2, v1);

	}

	protected static void renderBlockCropsImpl(RenderBlocks renderer, IIcon iicon, int rotate, double x, double y, double z)
	{
		if (rotate > 4) return;
		IConsumerVertexWithUV consumerVertexWithUV = CONSUMER_VERTEX_WITH_UVS[rotate];
		if (renderer.hasOverrideBlockTexture()) iicon = renderer.overrideBlockTexture;
		Tessellator t = Tessellator.instance;

		currentXCenter = x + 0.5;
		currentY = y;
		currentZCenter = z + 0.5;

		double u1 = iicon.getMinU();
		double v1 = iicon.getMinV();
		double u2 = iicon.getMaxU();
		double v2 = iicon.getMaxV();

		double x1 = 0.25 - 0.5;
		double x2 = 0.75 - 0.5;
		double y1 = 0.0;
		double y2 = 1.0;
		double z1 = 0 - 0.5;
		double z2 = 1 - 0.5;

		t.setNormal(-1.0F, 0.0F, 0.0F);
		consumerVertexWithUV.addVertexWithUV(x1, y2, z1, u1, v1);
		consumerVertexWithUV.addVertexWithUV(x1, y1, z1, u1, v2);
		consumerVertexWithUV.addVertexWithUV(x1, y1, z2, u2, v2);
		consumerVertexWithUV.addVertexWithUV(x1, y2, z2, u2, v1);

		t.setNormal(1.0F, 0.0F, 0.0F);
		consumerVertexWithUV.addVertexWithUV(x1, y2, z2, u1, v1);
		consumerVertexWithUV.addVertexWithUV(x1, y1, z2, u1, v2);
		consumerVertexWithUV.addVertexWithUV(x1, y1, z1, u2, v2);
		consumerVertexWithUV.addVertexWithUV(x1, y2, z1, u2, v1);

		t.setNormal(1.0F, 0.0F, 0.0F);
		consumerVertexWithUV.addVertexWithUV(x2, y2, z2, u1, v1);
		consumerVertexWithUV.addVertexWithUV(x2, y1, z2, u1, v2);
		consumerVertexWithUV.addVertexWithUV(x2, y1, z1, u2, v2);
		consumerVertexWithUV.addVertexWithUV(x2, y2, z1, u2, v1);

		t.setNormal(-1.0F, 0.0F, 0.0F);
		consumerVertexWithUV.addVertexWithUV(x2, y2, z1, u1, v1);
		consumerVertexWithUV.addVertexWithUV(x2, y1, z1, u1, v2);
		consumerVertexWithUV.addVertexWithUV(x2, y1, z2, u2, v2);
		consumerVertexWithUV.addVertexWithUV(x2, y2, z2, u2, v1);

		x1 = 0 - 0.5;
		x2 = 1 - 0.5;
		z1 = 0.25 - 0.5;
		z2 = 0.75 - 0.5;

		t.setNormal(0.0F, 0.0F, -1.0F);
		consumerVertexWithUV.addVertexWithUV(x1, y2, z1, u1, v1);
		consumerVertexWithUV.addVertexWithUV(x1, y1, z1, u1, v2);
		consumerVertexWithUV.addVertexWithUV(x2, y1, z1, u2, v2);
		consumerVertexWithUV.addVertexWithUV(x2, y2, z1, u2, v1);

		t.setNormal(0.0F, 0.0F, 1.0F);
		consumerVertexWithUV.addVertexWithUV(x2, y2, z1, u1, v1);
		consumerVertexWithUV.addVertexWithUV(x2, y1, z1, u1, v2);
		consumerVertexWithUV.addVertexWithUV(x1, y1, z1, u2, v2);
		consumerVertexWithUV.addVertexWithUV(x1, y2, z1, u2, v1);

		t.setNormal(0.0F, 0.0F, 1.0F);
		consumerVertexWithUV.addVertexWithUV(x2, y2, z2, u1, v1);
		consumerVertexWithUV.addVertexWithUV(x2, y1, z2, u1, v2);
		consumerVertexWithUV.addVertexWithUV(x1, y1, z2, u2, v2);
		consumerVertexWithUV.addVertexWithUV(x1, y2, z2, u2, v1);

		t.setNormal(0.0F, 0.0F, -1.0F);
		consumerVertexWithUV.addVertexWithUV(x1, y2, z2, u1, v1);
		consumerVertexWithUV.addVertexWithUV(x1, y1, z2, u1, v2);
		consumerVertexWithUV.addVertexWithUV(x2, y1, z2, u2, v2);
		consumerVertexWithUV.addVertexWithUV(x2, y2, z2, u2, v1);

	}

}
