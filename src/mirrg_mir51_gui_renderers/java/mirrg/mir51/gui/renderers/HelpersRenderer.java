package mirrg.mir51.gui.renderers;

import mirrg.mir50.gui.renderer.IGuiRenderHelper;
import mirrg.mir50.gui.renderer.IGuiRenderHelper.EnumRotate;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class HelpersRenderer
{

	public static enum EnumTextAlign
	{
		LEFT,
		RIGHT,
		CENTER,
	}

	public static void drawMirageFontString(IGuiRenderHelper gui, String text, int oX, int oY, EnumTextAlign textAlign, EnumRotate rotate)
	{
		ResourceLocation texture = new ResourceLocation("mir41" + ":" + "textures/gui/miragefont.png");
		gui.getMinecraft().renderEngine.bindTexture(texture);

		GL11.glPushMatrix();

		GL11.glTranslatef(oX, oY, 0);
		GL11.glRotatef(rotate.ordinal() * 90, 0, 0, 1);
		GL11.glTranslatef(-oX, -oY, 0);

		switch (textAlign) {
			case CENTER:
				for (int i = 0; i < text.length(); i++) {
					char ch = text.charAt(i);
					drawMirageFontChar(gui, ch, oX - text.length() * 2 + i * 4 - 1, oY - 1);
				}
				break;
			case LEFT:
				for (int i = 0; i < text.length(); i++) {
					char ch = text.charAt(i);
					drawMirageFontChar(gui, ch, oX + i * 4 - 1, oY - 1);
				}
				break;
			case RIGHT:
				for (int i = 0; i < text.length(); i++) {
					char ch = text.charAt(i);
					drawMirageFontChar(gui, ch, oX - text.length() * 4 + i * 4 - 1, oY - 1);
				}
				break;
			default:
				break;
		}

		GL11.glPopMatrix();
	}

	public static void drawMirageFontChar(IGuiRenderHelper gui, char ch, int oX, int oY)
	{
		if (' ' <= ch && ch <= '~') {
			int r = (ch >> 4) - 2;
			int c = ch & 0xf;
			gui.drawTexturedModelRect(oX, oY - 6, 4 + 2, 6 + 2,
				(float) (c) / 16, (float) (r) / 6, (float) (c + 1) / 16, (float) (r + 1) / 6, EnumRotate.RIGHT0);
		}
	}

}
