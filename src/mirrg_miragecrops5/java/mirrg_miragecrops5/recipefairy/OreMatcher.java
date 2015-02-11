package mirrg_miragecrops5.recipefairy;

import java.util.ArrayList;
import java.util.function.Predicate;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreMatcher implements Predicate<ItemStack>
{

	protected ArrayList<Predicate<ItemStack>> matchers = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public OreMatcher(Object... objects)
	{
		for (Object object : objects) {
			if (object instanceof ItemStack) {
				addMatcher(((ItemStack) object).copy());
			} else if (object instanceof Item) {
				addMatcher(new ItemStack((Item) object));
			} else if (object instanceof Block) {
				addMatcher(new ItemStack((Block) object));
			} else if (object instanceof String) {
				addMatcher((String) object);
			} else if (object instanceof Predicate) {
				addMatcher((Predicate<ItemStack>) object);
			} else {
				throw new RuntimeException("Invalid shapeless ore recipe: " + object);
			}
		}
	}

	public OreMatcher addMatcher(String... oreNames)
	{
		for (String oreName : oreNames) {
			addMatcher(oreName);
		}
		return this;
	}

	public OreMatcher addMatcher(String oreName)
	{
		return addMatcher(itemStack -> {
			ArrayList<ItemStack> ores = OreDictionary.getOres(oreName);
			for (ItemStack ore : ores) {
				if (OreDictionary.itemMatches(ore, itemStack, false)) return true;
			}
			return false;
		});
	}

	public OreMatcher addMatcher(Item matcher)
	{
		return addMatcher(new ItemStack(matcher));
	}

	public OreMatcher addMatcher(Block matcher)
	{
		return addMatcher(new ItemStack(matcher));
	}

	public OreMatcher addMatcher(ItemStack matcher)
	{
		return addMatcher(matcher, false);
	}

	public OreMatcher addMatcher(ItemStack matcher, boolean strict)
	{
		return addMatcher(itemStack -> OreDictionary.itemMatches(matcher, itemStack, strict));
	}

	public OreMatcher addMatcher(Predicate<ItemStack> matcher)
	{
		matchers.add(matcher);
		return this;
	}

	public boolean matches(ItemStack itemStack)
	{
		for (Predicate<ItemStack> matcher : matchers) {
			if (matcher.test(itemStack)) return true;
		}
		return false;
	}

	@Override
	public boolean test(ItemStack t)
	{
		return matches(t);
	}

}
