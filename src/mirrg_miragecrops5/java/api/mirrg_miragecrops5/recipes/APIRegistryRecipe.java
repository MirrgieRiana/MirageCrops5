package api.mirrg_miragecrops5.recipes;

import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IRegistryRecipeFuel;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IRegistryRecipeWritingDesk;

public class APIRegistryRecipe
{

	public static IRegistryRecipeFuel registryRecipeFairyFuel;

	public static void registryRecipeFairyFuel(IRegistryRecipeFuel registryRecipeFairyFuel)
	{
		APIRegistryRecipe.registryRecipeFairyFuel = registryRecipeFairyFuel;
	}

	public static IRegistryRecipeFuel registryRecipeFoodValue;

	public static void registryRecipeFoodValue(IRegistryRecipeFuel registryRecipeFoodValue)
	{
		APIRegistryRecipe.registryRecipeFoodValue = registryRecipeFoodValue;
	}

}
