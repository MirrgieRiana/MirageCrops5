package api.mirrg_miragecrops5.recipes;

import java.util.stream.Stream;

import mirrg.h.struct.Tuple;
import mirrg_miragecrops5.fairytype.FairyType;
import net.minecraft.item.ItemStack;
import api.mirrg.mir50.registry.recipe.IHandlerRecipeMir50;
import api.mirrg.mir50.registry.recipe.IMatcherMir50;
import api.mirrg.mir50.registry.recipe.IRegistryRecipeMir50;

public class InterfacesRecipeWritingDesk
{

	public static interface IRegistryRecipeWritingDesk
		extends IRegistryRecipeMir50<IHandlerRecipeWritingDesk, IRecipeWritingDesk, IMatcherRecipeWritingDesk, Tuple<ItemStack, FairyType>, ItemStack>
	{

		public void addRecipe(IRecipeWritingDesk recipe);

		public void addRecipe(ItemStack inputBook, FairyType fairyType, ItemStack output);

		public void addRecipe(String oreNameInput, FairyType fairyType, ItemStack output);

	}

	public static interface IHandlerRecipeWritingDesk
		extends IHandlerRecipeMir50<IRecipeWritingDesk, IMatcherRecipeWritingDesk, Tuple<ItemStack, FairyType>, ItemStack>
	{

	}

	public static interface IRecipeWritingDesk
	{

		public Tuple<Stream<ItemStack>, Integer> getInputBook();

		public Stream<FairyType> getInputFairy();

		public ItemStack getOutput();

	}

	public static interface IMatcherRecipeWritingDesk extends IMatcherMir50<Tuple<ItemStack, FairyType>, ItemStack>
	{

	}

}
