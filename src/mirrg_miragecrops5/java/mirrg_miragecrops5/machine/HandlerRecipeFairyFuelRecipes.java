package mirrg_miragecrops5.machine;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import api.mirrg_miragecrops5.recipes.APIRegistryRecipeFairyFuel.IHandlerRecipeFuel;
import api.mirrg_miragecrops5.recipes.APIRegistryRecipeFairyFuel.IMatcherFuel;
import api.mirrg_miragecrops5.recipes.APIRegistryRecipeFairyFuel.IRecipeFuel;

public class HandlerRecipeFairyFuelRecipes extends ArrayList<IRecipeFuel> implements IHandlerRecipeFuel
{

	@Override
	public Optional<IMatcherFuel> matcher(ItemStack input)
	{
		return stream()
			.filter(recipe -> recipe.getInputs().anyMatch(ore -> OreDictionary.itemMatches(ore, input, false)))
			.findFirst()
			.<IMatcherFuel> map(recipe -> new IMatcherFuel() {
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
	public Stream<IRecipeFuel> getRecipesToShow()
	{
		return stream();
	}

}
