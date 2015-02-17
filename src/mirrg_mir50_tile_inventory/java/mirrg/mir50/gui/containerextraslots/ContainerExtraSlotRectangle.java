package mirrg.mir50.gui.containerextraslots;

public abstract class ContainerExtraSlotRectangle extends ContainerExtraSlotAbstract
{

	public int x;
	public int y;
	public int w;
	public int h;

	public ContainerExtraSlotRectangle(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

}
