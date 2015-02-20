package mirrg_miragecrops5.machine;

import java.util.function.Consumer;

import mirrg_miragecrops5.fairytype.HelpersFairyType;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import api.mirrg.mir50.datamodel.IDatamodel;

public class DatamodelFairyValues implements IDatamodel<DatamodelFairyValues>
{

	private int[] values;
	private IInventory[] inventories;

	public DatamodelFairyValues(int[] values, IInventory... inventories)
	{
		this.values = values;
		this.inventories = inventories;
	}

	public Consumer<int[]> getIncreaser()
	{
		return HelpersFairyType.getIncreaser(values).andThen(HelpersFairyType.getIncreaser(inventories));
	}

	@Override
	public void markDirty()
	{

	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{

	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{

	}

	@Override
	public boolean isEmpty()
	{
		return true;
	}

	@Override
	public boolean isEqualContent(DatamodelFairyValues other)
	{
		return true;
	}

	@Override
	public DatamodelFairyValues copy()
	{
		return this;
	}

}
