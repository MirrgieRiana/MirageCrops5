package mirrg_miragecrops5.machine;

import java.util.function.ToIntFunction;

import mirrg.mir50.gui.renderer.IGuiRenderHelper;
import mirrg.mir50.gui.renderer.IRenderer;
import mirrg.mir50.gui.renderer.IRendererProvider;
import mirrg.mir52.tile.IContainerExtraSlot;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerExtraSlotLabel implements IContainerExtraSlot, IRendererProvider<ContainerExtraSlotLabel>
{

	protected String string;
	protected ToIntFunction<IGuiRenderHelper> x;
	protected ToIntFunction<IGuiRenderHelper> y;
	protected int color;

	public ContainerExtraSlotLabel(String string, ToIntFunction<IGuiRenderHelper> x, ToIntFunction<IGuiRenderHelper> y, int color)
	{
		this.string = string;
		this.x = x;
		this.y = y;
		this.color = color;
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

	@Override
	public IRenderer<ContainerExtraSlotLabel> getRenderer()
	{
		return new IRenderer<ContainerExtraSlotLabel>() {

			@Override
			public void drawBackgroundLayer(IGuiRenderHelper gui, ContainerExtraSlotLabel t, int mouseX, int mouseY)
			{

			}

			@Override
			public void drawForegroundLayer(IGuiRenderHelper gui, ContainerExtraSlotLabel t, int mouseX, int mouseY)
			{
				gui.getFontRenderer().drawString(string, x.applyAsInt(gui), y.applyAsInt(gui), color);
			}

			@Override
			public boolean drawToolTip(IGuiRenderHelper gui, ContainerExtraSlotLabel t, int mouseX, int mouseY)
			{
				return false;
			}

		};
	}

}
