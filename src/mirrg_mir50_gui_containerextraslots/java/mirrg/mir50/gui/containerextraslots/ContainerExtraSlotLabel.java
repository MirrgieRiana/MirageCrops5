package mirrg.mir50.gui.containerextraslots;

import java.util.function.ToIntFunction;

import net.minecraft.nbt.NBTTagCompound;
import api.mirrg.mir50.gui.renderer.EnumTextAlign;
import api.mirrg.mir50.gui.renderer.IGuiRenderHelper;

public class ContainerExtraSlotLabel extends ContainerExtraSlotAbstract
{

	public String string;
	public ToIntFunction<IGuiRenderHelper> x;
	public ToIntFunction<IGuiRenderHelper> y;
	public int color;
	public EnumTextAlign textAlign;

	public ContainerExtraSlotLabel(
		String string,
		ToIntFunction<IGuiRenderHelper> x,
		ToIntFunction<IGuiRenderHelper> y,
		int color,
		EnumTextAlign textAlign)
	{
		this.string = string;
		this.x = x;
		this.y = y;
		this.color = color;
		this.textAlign = textAlign;
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
