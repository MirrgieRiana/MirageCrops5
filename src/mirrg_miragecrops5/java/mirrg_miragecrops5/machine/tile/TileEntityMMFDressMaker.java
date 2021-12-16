package mirrg_miragecrops5.machine.tile;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Supplier;

import mirrg.h.struct.Tuple;
import mirrg.h.struct.Tuple4;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import mirrg_miragecrops5.machine.ModuleMachine;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityMMFDressMaker extends TileEntityMMFEasyLog
{

	@Override
	public String getDefaultName()
	{
		return "container.miragecrops5.mmfDressMaker";
	}

	@Override
	protected int[] getFairyValues()
	{
		return new int[] {
			0, -2, -8, 0, 0, 0,
		};
	}

	@Override
	protected ArrayList<Tuple4<Tuple<Predicate<ItemStack>, Integer>, Tuple<Predicate<ItemStack>, Integer>, Supplier<ItemStack>, Integer>> getRecipes()
	{
		ArrayList<Tuple4<Tuple<Predicate<ItemStack>, Integer>, Tuple<Predicate<ItemStack>, Integer>, Supplier<ItemStack>, Integer>> recipes = new ArrayList<>();

		recipes.add(new Tuple4<Tuple<Predicate<ItemStack>, Integer>, Tuple<Predicate<ItemStack>, Integer>, Supplier<ItemStack>, Integer>(
			new Tuple<>(
				itemStack -> OreDictionary.itemMatches(new ItemStack(Blocks.vine), itemStack, false),
				2),
			new Tuple<>(
				itemStack -> HelpersOreDictionary.isOre(itemStack, "treeLeaves"),
				8),
			() -> HelpersOreDictionary.copyOrThrow("craftingClothFairy"), 200));

		recipes.add(new Tuple4<Tuple<Predicate<ItemStack>, Integer>, Tuple<Predicate<ItemStack>, Integer>, Supplier<ItemStack>, Integer>(
			new Tuple<>(
				itemStack -> OreDictionary.itemMatches(new ItemStack(Blocks.vine), itemStack, false),
				5),
			new Tuple<>(
				itemStack -> OreDictionary.itemMatches(new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE), itemStack, false),
				4),
			() -> new ItemStack(ModuleMachine.loaderItem_helmetFairy.get()), 500));

		recipes.add(new Tuple4<Tuple<Predicate<ItemStack>, Integer>, Tuple<Predicate<ItemStack>, Integer>, Supplier<ItemStack>, Integer>(
			new Tuple<>(
				itemStack -> HelpersOreDictionary.isOre(itemStack, "craftingClothFairy"),
				16),
			new Tuple<>(
				itemStack -> OreDictionary.itemMatches(new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE), itemStack, false),
				12),
			() -> new ItemStack(ModuleMachine.loaderItem_chestplateFairy.get()), 2000));

		recipes.add(new Tuple4<Tuple<Predicate<ItemStack>, Integer>, Tuple<Predicate<ItemStack>, Integer>, Supplier<ItemStack>, Integer>(
			new Tuple<>(
				itemStack -> HelpersOreDictionary.isOre(itemStack, "craftingClothFairy"),
				14),
			new Tuple<>(
				itemStack -> OreDictionary.itemMatches(new ItemStack(Items.leather, 1, OreDictionary.WILDCARD_VALUE), itemStack, false),
				3),
			() -> new ItemStack(ModuleMachine.loaderItem_leggingsFairy.get()), 1500));

		recipes.add(new Tuple4<Tuple<Predicate<ItemStack>, Integer>, Tuple<Predicate<ItemStack>, Integer>, Supplier<ItemStack>, Integer>(
			new Tuple<>(
				itemStack -> HelpersOreDictionary.isOre(itemStack, "craftingClothFairy"),
				8),
			new Tuple<>(
				itemStack -> OreDictionary.itemMatches(new ItemStack(Items.feather, 1, OreDictionary.WILDCARD_VALUE), itemStack, false),
				2),
			() -> new ItemStack(ModuleMachine.loaderItem_bootsFairy.get()), 1800));

		return recipes;
	}

}
