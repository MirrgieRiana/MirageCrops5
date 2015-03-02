package api.mirrg.mir50.datamodel;

import net.minecraft.nbt.NBTTagCompound;

public interface IDatamodel<SELF>
{

	public void markDirty();

	public void writeToNBT(NBTTagCompound tag);

	public void readFromNBT(NBTTagCompound tag);

	public boolean isEmpty();

	public boolean isEqualContent(SELF other);

	public SELF copy();

	public void reset();

}
