package mirrg.mir50.oredictionary;

import java.util.ArrayList;
import java.util.function.Predicate;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OreMatcher implements Predicate<ItemStack>
{

	protected ArrayList<Predicate<ItemStack>> matchers = new ArrayList<>();

	public OreMatcher()
	{

	}

	public OreMatcher(String... oreNames)
	{
		addMatcher(oreNames);
	}

	public OreMatcher(ItemStack... matchers)
	{
		addMatcher(matchers);
	}

	public OreMatcher(ItemStack matcher, boolean strict)
	{
		addMatcher(matcher, strict);
	}

	public OreMatcher addMatcher(String... oreNames)
	{
		for (String oreName : oreNames) {
			addMatcher(new PredicateOreName(oreName));
		}
		return this;
	}

	public OreMatcher addMatcher(Item... matchers)
	{
		for (Item matcher : matchers) {
			addMatcher(new ItemStack(matcher));
		}
		return this;
	}

	public OreMatcher addMatcher(Block... matchers)
	{
		for (Block matcher : matchers) {
			addMatcher(new ItemStack(matcher));
		}
		return this;
	}

	public OreMatcher addMatcher(ItemStack... matchers)
	{
		for (ItemStack matcher : matchers) {
			addMatcher(matcher, false);
		}
		return this;
	}

	public OreMatcher addMatcher(ItemStack matcher, boolean strict)
	{
		return addMatcher(new PredicateItemMatches(matcher, strict));
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
