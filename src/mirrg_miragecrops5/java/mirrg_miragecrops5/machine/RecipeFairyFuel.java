package mirrg_miragecrops5.machine;

import java.util.function.Function;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import api.mirrg_miragecrops5.recipes.RecipeFuel;

public class RecipeFairyFuel implements RecipeFuel.IRecipeFuel
{

	public ItemStack input;
	public int fuelValue;

	public RecipeFairyFuel(ItemStack input, int fuelValue)
	{
		this.input = input;
		this.fuelValue = fuelValue;
	}

	@Override
	public Stream<ItemStack> getInputs()
	{
		return ((Function<ItemStack, Stream<ItemStack>>) Stream::of).apply(input); // wait for ASM5 TODO
	}

	@Override
	public int getFuelValue()
	{
		return fuelValue;
	}

}
