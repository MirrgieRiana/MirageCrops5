package api.mirrg_miragecrops5.recipes;

public class APIRegistryRecipe
{

	public static InterfacesRecipeFuel.IRegistryRecipeFuel registryRecipeFairyFuel;

	public static void registryRecipeFairyFuel(InterfacesRecipeFuel.IRegistryRecipeFuel registryRecipeFairyFuel)
	{
		APIRegistryRecipe.registryRecipeFairyFuel = registryRecipeFairyFuel;
	}

	public static InterfacesRecipeFuel.IRegistryRecipeFuel registryRecipeFoodValue;

	public static void registryRecipeFoodValue(InterfacesRecipeFuel.IRegistryRecipeFuel registryRecipeFoodValue)
	{
		APIRegistryRecipe.registryRecipeFoodValue = registryRecipeFoodValue;
	}

}
