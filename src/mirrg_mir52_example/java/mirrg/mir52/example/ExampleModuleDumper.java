package mirrg.mir52.example;

import java.util.ArrayList;
import java.util.Arrays;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import mirrg.mir51.modding.ModuleAbstract;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.FMLLog;

public class ExampleModuleDumper extends ModuleAbstract
{

	public ExampleModuleDumper()
	{
		add(new Loader<Void>() {

			@Override
			protected void loadThisLoader(EnumLoadEventTiming loadEvent)
			{
				if (loadEvent != EnumLoadEventTiming.Completed) return;

				String[] oreNames = OreDictionary.getOreNames();
				Arrays.sort(oreNames);

				FMLLog.info("[[Ore Dict]]: Start");

				for (String oreName : oreNames) {

					ArrayList<ItemStack> ores = OreDictionary.getOres(oreName);

					if (ores.size() == 1) {

						ItemStack ore = ores.get(0);
						{
							FMLLog.info("[%s] %4s, %5s, %s", oreName, Item.getIdFromItem(ore.getItem()),
								ore.getItemDamage(),
								ore.getItem().getClass().getSimpleName());
						}

					} else {

						FMLLog.info("[%s]", oreName);

						for (ItemStack ore : ores) {
							FMLLog.info("%4s, %5s, %s", Item.getIdFromItem(ore.getItem()),
								ore.getItemDamage(),
								ore.getItem().getClass().getSimpleName());
						}

					}

				}

				FMLLog.info("[[Ore Dict]]: End");

				loadCompleted();
			}

		});
	}

}
