package mirrg_miragecrops5.machine;

import mirrg.mir50.gui.renderer.IRenderer;
import mirrg.mir50.gui.renderer.IRendererProvider;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotAdvanced extends Slot implements IRendererProvider<Slot>
{

	public IRenderer<Slot> renderer = null;

	public SlotAdvanced(IInventory inventory, int index, int x, int y)
	{
		super(inventory, index, x, y);
	}

	public SlotAdvanced(IInventory inventory, int index, int x, int y, IRenderer<Slot> renderer)
	{
		super(inventory, index, x, y);
		this.renderer = renderer;
	}

	@Override
	public IRenderer<Slot> getRenderer()
	{
		return renderer;
	}

}
