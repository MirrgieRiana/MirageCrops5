package mirrg.mir50.tile.inventory;

import mirrg.mir50.datamodel.IDatamodel;
import mirrg.mir50.gui.container.IContainerExtraSlot;
import mirrg.mir50.gui.renderer.IRenderer;
import mirrg.mir50.gui.renderer.IRendererProvider;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerExtraSlotDatamodel<T extends IDatamodel<T>>
	implements IContainerExtraSlot, IRendererProvider<ContainerExtraSlotDatamodel<T>>
{

	public T datamodel;
	public T snapshot;
	public int x;
	public int y;
	public int w;
	public int h;
	public IRenderer<ContainerExtraSlotDatamodel<T>> renderer = null;

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

	@Override
	public IRenderer<ContainerExtraSlotDatamodel<T>> getRenderer()
	{
		return renderer;
	}

}
