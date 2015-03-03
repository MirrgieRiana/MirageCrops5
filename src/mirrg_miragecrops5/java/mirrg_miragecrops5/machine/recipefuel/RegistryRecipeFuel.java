package mirrg_miragecrops5.machine.recipefuel;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import mirrg.h.struct.Tuple;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import net.minecraft.item.ItemStack;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IHandlerRecipeFuel;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IMatcherRecipeFuel;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IRecipeFuel;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IRegistryRecipeFuel;

public class RegistryRecipeFuel implements IRegistryRecipeFuel
{

	private ArrayList<IHandlerRecipeFuel> handlers = new ArrayList<>();

	private HandlerRecipeFuelRecipes recipes = new HandlerRecipeFuelRecipes();

	public RegistryRecipeFuel()
	{
		addHandler(recipes);
	}

	@Override
	public Stream<IHandlerRecipeFuel> getHandlers()
	{
		return handlers.stream();
	}

	@Override
	public void addHandler(IHandlerRecipeFuel handler)
	{
		handlers.add(handler);
	}

	@Override
	public Optional<IMatcherRecipeFuel> matcher(ItemStack input)
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
		recipes.add(createRecipeOreFuel(oreName, stackSize, fuelValue));
	}

	@Override
	public void addRecipe(String oreName, int fuelValue)
	{
		addRecipe(oreName, 1, fuelValue);
	}

	@Override
	public void addRecipe(ItemStack itemStack, int stackSize, int fuelValue)
	{
		recipes.add(createRecipeFuel(itemStack, stackSize, fuelValue));
	}

	@Override
	public void addRecipe(ItemStack itemStack, int fuelValue)
	{
		addRecipe(itemStack, 1, fuelValue);
	}

	public static IRecipeFuel createRecipeFuel(ItemStack input, int stackSize, int fuelValue)
	{
		return new IRecipeFuel() {

			@Override
			public Tuple<Stream<ItemStack>, Integer> getInput()
			{
				// wait for ASM5 TODO
				return new Tuple<>(
					((Function<ItemStack, Stream<ItemStack>>) Stream::of).apply(input),
					stackSize);
			}

			@Override
			public int getFuelValue()
			{
				return fuelValue;
			}

		};
	}

	public static IRecipeFuel createRecipeOreFuel(String oreName, int stackSize, int fuelValue)
	{
		return new IRecipeFuel() {

			@Override
			public Tuple<Stream<ItemStack>, Integer> getInput()
			{
				return new Tuple<>(
					HelpersOreDictionary.getOresStream(oreName)
					, stackSize);
			}

			@Override
			public int getFuelValue()
			{
				return fuelValue;
			}

		};
	}

}
