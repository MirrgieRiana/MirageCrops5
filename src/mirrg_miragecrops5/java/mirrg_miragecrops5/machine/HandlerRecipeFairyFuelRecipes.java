package mirrg_miragecrops5.machine;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import api.mirrg_miragecrops5.recipes.APIRegistryRecipeFairyFuel.IHandlerRecipeFairyFuel;
import api.mirrg_miragecrops5.recipes.APIRegistryRecipeFairyFuel.IMatcherFairyFuel;
import api.mirrg_miragecrops5.recipes.APIRegistryRecipeFairyFuel.IRecipeFairyFuel;

public class HandlerRecipeFairyFuelRecipes extends ArrayList<IRecipeFairyFuel> implements IHandlerRecipeFairyFuel
{

	@Override
	public Optional<IMatcherFairyFuel> matcher(ItemStack input)
	{
		return stream()
			.filter(recipe -> recipe.getInputs().anyMatch(ore -> OreDictionary.itemMatches(ore, input, false)))
			.findFirst()
			.<IMatcherFairyFuel> map(recipe -> new IMatcherFairyFuel() {
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
	public Stream<IRecipeFairyFuel> getRecipesToShow()
	{
		return stream();
	}

}
