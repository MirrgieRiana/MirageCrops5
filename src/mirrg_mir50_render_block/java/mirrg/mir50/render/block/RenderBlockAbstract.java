package mirrg.mir50.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class RenderBlockAbstract implements ISimpleBlockRenderingHandler
{

	private final int renderId;

	public RenderBlockAbstract(int renderId)
	{
		this.renderId = renderId;
	}

	@Override
	public int getRenderId()
	{
		return renderId;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public abstract void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer);

	@Override
	public abstract boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z,
		Block block, int modelId, RenderBlocks renderer);

	public static void renderCubeInInventory(Block block, int metadata, RenderBlocks renderer)
	{
		renderCubeInInventory(block, metadata, renderer, 0xffffff);
	}

	public static void renderCubeInInventory(Block block, int metadata, RenderBlocks renderer, int color)
	{
		Tessellator t = Tessellator.instance;

		t.startDrawingQuads();
		t.setNormal(0.0F, -1.0F, 0.0F);
		Tessellator.instance.setColorOpaque_I(color);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
		t.draw();

		t.startDrawingQuads();
		t.setNormal(0.0F, 1.0F, 0.0F);
		Tessellator.instance.setColorOpaque_I(color);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
		t.draw();

		t.startDrawingQuads();
		t.setNormal(0.0F, 0.0F, -1.0F);
		Tessellator.instance.setColorOpaque_I(color);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
		t.draw();

		t.startDrawingQuads();
		t.setNormal(0.0F, 0.0F, 1.0F);
		Tessellator.instance.setColorOpaque_I(color);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
		t.draw();

		t.startDrawingQuads();
		t.setNormal(-1.0F, 0.0F, 0.0F);
		Tessellator.instance.setColorOpaque_I(color);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
		t.draw();

		t.startDrawingQuads();
		t.setNormal(1.0F, 0.0F, 0.0F);
		Tessellator.instance.setColorOpaque_I(color);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
		t.draw();

	}

}
