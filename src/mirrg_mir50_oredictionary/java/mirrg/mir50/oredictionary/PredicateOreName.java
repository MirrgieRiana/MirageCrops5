package mirrg.mir50.oredictionary;

import java.util.function.Predicate;

import net.minecraft.item.ItemStack;

public class PredicateOreName implements Predicate<ItemStack>
{

	public String oreName;

	public PredicateOreName(String oreName)
	{
		this.oreName = oreName;
	}

	@Override
	public boolean test(ItemStack itemStack)
	{
		return HelpersOreDictionary.isOre(itemStack, oreName);
	}

}
