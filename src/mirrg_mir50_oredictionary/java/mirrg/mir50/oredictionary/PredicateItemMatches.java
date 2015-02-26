package mirrg.mir50.oredictionary;

import java.util.function.Predicate;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class PredicateItemMatches implements Predicate<ItemStack>
{

	public ItemStack matcher;
	public boolean strict;

	public PredicateItemMatches(ItemStack matcher, boolean strict)
	{
		this.matcher = matcher;
		this.strict = strict;
	}

	@Override
	public boolean test(ItemStack itemStack)
	{
		return OreDictionary.itemMatches(matcher, itemStack, strict);
	}

}