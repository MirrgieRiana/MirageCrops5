package mirrg_miragecrops5.machine;

import mirrg.mir50.gui.renderer.IRenderer;
import mirrg.mir50.tile.inventory.ContainerExtraSlotDatamodel;
import mirrg.mir51.datamodels.DatamodelEnergy;
import mirrg.mir51.gui.renderers.RendererEnergySlotProgress;
import mirrg.mir51.gui.renderers.RendererEnergySlotProgress.EnumProgressAlign;

public class EnergySlotWithRenderer extends ContainerExtraSlotDatamodel<DatamodelEnergy>
{

	public final static IRenderer<ContainerExtraSlotDatamodel<DatamodelEnergy>> rendererFuel =
		new RendererEnergySlotProgress("fuel", EnumProgressAlign.DOWN);

	public IRenderer<ContainerExtraSlotDatamodel<DatamodelEnergy>> renderer;

	public EnergySlotWithRenderer(DatamodelEnergy energyTank, int x, int y, int w, int h,
		IRenderer<ContainerExtraSlotDatamodel<DatamodelEnergy>> renderer)
	{
		super(energyTank, x, y, w, h);
		this.renderer = renderer;
	}

	@Override
	public IRenderer<ContainerExtraSlotDatamodel<DatamodelEnergy>> getRenderer()
	{
		return renderer;
	}

}
