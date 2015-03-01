package mirrg_miragecrops5.machine;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel;

public class RegistryRecipeFuel implements InterfacesRecipeFuel.IRegistryRecipeFuel
{

	private ArrayList<InterfacesRecipeFuel.IHandlerRecipeFuel> handlers = new ArrayList<>();

	private HandlerRecipeFuelRecipes recipes = new HandlerRecipeFuelRecipes();

	public RegistryRecipeFuel()
	{
		addHandler(recipes);
	}

	@Override
	public Stream<InterfacesRecipeFuel.IHandlerRecipeFuel> getHandlers()
	{
		return handlers.stream();
	}

	@Override
	public void addHandler(InterfacesRecipeFuel.IHandlerRecipeFuel handler)
	{
		handlers.add(handler);
	}

	@Override
	public Optional<InterfacesRecipeFuel.IMatcherFuel> matcher(ItemStack input)
	{
		return getHandlers()
			.map(handler -> handler.matcher(input))
			.filter(Optional::isPresent)
			.map(Optional::get)
			.findFirst();
	}

	@Override
	public Stream<InterfacesRecipeFuel.IRecipeFuel> getRecipesToShow()
	{
		return getHandlers().flatMap(handler -> handler.getRecipesToShow());
	}

	@Override
	public void addRecipe(InterfacesRecipeFuel.IRecipeFuel recipe)
	{
		recipes.add(recipe);
	}

	@Override
	public void addRecipe(String oreName, int stackSize, int fuelValue)
	{
		recipes.add(new RecipeOreFuel(oreName, stackSize, fuelValue));
	}

	@Override
	public void addRecipe(String oreName, int fuelValue)
	{
		addRecipe(oreName, 1, fuelValue);
	}

	@Override
	public void addRecipe(ItemStack itemStack, int stackSize, int fuelValue)
	{
		recipes.add(new RecipeFuel(itemStack, stackSize, fuelValue));
	}

	@Override
	public void addRecipe(ItemStack itemStack, int fuelValue)
	{
		addRecipe(itemStack, 1, fuelValue);
	}

}
