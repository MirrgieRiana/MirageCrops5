package mirrg.mir50.gui.containerextraslots;

import net.minecraft.nbt.NBTTagCompound;
import api.mirrg.mir50.datamodel.IDatamodel;

public class ContainerExtraSlotDatamodel<T extends IDatamodel<T>> extends ContainerExtraSlotRectangle
{

	public T datamodel;
	public T snapshot = null;

	public ContainerExtraSlotDatamodel(int x, int y, int w, int h, T datamodel)
	{
		super(x, y, w, h);
		this.datamodel = datamodel;
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
