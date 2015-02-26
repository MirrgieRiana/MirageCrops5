package mirrg_miragecrops5.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir51.modding.HelpersSide;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.SupplierPositionContainerFlow;
import mirrg_miragecrops5.fairytype.FairyType;
import mirrg_miragecrops5.fairytype.HelpersFairyType;
import mirrg_miragecrops5.fairytype.RegistryFairyType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityMMFSpiritDeveloper extends TileEntityMMFEasy
{

	public final IInventoryMir51 inventoryInMirage;
	public final IInventoryMir51 inventoryInMirageProcessing;
	public final IInventoryMir51 inventoryInMaterial;
	public final IInventoryMir51 inventoryInMaterialProcessing;

	public TileEntityMMFSpiritDeveloper()
	{
		inventoryInMirage = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 2), "inventoryInMirage");
		inventoryInMirageProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInMirageProcessing");
		inventoryInMaterial = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 2), "inventoryInMaterial");
		inventoryInMaterialProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInMaterialProcessing");

		inventoryChain.add(inventoryInMirage);
		inventoryChain.add(inventoryInMirageProcessing);
		inventoryChain.add(inventoryInMaterial);
		inventoryChain.add(inventoryInMaterialProcessing);
	}

	@Override
	public String getDefaultName()
	{
		return "container.miragecrops5.mmfSpiritDeveloper";
	}

	@Override
	protected int[] getFairyValues()
	{
		return new int[] {
			-3, 0, -3, 0, -1, 0,
		};
	}

	@Override
	protected IInventoryMir51[] getInventoryAccessible(int side)
	{
		return new IInventoryMir51[] {
			inventoryOut,
			inventoryInMirage,
			inventoryInMaterial,
		};
	}

	@Override
	protected IInventoryMir51[] getInventoryInsert(int side, ItemStack itemStack)
	{
		if (side == ForgeDirection.UP.ordinal()) {
			return new IInventoryMir51[] {
				inventoryInMirage,
			};
		} else {
			return new IInventoryMir51[] {
				inventoryInMaterial,
			};
		}
	}

	@Override
	protected void prepareContainerSlots(ContainerMir52 container)
	{
		super.prepareContainerSlots(container);

		container.addInventory(inventoryInMirage,
			new SupplierPositionContainerFlow(LEFT + 9 * 4, TOP_CHEST + 9 * 1 - 1, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryInMirageProcessing,
			(inventory, index, x, y) -> new SlotProcessing(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT + 9 * 8, TOP_CHEST + 9 * 1 - 1, SHIFT, SHIFT, 9), false);

		container.addInventory(inventoryInMaterial,
			new SupplierPositionContainerFlow(LEFT + 9 * 4, TOP_CHEST + 9 * 3 + 1, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryInMaterialProcessing,
			(inventory, index, x, y) -> new SlotProcessing(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT + 9 * 8, TOP_CHEST + 9 * 3 + 1, SHIFT, SHIFT, 9), false);

		container.setTransferInventories(inventoryInMirage, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryInMaterial, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryPlayer.get(), inventoryFairy, inventoryFairyFuel, inventoryInMaterial, inventoryInMirage);
		container.setTransferInventories(inventoryHandle.get(), inventoryFairy, inventoryFairyFuel, inventoryInMaterial, inventoryInMirage);
	}

	@Override
	protected void tick()
	{
		if (HelpersSide.helper(this).isRemote()) return;

		ProcessingManager.bringRight(inventoryInMirage);
		ProcessingManager.bringRight(inventoryInMaterial);

		if (HelpersFairyType.isNotNegative(fairyValues.getIncreaser())) {

			new ProcessingManager(
				energyTankProcessing,
				new IInventoryMir51[] {
					inventoryInMaterialProcessing,
					inventoryInMirageProcessing,
				},
				inventoryOutProcessing,
				inventoryOut) {

				@Override
				protected void tryStartProcess(IntConsumer onStart)
				{
					ItemStack itemStack1 = getLastStack(inventoryInMirage);
					ItemStack itemStack2 = getLastStack(inventoryInMaterial);

					//

					if (itemStack1 == null) return;
					if (!HelpersOreDictionary.isOre(itemStack1, "dustMirage")) return;

					if (itemStack2 == null) return;
					ArrayList<FairyType> fairyTypes = RegistryFairyType.getFromItemStack(itemStack2);
					if (fairyTypes.size() == 0) return;
					FairyType fairyType = fairyTypes.get(getWorldObj().rand.nextInt(fairyTypes.size()));

					//

					List<ItemStack> outputs = new ArrayList<>();
					{
						ItemStack output = HelpersOreDictionary.copyOrThrow("craftingSpiritFairy");
						{
							NBTTagCompound nbt = new NBTTagCompound();
							nbt.setString("type", fairyType.typeName);
							output.setTagCompound(nbt);
						}
						outputs.add(output);
					}

					//

					{
						ItemStack copy = itemStack1.copy();
						inventoryInMirage.getInventoryCell(inventoryInMirage.getSizeInventory() - 1).decrStackSize(1);
						copy.stackSize = 1;
						inventoryInMirageProcessing.getInventoryCell(0).setInventorySlotContents(copy);
					}
					{
						ItemStack copy = itemStack2.copy();
						inventoryInMaterial.getInventoryCell(inventoryInMaterial.getSizeInventory() - 1).decrStackSize(1);
						copy.stackSize = 1;
						inventoryInMaterialProcessing.getInventoryCell(0).setInventorySlotContents(copy);
					}

					//

					for (int i = 0; i < Math.min(inventoryOutProcessing.getSizeInventory(), outputs.size()); i++) {
						inventoryOutProcessing.setInventorySlotContents(i, outputs.get(i).copy());
					}

					onStart.accept(100 * 1000);
				}

				@Override
				protected void processTick(DatamodelEnergy energyTankProcessing)
				{
					long need = energyTankProcessing.getCapacity() - energyTankProcessing.getAmount();

					need = Math.min(2000, need);

					int rate = 1;

					long pop = popFairyFuel(0, need * rate);

					energyTankProcessing.setAmount(energyTankProcessing.getAmount() + pop / rate);
				}

			}.tick();

		}

	}
}
