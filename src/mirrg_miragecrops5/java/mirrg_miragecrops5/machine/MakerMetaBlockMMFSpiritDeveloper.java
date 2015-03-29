package mirrg_miragecrops5.machine;

import mirrg.h.struct.Struct1;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.adaptors.AdaptorBlockIconRegister;
import mirrg.mir50.render.block.switcher.AdaptorBlockRenderingSwitcherOverriding;
import mirrg.mir51.block.multi.MetaBlock;
import mirrg.mir51.render.block.multiple.IBlockMultipleRendering;
import mirrg.mir51.render.block.multiple.RenderBlockMultipleRendering;
import mirrg_miragecrops5.machine.HelpersDirection.EnumDirection;
import mirrg_miragecrops5.machine.tile.ITileEntityHasDirection;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class MakerMetaBlockMMFSpiritDeveloper extends MakerMetaBlock
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
			new BlockMultipleRenderingFromConsumer(event -> handler -> {
				EnumDirection machineSide = HelpersDirection.subtract(event.getSide(), event.getDirection());

				if (machineSide == HelpersDirection.UP) {
					int[] table = {
						2, 1, 3, 0,
					};
					handler.accept(iconSide.x, 0xFFFFFF, table[event.getDirection() - 2], false);
				} else if (machineSide == HelpersDirection.DOWN) {
					int[] table = {
						1, 2, 3, 0,
					};
					handler.accept(iconSide.x, 0xFFFFFF, table[event.getDirection() - 2], false);
				} else if (machineSide == HelpersDirection.FRONT) {
					handler.accept(iconFront.x);
				} else if (machineSide == HelpersDirection.BACK) {
					handler.accept(iconBack.x);
				} else if (machineSide == HelpersDirection.RIGHT) {
					handler.accept(iconSide.x);
				} else if (machineSide == HelpersDirection.LEFT) {
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
					if (tileEntity instanceof ITileEntityHasDirection) {
						rotate = ((ITileEntityHasDirection) tileEntity).getDirection() - 2;
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

}
