package mirrg.mir51.modding;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IProxy
{

	public boolean isServerSide();

	public void handle(FMLPreInitializationEvent event, LoaderMod loader);

	public void handle(FMLInitializationEvent event, LoaderMod loader);

	public void handle(FMLPostInitializationEvent event, LoaderMod loader);

}
