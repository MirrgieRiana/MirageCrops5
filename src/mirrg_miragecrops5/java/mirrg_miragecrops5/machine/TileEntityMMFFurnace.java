package mirrg_miragecrops5.machine;

import mirrg.mir50.tile.inventory.EnergySlot;
import mirrg.mir50.tile.inventory.EnergyTank;
import mirrg.mir50.tile.inventory.Inventory;
import mirrg.mir50.tile.inventory.InventoryTrimmer;
import mirrg.mir52.tile.ContainerMir53;
import mirrg.mir52.tile.SupplierPositionFlow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityMMFFurnace extends TileEntityMMF
{

	public final Inventory inventoryInMaterial;
	public final Inventory inventoryInMaterialProcessing;
	public final Inventory inventoryInFuel;
	public final Inventory inventoryOut;
	public final Inventory inventoryFairy;
	public final Inventory inventoryFairyFuel;

	public final EnergyTank energyTank;

	public TileEntityMMFFurnace()
	{
		inventoryInMaterial = add(new Inventory(this, 2), "inventoryInMaterial");
		inventoryInMaterialProcessing = add(new Inventory(this, 1), "inventoryInMaterialProcessing");
		inventoryInFuel = add(new Inventory(this, 3), "inventoryInFuel");
		inventoryOut = add(new Inventory(this, 4), "inventoryOut");
		inventoryFairy = add(new Inventory(this, 3), "inventoryFairy");
		inventoryFairyFuel = add(new Inventory(this, 1), "inventoryFairyFuel");

		inventoryChain.add(inventoryInMaterial);
		inventoryChain.add(inventoryInMaterialProcessing);
		inventoryChain.add(inventoryInFuel);
		inventoryChain.add(inventoryOut);
		inventoryChain.add(inventoryFairy);
		inventoryChain.add(inventoryFairyFuel);

		energyTank = add(new EnergyTank(this, 1000), "energyTank");
	}

	@Override
	protected Inventory[] getInventoryAccessible(int side)
	{
		return new Inventory[] {};
	}

	@Override
	protected Inventory[] getInventoryExtract(int side, ItemStack itemStack)
	{
		return new Inventory[] {
			inventoryOut,
		};
	}

	@Override
	protected Inventory[] getInventoryInsert(int side, ItemStack itemStack)
	{
		if (side == ForgeDirection.DOWN.ordinal()) {
			return new Inventory[] {
				inventoryInFuel,
			};
		} else {
			return new Inventory[] {
				inventoryInMaterial,
			};
		}
	}

	@Override
	protected ResourceLocation getGuiTexture(ContainerMir53 container)
	{//machineMirageFairy
		return new ResourceLocation("miragecrops5" + ":" + "textures/gui/NULL_GUI_TEXTURE.png");
	}

	@Override
	protected boolean hasGui()
	{
		return true;
	}

	@Override
	protected void prepareContainerSlots(ContainerMir53 container)
	{
		InventoryTrimmer inventoryPlayer = new InventoryTrimmer(container.getPlayer().inventory, 9, 27);
		InventoryTrimmer inventoryHandle = new InventoryTrimmer(container.getPlayer().inventory, 0, 9);

		inventoryChain.add(inventoryInMaterial);
		inventoryChain.add(inventoryInMaterialProcessing);
		inventoryChain.add(inventoryInFuel);
		inventoryChain.add(inventoryOut);
		inventoryChain.add(inventoryFairy);
		inventoryChain.add(inventoryFairyFuel);

		container.addInventory(inventoryInMaterial,
			new SupplierPositionFlow(LEFT + 9 * 4, TOP_CHEST, SHIFT, SHIFT, 9), false);
		container.addInventory(inventoryInMaterialProcessing,
			(inventory, index, x, y) -> new SlotAdvanced(inventory, index, x, y, RendererSlot.instanceGroove),
			new SupplierPositionFlow(LEFT + 9 * 8, TOP_CHEST, SHIFT, SHIFT, 9), false);
		container.addInventory(inventoryInFuel,
			new SupplierPositionFlow(LEFT + 9 * 4, TOP_CHEST + 9 * 4, SHIFT, SHIFT, 9), false);
		container.addInventory(inventoryOut,
			new SupplierPositionFlow(LEFT + 9 * 14, TOP_CHEST + 9 * 1, SHIFT, SHIFT, 2), false);
		container.addInventory(inventoryFairy,
			new SupplierPositionFlow(LEFT, TOP_CHEST, SHIFT, SHIFT, 1), false);
		container.addInventory(inventoryFairyFuel,
			new SupplierPositionFlow(LEFT + 9 * 11, TOP_CHEST + 9 * 4, SHIFT, SHIFT, 1), false);

		container.addInventory(inventoryPlayer, new SupplierPositionFlow(LEFT, TOP_INVENTORY, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryHandle, new SupplierPositionFlow(LEFT, TOP_HOLDING, SHIFT, SHIFT, 9), true);

		container.setTransferInventories(inventoryInMaterial, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryInFuel, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryOut, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryFairy, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryFairyFuel, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryPlayer, inventoryInMaterial, inventoryInFuel, inventoryFairy, inventoryFairyFuel);
		container.setTransferInventories(inventoryHandle, inventoryInMaterial, inventoryInFuel, inventoryFairy, inventoryFairyFuel);

		int w = 24, h = 17;
		energyTank.amount = 725;
		container.addEnergySlot(new EnergySlot(energyTank, LEFT + 9 * 12 - w / 2, TOP_CHEST + 9 * 3 - h / 2 - 5, w, h));
	}

}
