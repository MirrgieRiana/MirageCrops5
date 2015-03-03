package mirrg_miragecrops5.machine.recipefuel;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel;

public class HandlerRecipeFuelRecipes extends ArrayList<InterfacesRecipeFuel.IRecipeFuel> implements InterfacesRecipeFuel.IHandlerRecipeFuel
{

	@Override
	public Optional<InterfacesRecipeFuel.IMatcherFuel> matcher(ItemStack input)
	{
		return stream()
			.filter(recipe -> recipe.getInput().getX().anyMatch(ore -> OreDictionary.itemMatches(ore, input, false)))
			.filter(recipe -> recipe.getInput().getY() <= input.stackSize)
			.findFirst()
			.<InterfacesRecipeFuel.IMatcherFuel> map(recipe -> new InterfacesRecipeFuel.IMatcherFuel() {
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
	public Stream<InterfacesRecipeFuel.IRecipeFuel> getRecipesToShow()
	{
		return stream();
	}

}
