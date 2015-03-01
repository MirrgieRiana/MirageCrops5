package mirrg_miragecrops5.machine;

import java.util.function.Function;
import java.util.stream.Stream;

import mirrg.h.struct.Tuple;
import net.minecraft.item.ItemStack;
import api.mirrg_miragecrops5.recipes.RecipeFuel;

public class RecipeFairyFuel implements RecipeFuel.IRecipeFuel
{

	public ItemStack input;
	public int stackSize;
	public int fuelValue;

	public RecipeFairyFuel(ItemStack input, int stackSize, int fuelValue)
	{
		this.input = input;
		this.stackSize = stackSize;
		this.fuelValue = fuelValue;
	}

	@Override
	public Tuple<Stream<ItemStack>, Integer> getInputs()
	{
		// wait for ASM5 TODO
		return new Tuple<>(
			((Function<ItemStack, Stream<ItemStack>>) Stream::of).apply(input),
			stackSize);
	}

	@Override
	public int getFuelValue()
	{
		return fuelValue;
	}

}
