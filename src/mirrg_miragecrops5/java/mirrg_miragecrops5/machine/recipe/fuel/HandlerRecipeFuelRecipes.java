package mirrg_miragecrops5.machine.recipe.fuel;

import mirrg_miragecrops5.machine.recipe.HandlerRecipeAbstract;
import net.minecraft.item.ItemStack;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IHandlerRecipeFuel;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IMatcherRecipeFuel;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IRecipeFuel;

public class HandlerRecipeFuelRecipes
	extends HandlerRecipeAbstract<IRecipeFuel, IMatcherRecipeFuel, ItemStack, Integer>
	implements IHandlerRecipeFuel
{

	@Override
	protected boolean filter(IRecipeFuel recipe, ItemStack input)
	{
		return matches(recipe.getInput(), input);
	}

	@Override
	protected IMatcherRecipeFuel createMatcher(IRecipeFuel recipe, ItemStack input)
	{
		return new IMatcherRecipeFuel() {
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
		};
	}

}
