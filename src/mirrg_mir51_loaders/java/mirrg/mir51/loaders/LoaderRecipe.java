package mirrg.mir51.loaders;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;

public class LoaderRecipe extends Loader<Void>
{

	protected Runnable runner;

	public LoaderRecipe()
	{

	}

	public LoaderRecipe(Runnable runner)
	{
		init(runner);
	}

	public void init(Runnable runner)
	{
		this.runner = runner;
	}

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.PostInit) {
			runner.run();
			loadCompleted();
		}
	}

}
