package mirrg_miragecrops5.machine.tile;

import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotDatamodel;
import mirrg.mir51.gui.renderers.RendererEnergySlotProgress;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.SupplierPositionContainerFlow;
import net.minecraft.item.ItemStack;

public abstract class TileEntityMMFEasySolid extends TileEntityMMFEasy
{

	public final IInventoryMir51 inventoryOut;
	public final IInventoryMir51 inventoryOutProcessing;

	public final DatamodelEnergy energyTankProcessing;

	public TileEntityMMFEasySolid()
	{
		inventoryOut = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 4), "inventoryOut");
		inventoryOutProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 4), "inventoryOutProcessing");

		inventoryChain.add(inventoryOut);
		inventoryChain.add(inventoryOutProcessing);

		energyTankProcessing = add(new DatamodelEnergy(this::markDirty, 0), "energyTankProcessing");
	}

	@Override
	protected IInventoryMir51[] getInventoryExtract(int side, ItemStack itemStack)
	{
		return new IInventoryMir51[] {
			inventoryOut,
		};
	}

	@Override
	protected void prepareContainerSlots(ContainerMir52 container)
	{
		super.prepareContainerSlots(container);

		container.addInventory(inventoryOut,
			new SupplierPositionContainerFlow(LEFT + 9 * 14, TOP_CHEST + 9 * 1, SHIFT, SHIFT, 2), false);

		{
			ContainerExtraSlotDatamodel<DatamodelEnergy> containerExtraSlot =
				new ContainerExtraSlotDatamodel<DatamodelEnergy>(
					LEFT + 9 * 12 - 24 / 2, TOP_CHEST + 9 * 2 - 17 / 2 - 3, 24, 17, energyTankProcessing);
			containerExtraSlot.renderer = RendererEnergySlotProgress.instanceLeft;
			container.addContainerExtraSlot(containerExtraSlot, getName(energyTankProcessing));
		}

		container.setTransferInventories(inventoryOut, inventoryHandle.get(), inventoryPlayer.get());
	}

}
