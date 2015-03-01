package mirrg_miragecrops5.machine;

import java.util.stream.Stream;

import mirrg.h.struct.Tuple;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import net.minecraft.item.ItemStack;
import api.mirrg_miragecrops5.recipes.RecipeFuel;

public class RecipeOreFairyFuel implements RecipeFuel.IRecipeFuel
{

	public String oreName;
	public int stackSize;
	public int fuelValue;

	public RecipeOreFairyFuel(String oreName, int stackSize, int fuelValue)
	{
		this.oreName = oreName;
		this.stackSize = stackSize;
		this.fuelValue = fuelValue;
	}

	@Override
	public Tuple<Stream<ItemStack>, Integer> getInputs()
	{
		return new Tuple<>(
			HelpersOreDictionary.getOresStream(oreName)
			, stackSize);
	}

	@Override
	public int getFuelValue()
	{
		return fuelValue;
	}

}
