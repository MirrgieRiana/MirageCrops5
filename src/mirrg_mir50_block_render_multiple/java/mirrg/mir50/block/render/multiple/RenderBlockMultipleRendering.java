package mirrg.mir50.block.render.multiple;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import mirrg.mir50.block.render.LoaderSimpleBlockRenderingHandler;
import mirrg.mir50.block.render.RenderBlockAbstract;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

public class RenderBlockMultipleRendering extends RenderBlockAbstract
{

	public static LoaderSimpleBlockRenderingHandler loader =
		new LoaderSimpleBlockRenderingHandler(renderId -> new RenderBlockMultipleRendering(renderId));

	public RenderBlockMultipleRendering(int renderId)
	{
		super(renderId);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		if (modelId != this.getRenderId()) return;

		if (!(block instanceof IBlockMultipleRendering)) return;

		GL11.glPushMatrix();
		{
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

			{
				renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				renderCubeInInventoryMultiply((Block & IBlockMultipleRendering) block, metadata, renderer);
			}

		}
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z,
		Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId != this.getRenderId()) return false;

		if (!(block instanceof IBlockMultipleRendering)) return false;

		{
			renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			renderStandardBlockMultiply(blockAccess, x, y, z, (Block & IBlockMultipleRendering) block, renderer);
		}

		return true;
	}

	public static <T extends Block & IBlockMultipleRendering>
		void renderCubeInInventoryMultiply(T block, int metadata, RenderBlocks renderer)
	{
		float r;
		float g;
		float b;
		float a;
		{
			ByteBuffer bb = ByteBuffer.allocateDirect(4 * 16);
			bb.order(ByteOrder.nativeOrder());
			FloatBuffer fb = bb.asFloatBuffer();
			GL11.glGetFloat(GL11.GL_CURRENT_COLOR, fb);
			r = fb.get(0);
			g = fb.get(1);
			b = fb.get(2);
			a = fb.get(3);
		}

		GL11.glPushAttrib(8192);
		//GL11.glEnable(GL11.GL_TEXTURE_2D);
		//GL11.glEnable(GL11.GL_ALPHA_TEST);
		//GL11.glDisable(GL11.GL_BLEND);
		//GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
		//GL11.glEnable(GL11.GL_BLEND);
		//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		/*TODO
		 GL_ZERO	(0,0,0,0)
		GL_ONE	(1,1,1,1)
		GL_SRC_COLOR	(Rs/kR ,Gs/kG,Bs/kB,As/kA)
		GL_ONE_MINUS_SRC_COLOR	(1,1,1,1)-(Rs/kR,Gs/kG,Bs/kB,As/kA)
		GL_DST_COLOR	(Rd/kR,Gd/kG,Bd/kB,Ad/kA)
		GL_ONE_MINUS_DST_COLOR	(1,1,1,1)-(Rd/kR,Gd/kG,Bd/kB,Ad/kA)
		GL_SRC_ALPHA	(As/kA,As/kA,As/kA,As/kA)
		GL_ONE_MINUS_SRC_ALPHA	(1,1,1,1)-(As/kA,As/kA,As/kA,As/kA)
		GL_DST_ALPHA	(Ad/kA,Ad/kA,Ad/kA,Ad/kA)
		GL_ONE_MINUS_DST_ALPHA	(1,1,1,1)-(Ad/kA,Ad/kA,Ad/kA,Ad/kA)
		GL_SRC_ALPHA_SATURATE	(i,i,i,1)
		 */

		block.setMultipleRendering(true);
		for (int i = 0; i < block.getMultipleRenderPasses(metadata); i++) {
			block.setMultipleRenderPass(i);

			int color = block.getMultipleRenderColor(metadata, i);
			renderCubeInInventory(block, metadata, renderer, color);

			//GL11.glColor4f(1, 1, 1, 1);
			/*
			GL11.glColor4f(
				r * ((color >> 16) & 0xff) / 255.0f,
				g * ((color >> 8) & 0xff) / 255.0f,
				b * (color & 0xff) / 255.0f,
				a);
				*/

			//Tessellator.instance.setColorOpaque_F(((color >> 16) & 0xff) / 255.0f, ((color >> 8) & 0xff) / 255.0f, (color & 0xff) / 255.0f);

		}
		block.setMultipleRendering(false);

		GL11.glColor4f(r, g, b, a);

		GL11.glPopAttrib();
	}

	public static <T extends Block & IBlockMultipleRendering>
		void renderStandardBlockMultiply(IBlockAccess blockAccess, int x, int y, int z,
			T block, RenderBlocks renderer)
	{
		block.setMultipleRendering(true);
		for (int i = 0; i < block.getMultipleRenderPasses(blockAccess, x, y, z); i++) {
			block.setMultipleRenderPass(i);

			renderer.renderStandardBlock(block, x, y, z);

		}
		block.setMultipleRendering(false);
	}

}
