package mirrg_miragecrops5.machine;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import api.mirrg_miragecrops5.recipes.RecipeFuel;

public class HandlerRecipeFairyFuelRecipes extends ArrayList<RecipeFuel.IRecipeFuel> implements RecipeFuel.IHandlerRecipeFuel
{

	@Override
	public Optional<RecipeFuel.IMatcherFuel> matcher(ItemStack input)
	{
		return stream()
			.filter(recipe -> recipe.getInputs().anyMatch(ore -> OreDictionary.itemMatches(ore, input, false)))
			.findFirst()
			.<RecipeFuel.IMatcherFuel> map(recipe -> new RecipeFuel.IMatcherFuel() {
				@Override
				public ItemStack consume()
				{
					input.stackSize--;
					return input;
				}

				@Override
				public Integer getOutput()
				{
					return recipe.getFuelValue();
				}
			});
	}

	@Override
	public Stream<RecipeFuel.IRecipeFuel> getRecipesToShow()
	{
		return stream();
	}

}
