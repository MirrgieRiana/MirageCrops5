package mirrg_miragecrops5.machine.tile;

import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.IntConsumer;

import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir51.modding.HelpersSide;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.SupplierPositionContainerFlow;
import mirrg_miragecrops5.fairytype.HelpersFairyType;
import mirrg_miragecrops5.machine.container.SlotProcessing;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityMMFMacerator extends TileEntityMMFEasy
{

	public final IInventoryMir51 inventoryInMaterial;
	public final IInventoryMir51 inventoryInMaterialProcessing;

	public TileEntityMMFMacerator()
	{
		inventoryInMaterial = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 2), "inventoryInMaterial");
		inventoryInMaterialProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInMaterialProcessing");

		inventoryChain.add(inventoryInMaterial);
		inventoryChain.add(inventoryInMaterialProcessing);
	}

	@Override
	public String getDefaultName()
	{
		return "container.miragecrops5.mmfMacerator";
	}

	@Override
	protected int[] getFairyValues()
	{
		return new int[] {
			0, -48, 0, 0, 0, 0,
		};
	}

	@Override
	protected IInventoryMir51[] getInventoryAccessible(int side)
	{
		return new IInventoryMir51[] {
			inventoryOut,
			inventoryInMaterial,
		};
	}

	@Override
	protected IInventoryMir51[] getInventoryInsert(int side, ItemStack itemStack)
	{
		return new IInventoryMir51[] {
			inventoryInMaterial,
		};
	}

	@Override
	protected void prepareContainerSlots(ContainerMir52 container)
	{
		super.prepareContainerSlots(container);

		container.addInventory(inventoryInMaterial,
			new SupplierPositionContainerFlow(LEFT + 9 * 4, TOP_CHEST + 9 * 2, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryInMaterialProcessing,
			(inventory, index, x, y) -> new SlotProcessing(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT + 9 * 8, TOP_CHEST + 9 * 2, SHIFT, SHIFT, 9), false);

		container.setTransferInventories(inventoryInMaterial, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryPlayer.get(), inventoryFairy, inventoryFairyFuel, inventoryInMaterial);
		container.setTransferInventories(inventoryHandle.get(), inventoryFairy, inventoryFairyFuel, inventoryInMaterial);
	}

	@Override
	protected void tick()
	{
		if (HelpersSide.helper(this).isRemote()) return;

		ProcessingManager.bringRight(inventoryInMaterial);

		if (HelpersFairyType.isNotNegative(fairyValues.getIncreaser())) {

			new ProcessingManager(
				energyTankProcessing,
				new IInventoryMir51[] {
					inventoryInMaterialProcessing,
				},
				inventoryOutProcessing,
				inventoryOut) {

				@Override
				protected void tryStartProcess(IntConsumer onStart)
				{
					ItemStack itemStack = getLastStack(inventoryInMaterial);
					if (itemStack != null) {

						Entry<IRecipeInput, RecipeOutput> matchingRecipe = null;
						{
							Map<IRecipeInput, RecipeOutput> recipes = ic2.api.recipe.Recipes.macerator.getRecipes();
							for (Entry<IRecipeInput, RecipeOutput> recipe : recipes.entrySet()) {

								if (recipe.getKey().matches(itemStack)) {
									if (recipe.getKey().getAmount() <= itemStack.stackSize) {
										matchingRecipe = recipe;
										break;
									}
								}

							}
						}

						if (matchingRecipe != null) {
							List<ItemStack> outputs = matchingRecipe.getValue().items;
							int cost = matchingRecipe.getKey().getAmount();

							if (outputs != null) {
								if (itemStack.stackSize >= cost) {
									// startable

									// 素材を必要数取り出して進行中スロットに設置する
									ItemStack copy = itemStack.copy();
									inventoryInMaterial.getInventoryCell(inventoryInMaterial.getSizeInventory() - 1).decrStackSize(cost);
									copy.stackSize = cost;
									inventoryInMaterialProcessing.getInventoryCell(0).setInventorySlotContents(copy);

									// 出力を一時バッファに設置する
									for (int i = 0; i < Math.min(inventoryOutProcessing.getSizeInventory(), outputs.size()); i++) {
										inventoryOutProcessing.setInventorySlotContents(i, outputs.get(i).copy());
									}

									onStart.accept(100 * 1000);
								}
							}
						}

					}
				}

				@Override
				protected void processTick(DatamodelEnergy energyTankProcessing)
				{
					long need = energyTankProcessing.getCapacity() - energyTankProcessing.getAmount();

					need = Math.min(200, need);

					int rate = 1;

					long pop = popFairyFuel(0, need * rate);

					energyTankProcessing.setAmount(energyTankProcessing.getAmount() + pop / rate);
				}

			}.tick();

		}

	}

	public static void addRecipeIC2Macerator(String inputOreName, String outputOreName)
	{
		Map<IRecipeInput, RecipeOutput> recipes = ic2.api.recipe.Recipes.macerator.getRecipes();

		ArrayList<ItemStack> ores = OreDictionary.getOres(inputOreName);

		ItemStack output = HelpersOreDictionary.copyOrThrow(outputOreName);
		ArrayList<ItemStack> inputs = new ArrayList<>(); // without stackSize

		ore:
		for (ItemStack ore : ores) {

			for (Entry<IRecipeInput, RecipeOutput> recipe : recipes.entrySet()) {

				if (recipe.getValue().items.size() == 1) {
					if (HelpersOreDictionary.isOre(recipe.getValue().items.get(0), outputOreName)) {
						if (recipe.getValue().items.get(0).stackSize == 1) {

							for (ItemStack input : recipe.getKey().getInputs()) {
								if (ItemStack.areItemStacksEqual(input, ore)) {
									continue ore;
								}
							}

						}
					}
				}

			}

			System.out.println("Add!!: " + ore + " " + ore.getDisplayName());
			inputs.add(ore);
		}

		ic2.api.recipe.Recipes.macerator.addRecipe(
			new IRecipeInput() {
				public ItemStack match(ItemStack subject)
				{
					for (ItemStack input : inputs) {
						if (OreDictionary.itemMatches(input, subject, false)) {
							return input;
						}
					}
					return null;
				}

				@Override
				public boolean matches(ItemStack subject)
				{
					return match(subject) != null;
				}

				@Override
				public List<ItemStack> getInputs()
				{
					return inputs;
				}

				@Override
				public int getAmount()
				{
					return 1;
				}
			}, null, output);
	}

	public static void dump()
	{
		Map<IRecipeInput, RecipeOutput> recipes = ic2.api.recipe.Recipes.macerator.getRecipes();

		for (Entry<IRecipeInput, RecipeOutput> recipe : recipes.entrySet()) {
			System.out.println("----------------------------------------------");
			System.out.println("[AMOUNT]:  " + recipe.getKey().getAmount());
			for (ItemStack input : recipe.getKey().getInputs()) {
				System.out.println("[INPUT]:  " + input + " " + input.getDisplayName());
			}
			for (ItemStack input : recipe.getValue().items) {
				System.out.println("[OUTPUT]: " + input);
			}
		}
	}

}
