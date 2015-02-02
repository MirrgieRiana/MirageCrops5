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

	public Loader<T> dependsOn(Loader<?>... loaders)
	{
		for (Loader<?> loader : loaders) {
			dependancies.add(loader);
		}
		return this;
	}

	public void load(EnumLoadEventTiming loadEvent)
	{
		if (loaded[loadEvent.ordinal()]) return;
		loaded[loadEvent.ordinal()] = true;

		loadDependancies(loadEvent);
		loadThisLoader(loadEvent);

		if (loadEvent == EnumLoadEventTiming.Completed) {
			if (!loadCompleted) throw new LoadingException(this);
		}
	}

	protected void loadDependancies(EnumLoadEventTiming loadEvent)
	{
		dependancies.stream().forEach(loader -> loader.load(loadEvent));
	}

	protected abstract void loadThisLoader(EnumLoadEventTiming loadEvent);

	protected void set(T object)
	{
		this.object = object;
		loadCompleted();
	}

	protected void loadCompleted()
	{
		if (loadCompleted) throw new LoadingException(this);
		loadCompleted = true;
	}

	@Override
	public T get()
	{
		if (!loadCompleted) throw new LoadingException(this);
		return object;
	}

}
