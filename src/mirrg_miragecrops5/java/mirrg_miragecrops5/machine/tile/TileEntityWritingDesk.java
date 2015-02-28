package mirrg_miragecrops5.machine.tile;

import java.util.function.IntConsumer;

import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotDatamodel;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotLabel;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import mirrg.mir50.world.pointer.SupplierPositionWorldFromTileEntity;
import mirrg.mir51.gui.renderers.RendererEnergySlotMeter;
import mirrg.mir51.gui.renderers.RendererEnergySlotProgress;
import mirrg.mir51.gui.renderers.RendererLabel;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir51.inventory.InventoryMir51FromInventory;
import mirrg.mir51.inventory.InventoryMir51Trimmer;
import mirrg.mir51.modding.HelpersSide;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.SupplierPositionContainerFlow;
import mirrg_miragecrops5.machine.container.SlotFairy;
import mirrg_miragecrops5.machine.container.SlotFairyFuel;
import mirrg_miragecrops5.machine.container.SlotProcessing;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import api.mirrg.mir50.gui.renderer.EnumTextAlign;
import api.mirrg_miragecrops5.APIRegistryRecipeFairyFuel;
import api.mirrg_miragecrops5.APIRegistryRecipeFairyFuel.IMatcherFairyFuel;

public class TileEntityWritingDesk extends TileEntityMMF
{

	public final IInventoryMir51 inventoryInFairy;
	public final IInventoryMir51 inventoryInFairyProcessing;
	public final IInventoryMir51 inventoryInMaterial;
	public final IInventoryMir51 inventoryInMaterialProcessing;

	public final IInventoryMir51 inventoryFairyFuel;
	public final IInventoryMir51 inventoryOut;
	public final IInventoryMir51 inventoryOutProcessing;

	public final DatamodelEnergy energyTankProcessing;
	public final DatamodelEnergy energyTankHyleon;

	public TileEntityWritingDesk()
	{
		inventoryInFairy = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInFairy");
		inventoryInFairyProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInFairyProcessing");
		inventoryInMaterial = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInMaterial");
		inventoryInMaterialProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInMaterialProcessing");
		inventoryFairyFuel = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryFairyFuel");
		inventoryOut = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryOut");
		inventoryOutProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryOutProcessing");

		inventoryChain.add(inventoryInFairy);
		inventoryChain.add(inventoryInFairyProcessing);
		inventoryChain.add(inventoryInMaterial);
		inventoryChain.add(inventoryInMaterialProcessing);
		inventoryChain.add(inventoryFairyFuel);
		inventoryChain.add(inventoryOut);
		inventoryChain.add(inventoryOutProcessing);

		energyTankProcessing = add(new DatamodelEnergy(this::markDirty, 0), "energyTankProcessing");
		energyTankHyleon = add(new DatamodelEnergy(this::markDirty, 100000), "energyTankHyleon");
	}

	@Override
	public String getDefaultName()
	{
		return "container.miragecrops5.writingDesk";
	}

	@Override
	protected IInventoryMir51[] getInventoryAccessible(int side)
	{
		return new IInventoryMir51[] {
			inventoryInMaterial,
			inventoryOut,
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
	protected IInventoryMir51[] getInventoryExtract(int side, ItemStack itemStack)
	{
		return new IInventoryMir51[] {
			inventoryOut,
		};
	}

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
			IInventoryMir51 inventory2 = new InventoryMir51FromInventory(container.getPlayer().inventory,
				new SupplierPositionWorldFromTileEntity(this));
			inventoryPlayer.set(new InventoryMir51Trimmer(inventory2, 9, 27));
			inventoryHandle.set(new InventoryMir51Trimmer(inventory2, 0, 9));

			container.addInventory(inventoryPlayer.get(),
				new SupplierPositionContainerFlow(LEFT, TOP_INVENTORY, SHIFT, SHIFT, 9), true);
			container.addInventory(inventoryHandle.get(),
				new SupplierPositionContainerFlow(LEFT, TOP_HOLDING, SHIFT, SHIFT, 9), true);
		}

		container.addInventory(inventoryInMaterial,
			new SupplierPositionContainerFlow(LEFT + 9 * 2, TOP_CHEST + 9 * 1 - 1, SHIFT, SHIFT, 3), true);
		container.addInventory(inventoryInMaterialProcessing,
			(inventory, index, x, y) -> new SlotProcessing(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT + 9 * 4, TOP_CHEST + 9 * 1 - 1, SHIFT, SHIFT, 9), false);

		container.addInventory(inventoryInFairy,
			(inventory, index, x, y) -> new SlotFairy(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT + 9 * 2, TOP_CHEST + 9 * 3 + 1, SHIFT, SHIFT, 1), false);
		container.addInventory(inventoryInFairyProcessing,
			(inventory, index, x, y) -> new SlotProcessing(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT + 9 * 4, TOP_CHEST + 9 * 3 + 1, SHIFT, SHIFT, 9), false);

		container.addInventory(inventoryOut,
			new SupplierPositionContainerFlow(LEFT + 9 * 12, TOP_CHEST + 9 * 2, SHIFT, SHIFT, 3), false);

		{
			ContainerExtraSlotDatamodel<DatamodelEnergy> containerExtraSlot =
				new ContainerExtraSlotDatamodel<DatamodelEnergy>(
					LEFT + 9 * 9 - 24 / 2, TOP_CHEST + 9 * 2 - 17 / 2 - 3, 24, 17, energyTankProcessing);
			containerExtraSlot.renderer = RendererEnergySlotProgress.instanceLeft;
			container.addContainerExtraSlot(containerExtraSlot, getName(energyTankProcessing));
		}
		{
			ContainerExtraSlotDatamodel<DatamodelEnergy> containerExtraSlot =
				new ContainerExtraSlotDatamodel<DatamodelEnergy>(
					LEFT + 9 * 9 - 0 / 2, TOP_CHEST + 9 * 4 - 0 / 2 - 3, 0, 0, energyTankHyleon);
			containerExtraSlot.renderer = RendererEnergySlotMeter.instance;
			container.addContainerExtraSlot(containerExtraSlot, getName(energyTankHyleon));
		}
		container.addInventory(inventoryFairyFuel,
			(inventory, index, x, y) -> new SlotFairyFuel(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT + 9 * 8, TOP_CHEST + 9 * 4, SHIFT, SHIFT, 1), false);

		container.setTransferInventories(inventoryInFairy, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryInMaterial, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryOut, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryFairyFuel, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryPlayer.get(), inventoryInFairy, inventoryFairyFuel, inventoryInMaterial);
		container.setTransferInventories(inventoryHandle.get(), inventoryInFairy, inventoryFairyFuel, inventoryInMaterial);
	}

	@Override
	protected void tick()
	{
		if (HelpersSide.helper(this).isRemote()) return;

		ProcessingManager.bringRight(inventoryInMaterial);

		ProcessingManager processingManager = new ProcessingManager(
			energyTankProcessing,
			new IInventoryMir51[] {
				inventoryInMaterialProcessing,
			}) {

			@Override
			protected void tryStartProcess(IntConsumer onStart)
			{
				ItemStack itemStackFairy = getLastStack(inventoryInFairy);
				if (HelpersOreDictionary.isOre(itemStackFairy, "craftingMirageFairyCobblestone")) {
					int costFairy = 1;

					ItemStack itemStack = getLastStack(inventoryInMaterial);
					if (itemStack == null) return;
					if (OreDictionary.itemMatches(new ItemStack(Items.writable_book), itemStack, true)) {
						int cost = 1;

						ItemStack output = HelpersOreDictionary.getOrThrow("craftingBookMirageToolIndustrial");

						if (output != null) {
							if (itemStack.stackSize >= cost) {
								// startable

								{
									// 素材を必要数取り出して進行中スロットに設置する
									ItemStack copy = itemStack.copy();
									inventoryInMaterial.getInventoryCell(inventoryInMaterial.getSizeInventory() - 1).decrStackSize(cost);
									copy.stackSize = cost;
									inventoryInMaterialProcessing.getInventoryCell(0).setInventorySlotContents(copy);

									// 出力を一時バッファに設置する
									inventoryOutProcessing.getInventoryCell(0).setInventorySlotContents(output.copy());
								}

								{
									// 素材を必要数取り出して進行中スロットに設置する
									ItemStack copy = itemStackFairy.copy();
									inventoryInFairy.getInventoryCell(inventoryInFairy.getSizeInventory() - 1).decrStackSize(costFairy);
									copy.stackSize = costFairy;
									inventoryInFairyProcessing.getInventoryCell(0).setInventorySlotContents(copy);
								}

								onStart.accept(1000 * 1000);
							}
						}

					}

				}
			}

			@Override
			protected void processTick(DatamodelEnergy energyTankProcessing)
			{
				long need = energyTankProcessing.getCapacity() - energyTankProcessing.getAmount();

				need = Math.min(500, need);

				int rate = 1;

				long pop = popFairyFuel(0, need * rate);

				energyTankProcessing.setAmount(energyTankProcessing.getAmount() + pop / rate);
			}

		};
		processingManager.addOut(inventoryOutProcessing, inventoryOut);
		processingManager.addOut(inventoryInFairyProcessing, inventoryInFairy);
		processingManager.tick();

	}

	protected long popFairyFuel(long min, long max)
	{
		return ProcessingManager.popFuel(min, max, i -> {},
			energyTankHyleon, inventoryFairyFuel,
			itemStack -> APIRegistryRecipeFairyFuel.registry.matcher(itemStack)
				.map(IMatcherFairyFuel::getOutput)
				.orElse(0) * 1000);
	}

}
