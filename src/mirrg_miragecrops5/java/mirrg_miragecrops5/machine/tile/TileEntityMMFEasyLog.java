package mirrg_miragecrops5.machine.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import mirrg.h.struct.Tuple;
import mirrg.h.struct.Tuple4;
import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir51.modding.HelpersSide;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.SupplierPositionContainerFlow;
import mirrg_miragecrops5.fairytype.HelpersFairyType;
import mirrg_miragecrops5.machine.container.SlotProcessing;
import net.minecraft.item.ItemStack;

public abstract class TileEntityMMFEasyLog extends TileEntityMMFEasySolid
{

	public final IInventoryMir51 inventoryInMaterial;
	public final IInventoryMir51 inventoryInMaterialProcessing;
	public final IInventoryMir51 inventoryInMaterial2;
	public final IInventoryMir51 inventoryInMaterial2Processing;

	public TileEntityMMFEasyLog()
	{
		inventoryInMaterial = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 2), "inventoryInMaterial");
		inventoryInMaterialProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInMaterialProcessing");
		inventoryInMaterial2 = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 2), "inventoryInMaterial2");
		inventoryInMaterial2Processing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInMaterial2Processing");

		inventoryChain.add(inventoryInMaterial);
		inventoryChain.add(inventoryInMaterialProcessing);
		inventoryChain.add(inventoryInMaterial2);
		inventoryChain.add(inventoryInMaterial2Processing);
	}

	@Override
	protected IInventoryMir51[] getInventoryAccessible(int side)
	{
		return new IInventoryMir51[] {
			inventoryOut,
			inventoryInMaterial,
			inventoryInMaterial2,
		};
	}

	@Override
	protected IInventoryMir51[] getInventoryInsert(int side, ItemStack itemStack)
	{
		return new IInventoryMir51[] {
			inventoryInMaterial,
			inventoryInMaterial2,
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

		container.addInventory(inventoryInMaterial2,
			new SupplierPositionContainerFlow(LEFT + 9 * 4, TOP_CHEST + 9 * 4, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryInMaterial2Processing,
			(inventory, index, x, y) -> new SlotProcessing(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT + 9 * 8, TOP_CHEST + 9 * 4, SHIFT, SHIFT, 9), false);

		container.setTransferInventories(inventoryInMaterial, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryInMaterial2, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryPlayer.get(), inventoryFairy, inventoryFairyFuel, inventoryInMaterial, inventoryInMaterial2);
		container.setTransferInventories(inventoryHandle.get(), inventoryFairy, inventoryFairyFuel, inventoryInMaterial, inventoryInMaterial2);
	}

	@Override
	protected void tick()
	{
		if (HelpersSide.helper(this).isRemote()) return;

		ProcessingManager.bringRight(inventoryInMaterial);
		ProcessingManager.bringRight(inventoryInMaterial2);

		if (HelpersFairyType.isNotNegative(fairyValues.getIncreaser())) {

			new ProcessingManager(
				energyTankProcessing,
				new IInventoryMir51[] {
					inventoryInMaterialProcessing,
					inventoryInMaterial2Processing,
				},
				inventoryOutProcessing,
				inventoryOut) {

				@Override
				protected void tryStartProcess(IntConsumer onStart)
				{
					ArrayList<Tuple4<Tuple<Predicate<ItemStack>, Integer>, Tuple<Predicate<ItemStack>, Integer>, Supplier<ItemStack>, Integer>> recipes = getRecipes();

					ItemStack itemStack = getLastStack(inventoryInMaterial);
					ItemStack itemStack2 = getLastStack(inventoryInMaterial2);

					for (Tuple4<Tuple<Predicate<ItemStack>, Integer>, Tuple<Predicate<ItemStack>, Integer>, Supplier<ItemStack>, Integer> recipe : recipes) {

						if (recipe.getX().getX().test(itemStack)) {
							int cost1 = recipe.getX().getY();
							if (itemStack.stackSize >= cost1) {

								if (recipe.getY().getX().test(itemStack2)) {
									int cost2 = recipe.getY().getY();
									if (itemStack2.stackSize >= cost2) {
										// startable

										// 素材を必要数取り出して進行中スロットに設置する
										ItemStack copy = itemStack.copy();
										inventoryInMaterial.getInventoryCell(inventoryInMaterial.getSizeInventory() - 1).decrStackSize(cost1);
										copy.stackSize = cost1;
										inventoryInMaterialProcessing.getInventoryCell(0).setInventorySlotContents(copy);

										copy = itemStack2.copy();
										inventoryInMaterial2.getInventoryCell(inventoryInMaterial2.getSizeInventory() - 1).decrStackSize(cost2);
										copy.stackSize = cost2;
										inventoryInMaterial2Processing.getInventoryCell(0).setInventorySlotContents(copy);

										// 出力を一時バッファに設置する
										List<ItemStack> outputs = new ArrayList<>();
										outputs.add(recipe.getZ().get());

										for (int i = 0; i < Math.min(inventoryOutProcessing.getSizeInventory(), outputs.size()); i++) {
											inventoryOutProcessing.setInventorySlotContents(i, outputs.get(i).copy());
										}

										onStart.accept(recipe.getW() * 1000);

										break;
									}
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

	protected abstract ArrayList<Tuple4<Tuple<Predicate<ItemStack>, Integer>, Tuple<Predicate<ItemStack>, Integer>, Supplier<ItemStack>, Integer>> getRecipes();

}
