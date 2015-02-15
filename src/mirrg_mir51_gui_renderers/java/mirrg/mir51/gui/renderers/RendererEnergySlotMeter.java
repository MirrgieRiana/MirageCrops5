package mirrg.mir51.gui.renderers;

import java.util.ArrayList;

import mirrg.he.math.HelpersCollision;
import mirrg.he.math.HelpersString;
import mirrg.mir50.gui.renderer.IGuiRenderHelper;
import mirrg.mir50.gui.renderer.IGuiRenderHelper.EnumRotate;
import mirrg.mir50.gui.renderer.IRenderer;
import mirrg.mir50.tile.inventory.EnergySlot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RendererEnergySlotMeter implements IRenderer<EnergySlot>
{

	public static final RendererEnergySlotMeter instance = new RendererEnergySlotMeter();

	@Override
	@SideOnly(Side.CLIENT)
	public void drawBackgroundLayer(IGuiRenderHelper gui, EnergySlot t, int mouseX, int mouseY)
	{

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawForegroundLayer(IGuiRenderHelper gui, EnergySlot t, int mouseX, int mouseY)
	{
		HelpersRenderer.drawMirageFontString(gui, HelpersString.getSeparatedString(t.energyTank.amount) + " H",
			t.x, t.y, HelpersRenderer.EnumTextAlign.CENTER, EnumRotate.RIGHT0);
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
