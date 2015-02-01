package mirrg_miragecrops5;

import mirrg.mir50.modding.ModAbstract;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
	modid = ModMirageCrops.MODID,
	version = ModMirageCrops.VERSION,
	name = ModMirageCrops.NAME,
	dependencies = ModMirageCrops.DEPENDENCIES)
public class ModMirageCrops extends ModAbstract
{

	public static final String MODID = "miragecrops5";
	public static final String VERSION = "0.0.1";
	public static final String NAME = "MirageCrops 5";
	public static final String DEPENDENCIES =
		"after:IC2;" +
			//"required-after:IC2;" +
			"";

	@Instance(ModMirageCrops.MODID)
	public static ModMirageCrops instance;

	//

	public static ModuleMirageCropsCore moduleMirageCropsCore = new ModuleMirageCropsCore();

	public ModMirageCrops()
	{
		add(moduleMirageCropsCore);

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
