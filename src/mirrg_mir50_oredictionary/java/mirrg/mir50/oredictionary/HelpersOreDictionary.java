package mirrg.mir50.oredictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class HelpersOreDictionary
{

	public static ItemStack get(String name)
	{
		ArrayList<ItemStack> ores = OreDictionary.getOres(name);
		if (ores.size() == 0) return null;
		return ores.get(0);
	}

	public static ItemStack copy(String name)
	{
		ItemStack itemStack = get(name);
		return itemStack != null ? itemStack.copy() : null;
	}

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

	public static ItemStack get(String name, int stackSize)
	{
		ItemStack itemStack = get(name);
		if (itemStack == null) return null;
		itemStack.stackSize = stackSize;
		return itemStack;
	}

	public static ItemStack copy(String name, int stackSize)
	{
		ItemStack itemStack = copy(name);
		if (itemStack == null) return null;
		itemStack.stackSize = stackSize;
		return itemStack;
	}

	public static ItemStack getOrThrow(String name, int stackSize)
	{
		ItemStack itemStack = getOrThrow(name);
		itemStack.stackSize = stackSize;
		return itemStack;
	}

	public static ItemStack copyOrThrow(String name, int stackSize)
	{
		ItemStack itemStack = copyOrThrow(name);
		itemStack.stackSize = stackSize;
		return itemStack;
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
		if (itemStack == null) return false;
		for (ItemStack ore : OreDictionary.getOres(oreName)) {
			if (OreDictionary.itemMatches(ore, itemStack, false)) {
				return true;
			}
		}
		return false;
	}

	public static Stream<ItemStack> getOresStream(String oreName)
	{
		// wait for Forge for Java1.8
		ArrayList<ItemStack> ores = OreDictionary.getOres(oreName);
		Iterator<ItemStack> iterator = ores.iterator();
		return ((Function<Supplier<ItemStack>, Stream<ItemStack>>) Stream::generate)
			.apply(iterator::next)
			.limit(ores.size());
	}

}
