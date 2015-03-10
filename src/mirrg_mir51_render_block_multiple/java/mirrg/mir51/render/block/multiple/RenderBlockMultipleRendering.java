package mirrg.mir51.render.block.multiple;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.ObjIntConsumer;

import mirrg.h.struct.Tuple;
import mirrg.mir50.render.block.RenderBlockAbstract;
import mirrg.numberreave.NumberReave;
import mirrg.numberreave.Storage;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

public class RenderBlockMultipleRendering extends RenderBlockAbstract
{

	public RenderBlockMultipleRendering(int renderId)
	{
		super(renderId);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		if (modelId != this.getRenderId()) return;

		IBlockMultipleRendering blockMultipleRendering = null;
		if (block instanceof IBlockMultipleRendering) {
			blockMultipleRendering = (IBlockMultipleRendering) block;
		}
		if (block instanceof IVirtualClass) {
			if (((IVirtualClass) block).getVirtualClass().instanceOf(IBlockMultipleRendering.class)) {
				blockMultipleRendering = ((IVirtualClass) block).getVirtualClass().cast(IBlockMultipleRendering.class).get();
			}
		}
		if (blockMultipleRendering == null) {
			return;
		}

		GL11.glPushMatrix();
		{
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

			{
				renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				renderCubeInInventoryMultiply(block, blockMultipleRendering, metadata, renderer);
			}

		}
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z,
		Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId != this.getRenderId()) return false;

		IBlockMultipleRendering blockMultipleRendering = null;
		if (block instanceof IBlockMultipleRendering) {
			blockMultipleRendering = (IBlockMultipleRendering) block;
		}
		if (block instanceof IVirtualClass) {
			if (((IVirtualClass) block).getVirtualClass().instanceOf(IBlockMultipleRendering.class)) {
				blockMultipleRendering = ((IVirtualClass) block).getVirtualClass().cast(IBlockMultipleRendering.class).get();
			}
		}
		if (blockMultipleRendering == null) {
			return false;
		}

		{
			renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			renderStandardBlockMultiply(blockAccess, x, y, z, block, blockMultipleRendering, renderer);
		}

		return true;
	}

	public static void renderCubeInInventoryMultiply(
		Block block, IBlockMultipleRendering blockMultipleRendering, int metadata, RenderBlocks renderer)
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
		{
			renderImpl(blockMultipleRendering,
				side -> blockMultipleRendering.getMultipleRendering(metadata, side), color -> {
					renderCubeInInventory(block, metadata, renderer, color);
				});

			GL11.glColor4f(r, g, b, a);
		}
		GL11.glPopAttrib();
	}

	public static void renderCubeInInventory(Block block, int metadata, RenderBlocks renderer, int color)
	{
		Tessellator t = Tessellator.instance;

		if (HelpersBlockMultipleRendering.visibles[0]) {
			t.startDrawingQuads();
			t.setNormal(0.0F, -1.0F, 0.0F);
			Tessellator.instance.setColorOpaque_I(color);
			renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
			t.draw();
		}

		if (HelpersBlockMultipleRendering.visibles[1]) {
			t.startDrawingQuads();
			t.setNormal(0.0F, 1.0F, 0.0F);
			Tessellator.instance.setColorOpaque_I(color);
			renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
			t.draw();
		}

		if (HelpersBlockMultipleRendering.visibles[2]) {
			t.startDrawingQuads();
			t.setNormal(0.0F, 0.0F, -1.0F);
			Tessellator.instance.setColorOpaque_I(color);
			renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
			t.draw();
		}

		if (HelpersBlockMultipleRendering.visibles[3]) {
			t.startDrawingQuads();
			t.setNormal(0.0F, 0.0F, 1.0F);
			Tessellator.instance.setColorOpaque_I(color);
			renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
			t.draw();
		}

		if (HelpersBlockMultipleRendering.visibles[4]) {
			t.startDrawingQuads();
			t.setNormal(-1.0F, 0.0F, 0.0F);
			Tessellator.instance.setColorOpaque_I(color);
			renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
			t.draw();
		}

		if (HelpersBlockMultipleRendering.visibles[5]) {
			t.startDrawingQuads();
			t.setNormal(1.0F, 0.0F, 0.0F);
			Tessellator.instance.setColorOpaque_I(color);
			renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
			t.draw();
		}

	}

	public static void renderStandardBlockMultiply(IBlockAccess blockAccess, int x, int y, int z,
		Block block, IBlockMultipleRendering blockMultipleRendering, RenderBlocks renderer)
	{
		renderImpl(blockMultipleRendering,
			side -> blockMultipleRendering.getMultipleRendering(blockAccess, x, y, z, side), color -> {
				renderer.renderStandardBlock(block, x, y, z);
			});
	}

	protected static void renderImpl(
		IBlockMultipleRendering blockMultipleRendering,
		IntFunction<Consumer<ObjIntConsumer<IIcon>>> sideToMultipleRendering,
		IntConsumer handlerRenderCube)
	{
		ArrayList<ArrayList<Tuple<IIcon, Integer>>> listListIconAndColor = new ArrayList<>();
		for (int side = 0; side < 6; side++) {
			ArrayList<Tuple<IIcon, Integer>> listIconAndColor = new ArrayList<>();

			sideToMultipleRendering.apply(side).accept((icon, color) -> {
				listIconAndColor.add(new Tuple<>(icon, color));
			});

			listListIconAndColor.add(listIconAndColor);
		}

		ArrayList<Tuple<Integer, boolean[]>> listColorAndMask = NumberReave.allReaveShort(new Storage(
			(side, pass) -> listListIconAndColor.get(side).get(pass).getY(),
			side -> listListIconAndColor.get(side).size(),
			listListIconAndColor.size()));

		//

		HelpersBlockMultipleRendering.enabled = true;

		int[] currentLayer = new int[6];

		for (int layer = 0; layer < listColorAndMask.size(); layer++) {
			Tuple<Integer, boolean[]> colorAndMask = listColorAndMask.get(layer);

			for (int side = 0; side < colorAndMask.getY().length; side++) {
				if (colorAndMask.getY()[side]) {
					HelpersBlockMultipleRendering.visibles[side] = true;
					HelpersBlockMultipleRendering.icons[side] = listListIconAndColor.get(side).get(currentLayer[side]).getX();
					HelpersBlockMultipleRendering.color = colorAndMask.getX();

					currentLayer[side]++;
				} else {
					HelpersBlockMultipleRendering.visibles[side] = false;
				}
			}

			handlerRenderCube.accept(colorAndMask.getX());
		}

		HelpersBlockMultipleRendering.enabled = false;
	}

}
