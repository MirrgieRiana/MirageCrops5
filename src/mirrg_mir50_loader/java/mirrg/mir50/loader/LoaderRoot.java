package mirrg.mir50.loader;

import java.util.Iterator;

public class LoaderRoot extends Loader<Void> implements Iterable<Loader<?>>
{

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.Created) loadCompleted();
	}

	@Override
	public Iterator<Loader<?>> iterator()
	{
		return dependancies.iterator();
	}

}
