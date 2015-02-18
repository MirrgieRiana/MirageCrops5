package mirrg.mir52.gui;

public class SupplierPositionContainerFlow implements ISupplierPositionContainer
{

	private int startX, startY, shiftX, shiftY, lineLength;

	public SupplierPositionContainerFlow(int startX, int startY, int shiftX, int shiftY, int lineLength)
	{
		this.startX = startX;
		this.startY = startY;
		this.shiftX = shiftX;
		this.shiftY = shiftY;
		this.lineLength = lineLength;
	}

	@Override
	public int getX(int id)
	{
		return (id % lineLength) * shiftX + startX;
	}

	@Override
	public int getY(int id)
	{
		return (id / lineLength) * shiftY + startY;
	}

}
