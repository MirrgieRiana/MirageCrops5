package mirrg.mir51.modding;

import mirrg.mir50.loader.EnumLoadEventTiming;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public abstract class ModAbstract
{

	public final String modId;
	public final String version;
	public final String name;
	public final String dependencies;

	public ModAbstract()
	{

		try {
			modId = (String) this.getClass().getField("MODID").get(null);
			version = (String) this.getClass().getField("VERSION").get(null);
			name = (String) this.getClass().getField("NAME").get(null);
			dependencies = (String) this.getClass().getField("DEPENDENCIES").get(null);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}

	}

	public final String getModId()
	{
		return modId;
	}

	public final String getVersion()
	{
		return version;
	}

	public final String getName()
	{
		return name;
	}

	public final String getDependencies()
	{
		return dependencies;
	}

	//

	public LoaderMod loaderMod = new LoaderMod();

	public void add(ModuleAbstract module)
	{
		loaderMod.addModule(module);

		MinecraftForge.EVENT_BUS.register(module);
	}

	//

	@SidedProxy(
		clientSide = "mirrg.mir51.modding.ProxyClient",
		serverSide = "mirrg.mir51.modding.ProxyServer")
	public static IProxy proxy;

	public static boolean isClient()
	{
		return !proxy.isServerSide();
	}

	public static boolean isServer()
	{
		return proxy.isServerSide();
	}

	//

	protected void handleCreated()
	{
		loaderMod.load(EnumLoadEventTiming.Created);
	}

	/**
	 * 末端の実装クラスでは、{@link @EventHandler}を付ける。
	 * 必ずオーバーライドしなければならない。
	 */
	public void handle(FMLPreInitializationEvent event)
	{
		proxy.handle(event, loaderMod);
	}

	/**
	 * 末端の実装クラスでは、{@link @EventHandler}を付ける。
	 * 必ずオーバーライドしなければならない。
	 */
	public void handle(FMLInitializationEvent event)
	{
		proxy.handle(event, loaderMod);
	}

	/**
	 * 末端の実装クラスでは、{@link @EventHandler}を付ける。
	 * 必ずオーバーライドしなければならない。
	 */
	public void handle(FMLPostInitializationEvent event)
	{
		proxy.handle(event, loaderMod);

		loaderMod.load(EnumLoadEventTiming.Completed);
	}

}
