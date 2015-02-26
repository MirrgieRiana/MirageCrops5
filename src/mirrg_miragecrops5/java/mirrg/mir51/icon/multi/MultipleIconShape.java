package mirrg.mir51.icon.multi;

import mirrg.h.struct.Tuple3;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class MultipleIconShape
{

	private final String prefix;
	private final IIcon[] icons;
	private final Entry[] entries;
	private final int channelsUpperBound;

	public MultipleIconShape(String prefix, Entry... entries)
	{
		this.prefix = prefix;
		icons = new IIcon[entries.length];
		this.entries = entries;

		int channelsUpperBound = 0;
		for (Entry entry : entries) {
			if (channelsUpperBound < entry.getZ()) {
				channelsUpperBound = entry.getZ();
			}
		}
		this.channelsUpperBound = channelsUpperBound;
	}

	public static Entry entry(String name, double defaultColorRate)
	{
		return entry(name, defaultColorRate, 0);
	}

	public static Entry entry(String name, double defaultColorRate, int channel)
	{
		return new Entry(name, defaultColorRate, channel);
	}

	public static class Entry extends Tuple3<String, Double, Integer>
	{

		protected Entry(String name, double defaultColorRate, int channel)
		{
			super(name, defaultColorRate, channel);
		}

	}

	public int getLength()
	{
		return entries.length;
	}

	public int getChannelsUpperBound()
	{
		return channelsUpperBound;
	}

	public IIcon getIcon(int pass)
	{
		if (pass >= getLength()) return getIcon(getLength() - 1);
		return icons[pass];
	}

	public void register(IIconRegister iIconRegister)
	{
		for (int pass = 0; pass < getLength(); pass++) {
			icons[pass] = iIconRegister.registerIcon(getTextureName(pass));
		}
	}

	private String getTextureName(int pass)
	{
		return getPrefix() + "_" + getEntryName(pass);
	}

	private String getEntryName(int pass)
	{
		return entries[pass].getX();
	}

	private String getPrefix()
	{
		return prefix;
	}

	public double getDefaultColorRate(int pass)
	{
		if (pass >= getLength()) return getDefaultColorRate(getLength() - 1);
		return entries[pass].getY();
	}

	public int getChannel(int pass)
	{
		if (pass >= getLength()) return getChannel(getLength() - 1);
		return entries[pass].getZ();
	}

	public String getIconString(int pass)
	{
		return getTextureName(pass);
	}

}
