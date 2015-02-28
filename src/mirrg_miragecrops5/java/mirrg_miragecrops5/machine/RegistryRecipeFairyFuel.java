package mirrg_miragecrops5.machine;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import api.mirrg_miragecrops5.recipes.RecipeFuel;

public class RegistryRecipeFairyFuel implements RecipeFuel.IRegistryRecipeFuel
{

	private ArrayList<RecipeFuel.IHandlerRecipeFuel> handlers = new ArrayList<>();

	private HandlerRecipeFairyFuelRecipes recipes = new HandlerRecipeFairyFuelRecipes();

	public RegistryRecipeFairyFuel()
	{
		addHandler(recipes);
	}

	@Override
	public Stream<RecipeFuel.IHandlerRecipeFuel> getHandlers()
	{
		return handlers.stream();
	}

	@Override
	public void addHandler(RecipeFuel.IHandlerRecipeFuel handler)
	{
		handlers.add(handler);
	}

	@Override
	public Optional<RecipeFuel.IMatcherFuel> matcher(ItemStack input)
	{
		return getHandlers()
			.map(handler -> handler.matcher(input))
			.filter(Optional::isPresent)
			.map(Optional::get)
			.findFirst();
	}

	@Override
	public Stream<RecipeFuel.IRecipeFuel> getRecipesToShow()
	{
		return getHandlers().flatMap(handler -> handler.getRecipesToShow());
	}

	@Override
	public void addRecipe(RecipeFuel.IRecipeFuel recipe)
	{
		recipes.add(recipe);
	}

	@Override
	public void addRecipe(String oreName, int fuelValue)
	{
		recipes.add(new RecipeOreFairyFuel(oreName, fuelValue));
	}

	@Override
	public void addRecipe(ItemStack itemStack, int fuelValue)
	{
		recipes.add(new RecipeFairyFuel(itemStack, fuelValue));
	}

}
