package mirrg.mir51.gui.renderers;

import java.util.ArrayList;

import mirrg.he.math.HelpersCollision;
import mirrg.mir50.gui.renderer.IGuiRenderHelper;
import mirrg.mir50.gui.renderer.IRenderer;
import mirrg.mir50.tile.inventory.EnergySlot;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RendererEnergySlotProgress implements IRenderer<EnergySlot>
{

	public static final RendererEnergySlotProgress instance = new RendererEnergySlotProgress();

	public String domain = "mir41";

	@Override
	@SideOnly(Side.CLIENT)
	public void drawForegroundLayer(IGuiRenderHelper gui, EnergySlot t, int mouseX, int mouseY)
	{
		ResourceLocation texture = new ResourceLocation(domain + ":" + "textures/gui/progress_foreground.png");
		gui.getMinecraft().renderEngine.bindTexture(texture);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glColorMask(true, true, true, false);

		float rate = t.energyTank.capacity == 0 ? 1 : (float) t.energyTank.amount / t.energyTank.capacity;
		int w = (int) (t.w * rate);
		rate = (float) w / t.w;
		gui.drawTexturedModelRect(t.x, t.y, w, t.h, 0, 0, rate, 1);

		GL11.glColorMask(true, true, true, true);
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawBackgroundLayer(IGuiRenderHelper gui, EnergySlot t, int mouseX, int mouseY)
	{
		int xStart = (gui.getScreenWidth() - gui.getGuiWidth()) / 2;
		int yStart = (gui.getScreenHeight() - gui.getGuiHeight()) / 2;

		ResourceLocation texture = new ResourceLocation(domain + ":" + "textures/gui/progress_background.png");
		gui.getMinecraft().renderEngine.bindTexture(texture);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glColorMask(true, true, true, false);

		gui.drawTexturedModelRect(xStart + t.x, yStart + t.y, t.w, t.h, 0, 0, 1, 1);

		GL11.glColorMask(true, true, true, true);
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		//

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glColorMask(true, true, true, false);

		{
			int i = 0, length;
			length = 0;
			gui.drawRectBlend(xStart + 27, yStart + i * 9 + 16, xStart + 27 + length, yStart + i * 9 + 23, 0xffFF4242);
			i++;
			length = 2;
			gui.drawRectBlend(xStart + 27, yStart + i * 9 + 16, xStart + 27 + length, yStart + i * 9 + 23, 0xff00C400);
			i++;
			length = 0;
			gui.drawRectBlend(xStart + 27, yStart + i * 9 + 16, xStart + 27 + length, yStart + i * 9 + 23, 0xffFF4242);
			i++;
			length = 11;
			gui.drawRectBlend(xStart + 27, yStart + i * 9 + 16, xStart + 27 + length, yStart + i * 9 + 23, 0xff00C400);
			i++;
			length = 4;
			gui.drawRectBlend(xStart + 27, yStart + i * 9 + 16, xStart + 27 + length, yStart + i * 9 + 23, 0xffFF4242);
			i++;
			length = 2;
			gui.drawRectBlend(xStart + 27, yStart + i * 9 + 16, xStart + 27 + length, yStart + i * 9 + 23, 0xffFF4242);
		}

		GL11.glColorMask(true, true, true, true);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean drawToolTip(IGuiRenderHelper gui, EnergySlot t, int mouseX, int mouseY)
	{
		int xStart = (gui.getScreenWidth() - gui.getGuiWidth()) / 2;
		int yStart = (gui.getScreenHeight() - gui.getGuiHeight()) / 2;

		if (hit(gui, t, mouseX, mouseY)) {
			ArrayList<String> list = new ArrayList<String>();
			list.add("" + t.energyTank.amount + " / " + t.energyTank.capacity);
			gui.drawHoveringText(list, mouseX - xStart, mouseY - yStart, gui.getFontRenderer());
			return true;
		}
		return false;
	}

	public boolean hit(IGuiRenderHelper gui, EnergySlot t, int mouseX, int mouseY)
	{
		int xStart = (gui.getScreenWidth() - gui.getGuiWidth()) / 2;
		int yStart = (gui.getScreenHeight() - gui.getGuiHeight()) / 2;

		return HelpersCollision.isHit(mouseX, mouseY,
			xStart + t.x, yStart + t.y, xStart + t.x + t.w, yStart + t.y + t.h) > 0;
	}

}
