package mirrg_miragecrops5.machine.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

import mirrg.h.struct.Tuple;
import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import mirrg.mir50.oredictionary.OreMatcher;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir51.modding.HelpersSide;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.SupplierPositionContainerFlow;
import mirrg_miragecrops5.fairytype.HelpersFairyType;
import mirrg_miragecrops5.machine.container.SlotProcessing;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TileEntityMMFCarbonizationFurnace extends TileEntityMMFEasy
{

	public final IInventoryMir51 inventoryInMaterial;
	public final IInventoryMir51 inventoryInMaterialProcessing;

	public TileEntityMMFCarbonizationFurnace()
	{
		inventoryInMaterial = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 2), "inventoryInMaterial");
		inventoryInMaterialProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInMaterialProcessing");

		inventoryChain.add(inventoryInMaterial);
		inventoryChain.add(inventoryInMaterialProcessing);
	}

	@Override
	public String getDefaultName()
	{
		return "container.miragecrops5.mmfCarbonizationFurnace";
	}

	@Override
	protected int[] getFairyValues()
	{
		return new int[] {
			0, -8, 0, 0, -8, 0,
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

	protected static ArrayList<Tuple<Tuple<OreMatcher, Integer>, Supplier<ItemStack>>> recipes = new ArrayList<>();
	static {
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(new ItemStack(Items.coal)), 1), () -> HelpersOreDictionary.copyOrThrow("craftingCoke")));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher("logWood"), 1), () -> new ItemStack(Items.coal, 1, 1)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(new ItemStack(Items.coal, 1, 1)), 4), () -> HelpersOreDictionary.copyOrThrow("craftingCoke")));
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
					if (itemStack == null) return;

					Tuple<Tuple<OreMatcher, Integer>, Supplier<ItemStack>> theRecipe = null;
					for (Tuple<Tuple<OreMatcher, Integer>, Supplier<ItemStack>> recipe : recipes) {
						if (recipe.getX().getX().matches(itemStack) && itemStack.stackSize >= recipe.getX().getY()) {
							theRecipe = recipe;
							break;
						}
					}
					if (theRecipe == null) return;

					List<ItemStack> outputs = new ArrayList<>();
					outputs.add(theRecipe.getY().get());
					int cost = theRecipe.getX().getY();

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

				@Override
				protected void processTick(DatamodelEnergy energyTankProcessing)
				{
					long need = energyTankProcessing.getCapacity() - energyTankProcessing.getAmount();

					need = Math.min(1000, need);

					int rate = 1;

					long pop = popFairyFuel(0, need * rate);

					energyTankProcessing.setAmount(energyTankProcessing.getAmount() + pop / rate);
				}

			}.tick();

		}

	}
}
