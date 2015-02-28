package api.mirrg_miragecrops5;

import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import api.mirrg.mir50.registry.recipe.IHandlerRecipeMir50;
import api.mirrg.mir50.registry.recipe.IMatcherMir50;
import api.mirrg.mir50.registry.recipe.IRegistryRecipeMir50;

public class APIRegistryRecipeFairyFuel
{

	public static IRegistryRecipeFairyFuel registry;

	public static void set(IRegistryRecipeFairyFuel registry)
	{
		APIRegistryRecipeFairyFuel.registry = registry;
	}

	public static interface IRegistryRecipeFairyFuel
		extends IRegistryRecipeMir50<IHandlerRecipeFairyFuel, IRecipeFairyFuel, IMatcherFairyFuel, ItemStack, Integer>
	{

		public void addRecipe(IRecipeFairyFuel recipe);

		public void addRecipe(String oreName, int fuelValue);

		public void addRecipe(ItemStack itemStack, int fuelValue);

	}

	public static interface IHandlerRecipeFairyFuel
		extends IHandlerRecipeMir50<IRecipeFairyFuel, IMatcherFairyFuel, ItemStack, Integer>
	{

	}

	public interface IRecipeFairyFuel
	{

		public Stream<ItemStack> getInputs();

		public int getFuelValue();

	}

	public static interface IMatcherFairyFuel extends IMatcherMir50<ItemStack, Integer>
	{

	}

}
