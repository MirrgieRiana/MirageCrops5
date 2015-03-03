package mirrg_miragecrops5.machine.recipewritingdesk;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import mirrg.h.struct.Tuple;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import mirrg_miragecrops5.fairytype.FairyType;
import net.minecraft.item.ItemStack;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IHandlerRecipeWritingDesk;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IMatcherRecipeWritingDesk;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IRecipeWritingDesk;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IRegistryRecipeWritingDesk;

public class RegistryRecipeWritingDesk implements IRegistryRecipeWritingDesk
{

	private ArrayList<IHandlerRecipeWritingDesk> handlers = new ArrayList<>();

	private HandlerRecipeWritingDesk recipes = new HandlerRecipeWritingDesk();

	public RegistryRecipeWritingDesk()
	{
		addHandler(recipes);
	}

	@Override
	public Stream<IHandlerRecipeWritingDesk> getHandlers()
	{
		return handlers.stream();
	}

	@Override
	public void addHandler(IHandlerRecipeWritingDesk handler)
	{
		handlers.add(handler);
	}

	@Override
	public Optional<IMatcherRecipeWritingDesk> matcher(Tuple<ItemStack, FairyType> input)
	{
		return getHandlers()
			.map(handler -> handler.matcher(input))
			.filter(Optional::isPresent)
			.map(Optional::get)
			.findFirst();
	}

	@Override
	public Stream<IRecipeWritingDesk> getRecipesToShow()
	{
		return getHandlers().flatMap(handler -> handler.getRecipesToShow());
	}

	@Override
	public void addRecipe(IRecipeWritingDesk recipe)
	{
		recipes.add(recipe);
	}

	@Override
	public void addRecipe(ItemStack inputBook, FairyType fairyType, ItemStack output)
	{
		addRecipe(createRecipeWritingDesk(inputBook, fairyType, output));
	}

	@Override
	public void addRecipe(String oreNameInput, FairyType fairyType, ItemStack output)
	{
		addRecipe(createRecipeOreFuel(oreNameInput, fairyType, output));
	}

	public static IRecipeWritingDesk createRecipeWritingDesk(ItemStack inputBook, FairyType fairyType, ItemStack output)
	{
		return new IRecipeWritingDesk() {

			@Override
			public Tuple<Stream<ItemStack>, Integer> getInputBook()
			{
				// wait for ASM5 TODO
				return new Tuple<>(((Function<ItemStack, Stream<ItemStack>>) Stream::of).apply(inputBook), 1);
			}

			@Override
			public Stream<FairyType> getInputFairy()
			{
				// wait for ASM5 TODO
				return ((Function<FairyType, Stream<FairyType>>) Stream::of).apply(fairyType);
			}

			@Override
			public ItemStack getOutput()
			{
				return output;
			}

		};
	}

	public static IRecipeWritingDesk createRecipeOreFuel(String oreNameInput, FairyType fairyType, ItemStack output)
	{
		return new IRecipeWritingDesk() {

			@Override
			public Tuple<Stream<ItemStack>, Integer> getInputBook()
			{
				return new Tuple<>(HelpersOreDictionary.getOresStream(oreNameInput), 1);
			}

			@Override
			public Stream<FairyType> getInputFairy()
			{
				// wait for ASM5 TODO
				return ((Function<FairyType, Stream<FairyType>>) Stream::of).apply(fairyType);
			}

			@Override
			public ItemStack getOutput()
			{
				return output;
			}

		};
	}

}
