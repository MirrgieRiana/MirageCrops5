package api.mirrg_miragecrops5.recipes;

public class APIRegistryRecipe
{

	public static RecipeFuel.IRegistryRecipeFuel registryRecipeFairyFuel;

	public static void registryRecipeFairyFuel(RecipeFuel.IRegistryRecipeFuel registryRecipeFairyFuel)
	{
		APIRegistryRecipe.registryRecipeFairyFuel = registryRecipeFairyFuel;
	}

	public static RecipeFuel.IRegistryRecipeFuel registryRecipeFoodValue;

	public static void registryRecipeFoodValue(RecipeFuel.IRegistryRecipeFuel registryRecipeFoodValue)
	{
		APIRegistryRecipe.registryRecipeFoodValue = registryRecipeFoodValue;
	}

}
