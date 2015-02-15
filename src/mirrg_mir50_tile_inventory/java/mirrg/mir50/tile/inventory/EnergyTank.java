package mirrg.mir50.tile.inventory;

import net.minecraft.nbt.NBTTagCompound;
import api.mirrg.mir50.net.NBTTypes;

public class EnergyTank
{

	public long amount;
	public long capacity;
	public final long defaultCapacity;
	protected Runnable runnableMarkDirty;

	public EnergyTank(Runnable runnableMarkDirty, long capacity)
	{
		this.runnableMarkDirty = runnableMarkDirty;
		this.capacity = capacity;
		this.defaultCapacity = capacity;
	}

	public long getAmount()
	{
		return amount;
	}

	public long getCapacity()
	{
		return capacity;
	}

	public long getDefaultCapacity()
	{
		return defaultCapacity;
	}

	public void markDirty()
	{
		runnableMarkDirty.run();
	}

	public void setAmount(long amount)
	{
		this.amount = amount;
	}

	public void setCapacity(long capacity)
	{
		this.capacity = capacity;
	}

	public void writeToNBT(NBTTagCompound tag)
	{
		tag.setLong("Amount", amount);
		tag.setLong("Capacity", capacity);
	}

	public void readFromNBT(NBTTagCompound tag)
	{

		if (tag.hasKey("Amount", NBTTypes.LONG)) {
			amount = tag.getLong("Amount");
		} else {
			amount = 0;
		}

		if (tag.hasKey("Capacity", NBTTypes.LONG)) {
			capacity = tag.getLong("Capacity");
		} else {
			capacity = 0;
		}

	}

	public EnergyTank copy()
	{
		EnergyTank energyTank = new EnergyTank(runnableMarkDirty, defaultCapacity);
		energyTank.amount = amount;
		energyTank.capacity = capacity;
		return energyTank;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (amount ^ (amount >>> 32));
		result = prime * result + (int) (capacity ^ (capacity >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		EnergyTank other = (EnergyTank) obj;
		if (amount != other.amount) return false;
		if (capacity != other.capacity) return false;
		return true;
	}

}
