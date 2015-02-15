package mirrg.mir50.tile.inventory;

public class FluidSlot
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

	public boolean isDirtyAndSaveSnapshot()
	{
		boolean isDirty = !fluidTank.equals(snapshot);
		snapshot = fluidTank.copy();
		return isDirty;
	}

}
