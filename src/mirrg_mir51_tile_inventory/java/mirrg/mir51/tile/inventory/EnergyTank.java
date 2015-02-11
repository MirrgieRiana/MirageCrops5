package mirrg.mir51.tile.inventory;

import mirrg.mir50.net.NBTTypes;
import net.minecraft.nbt.NBTTagCompound;

public class EnergyTank implements ISetDirty
{

	public long amount;
	public long capacity;
	public final long defaultCapacity;
	protected ISetDirty parent;

	public EnergyTank(ISetDirty parent, long capacity)
	{
		this.parent = parent;
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
	public void setDirty()
	{
		parent.setDirty();
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
