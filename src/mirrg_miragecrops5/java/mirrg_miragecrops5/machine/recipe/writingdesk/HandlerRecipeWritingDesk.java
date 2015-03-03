package mirrg_miragecrops5.machine.recipe.writingdesk;

import mirrg.h.struct.Tuple;
import mirrg_miragecrops5.fairytype.FairyType;
import mirrg_miragecrops5.machine.recipe.HandlerRecipeAbstract;
import net.minecraft.item.ItemStack;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IHandlerRecipeWritingDesk;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IMatcherRecipeWritingDesk;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IRecipeWritingDesk;

public class HandlerRecipeWritingDesk
	extends HandlerRecipeAbstract<IRecipeWritingDesk, IMatcherRecipeWritingDesk, Tuple<ItemStack, FairyType>, ItemStack>
	implements IHandlerRecipeWritingDesk
{

	@Override
	protected boolean filter(IRecipeWritingDesk recipe, Tuple<ItemStack, FairyType> input)
	{
		return matches(recipe.getInputBook(), input.getX())
			&& recipe.getInputFairy().anyMatch(fairyType -> fairyType.typeName.equals(input.getY().typeName));
	}

	@Override
	protected IMatcherRecipeWritingDesk createMatcher(IRecipeWritingDesk recipe, Tuple<ItemStack, FairyType> input)
	{
		return new IMatcherRecipeWritingDesk() {
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
		};
	}

}
