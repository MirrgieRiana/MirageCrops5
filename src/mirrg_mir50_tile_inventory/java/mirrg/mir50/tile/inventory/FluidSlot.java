package mirrg.mir50.tile.inventory;

import mirrg.mir50.gui.renderer.IRenderer;
import mirrg.mir50.gui.renderer.IRendererProvider;
import mirrg.mir51.gui.renderers.RendererFluidSlot;
import mirrg.mir52.tile.IContainerExtraSlot;
import net.minecraft.nbt.NBTTagCompound;

public class FluidSlot implements IContainerExtraSlot, IRendererProvider<FluidSlot>
{

	public FluidTank fluidTank;
	public FluidTank snapshot;
	public int x;
	public int y;
	public int w;
	public int h;

	public FluidSlot(FluidTank fluidTank, int x, int y, int w, int h)
	{
		this.fluidTank = fluidTank;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	@Override
	public boolean isDirtyAndSaveSnapshot()
	{
		boolean isDirty = !fluidTank.equals(snapshot);
		snapshot = fluidTank.copy();
		return isDirty;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		fluidTank.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		fluidTank.writeToNBT(nbt);
	}

	@Override
	public IRenderer<FluidSlot> getRenderer()
	{
		return RendererFluidSlot.instance;
	}

}
