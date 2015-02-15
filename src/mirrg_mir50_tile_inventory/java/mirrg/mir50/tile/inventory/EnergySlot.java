package mirrg.mir50.tile.inventory;

public class EnergySlot
{

	public EnergyTank energyTank;
	public EnergyTank snapshot;
	public int x;
	public int y;
	public int w;
	public int h;

	public EnergySlot(EnergyTank energyTank, int x, int y, int w, int h)
	{
		this.energyTank = energyTank;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public boolean isDirtyAndSaveSnapshot()
	{
		boolean isDirty = !energyTank.equals(snapshot);
		snapshot = energyTank.copy();
		return isDirty;
	}

}
