package mirrg_miragecrops5.machine;

import java.util.stream.Stream;

import mirrg.mir50.oredictionary.HelpersOreDictionary;
import net.minecraft.item.ItemStack;
import api.mirrg_miragecrops5.APIRegistryRecipeFairyFuel.IRecipeFairyFuel;

public class RecipeOreFairyFuel implements IRecipeFairyFuel
{

	public String oreName;
	public int fuelValue;

	public RecipeOreFairyFuel(String oreName, int fuelValue)
	{
		this.oreName = oreName;
		this.fuelValue = fuelValue;
	}

	@Override
	public Stream<ItemStack> getInputs()
	{
		return HelpersOreDictionary.getOresStream(oreName);
	}

	@Override
	public int getFuelValue()
	{
		return fuelValue;
	}

}
