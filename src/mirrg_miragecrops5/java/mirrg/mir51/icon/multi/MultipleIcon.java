package mirrg.mir51.icon.multi;

import mirrg.he.math.HelpersColor;
import mirrg.mir50.icon.multi.IMultipleIcon;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MultipleIcon implements IMultipleIcon
{

	protected final MultipleIconShape multiIconShape;
	private final int[] colors;

	protected class IniterSuper
	{

		protected void init(int[] colors)
		{

		}

	}

	protected class Initer extends IniterSuper
	{

		@Override
		@SideOnly(Side.CLIENT)
		protected void init(int[] colors)
		{
			if (multiIconShape.getChannelsUpperBound() != colors.length - 1) {
				throw new RuntimeException("channel size mismatched: " + multiIconShape.getChannelsUpperBound()
					+ " != " + (colors.length - 1));
			}
		}

	}

	public MultipleIcon(MultipleIconShape multiIconShape, int... colors)
	{
		this.multiIconShape = multiIconShape;
		this.colors = colors.clone();

		new Initer().init(colors);
	}

	@Override
	public int getLength()
	{
		return multiIconShape.getLength();
	}

	public int getChannelsUpperBound()
	{
		return multiIconShape.getChannelsUpperBound();
	}

	public IIcon getIcon(int pass)
	{
		return multiIconShape.getIcon(pass);
	}

	public void register(IIconRegister iconRegister)
	{
		multiIconShape.register(iconRegister);
	}

	public double getDefaultColorRate(int pass)
	{
		return multiIconShape.getDefaultColorRate(pass);
	}

	public int getChannel(int pass)
	{
		return multiIconShape.getChannel(pass);
	}

	@Override
	public int getColor(int pass)
	{
		return HelpersColor.multiplicate(colors[getChannel(pass)], getDefaultColorRate(pass));
	}

	@Override
	public String getIconString(int pass)
	{
		return multiIconShape.getIconString(pass);
	}

}
