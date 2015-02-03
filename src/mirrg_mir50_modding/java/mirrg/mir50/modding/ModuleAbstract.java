package mirrg.mir50.modding;

import mirrg.mir50.loader.Loader;

public class ModuleAbstract
{

	public LoaderModule loaderModule = new LoaderModule();

	protected void add(Loader<?> loader)
	{
		this.loaderModule.dependsOn(loader);
	}

}
