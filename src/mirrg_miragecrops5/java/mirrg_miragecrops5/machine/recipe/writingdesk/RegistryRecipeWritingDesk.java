package mirrg_miragecrops5.machine.recipe.writingdesk;

import java.util.function.Function;
import java.util.stream.Stream;

import mirrg.h.struct.Tuple;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import mirrg_miragecrops5.fairytype.FairyType;
import mirrg_miragecrops5.machine.recipe.RegistryRecipeAbstract;
import net.minecraft.item.ItemStack;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IHandlerRecipeWritingDesk;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IMatcherRecipeWritingDesk;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IRecipeWritingDesk;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IRegistryRecipeWritingDesk;

public class RegistryRecipeWritingDesk
	extends RegistryRecipeAbstract<IHandlerRecipeWritingDesk, IRecipeWritingDesk, IMatcherRecipeWritingDesk, Tuple<ItemStack, FairyType>, ItemStack>
	implements IRegistryRecipeWritingDesk
{

	private HandlerRecipeWritingDesk recipes = add(new HandlerRecipeWritingDesk());

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
