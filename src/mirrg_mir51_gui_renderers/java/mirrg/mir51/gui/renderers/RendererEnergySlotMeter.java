package mirrg.mir51.gui.renderers;

import java.util.ArrayList;

import mirrg.he.math.HelpersCollision;
import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotDatamodel;
import mirrg.mir50.gui.renderer.HelpersRenderer;
import api.mirrg.mir50.gui.renderer.EnumRotate;
import api.mirrg.mir50.gui.renderer.EnumTextAlign;
import api.mirrg.mir50.gui.renderer.IGuiRenderHelper;
import api.mirrg.mir50.gui.renderer.IRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RendererEnergySlotMeter implements IRenderer<ContainerExtraSlotDatamodel<DatamodelEnergy>>
{

	public static final RendererEnergySlotMeter instance = new RendererEnergySlotMeter();

	@Override
	@SideOnly(Side.CLIENT)
	public void drawBackgroundLayer(IGuiRenderHelper gui, ContainerExtraSlotDatamodel<DatamodelEnergy> t, int mouseX, int mouseY)
	{

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawForegroundLayer(IGuiRenderHelper gui, ContainerExtraSlotDatamodel<DatamodelEnergy> t, int mouseX, int mouseY)
	{
		HelpersRenderer.drawMirageFontString(gui, String.format("%,.3f H", t.datamodel.getAmount() * 0.001),
			t.x, t.y, EnumTextAlign.CENTER, EnumRotate.RIGHT0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean drawToolTip(IGuiRenderHelper gui, ContainerExtraSlotDatamodel<DatamodelEnergy> t, int mouseX, int mouseY)
	{
		int xStart = (gui.getScreenWidth() - gui.getGuiWidth()) / 2;
		int yStart = (gui.getScreenHeight() - gui.getGuiHeight()) / 2;

		if (hit(gui, t, mouseX, mouseY)) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(String.format("%.3f / %.3f", t.datamodel.getAmount() * 0.001, t.datamodel.getCapacity() * 0.001));
			gui.drawHoveringText(list, mouseX - xStart, mouseY - yStart, gui.getFontRenderer());
			return true;
		}

		return false;
	}

	public boolean hit(IGuiRenderHelper gui, ContainerExtraSlotDatamodel<DatamodelEnergy> t, int mouseX, int mouseY)
	{
		int xStart = (gui.getScreenWidth() - gui.getGuiWidth()) / 2;
		int yStart = (gui.getScreenHeight() - gui.getGuiHeight()) / 2;

		return HelpersCollision.isHit(mouseX, mouseY,
			xStart + t.x, yStart + t.y, xStart + t.x + t.w, yStart + t.y + t.h) > 0;
	}

}
