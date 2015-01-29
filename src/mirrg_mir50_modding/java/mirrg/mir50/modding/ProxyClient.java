package mirrg.mir50.modding;

import mirrg.mir50.loader.EnumLoadEventTiming;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ProxyClient implements IProxy
{

	@Override
	public boolean isServerSide()
	{
		return false;
	}

	@Override
	public void handle(FMLPreInitializationEvent event, LoaderMod loader)
	{
		loader.load(EnumLoadEventTiming.ClientBeforePreInit);
		loader.load(EnumLoadEventTiming.PreInit);
		loader.load(EnumLoadEventTiming.ClientAfterPreInit);
	}

	@Override
	public void handle(FMLInitializationEvent event, LoaderMod loader)
	{
		loader.load(EnumLoadEventTiming.ClientBeforePreInit);
		loader.load(EnumLoadEventTiming.PreInit);
		loader.load(EnumLoadEventTiming.ClientAfterPreInit);
	}

	@Override
	public void handle(FMLPostInitializationEvent event, LoaderMod loader)
	{
		loader.load(EnumLoadEventTiming.ClientBeforePreInit);
		loader.load(EnumLoadEventTiming.PreInit);
		loader.load(EnumLoadEventTiming.ClientAfterPreInit);
	}

}
