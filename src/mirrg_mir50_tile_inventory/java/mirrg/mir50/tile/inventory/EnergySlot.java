package mirrg.mir50.tile.inventory;

import mirrg.mir52.tile.IContainerExtraSlot;
import net.minecraft.nbt.NBTTagCompound;

public class EnergySlot implements IContainerExtraSlot
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

	@Override
	public boolean isDirtyAndSaveSnapshot()
	{
		boolean isDirty = !energyTank.equals(snapshot);
		snapshot = energyTank.copy();
		return isDirty;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		energyTank.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		energyTank.writeToNBT(nbt);
	}

}
