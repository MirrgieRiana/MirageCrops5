package mirrg_miragecrops5.machine.recipe.writingdesk;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import mirrg.h.struct.Tuple;
import mirrg_miragecrops5.fairytype.FairyType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IHandlerRecipeWritingDesk;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IMatcherRecipeWritingDesk;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IRecipeWritingDesk;

public class HandlerRecipeWritingDesk extends ArrayList<IRecipeWritingDesk> implements IHandlerRecipeWritingDesk
{

	@Override
	public Optional<IMatcherRecipeWritingDesk> matcher(Tuple<ItemStack, FairyType> input)
	{
		return stream()
			.filter(recipe -> recipe.getInputBook().getX().anyMatch(ore -> OreDictionary.itemMatches(ore, input.getX(), false)))
			.filter(recipe -> recipe.getInputBook().getY() <= input.getX().stackSize)
			.filter(recipe -> recipe.getInputFairy().anyMatch(fairyType -> fairyType.typeName.equals(input.getY().typeName)))
			.findFirst()
			.<IMatcherRecipeWritingDesk> map(recipe -> new IMatcherRecipeWritingDesk() {
				@Override
				public Tuple<ItemStack, FairyType> consume()
				{
					input.getX().stackSize -= recipe.getInputBook().getY();
					ItemStack cost = input.getX().copy();
					cost.stackSize = recipe.getInputBook().getY();

					return new Tuple<>(cost, input.getY());
				}

				@Override
				public ItemStack getOutput()
				{
					return recipe.getOutput();
				}
			});
	}

	@Override
	public Stream<IRecipeWritingDesk> getRecipesToShow()
	{
		return stream();
	}

}
