package api.mirrg_miragecrops5.recipes;

import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import api.mirrg.mir50.registry.recipe.IHandlerRecipeMir50;
import api.mirrg.mir50.registry.recipe.IMatcherMir50;
import api.mirrg.mir50.registry.recipe.IRegistryRecipeMir50;

public class RecipeFuel
{

	public static interface IRegistryRecipeFuel
		extends IRegistryRecipeMir50<RecipeFuel.IHandlerRecipeFuel, RecipeFuel.IRecipeFuel, RecipeFuel.IMatcherFuel, ItemStack, Integer>
	{

		public void addRecipe(RecipeFuel.IRecipeFuel recipe);

		public void addRecipe(String oreName, int fuelValue);

		public void addRecipe(ItemStack itemStack, int fuelValue);

	}

	public static interface IHandlerRecipeFuel
		extends IHandlerRecipeMir50<RecipeFuel.IRecipeFuel, RecipeFuel.IMatcherFuel, ItemStack, Integer>
	{

	}

	public interface IRecipeFuel
	{

		public Stream<ItemStack> getInputs();

		public int getFuelValue();

	}

	public static interface IMatcherFuel extends IMatcherMir50<ItemStack, Integer>
	{

	}

}
