package mirrg_miragecrops5.machine;

import java.util.ArrayList;

import mirrg.he.math.HelpersCollision;
import mirrg.he.math.HelpersMath;
import mirrg.mir50.gui.renderer.HelpersRenderer;
import mirrg_miragecrops5.fairytype.FairyType;
import mirrg_miragecrops5.fairytype.HelpersFairyType;
import net.minecraft.client.renderer.OpenGlHelper;

import org.lwjgl.opengl.GL11;

import api.mirrg.mir50.gui.renderer.EnumRotate;
import api.mirrg.mir50.gui.renderer.EnumTextAlign;
import api.mirrg.mir50.gui.renderer.IGuiRenderHelper;
import api.mirrg.mir50.gui.renderer.IRenderer;

public class RendererFairyGraph implements IRenderer<ContainerExtraSlotFairyGraph>
{

	@Override
	public void drawBackgroundLayer(IGuiRenderHelper gui, ContainerExtraSlotFairyGraph t, int mouseX, int mouseY)
	{

	}

	@Override
	public void drawForegroundLayer(IGuiRenderHelper gui, ContainerExtraSlotFairyGraph t, int mouseX, int mouseY)
	{
		int xStart = (gui.getScreenWidth() - gui.getGuiWidth()) / 2;
		int yStart = (gui.getScreenHeight() - gui.getGuiHeight()) / 2;

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glColorMask(true, true, true, false);

		int lengthMax = 0;

		int[] table = {
			5, 3, 4, 1, 2, 0,
		};

		int[] values = HelpersFairyType.getValues(t.getIncreaser());

		for (int i = 0; i < 6; i++) {
			if (lengthMax < HelpersMath.log2(Math.abs(values[table[i]]))) {
				lengthMax = HelpersMath.log2(Math.abs(values[table[i]]));
			}
		}

		if (lengthMax != 0) {
			for (int i = 0; i < 6; i++) {
				int x = t.x;
				int y = t.y + t.h * i / 6;
				int h = t.h / 6 - 2;
				int length = values[table[i]];
				int color = length > 0 ? 0xff00C400 : 0xffFF4242;
				int w = HelpersMath.log2(Math.abs(length)) * 16 / lengthMax;

				gui.drawRectBlend(x, y, x + w, y + h, color);
			}
		}

		GL11.glColorMask(true, true, true, true);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		//

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColorMask(true, true, true, false);

		for (int i = 0; i < 6; i++) {
			int x = t.x;
			int y = t.y + t.h * i / 6 + 1;
			int h = t.h / 6 - 2;
			int length = values[table[i]];
			int color = length > 0 ? 0xff00C400 : length == 0 ? 0xffaaaaaa : 0xffFF4242;

			GL11.glColor4f(
				((color >> 16) & 0xff) / 255.0f,
				((color >> 8) & 0xff) / 255.0f,
				(color & 0xff) / 255.0f,
				((color >> 24) & 0xff) / 255.0f);
			HelpersRenderer.drawMirageFontString(gui, "" + length, x, y + h, EnumTextAlign.LEFT, EnumRotate.RIGHT0);
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		GL11.glColorMask(true, true, true, true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	@Override
	public boolean drawToolTip(IGuiRenderHelper gui, ContainerExtraSlotFairyGraph t, int mouseX, int mouseY)
	{
		int xStart = (gui.getScreenWidth() - gui.getGuiWidth()) / 2;
		int yStart = (gui.getScreenHeight() - gui.getGuiHeight()) / 2;

		int[] table = {
			5, 3, 4, 1, 2, 0,
		};

		int[] values = HelpersFairyType.getValues(t.getIncreaser());

		for (int i = 0; i < 6; i++) {
			int x = t.x;
			int y = t.y + t.h * i / 6;
			int w = 16;
			int h = t.h / 6 - 2;

			if (HelpersCollision.isHit(mouseX, mouseY, xStart + x, yStart + y, xStart + x + w, yStart + y + h) > 0) {
				ArrayList<String> list = new ArrayList<String>();

				list.add(FairyType.getLabel(i) + ": " + values[table[i]]);

				gui.drawHoveringText(list, mouseX - xStart, mouseY - yStart, gui.getFontRenderer());
				return true;
			}

		}

		return false;
	}

}
