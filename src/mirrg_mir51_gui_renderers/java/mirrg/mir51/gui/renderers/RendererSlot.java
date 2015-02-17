package mirrg.mir51.gui.renderers;

import net.minecraft.inventory.Slot;

import org.lwjgl.opengl.GL11;

import api.mirrg.mir50.gui.renderer.EnumStyleFrame;
import api.mirrg.mir50.gui.renderer.IGuiRenderHelper;
import api.mirrg.mir50.gui.renderer.IRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RendererSlot implements IRenderer<Slot>
{

	public static final RendererSlot instance = new RendererSlot(null);

	public static final RendererSlot instanceGroove = new RendererSlot(EnumStyleFrame.GROOVE);
	public static final RendererSlot instanceRidge = new RendererSlot(EnumStyleFrame.RIDGE);
	public static final RendererSlot instanceInset = new RendererSlot(EnumStyleFrame.INSET);
	public static final RendererSlot instanceOutset = new RendererSlot(EnumStyleFrame.OUTSET);

	protected EnumStyleFrame styleFrame;

	public RendererSlot(EnumStyleFrame styleFrame)
	{
		this.styleFrame = styleFrame;
	}

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

		if (styleFrame != null) {
			gui.drawRectFrame(xStart + t.xDisplayPosition, yStart + t.yDisplayPosition, 16, 16, 0, 1, styleFrame);
		} else {
			gui.drawRectFrame(xStart + t.xDisplayPosition, yStart + t.yDisplayPosition, 16, 16, 0, 1);
		}

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
