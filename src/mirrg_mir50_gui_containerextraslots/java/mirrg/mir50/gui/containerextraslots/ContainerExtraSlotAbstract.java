package mirrg.mir50.gui.containerextraslots;

import api.mirrg.mir50.gui.containerextraslot.IContainerExtraSlot;
import api.mirrg.mir50.gui.renderer.IRenderer;
import api.mirrg.mir50.gui.renderer.IRendererProvider;

@SuppressWarnings("rawtypes")
public abstract class ContainerExtraSlotAbstract implements IContainerExtraSlot, IRendererProvider
{

	public IRenderer renderer = null;

	@Override
	public IRenderer getRenderer()
	{
		return renderer;
	}

}
