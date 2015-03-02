package mirrg.mir50.datamodels;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import api.mirrg.mir50.datamodel.IDatamodel;
import api.mirrg.mir50.net.NBTTypes;

public class DatamodelDirection implements IDatamodel<DatamodelDirection>
{

	public int direction;

	protected Runnable runnableMarkDirty;

	public DatamodelDirection(Runnable runnableMarkDirty, int direction)
	{
		this(runnableMarkDirty);
		this.direction = direction;
	}

	public DatamodelDirection(Runnable runnableMarkDirty)
	{
		reset();
		this.runnableMarkDirty = runnableMarkDirty;
	}

	@Override
	public void markDirty()
	{
		runnableMarkDirty.run();
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		tag.setInteger("Direction", direction);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{

		if (tag.hasKey("Direction", NBTTypes.INT)) {
			direction = tag.getInteger("Direction");
		} else {
			direction = ForgeDirection.EAST.ordinal();
		}

	}

	@Override
	public DatamodelDirection copy()
	{
		return new DatamodelDirection(runnableMarkDirty, direction);
	}

	@Override
	public boolean isEmpty()
	{
		return false;
	}

	@Override
	public boolean isEqualContent(DatamodelDirection other)
	{
		if (other == null) return false;
		return direction == other.direction;
	}

	@Override
	public void reset()
	{
		direction = ForgeDirection.EAST.ordinal();
	}

}
