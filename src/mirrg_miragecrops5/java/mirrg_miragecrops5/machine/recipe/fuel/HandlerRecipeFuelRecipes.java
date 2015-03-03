package mirrg_miragecrops5.machine.recipe.fuel;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IHandlerRecipeFuel;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IMatcherRecipeFuel;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IRecipeFuel;

public class HandlerRecipeFuelRecipes extends ArrayList<IRecipeFuel> implements IHandlerRecipeFuel
{

	@Override
	public Optional<IMatcherRecipeFuel> matcher(ItemStack input)
	{
		return stream()
			.filter(recipe -> recipe.getInput().getX().anyMatch(ore -> OreDictionary.itemMatches(ore, input, false)))
			.filter(recipe -> recipe.getInput().getY() <= input.stackSize)
			.findFirst()
			.<IMatcherRecipeFuel> map(recipe -> new IMatcherRecipeFuel() {
				@Override
				public ItemStack consume()
				{
					input.stackSize -= recipe.getInput().getY();
					ItemStack cost = input.copy();
					cost.stackSize = recipe.getInput().getY();
					return cost;
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
