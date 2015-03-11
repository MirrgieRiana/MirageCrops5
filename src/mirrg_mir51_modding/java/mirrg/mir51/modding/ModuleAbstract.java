package mirrg.mir51.modding;

import java.util.function.Consumer;

import mirrg.mir50.loader.Loader;

public class ModuleAbstract implements Consumer<Loader<?>>
{

	public LoaderModule loaderModule = new LoaderModule();

	protected void add(Loader<?>... loader)
	{
		this.loaderModule.dependsOn(loader);
	}

	@Override
	public void accept(Loader<?> t)
	{
		add(t);
	}

}
