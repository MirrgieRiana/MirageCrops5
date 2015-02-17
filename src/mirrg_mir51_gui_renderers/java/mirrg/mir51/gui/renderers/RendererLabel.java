package mirrg.mir51.gui.renderers;

import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotLabel;
import api.mirrg.mir50.gui.renderer.IGuiRenderHelper;
import api.mirrg.mir50.gui.renderer.IRenderer;

public class RendererLabel implements IRenderer<ContainerExtraSlotLabel>
{

	private ContainerExtraSlotLabel containerExtraSlotLabel;

	public RendererLabel(ContainerExtraSlotLabel containerExtraSlotLabel)
	{
		this.containerExtraSlotLabel = containerExtraSlotLabel;
	}

	@Override
	public void drawBackgroundLayer(IGuiRenderHelper gui, ContainerExtraSlotLabel t, int mouseX, int mouseY)
	{

	}

	@Override
	public void drawForegroundLayer(IGuiRenderHelper gui, ContainerExtraSlotLabel t, int mouseX, int mouseY)
	{
		int xi;

		switch (containerExtraSlotLabel.textAlign) {
			case CENTER:
				xi = -gui.getFontRenderer().getStringWidth(containerExtraSlotLabel.string) / 2;
				break;
			case LEFT:
				xi = 0;
				break;
			case RIGHT:
				xi = -gui.getFontRenderer().getStringWidth(containerExtraSlotLabel.string);
				break;
			default:
				xi = 0;
				break;
		}

		gui.getFontRenderer().drawString(
			containerExtraSlotLabel.string,
			containerExtraSlotLabel.x.applyAsInt(gui) + xi,
			containerExtraSlotLabel.y.applyAsInt(gui),
			containerExtraSlotLabel.color);
	}

	@Override
	public boolean drawToolTip(IGuiRenderHelper gui, ContainerExtraSlotLabel t, int mouseX, int mouseY)
	{
		return false;
	}

}
