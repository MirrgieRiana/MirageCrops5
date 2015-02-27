package mirrg_miragecrops5.machine.container;

import api.mirrg.mir50.gui.renderer.IRenderer;
import api.mirrg.mir50.gui.renderer.IRendererProvider;
import mirrg.mir51.gui.renderers.RendererSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotProcessing extends Slot implements IRendererProvider<Slot>
{

	public SlotProcessing(IInventory inventory, int index, int x, int y)
	{
		super(inventory, index, x, y);
	}

	@Override
	public IRenderer<Slot> getRenderer()
	{
		return RendererSlot.instanceGroove;
	}

	@Override
	public boolean isItemValid(ItemStack itemStack)
	{
		return false;
	}

	@Override
	public boolean canTakeStack(EntityPlayer p_82869_1_)
	{
		return false;
	}

}
