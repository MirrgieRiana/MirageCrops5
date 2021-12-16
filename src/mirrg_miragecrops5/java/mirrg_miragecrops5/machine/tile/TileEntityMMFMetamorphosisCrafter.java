package mirrg_miragecrops5.machine.tile;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Supplier;

import mirrg.h.struct.Tuple;
import mirrg.h.struct.Tuple4;
import net.minecraft.item.ItemStack;

public class TileEntityMMFMetamorphosisCrafter extends TileEntityMMFEasyLog
{

	@Override
	public String getDefaultName()
	{
		return "container.miragecrops5.mmfMetamorphosisCrafter";
	}

	@Override
	protected int[] getFairyValues()
	{
		return new int[] {
			0, 0, -5, 0, -9, 0,
		};
	}

	@Override
	protected ArrayList<Tuple4<Tuple<Predicate<ItemStack>, Integer>, Tuple<Predicate<ItemStack>, Integer>, Supplier<ItemStack>, Integer>> getRecipes()
	{
		ArrayList<Tuple4<Tuple<Predicate<ItemStack>, Integer>, Tuple<Predicate<ItemStack>, Integer>, Supplier<ItemStack>, Integer>> recipes = new ArrayList<>();

		return recipes;
	}

}
