package mirrg.mir50.loader;

import java.util.ArrayList;

import api.mirrg.mir50.loader.ILoader;
import api.mirrg.mir50.loader.LoadingException;

public abstract class Loader<T> implements ILoader<T>
{

	protected ArrayList<Loader<?>> dependancies = new ArrayList<>();
	protected T object = null;
	protected boolean[] loaded = new boolean[EnumLoadEventTiming.count];
	protected boolean loadCompleted = false;

	public void dependsOn(Loader<?> loader)
	{
		dependancies.add(loader);
	}

	public void load(EnumLoadEventTiming loadEvent)
	{
		if (loaded[loadEvent.ordinal()]) return;
		loaded[loadEvent.ordinal()] = true;

		loadDependancies(loadEvent);
		loadThisLoader(loadEvent);
	}

	protected void loadDependancies(EnumLoadEventTiming loadEvent)
	{
		dependancies.stream().forEach(loader -> loader.load(loadEvent));
	}

	protected abstract void loadThisLoader(EnumLoadEventTiming loadEvent);

	protected void set(T object)
	{
		if (loadCompleted) throw new LoadingException();
		loadCompleted = true;

		this.object = object;
	}

	@Override
	public T get()
	{
		if (!loadCompleted) throw new LoadingException();
		return object;
	}

}
