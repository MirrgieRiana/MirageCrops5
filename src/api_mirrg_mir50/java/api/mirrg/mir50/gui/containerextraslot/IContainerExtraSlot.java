package api.mirrg.mir50.gui.containerextraslot;

import net.minecraft.nbt.NBTTagCompound;

public interface IContainerExtraSlot
{

	public boolean isDirtyAndSaveSnapshot();

	public void readFromNBT(NBTTagCompound nbt);

	public void writeToNBT(NBTTagCompound nbt);

}
