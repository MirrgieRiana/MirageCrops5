package mirrg_miragecrops5.machine;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import api.mirrg_miragecrops5.APIRegistryRecipeFairyFuel.IHandlerRecipeFairyFuel;
import api.mirrg_miragecrops5.APIRegistryRecipeFairyFuel.IMatcherFairyFuel;
import api.mirrg_miragecrops5.APIRegistryRecipeFairyFuel.IRecipeFairyFuel;
import api.mirrg_miragecrops5.APIRegistryRecipeFairyFuel.IRegistryRecipeFairyFuel;

public class RegistryRecipeFairyFuel implements IRegistryRecipeFairyFuel
{

	private ArrayList<IHandlerRecipeFairyFuel> handlers = new ArrayList<>();

	private HandlerRecipeFairyFuelRecipes recipes = new HandlerRecipeFairyFuelRecipes();

	public RegistryRecipeFairyFuel()
	{
		addHandler(recipes);
	}

	@Override
	public Stream<IHandlerRecipeFairyFuel> getHandlers()
	{
		return handlers.stream();
	}

	@Override
	public void addHandler(IHandlerRecipeFairyFuel handler)
	{
		handlers.add(handler);
	}

	@Override
	public Optional<IMatcherFairyFuel> matcher(ItemStack input)
	{
		return getHandlers()
			.map(handler -> handler.matcher(input))
			.filter(Optional::isPresent)
			.map(Optional::get)
			.findFirst();
	}

	@Override
	public Stream<IRecipeFairyFuel> getRecipesToShow()
	{
		return getHandlers().flatMap(handler -> handler.getRecipesToShow());
	}

	@Override
	public void addRecipe(IRecipeFairyFuel recipe)
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
