package mirrg_miragecrops5.machine;

import mirrg.mir50.gui.renderer.IRenderer;
import mirrg.mir50.gui.renderer.IRendererProvider;
import mirrg.mir50.tile.inventory.EnergySlot;
import mirrg.mir50.tile.inventory.EnergyTank;
import mirrg.mir51.gui.renderers.RendererEnergySlotProgress;
import mirrg.mir51.gui.renderers.RendererEnergySlotProgress.EnumProgressAlign;

public class EnergySlotWithRenderer extends EnergySlot implements IRendererProvider<EnergySlot>
{

	public final static IRenderer<EnergySlot> rendererFuel = new RendererEnergySlotProgress("fuel", EnumProgressAlign.DOWN);

	public IRenderer<EnergySlot> renderer;

	public EnergySlotWithRenderer(EnergyTank energyTank, int x, int y, int w, int h, IRenderer<EnergySlot> renderer)
	{
		super(energyTank, x, y, w, h);
		this.renderer = renderer;
	}

	@Override
	public IRenderer<EnergySlot> getRenderer()
	{
		return renderer;
	}

}
