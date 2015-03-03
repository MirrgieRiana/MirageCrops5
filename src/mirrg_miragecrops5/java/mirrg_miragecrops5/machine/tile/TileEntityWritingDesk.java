package mirrg_miragecrops5.machine.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.IntConsumer;

import mirrg.h.struct.Tuple;
import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotDatamodel;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotLabel;
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
import mirrg_miragecrops5.ModuleCore;
import mirrg_miragecrops5.fairytype.FairyType;
import mirrg_miragecrops5.fairytype.RegistryFairyType;
import mirrg_miragecrops5.machine.container.SlotFairy;
import mirrg_miragecrops5.machine.container.SlotFairyFuel;
import mirrg_miragecrops5.machine.container.SlotProcessing;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import api.mirrg.mir50.gui.renderer.EnumTextAlign;
import api.mirrg_miragecrops5.recipes.APIRegistryRecipe;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeWritingDesk.IMatcherRecipeWritingDesk;

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
				ItemStack itemStack = getLastStack(inventoryInMaterial);

				ItemStack itemStackFairy = getLastStack(inventoryInFairy);
				if (itemStackFairy == null) return;
				if (itemStackFairy.getItem() != ModuleCore.loaderItem_craftingMirageFairy.get()) return;
				int damage = itemStackFairy.getItemDamage();
				int indexFairyType = damage / 10;
				int tier = (damage % 10) + 1;
				FairyType fairyType = RegistryFairyType.registry.get(indexFairyType);

				//

				Optional<IMatcherRecipeWritingDesk> optionalMatcher = APIRegistryRecipe.registryRecipeWritingDesk.matcher(new Tuple<>(itemStack, fairyType));
				if (!optionalMatcher.isPresent()) return;
				IMatcherRecipeWritingDesk matcher = optionalMatcher.get();

				List<ItemStack> outputs = new ArrayList<>();
				outputs.add(matcher.getOutput());

				{
					// startable

					{
						// 素材を必要数取り出して進行中スロットに設置する
						Tuple<ItemStack, FairyType> out = matcher.consume();
						inventoryInMaterial.getInventoryCell(inventoryInMaterial.getSizeInventory() - 1).decrStackSize(0);
						inventoryInMaterialProcessing.getInventoryCell(0).setInventorySlotContents(out.getX());
					}

					{
						// 素材を必要数取り出して進行中スロットに設置する
						ItemStack copy = itemStackFairy.copy();
						inventoryInFairy.getInventoryCell(inventoryInFairy.getSizeInventory() - 1).decrStackSize(1);
						copy.stackSize = 1;
						inventoryInFairyProcessing.getInventoryCell(0).setInventorySlotContents(copy);
					}

					{
						// 出力を一時バッファに設置する
						for (int i = 0; i < Math.min(inventoryOutProcessing.getSizeInventory(), outputs.size()); i++) {
							inventoryOutProcessing.setInventorySlotContents(i, outputs.get(i).copy());
						}
					}

					onStart.accept(1000 * 1000);
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
			itemStack -> APIRegistryRecipe.registryRecipeFairyFuel.matcher(itemStack)
				.map(InterfacesRecipeFuel.IMatcherRecipeFuel::getOutput)
				.orElse(0) * 1000);
	}

}
