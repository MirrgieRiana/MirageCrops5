package mirrg_miragecrops5;

import mirrg.mir51.modding.ModAbstract;
import mirrg_miragecrops5.fairytype.ModuleFairyType;
import mirrg_miragecrops5.machine.ModuleMachine;
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

	public static ModuleCore moduleCore = new ModuleCore();
	public static ModuleMaterials moduleMaterials = new ModuleMaterials();
	public static ModuleWorldGeneration moduleWorldGeneration = new ModuleWorldGeneration();
	public static ModuleFairyType moduleFairyType = new ModuleFairyType();
	public static ModuleMachine moduleMachine = new ModuleMachine();

	public ModMirageCrops()
	{
		add(moduleCore);
		add(moduleMaterials);
		add(moduleWorldGeneration);
		add(moduleFairyType);
		add(moduleMachine);

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
