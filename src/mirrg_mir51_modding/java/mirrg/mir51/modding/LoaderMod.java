package mirrg.mir51.modding;

import mirrg.mir50.loader.LoaderRoot;

public class LoaderMod extends LoaderRoot
{

	public void addModule(ModuleAbstract module)
	{
		dependsOn(module.loaderModule);
	}

}
