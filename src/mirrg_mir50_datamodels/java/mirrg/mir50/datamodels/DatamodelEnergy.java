package mirrg.mir50.datamodels;

import net.minecraft.nbt.NBTTagCompound;
import api.mirrg.mir50.datamodel.IDatamodel;
import api.mirrg.mir50.net.NBTTypes;

public class DatamodelEnergy implements IDatamodel<DatamodelEnergy>
{

	/**
	 * 単位は1/1000。
	 */
	public long amount;

	/**
	 * 単位は1/1000。
	 */
	public long capacity;

	public final long defaultCapacity;
	protected Runnable runnableMarkDirty;

	public DatamodelEnergy(Runnable runnableMarkDirty, long capacity)
	{
		reset();
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

	@Override
	public void markDirty()
	{
		runnableMarkDirty.run();
	}

	public void setAmount(long amount)
	{
		this.amount = amount;
	}

	public void decrAmount(long amount)
	{
		this.amount -= amount;
	}

	public void setCapacity(long capacity)
	{
		this.capacity = capacity;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		tag.setLong("Amount", amount);
		tag.setLong("Capacity", capacity);
	}

	@Override
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

	@Override
	public DatamodelEnergy copy()
	{
		DatamodelEnergy energyTank = new DatamodelEnergy(runnableMarkDirty, defaultCapacity);
		energyTank.amount = amount;
		energyTank.capacity = capacity;
		return energyTank;
	}

	@Override
	public boolean isEmpty()
	{
		return amount > 0;
	}

	@Override
	public boolean isEqualContent(DatamodelEnergy other)
	{
		if (other == null) return false;
		return amount == other.amount && capacity == other.capacity;
	}

	@Override
	public void reset()
	{
		setAmount(0);
		setCapacity(getDefaultCapacity());
	}

}
