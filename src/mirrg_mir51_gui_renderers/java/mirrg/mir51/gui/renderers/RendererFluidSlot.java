package mirrg.mir51.gui.renderers;

import java.util.ArrayList;

import mirrg.he.math.HelpersCollision;
import mirrg.mir50.datamodels.DatamodelFluid;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotDatamodel;
import mirrg.mir50.gui.renderer.HelpersRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import api.mirrg.mir50.gui.renderer.EnumRotate;
import api.mirrg.mir50.gui.renderer.EnumTextAlign;
import api.mirrg.mir50.gui.renderer.IGuiRenderHelper;
import api.mirrg.mir50.gui.renderer.IRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RendererFluidSlot implements IRenderer<ContainerExtraSlotDatamodel<DatamodelFluid>>
{

	public static final RendererFluidSlot instance = new RendererFluidSlot();

	public String domain = "mir41";

	@Override
	@SideOnly(Side.CLIENT)
	public void drawForegroundLayer(IGuiRenderHelper gui, ContainerExtraSlotDatamodel<DatamodelFluid> t,
		int mouseX, int mouseY)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColorMask(true, true, true, false);

		if (t.datamodel.fluidStack != null) {
			if (t.datamodel.fluidStack.getFluid() != null) {

				ResourceLocation resourcelocation = gui.getMinecraft().renderEngine.getResourceLocation(
					t.datamodel.fluidStack.getFluid().getSpriteNumber());
				gui.getMinecraft().renderEngine.bindTexture(resourcelocation);

				{
					float rate = (float) t.datamodel.fluidStack.amount / t.datamodel.getCapacity();
					int height = (int) (t.h * rate);
					int begin = t.y + t.h - height;
					int end = t.y + t.h;

					while (height >= t.w) {
						gui.drawTexturedModelRectFromIcon(t.x, end - t.w, t.w, t.w,
							t.datamodel.fluidStack.getFluid().getIcon(t.datamodel.fluidStack),
							0, 0, 1, 1);
						end -= t.w;
						height -= t.w;
					}

					gui.drawTexturedModelRectFromIcon(t.x, begin, t.w, height,
						t.datamodel.fluidStack.getFluid().getIcon(t.datamodel.fluidStack),
						0, 1.0F * (t.w - height) / t.w, 1, 1);
				}
			}
		}

		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			{
				String amount = "" + t.datamodel.getFluidAmount();

				if (amount.length() >= 4) {
					HelpersRenderer.drawMirageFontString(gui, amount.substring(0, amount.length() - 3),
						t.x + t.w - 6 - 1, t.y + t.h / 2, EnumTextAlign.RIGHT, EnumRotate.RIGHT90);
					HelpersRenderer.drawMirageFontString(gui, amount.substring(amount.length() - 3),
						t.x + t.w - 6 - 1, t.y + t.h / 2 + 3 * 4 + 2, EnumTextAlign.RIGHT, EnumRotate.RIGHT90);
				} else {
					HelpersRenderer.drawMirageFontString(gui, amount,
						t.x + t.w - 6 - 1, t.y + t.h / 2 + 3 * 4 + 2, EnumTextAlign.RIGHT, EnumRotate.RIGHT90);
				}
			}
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		GL11.glColorMask(true, true, true, true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawBackgroundLayer(IGuiRenderHelper gui, ContainerExtraSlotDatamodel<DatamodelFluid> t, int mouseX, int mouseY)
	{
		int xStart = (gui.getScreenWidth() - gui.getGuiWidth()) / 2;
		int yStart = (gui.getScreenHeight() - gui.getGuiHeight()) / 2;

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glColorMask(true, true, true, false);

		gui.drawRectFrame(xStart + t.x, yStart + t.y, t.w, t.h, 0, 1);

		for (int i = 1; i <= 9; i++) {
			int y = yStart + t.y + (t.h * i / 10);
			int w = i == 5 ? t.w : t.w / 3;

			gui.drawGradientRect(
				xStart + t.x,
				y,
				xStart + t.x + w,
				y + 1,
				0xffa00000, 0xffa00000);
		}

		GL11.glColorMask(true, true, true, true);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean drawToolTip(IGuiRenderHelper gui, ContainerExtraSlotDatamodel<DatamodelFluid> t, int mouseX, int mouseY)
	{
		int xStart = (gui.getScreenWidth() - gui.getGuiWidth()) / 2;
		int yStart = (gui.getScreenHeight() - gui.getGuiHeight()) / 2;

		if (hit(gui, t, mouseX, mouseY)) {
			ArrayList<String> list = new ArrayList<String>();
			if (t.datamodel.getFluid() == null) {
				list.add("Empty");
			} else {
				list.add(t.datamodel.getFluid().getFluid().getLocalizedName(t.datamodel.getFluid()));
			}
			list.add("" + t.datamodel.getFluidAmount() + " / " + t.datamodel.getCapacity());
			gui.drawHoveringText(list, mouseX - xStart, mouseY - yStart, gui.getFontRenderer());
			return true;
		}
		return false;
	}

	public boolean hit(IGuiRenderHelper gui, ContainerExtraSlotDatamodel<DatamodelFluid> t, int mouseX, int mouseY)
	{
		int xStart = (gui.getScreenWidth() - gui.getGuiWidth()) / 2;
		int yStart = (gui.getScreenHeight() - gui.getGuiHeight()) / 2;

		return HelpersCollision.isHit(mouseX, mouseY,
			xStart + t.x, yStart + t.y, xStart + t.x + t.w, yStart + t.y + t.h) > 0;
	}

}
