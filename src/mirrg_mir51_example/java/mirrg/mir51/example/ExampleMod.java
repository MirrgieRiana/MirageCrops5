package mirrg.mir51.example;

import mirrg.mir50.modding.ModAbstract;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
	modid = ExampleMod.MODID,
	version = ExampleMod.VERSION,
	name = ExampleMod.NAME,
	dependencies = ExampleMod.DEPENDENCIES)
public class ExampleMod extends ModAbstract
{

	public static final String MODID = "mirrg_example_mod";
	public static final String VERSION = "0.0.1";
	public static final String NAME = "Mirrg Example Mod";
	public static final String DEPENDENCIES =
		"after:IC2;" +
			//"required-after:IC2;" +
			"";

	@Instance(ExampleMod.MODID)
	public static ExampleMod instance;

	public static ExampleModuleCore exampleModuleCore = new ExampleModuleCore();

	public ExampleMod()
	{
		add(exampleModuleCore);

		handleCreated();
	}

	//

	@Override
	@EventHandler
	public void handle(FMLPreInitializationEvent event)
	{
		super.handle(event);
	}

	@Override
	@EventHandler
	public void handle(FMLInitializationEvent event)
	{
		super.handle(event);
	}

	@Override
	@EventHandler
	public void handle(FMLPostInitializationEvent event)
	{
		super.handle(event);
	}

}
