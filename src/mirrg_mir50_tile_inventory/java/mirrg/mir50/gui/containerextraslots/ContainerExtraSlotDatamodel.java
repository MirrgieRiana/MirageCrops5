package mirrg.mir50.gui.containerextraslots;

import net.minecraft.nbt.NBTTagCompound;
import api.mirrg.mir50.datamodel.IDatamodel;

public class ContainerExtraSlotDatamodel<T extends IDatamodel<T>> extends ContainerExtraSlotAbstract
{

	public T datamodel;
	public T snapshot;
	public int x;
	public int y;
	public int w;
	public int h;

	public ContainerExtraSlotDatamodel(T datamodel, int x, int y, int w, int h)
	{
		this.datamodel = datamodel;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	@Override
	public boolean isDirtyAndSaveSnapshot()
	{
		boolean isDirty = !datamodel.isEqualContent(snapshot);
		snapshot = datamodel.copy();
		return isDirty;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		datamodel.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		datamodel.writeToNBT(nbt);
	}

}
