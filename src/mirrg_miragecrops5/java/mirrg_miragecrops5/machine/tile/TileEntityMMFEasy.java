package mirrg_miragecrops5.machine.tile;

import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotDatamodel;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotLabel;
import mirrg.mir50.world.pointer.SupplierPositionWorldFromTileEntity;
import mirrg.mir51.gui.renderers.RendererEnergySlotMeter;
import mirrg.mir51.gui.renderers.RendererLabel;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir51.inventory.InventoryMir51FromInventory;
import mirrg.mir51.inventory.InventoryMir51Trimmer;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.SupplierPositionContainerFlow;
import mirrg_miragecrops5.machine.container.ContainerExtraSlotFairyGraph;
import mirrg_miragecrops5.machine.container.SlotFairy;
import mirrg_miragecrops5.machine.container.SlotFairyFuel;
import mirrg_miragecrops5.machine.gui.RendererFairyGraph;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import api.mirrg.mir50.gui.renderer.EnumTextAlign;
import api.mirrg_miragecrops5.recipes.APIRegistryRecipe;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel;

public abstract class TileEntityMMFEasy extends TileEntityMMF
{

	public final IInventoryMir51 inventoryFairy;
	public final IInventoryMir51 inventoryFairyFuel;

	public final DatamodelEnergy energyTankHyleon;

	public final DatamodelFairyValues fairyValues;

	public TileEntityMMFEasy()
	{
		inventoryFairy = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 3), "inventoryFairy");
		inventoryFairyFuel = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryFairyFuel");

		inventoryChain.add(inventoryFairy);
		inventoryChain.add(inventoryFairyFuel);

		energyTankHyleon = add(new DatamodelEnergy(this::markDirty, 100000), "energyTankHyleon");

		fairyValues = new DatamodelFairyValues(getFairyValues(), inventoryFairy);
	}

	protected abstract int[] getFairyValues();

	@Override
	protected ResourceLocation getGuiTexture(ContainerMir52 container)
	{//machineMirageFairy
		return new ResourceLocation("miragecrops5" + ":" + "textures/gui/NULL_GUI_TEXTURE.png");
	}

	@Override
	protected boolean hasGui()
	{
		return true;
	}

	protected ThreadLocal<InventoryMir51Trimmer> inventoryPlayer = new ThreadLocal<>();
	protected ThreadLocal<InventoryMir51Trimmer> inventoryHandle = new ThreadLocal<>();

	@Override
	protected void prepareContainerSlots(ContainerMir52 container)
	{
		super.prepareContainerSlots(container);

		{
			ContainerExtraSlotLabel containerExtraSlot;

			containerExtraSlot = new ContainerExtraSlotLabel(getLocalizedName(),
				gui -> gui.getGuiWidth() / 2,
				gui -> TOP,
				0x404040, EnumTextAlign.CENTER);
			containerExtraSlot.renderer = new RendererLabel(containerExtraSlot);
			container.addContainerExtraSlot(containerExtraSlot, "labelTileEntity");

			containerExtraSlot = new ContainerExtraSlotLabel(I18n.format("container.inventory"),
				gui -> SHIFT,
				gui -> gui.getGuiHeight() - 96 + 2,
				0x404040, EnumTextAlign.LEFT);
			containerExtraSlot.renderer = new RendererLabel(containerExtraSlot);
			container.addContainerExtraSlot(containerExtraSlot, "labelInventory");
		}
		{
			ContainerExtraSlotFairyGraph containerExtraSlot = new ContainerExtraSlotFairyGraph(
				LEFT + 9 * 2, TOP_CHEST, 9 * 2, 9 * 6, fairyValues);
			containerExtraSlot.renderer = new RendererFairyGraph();
			container.addContainerExtraSlot(containerExtraSlot, "labelFairyTypes");
		}

		{
			IInventoryMir51 inventory2 = new InventoryMir51FromInventory(container.getPlayer().inventory,
				new SupplierPositionWorldFromTileEntity(this));
			inventoryPlayer.set(new InventoryMir51Trimmer(inventory2, 9, 27));
			inventoryHandle.set(new InventoryMir51Trimmer(inventory2, 0, 9));

			container.addInventory(inventoryPlayer.get(),
				new SupplierPositionContainerFlow(LEFT, TOP_INVENTORY, SHIFT, SHIFT, 9), true);
			container.addInventory(inventoryHandle.get(),
				new SupplierPositionContainerFlow(LEFT, TOP_HOLDING, SHIFT, SHIFT, 9), true);
		}

		container.addInventory(inventoryFairy,
			(inventory, index, x, y) -> new SlotFairy(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT, TOP_CHEST, SHIFT, SHIFT, 1), false);
		container.addInventory(inventoryFairyFuel,
			(inventory, index, x, y) -> new SlotFairyFuel(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT + 9 * 11, TOP_CHEST + 9 * 4, SHIFT, SHIFT, 1), false);

		{
			ContainerExtraSlotDatamodel<DatamodelEnergy> containerExtraSlot =
				new ContainerExtraSlotDatamodel<DatamodelEnergy>(
					LEFT + 9 * 12 - 0 / 2, TOP_CHEST + 9 * 4 - 0 / 2 - 3, 0, 0, energyTankHyleon);
			containerExtraSlot.renderer = RendererEnergySlotMeter.instance;
			container.addContainerExtraSlot(containerExtraSlot, getName(energyTankHyleon));
		}

		container.setTransferInventories(inventoryFairy, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryFairyFuel, inventoryHandle.get(), inventoryPlayer.get());
	}

	protected long popFairyFuel(long min, long max)
	{
		return ProcessingManager.popFuel(min, max, i -> {},
			energyTankHyleon, inventoryFairyFuel,
			itemStack -> APIRegistryRecipe.registryRecipeFairyFuel.matcher(itemStack)
				.map(InterfacesRecipeFuel.IMatcherRecipeFuel::getOutput)
				.orElse(0) * 1000);
	}

}