package mirrg_miragecrops5.recipefairy;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeFairyInput implements IRecipeFairyInput
{

	protected ArrayList<ItemStack> inputOres = new ArrayList<>();

	public RecipeFairyInput(Object... objects)
	{
		for (Object object : objects) {
			accept(object);
		}
	}

	public void accept(Object object)
	{
		if (object instanceof ItemStack) {
			inputOres.add(((ItemStack) object).copy());
		} else if (object instanceof Item) {
			inputOres.add(new ItemStack((Item) object));
		} else if (object instanceof Block) {
			inputOres.add(new ItemStack((Block) object));
		} else if (object instanceof String) {
			inputOres.addAll(OreDictionary.getOres((String) object));
		} else {
			throw new RuntimeException("Invalid shapeless ore recipe: " + object);
		}
	}

	@Override
	public boolean matches(ItemStack itemStack)
	{
		if (itemStack == null) return false;

		for (ItemStack inputOre : inputOres) {
			if (OreDictionary.itemMatches(inputOre, itemStack, false)) return true;
		}

		return false;
	}

}
