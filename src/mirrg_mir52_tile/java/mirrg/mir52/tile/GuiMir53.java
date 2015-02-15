package mirrg.mir52.tile;

import java.util.List;

import mirrg.mir50.gui.renderer.IGuiRenderHelper;
import mirrg.mir50.gui.renderer.IRenderer;
import mirrg.mir50.gui.renderer.IRendererProvider;
import mirrg.mir51.gui.renderers.RendererEnergySlotProgress;
import mirrg.mir51.gui.renderers.RendererFluidSlot;
import mirrg.mir51.gui.renderers.RendererSlot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMir53 extends GuiContainer
{

	protected final ResourceLocation guiTexture;
	protected final ContainerMir53 container;
	protected final GuiRenderHelperImpl handler = new GuiRenderHelperImpl();

	public GuiMir53(ContainerMir53 container, ResourceLocation guiTexture)
	{
		super(container);
		this.container = container;
		this.guiTexture = guiTexture;
	}

	@SuppressWarnings("unchecked")
	public static <T> IRenderer<T> getRenderer(T slot, IRenderer<T> defaultRenderer)
	{
		if (slot instanceof IRenderer) {
			return (IRenderer<T>) slot;
		} else if (slot instanceof IRendererProvider) {
			IRenderer<T> renderer = ((IRendererProvider<T>) slot).getRenderer();
			if (renderer != null) return renderer;
		}

		return defaultRenderer;
	}

	public static enum EnumLayer
	{
		Foreground, ToolTip, Background,
	}

	protected <T> void drawSlotsLayer(EnumLayer layer, List<T> slots, IRenderer<T> defaultRenderer, int mouseX, int mouseY)
	{
		for (T slot : slots) {
			drawLayer(layer, getRenderer(slot, defaultRenderer), handler, slot, mouseX, mouseY);
		}
	}

	public static <T> void drawLayer(EnumLayer layer, IRenderer<T> renderer, IGuiRenderHelper gui, T slot, int mouseX, int mouseY)
	{
		switch (layer)
		{
			case Background:
				renderer.drawBackgroundLayer(gui, slot, mouseX, mouseY);
				break;
			case ToolTip:
				renderer.drawToolTip(gui, slot, mouseX, mouseY);
				RenderHelper.enableGUIStandardItemLighting();
				break;
			case Foreground:
				renderer.drawForegroundLayer(gui, slot, mouseX, mouseY);
				break;
			default:
				break;
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{

		String s = container.getTileEntity().getLocalizedName();
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 0x404040);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 0x404040);

		drawSlotsLayer(EnumLayer.Foreground, container.getSlots(), RendererSlot.instance, mouseX, mouseY);
		drawSlotsLayer(EnumLayer.Foreground, container.fluidSlots, RendererFluidSlot.instance, mouseX, mouseY);
		drawSlotsLayer(EnumLayer.Foreground, container.energySlots, RendererEnergySlotProgress.instanceLeft, mouseX, mouseY);

		drawSlotsLayer(EnumLayer.ToolTip, container.getSlots(), RendererSlot.instance, mouseX, mouseY);
		drawSlotsLayer(EnumLayer.ToolTip, container.fluidSlots, RendererFluidSlot.instance, mouseX, mouseY);
		drawSlotsLayer(EnumLayer.ToolTip, container.energySlots, RendererEnergySlotProgress.instanceLeft, mouseX, mouseY);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float arg0, int mouseX, int mouseY)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if (guiTexture != null) {
			mc.renderEngine.bindTexture(guiTexture);
			int xStart = (width - xSize) / 2;
			int yStart = (height - ySize) / 2;
			drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		}

		drawSlotsLayer(EnumLayer.Background, container.getSlots(), RendererSlot.instance, mouseX, mouseY);
		drawSlotsLayer(EnumLayer.Background, container.fluidSlots, RendererFluidSlot.instance, mouseX, mouseY);
		drawSlotsLayer(EnumLayer.Background, container.energySlots, RendererEnergySlotProgress.instanceLeft, mouseX, mouseY);

	}

	public static void glColor4f(int color)
	{
		int b = color & 0xff;
		color >>= 8;
		int g = color & 0xff;
		color >>= 8;
		int r = color & 0xff;
		color >>= 8;
		int a = color & 0xff;

		GL11.glColor4f(r * 0.00390625F, g * 0.00390625F, b * 0.00390625F, a * 0.00390625F);
	}

	@SuppressWarnings("synthetic-access")
	public class GuiRenderHelperImpl implements IGuiRenderHelper
	{

		@Override
		public Minecraft getMinecraft()
		{
			return mc;
		}

		@Override
		public FontRenderer getFontRenderer()
		{
			return fontRendererObj;
		}

		@Override
		public void drawGradientRect(int x1, int y1, int x2, int y2, int color1, int color2)
		{
			GuiMir53.this.drawGradientRect(x1, y1, x2, y2, color1, color2);
		}

		@Override
		public void drawTexturedModelRect(int x, int y, int w, int h, float sx1, float sy1, float sx2, float sy2)
		{
			drawTexturedModelRect(x, y, w, h, sx1, sy1, sx2, sy2, EnumRotate.RIGHT0);
		}

		@Override
		public void drawTexturedModelRect(int x, int y, int w, int h, float sx1, float sy1, float sx2, float sy2, EnumRotate rotate)
		{
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();

			switch (rotate) {
				case RIGHT0:
					tessellator.addVertexWithUV(x + 0, y + h, zLevel, sx1, sy2);
					tessellator.addVertexWithUV(x + w, y + h, zLevel, sx2, sy2);
					tessellator.addVertexWithUV(x + w, y + 0, zLevel, sx2, sy1);
					tessellator.addVertexWithUV(x + 0, y + 0, zLevel, sx1, sy1);
					break;
				case RIGHT90:
					tessellator.addVertexWithUV(x + 0, y + h, zLevel, sx2, sy2);
					tessellator.addVertexWithUV(x + w, y + h, zLevel, sx2, sy1);
					tessellator.addVertexWithUV(x + w, y + 0, zLevel, sx1, sy1);
					tessellator.addVertexWithUV(x + 0, y + 0, zLevel, sx1, sy2);
					break;
				case RIGHT180:
					tessellator.addVertexWithUV(x + 0, y + h, zLevel, sx2, sy1);
					tessellator.addVertexWithUV(x + w, y + h, zLevel, sx1, sy1);
					tessellator.addVertexWithUV(x + w, y + 0, zLevel, sx1, sy2);
					tessellator.addVertexWithUV(x + 0, y + 0, zLevel, sx2, sy2);
					break;
				case RIGHT270:
					tessellator.addVertexWithUV(x + 0, y + h, zLevel, sx1, sy1);
					tessellator.addVertexWithUV(x + w, y + h, zLevel, sx1, sy2);
					tessellator.addVertexWithUV(x + w, y + 0, zLevel, sx2, sy2);
					tessellator.addVertexWithUV(x + 0, y + 0, zLevel, sx2, sy1);
					break;
				default:
					break;
			}

			tessellator.draw();
		}

		@Override
		public void drawTexturedModelRectFromIcon(int x, int y, int w, int h, IIcon icon, float sx1, float sy1, float sx2, float sy2)
		{
			float su1 = icon.getMinU() + (icon.getMaxU() - icon.getMinU()) * sx1;
			float sv1 = icon.getMinV() + (icon.getMaxV() - icon.getMinV()) * sy1;
			float su2 = icon.getMinU() + (icon.getMaxU() - icon.getMinU()) * sx2;
			float sv2 = icon.getMinV() + (icon.getMaxV() - icon.getMinV()) * sy2;

			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(x + 0, y + h, zLevel, su1, sv2);
			tessellator.addVertexWithUV(x + w, y + h, zLevel, su2, sv2);
			tessellator.addVertexWithUV(x + w, y + 0, zLevel, su2, sv1);
			tessellator.addVertexWithUV(x + 0, y + 0, zLevel, su1, sv1);
			tessellator.draw();
		}

		@Override
		public void drawRectAdd(int x1, int y1, int x2, int y2, int color)
		{
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_TEXTURE_2D);

			glColor4f(color);

			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			drawRect(x1, y1, x2, y2);

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}

		@Override
		public void drawRectMultiply(int x1, int y1, int x2, int y2, int color)
		{
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_TEXTURE_2D);

			glColor4f(color);

			GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_SRC_COLOR);
			drawRect(x1, y1, x2, y2);

			GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_SRC_ALPHA);
			drawRect(x1, y1, x2, y2);

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}

		@Override
		public void drawRectBlend(int x1, int y1, int x2, int y2, int color)
		{
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_TEXTURE_2D);

			glColor4f(color);

			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			drawRect(x1, y1, x2, y2);

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}

		@Override
		public void drawRect(int x1, int y1, int x2, int y2)
		{
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			tessellator.addVertex(x1, y2, zLevel);
			tessellator.addVertex(x2, y2, zLevel);
			tessellator.addVertex(x2, y1, zLevel);
			tessellator.addVertex(x1, y1, zLevel);
			tessellator.draw();
		}

		@Override
		public void drawRectFrame(int x, int y, int w, int h, int padding, int borderSize)
		{
			drawRectFrame(x, y, w, h, padding, borderSize, EnumStyleFrame.INSET);
		}

		@Override
		public void drawRectFrame(int x, int y, int w, int h, int padding, int borderSize, EnumStyleFrame styleFrame)
		{

			x -= padding;
			y -= padding;
			w += 2 * padding;
			h += 2 * padding;

			switch (styleFrame) {
				case GROOVE:
					drawRectMultiply(x, y, x + w, y + h, 0xffb3b3b3);
					drawRectMultiply(x - borderSize, y - borderSize, x + w, y, 0xff474747);
					drawRectMultiply(x - borderSize, y, x, y + h, 0xff474747);
					drawRectMultiply(x - borderSize, y + h, x, y + h + borderSize, 0xff474747);
					drawRectMultiply(x + w, y - borderSize, x + w + borderSize, y, 0xff474747);
					drawRectMultiply(x + w, y, x + w + borderSize, y + h, 0xff474747);
					drawRectMultiply(x, y + h, x + w + borderSize, y + h + borderSize, 0xff474747);
					break;
				case INSET:
					drawRectMultiply(x, y, x + w, y + h, 0xffb3b3b3);
					drawRectMultiply(x - borderSize, y - borderSize, x + w, y, 0xff474747);
					drawRectMultiply(x - borderSize, y, x, y + h, 0xff474747);
					drawRectMultiply(x - borderSize, y + h, x, y + h + borderSize, 0xffb3b3b3);
					drawRectMultiply(x + w, y - borderSize, x + w + borderSize, y, 0xffb3b3b3);
					drawRectAdd(x + w, y, x + w + borderSize, y + h, 0xff3a3a3a);
					drawRectAdd(x, y + h, x + w + borderSize, y + h + borderSize, 0xff3a3a3a);
					break;
				case OUTSET:
					drawRectMultiply(x, y, x + w, y + h, 0xffb3b3b3);
					drawRectAdd(x - borderSize, y - borderSize, x + w, y, 0xff3a3a3a);
					drawRectAdd(x - borderSize, y, x, y + h, 0xff3a3a3a);
					drawRectMultiply(x - borderSize, y + h, x, y + h + borderSize, 0xffb3b3b3);
					drawRectMultiply(x + w, y - borderSize, x + w + borderSize, y, 0xffb3b3b3);
					drawRectMultiply(x + w, y, x + w + borderSize, y + h, 0xff474747);
					drawRectMultiply(x, y + h, x + w + borderSize, y + h + borderSize, 0xff474747);
					break;
				case RIDGE:
					drawRectMultiply(x, y, x + w, y + h, 0xffb3b3b3);
					drawRectAdd(x - borderSize, y - borderSize, x + w, y, 0xff3a3a3a);
					drawRectAdd(x - borderSize, y, x, y + h, 0xff3a3a3a);
					drawRectAdd(x - borderSize, y + h, x, y + h + borderSize, 0xff3a3a3a);
					drawRectAdd(x + w, y - borderSize, x + w + borderSize, y, 0xff3a3a3a);
					drawRectAdd(x + w, y, x + w + borderSize, y + h, 0xff3a3a3a);
					drawRectAdd(x, y + h, x + w + borderSize, y + h + borderSize, 0xff3a3a3a);
					break;
				default:
					break;
			}

		}

		@Override
		public void drawHoveringText(List<String> list, int mouseX, int mouseY, FontRenderer font)
		{
			GuiMir53.this.drawHoveringText(list, mouseX, mouseY, font);
		}

		@Override
		public int getScreenWidth()
		{
			return width;
		}

		@Override
		public int getScreenHeight()
		{
			return height;
		}

		@Override
		public int getGuiWidth()
		{
			return xSize;
		}

		@Override
		public int getGuiHeight()
		{
			return ySize;
		}

	}

}
