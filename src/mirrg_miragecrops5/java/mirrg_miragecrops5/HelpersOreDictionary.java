package mirrg_miragecrops5;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class HelpersOreDictionary
{

	public static ItemStack getOrThrow(String name)
	{
		ArrayList<ItemStack> ores = OreDictionary.getOres(name);
		if (ores.size() == 0) throw new NullPointerException("ore not found: " + name);
		return ores.get(0);
	}

	public static ItemStack copyOrThrow(String name)
	{
		return getOrThrow(name).copy();
	}

	public static ArrayList<String> getNames(ItemStack itemStack)
	{
		ArrayList<String> names = new ArrayList<>();

		for (String name : OreDictionary.getOreNames()) {
			ore:
			for (ItemStack ore : OreDictionary.getOres(name)) {
				if (OreDictionary.itemMatches(ore, itemStack, false)) {
					names.add(name);
					break ore;
				}
			}
		}

		return names;
	}

	public static boolean isOre(ItemStack itemStack, String oreName)
	{
		for (ItemStack ore : OreDictionary.getOres(oreName)) {
			if (OreDictionary.itemMatches(ore, itemStack, false)) {
				return true;
			}
		}
		return false;
	}

}
