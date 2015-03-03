package api.mirrg_miragecrops5.recipes;

import java.util.stream.Stream;

import mirrg.h.struct.Tuple;
import net.minecraft.item.ItemStack;
import api.mirrg.mir50.registry.recipe.IHandlerRecipeMir50;
import api.mirrg.mir50.registry.recipe.IMatcherMir50;
import api.mirrg.mir50.registry.recipe.IRegistryRecipeMir50;

public class InterfacesRecipeFuel
{

	public static interface IRegistryRecipeFuel
		extends IRegistryRecipeMir50<InterfacesRecipeFuel.IHandlerRecipeFuel, InterfacesRecipeFuel.IRecipeFuel, InterfacesRecipeFuel.IMatcherFuel, ItemStack, Integer>
	{

		public void addRecipe(InterfacesRecipeFuel.IRecipeFuel recipe);

		public void addRecipe(String oreName, int stackSize, int fuelValue);

		public void addRecipe(String oreName, int fuelValue);

		public void addRecipe(ItemStack itemStack, int stackSize, int fuelValue);

		public void addRecipe(ItemStack itemStack, int fuelValue);

	}

	public static interface IHandlerRecipeFuel
		extends IHandlerRecipeMir50<InterfacesRecipeFuel.IRecipeFuel, InterfacesRecipeFuel.IMatcherFuel, ItemStack, Integer>
	{

	}

	public static interface IRecipeFuel
	{

		public Tuple<Stream<ItemStack>, Integer> getInput();

		public int getFuelValue();

	}

	public static interface IMatcherFuel extends IMatcherMir50<ItemStack, Integer>
	{

	}

}
