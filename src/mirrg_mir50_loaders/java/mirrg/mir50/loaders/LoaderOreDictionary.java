package mirrg.mir50.loaders;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;

public class LoaderOreDictionary extends Loader<Void>
{

	protected Runnable runner;

	public void init(Runnable runner)
	{
		this.runner = runner;
	}

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.PreInit) {
			runner.run();
			loadCompleted();
		}
	}

}
