package mirrg.mir51.loaders;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class LoaderTileEntity extends Loader<Void>
{

	public Class<? extends TileEntity> tileEntityClass;
	public String id;

	public LoaderTileEntity()
	{

	}

	public LoaderTileEntity(Class<? extends TileEntity> tileEntityClass, String id)
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
