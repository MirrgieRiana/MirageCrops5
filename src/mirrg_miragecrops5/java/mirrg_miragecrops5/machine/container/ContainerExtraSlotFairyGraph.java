package mirrg_miragecrops5.machine.container;

import java.util.function.Consumer;

import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotRectangle;
import mirrg_miragecrops5.machine.tile.DatamodelFairyValues;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerExtraSlotFairyGraph extends ContainerExtraSlotRectangle
{

	public DatamodelFairyValues fairyValues;

	public ContainerExtraSlotFairyGraph(int x, int y, int w, int h, DatamodelFairyValues fairyValues)
	{
		super(x, y, w, h);
		this.fairyValues = fairyValues;
	}

	public Consumer<int[]> getIncreaser()
	{
		return fairyValues.getIncreaser();
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{

	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{

	}

	@Override
	public boolean isDirtyAndSaveSnapshot()
	{
		return false;
	}

}
