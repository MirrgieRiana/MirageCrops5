package mirrg_miragecrops5;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import mirrg_miragecrops5.machine.TileEntityMachineMirageFairy;
import cpw.mods.fml.common.registry.GameRegistry;

public class LoaderTileEntity extends Loader<Void>
{

	public Class<TileEntityMachineMirageFairy> tileEntityClass;
	public String id;

	public LoaderTileEntity()
	{

	}

	public LoaderTileEntity(Class<TileEntityMachineMirageFairy> tileEntityClass, String id)
	{
		this.tileEntityClass = tileEntityClass;
		this.id = id;
	}

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.PreInit) {
			GameRegistry.registerTileEntity(tileEntityClass, id);
			loadCompleted();
		}
	}

}
