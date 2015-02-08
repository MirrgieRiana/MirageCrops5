package mirrg_miragecrops5.recipefairy;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeFairy implements IRecipe
{

	protected IRecipeFairyInput[] inputs;
	protected IRecipeFairyMatching result;

	public RecipeFairy(IRecipeFairyMatching result, IRecipeFairyInput... inputs)
	{
		this.result = result;
		this.inputs = inputs;
	}

	@Override
	public int getRecipeSize()
	{
		return inputs.length;
	}

	@Override
	public boolean matches(InventoryCrafting inventoryCrafting, World world)
	{

		IRecipeFairyInput[] inputs = this.inputs.clone();
		Integer[] slotIndexes = new Integer[inputs.length];
		int matchCount = 0;

		for (int indexSlot = 0; indexSlot < inventoryCrafting.getSizeInventory(); indexSlot++) {
			ItemStack watching = inventoryCrafting.getStackInSlot(indexSlot);

			for (int indexInput = 0; indexInput < inputs.length; indexInput++) {
				if (slotIndexes[indexInput] == null) {
					if (inputs[indexInput].matches(watching)) {
						matchCount++;
						slotIndexes[indexInput] = indexSlot;
					}
				}
			}

		}

		return matchCount == inputs.length;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting)
	{
		IRecipeFairyInput[] inputs = this.inputs.clone();
		Integer[] slotIndexes = new Integer[inputs.length];
		int matchCount = 0;

		for (int indexSlot = 0; indexSlot < inventoryCrafting.getSizeInventory(); indexSlot++) {
			ItemStack watching = inventoryCrafting.getStackInSlot(indexSlot);

			for (int indexInput = 0; indexInput < inputs.length; indexInput++) {
				if (slotIndexes[indexInput] == null) {
					if (inputs[indexInput].matches(watching)) {
						matchCount++;
						slotIndexes[indexInput] = indexSlot;
					}
				}
			}

		}

		if (matchCount == inputs.length) {
			return result.create(inventoryCrafting, slotIndexes);
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return null;
	}

}
