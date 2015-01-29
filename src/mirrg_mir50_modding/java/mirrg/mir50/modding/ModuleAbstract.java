package mirrg.mir50.modding;

import mirrg.mir50.loader.Loader;

public class ModuleAbstract
{

	public LoaderModule loader = new LoaderModule();

	protected void add(Loader<?> loader)
	{
		this.loader.dependsOn(loader);
	}

}
