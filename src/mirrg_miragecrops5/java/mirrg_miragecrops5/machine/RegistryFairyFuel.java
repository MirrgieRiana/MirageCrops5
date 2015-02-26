package mirrg_miragecrops5.machine;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import mirrg.mir50.oredictionary.HelpersOreDictionary;
import net.minecraft.item.ItemStack;

public class RegistryFairyFuel
{

	@FunctionalInterface
	public interface IHandlerFairyFuel
	{

		public int getFuelValue(ItemStack itemStack);

	}

	public static ArrayList<IHandlerFairyFuel> handlerFairyFuels = new ArrayList<>();

	public static Hashtable<String, Integer> registryFairyFuelOre = new Hashtable<>();
	static {
		register(itemStack -> {
			List<String> names = HelpersOreDictionary.getNames(itemStack);

			int theFuelValue = 0;
			for (String name : names) {
				if (registryFairyFuelOre.containsKey(name)) {
					int fuelValue = registryFairyFuelOre.get(name);
					if (fuelValue > theFuelValue) theFuelValue = fuelValue;
				}
			}
			return theFuelValue;
		});
	}

	public static void register(String dictionaryName, int fuelValue)
	{
		registryFairyFuelOre.put(dictionaryName, fuelValue);
	}

	public static void register(IHandlerFairyFuel handlerFairyFuel)
	{
		handlerFairyFuels.add(handlerFairyFuel);
	}

	public static int getFairyFuelValue(ItemStack itemStack)
	{
		if (itemStack == null) return 0;

		int theFuelValue = 0;
		for (int i = 0; i < handlerFairyFuels.size(); i++) {
			int fuelValue = handlerFairyFuels.get(i).getFuelValue(itemStack);
			if (fuelValue > 0 && fuelValue > theFuelValue) theFuelValue = fuelValue;
		}
		return theFuelValue;
	}

}
