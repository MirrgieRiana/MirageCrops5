package mirrg_miragecrops5.machine.recipe;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import mirrg.h.struct.Tuple;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import api.mirrg.mir50.registry.recipe.IHandlerRecipeMir50;
import api.mirrg.mir50.registry.recipe.IMatcherMir50;

public abstract class HandlerRecipeAbstract<R, M extends IMatcherMir50<I, O>, I, O> extends ArrayList<R>
	implements IHandlerRecipeMir50<R, M, I, O>
{

	@Override
	public Optional<M> matcher(I input)
	{
		return getRecipesToShow()
			.filter(recipe -> filter(recipe, input))
			.findFirst()
			.map(recipe -> createMatcher(recipe, input));
	}

	@Override
	public Stream<R> getRecipesToShow()
	{
		return stream();
	}

	protected abstract boolean filter(R recipe, I input);

	protected abstract M createMatcher(R recipe, I input);

	protected boolean matches(Tuple<Stream<ItemStack>, Integer> ores, ItemStack input)
	{
		if (ores.getX().anyMatch(ore -> OreDictionary.itemMatches(ore, input, false))) {
			if (ores.getY() <= input.stackSize) {
				return true;
			}
		}
		return false;
	}

}
