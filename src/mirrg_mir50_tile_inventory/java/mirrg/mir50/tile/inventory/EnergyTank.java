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

}
