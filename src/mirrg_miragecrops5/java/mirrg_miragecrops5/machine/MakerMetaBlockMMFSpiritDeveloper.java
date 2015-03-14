package mirrg_miragecrops5.machine;

import java.util.function.Consumer;

import mirrg.h.struct.Struct1;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.adaptors.AdaptorBlockIconRegister;
import mirrg.mir50.render.block.switcher.AdaptorBlockRenderingSwitcherOverriding;
import mirrg.mir51.block.multi.MetaBlock;
import mirrg.mir51.render.block.multiple.IBlockMultipleRendering;
import mirrg.mir51.render.block.multiple.IConsumerMultipleRendering;
import mirrg.mir51.render.block.multiple.RenderBlockMultipleRendering;
import mirrg_miragecrops5.machine.tile.ITileEntityHasDirection;
import mirrg_miragecrops5.machine.tile.TileEntityMMFSpiritDeveloper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class MakerMetaBlockMMFSpiritDeveloper
{

	public static void makeRenderer(BlockMir50 blockMir50, MetaBlock metaBlock)
	{

		Struct1<IIcon> iconFeather = new Struct1<>(null);
		Struct1<IIcon> iconFront = new Struct1<>(null);
		Struct1<IIcon> iconSide = new Struct1<>(null);
		Struct1<IIcon> iconBack = new Struct1<>(null);
		metaBlock.virtualClass.override(new AdaptorBlockIconRegister(blockMir50, metaBlock, iconRegister -> {
			iconFeather.x = iconRegister.registerIcon("miragecrops5:002");
			iconFront.x = iconRegister.registerIcon("miragecrops5:005");
			iconSide.x = iconRegister.registerIcon("miragecrops5:006");
			iconBack.x = iconRegister.registerIcon("miragecrops5:007");
		}, true));

		IBlockMultipleRendering blockMultipleRendering_casing =
			new BlockMultipleRenderingFromConsumer((direction, side) -> handler -> {
				if (HelpersDirection.subtract(side, direction) == HelpersDirection.UP) {
					int[] table = {
						2, 1, 3, 0,
					};
					handler.accept(iconSide.x, 0xFFFFFF, table[direction - 2], false);
				} else if (HelpersDirection.subtract(side, direction) == HelpersDirection.DOWN) {
					int[] table = {
						1, 2, 3, 0,
					};
					handler.accept(iconSide.x, 0xFFFFFF, table[direction - 2], false);
				} else if (HelpersDirection.subtract(side, direction) == HelpersDirection.FRONT) {
					handler.accept(iconFront.x);
				} else if (HelpersDirection.subtract(side, direction) == HelpersDirection.BACK) {
					handler.accept(iconBack.x);
				} else if (HelpersDirection.subtract(side, direction) == HelpersDirection.RIGHT) {
					handler.accept(iconSide.x);
				} else if (HelpersDirection.subtract(side, direction) == HelpersDirection.LEFT) {
					handler.accept(iconSide.x, 0xFFFFFF, 3, false);
				}
			});

		metaBlock.virtualClass.override(new AdaptorBlockRenderingSwitcherOverriding(metaBlock) {

			@Override
			public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
			{
				//renderer.setRenderBounds(0, 0, 0, 1, 0.5f, 1);
				//renderer.setRenderBounds(0, 0, 0, 1, 1, 1);

				//GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
				float scale = 0.6f;

				GL11.glScalef(scale, scale, scale);
				GL11.glTranslatef(0.5f, -0.4F, 0);
				{
					renderer.setRenderBounds(0.125f, 0.125f, 0.125f, 0.875f, 0.875f, 0.875f);
					super.renderInventoryBlock(block, metadata, modelId, renderer);
					renderer.setRenderBounds(0, 0, 0, 1, 1, 1);

					RenderBlockMultipleRendering.renderInventoryBlock(blockMultipleRendering_casing,
						block, metadata, renderer);

					GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
					{
						Tessellator t = Tessellator.instance;

						//IIcon iicon = renderer.getBlockIconFromSideAndMetadata(block, 0, metadata);

						//t.setBrightness(0xffffff);
						//t.setColorOpaque_F(1.0F, 1.0F, 1.0F);

						t.startDrawingQuads();
						//renderBlockCropsImpl(renderer, block, 0, 0, 0, 0);
						renderFeather(renderer, iconFeather.x, ForgeDirection.EAST.ordinal() - 2, 0, 0, 0);
						t.draw();
					}
					GL11.glTranslatef(0.5F, 0.5F, 0.5F);
				}
				GL11.glTranslatef(-0.5f, 0.5F, 0);
				GL11.glScalef(1 / scale, 1 / scale, 1 / scale);

				/*
				renderer.setRenderBounds(0, 0, 0, 1, 1, 0.5f);
				super.renderInventoryBlock(block, metadata, modelId, renderer);
				renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
				*/
			}

			@Override
			public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
			{
				int rotate = 0;
				{
					TileEntity tileEntity = world.getTileEntity(x, y, z);
					if (tileEntity != null) {
						if (tileEntity instanceof TileEntityMMFSpiritDeveloper) {
							TileEntityMMFSpiritDeveloper tileEntityMMFFurnace = (TileEntityMMFSpiritDeveloper) tileEntity;
							int direction = tileEntityMMFFurnace.direction.direction;

							rotate = tileEntityMMFFurnace.direction.direction - 2;
						}
					}
				}

				renderer.setRenderBounds(0.125f, 0.125f, 0.125f, 0.875f, 0.875f, 0.875f);
				boolean res = super.renderWorldBlock(world, x, y, z, block, modelId, renderer);
				renderer.setRenderBounds(0, 0, 0, 1, 1, 1);

				//renderer.setRenderBounds(0, 0, 0, 1, 0.5f, 1);
				RenderBlockMultipleRendering.renderWorldBlock(blockMultipleRendering_casing,
					block, world, x, y, z, renderer);
				//renderer.setRenderBounds(0, 0, 0, 1, 1, 1);

				{
					Tessellator t = Tessellator.instance;

					//return p_149677_1_.getLightBrightnessForSkyBlocks(p_149677_2_, p_149677_3_, p_149677_4_,
					//	block.getLightValue(p_149677_1_, p_149677_2_, p_149677_3_, p_149677_4_));

					t.setBrightness(world.getLightBrightnessForSkyBlocks(x, y, z, block.getLightValue(world, x, y, z)));
					t.setColorOpaque_F(1.0F, 1.0F, 1.0F);

					//renderBlockCropsImpl(renderer, block, 0, x, y, z);

					renderFeather(renderer, iconFeather.x, rotate, x, y, z);
				}

				return res;
			}

		});

	}

	static interface BiIntFunction<T>
	{

		public T apply(int a, int b);

	}

	static class BlockMultipleRenderingFromConsumer implements IBlockMultipleRendering
	{

		/**
		 * direction, side
		 */
		public BiIntFunction<Consumer<IConsumerMultipleRendering>> consumer;

		public BlockMultipleRenderingFromConsumer(BiIntFunction<Consumer<IConsumerMultipleRendering>> consumer)
		{
			this.consumer = consumer;
		}

		@Override
		public Consumer<IConsumerMultipleRendering> getMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, int side)
		{
			{
				TileEntity tileEntity = blockAccess.getTileEntity(x, y, z);
				if (tileEntity instanceof ITileEntityHasDirection) {
					ITileEntityHasDirection tileEntityHasDirection = (ITileEntityHasDirection) tileEntity;
					int direction = tileEntityHasDirection.getDirection();

					return consumer.apply(direction, side);
				}
			}

			return consumer.apply(ForgeDirection.EAST.ordinal(), side);
		}

		@Override
		public Consumer<IConsumerMultipleRendering> getMultipleRendering(int metadata, int side)
		{
			return consumer.apply(ForgeDirection.EAST.ordinal(), side);
		}

	}

	static interface IConsumerVertexWithUV
	{

		public void addVertexWithUV(double xi, double yi, double zi, double u, double v);

	}

	static double currentXCenter;
	static double currentY;
	static double currentZCenter;

	static final IConsumerVertexWithUV[] CONSUMER_VERTEX_WITH_UVS = {
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

	static void renderFeather(RenderBlocks renderer, IIcon iicon, int rotate, double x, double y, double z)
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

	static void renderBlockCropsImpl(RenderBlocks renderer, IIcon iicon, int rotate, double x, double y, double z)
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
