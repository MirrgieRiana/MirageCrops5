package mirrg.mir52.tile.renderers;

import mirrg.mir50.tile.renderer.IGuiRenderHelper;
import mirrg.mir50.tile.renderer.IRenderer;
import net.minecraft.inventory.Slot;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RendererSlot implements IRenderer<Slot>
{

	public static final RendererSlot instance = new RendererSlot();

	@Override
	@SideOnly(Side.CLIENT)
	public void drawBackgroundLayer(IGuiRenderHelper gui, Slot t, int mouseX, int mouseY)
	{
		int xStart = (gui.getScreenWidth() - gui.getGuiWidth()) / 2;
		int yStart = (gui.getScreenHeight() - gui.getGuiHeight()) / 2;

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glColorMask(true, true, true, false);

		gui.drawRectFrame(xStart + t.xDisplayPosition, yStart + t.yDisplayPosition, 16, 16, 0, 1);

		GL11.glColorMask(true, true, true, true);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawForegroundLayer(IGuiRenderHelper gui, Slot t, int mouseX, int mouseY)
	{

	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean drawToolTip(IGuiRenderHelper gui, Slot t, int mouseX, int mouseY)
	{
		return false;
	}

}
