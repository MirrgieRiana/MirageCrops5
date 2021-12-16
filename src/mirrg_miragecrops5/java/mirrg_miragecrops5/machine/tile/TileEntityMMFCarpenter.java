package mirrg_miragecrops5.machine.tile;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Supplier;

import mirrg.h.struct.Tuple;
import mirrg.h.struct.Tuple4;
import net.minecraft.item.ItemStack;

public class TileEntityMMFCarpenter extends TileEntityMMFEasyLog
{

	@Override
	public String getDefaultName()
	{
		return "container.miragecrops5.mmfCarpenter";
	}

	@Override
	protected int[] getFairyValues()
	{
		return new int[] {
			0, -12, 0, 0, 0, 0,
		};
	}

	@Override
	protected ArrayList<Tuple4<Tuple<Predicate<ItemStack>, Integer>, Tuple<Predicate<ItemStack>, Integer>, Supplier<ItemStack>, Integer>> getRecipes()
	{
		ArrayList<Tuple4<Tuple<Predicate<ItemStack>, Integer>, Tuple<Predicate<ItemStack>, Integer>, Supplier<ItemStack>, Integer>> recipes = new ArrayList<>();

		return recipes;
	}

}
