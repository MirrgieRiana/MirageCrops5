package mirrg.mir50.tile.inventory;

public class EnergySlot
{

	public EnergyTank energyTank;
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

}
