package mirrg_miragecrops5;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.IntSupplier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HelpersItemUse
{

	public static ItemStack craft(ItemStack holding, World world, EntityPlayer player,
		Runnable onCrafted, IntSupplier supplierCost, Consumer<ArrayList<ItemStack>> createChangedItem,
		boolean useHoldingItemSlot)
	{
		int cost = supplierCost.getAsInt();

		if (holding.stackSize < cost) return holding;

		ArrayList<ItemStack> outputs = new ArrayList<>();
		createChangedItem.accept(outputs);
		if (outputs.size() == 0) return holding;

		holding.stackSize -= cost;

		onCrafted.run();

		if (useHoldingItemSlot) {
			return spawnOutputs(holding, player, outputs);
		} else {
			spawnOutputs(player, outputs);
			return holding;
		}
	}

	public static ItemStack spawnOutputs(ItemStack holding, EntityPlayer player, ArrayList<ItemStack> outputs)
	{
		if (outputs == null) return holding;
		if (outputs.size() == 0) return holding;

		{
			ItemStack responce = holding;

			if (holding.stackSize <= 0) {
				responce = outputs.remove(0);
			}

			spawnOutputs(player, outputs);

			return responce;
		}
	}

	public static void spawnOutputs(EntityPlayer player, ArrayList<ItemStack> outputs)
	{
		if (outputs == null) return;

		for (ItemStack output : outputs) {
			if (!player.inventory.addItemStackToInventory(output)) {
				player.dropPlayerItemWithRandomChoice(output, false);
			}
		}
	}

}
