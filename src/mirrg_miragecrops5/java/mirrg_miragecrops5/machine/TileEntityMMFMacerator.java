package mirrg_miragecrops5.machine;

import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.IntConsumer;

import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotDatamodel;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotLabel;
import mirrg.mir50.world.pointer.SupplierPositionWorldFromTileEntity;
import mirrg.mir51.gui.renderers.RendererEnergySlotMeter;
import mirrg.mir51.gui.renderers.RendererEnergySlotProgress;
import mirrg.mir51.gui.renderers.RendererLabel;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir51.inventory.InventoryMir51Chain;
import mirrg.mir51.inventory.InventoryMir51FromInventory;
import mirrg.mir51.inventory.InventoryMir51Trimmer;
import mirrg.mir51.modding.HelpersSide;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.SupplierPositionContainerFlow;
import mirrg_miragecrops5.fairytype.HelpersFairyType;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import api.mirrg.mir50.gui.renderer.EnumTextAlign;

public class TileEntityMMFMacerator extends TileEntityMMF
{

	public final IInventoryMir51 inventoryInMaterial;
	public final IInventoryMir51 inventoryInMaterialProcessing;
	public final IInventoryMir51 inventoryOut;
	public final IInventoryMir51 inventoryOutProcessing;
	public final IInventoryMir51 inventoryFairy;
	public final IInventoryMir51 inventoryFairyFuel;

	public final DatamodelEnergy energyTankProcessing;
	public final DatamodelEnergy energyTankHyleon;

	public final DatamodelFairyValues fairyValues;

	protected int ticker = 0;

	public TileEntityMMFMacerator()
	{
		inventoryInMaterial = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 2), "inventoryInMaterial");
		inventoryInMaterialProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInMaterialProcessing");
		inventoryOut = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 4), "inventoryOut");
		inventoryOutProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 4), "inventoryOutProcessing");
		inventoryFairy = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 3), "inventoryFairy");
		inventoryFairyFuel = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryFairyFuel");

		inventoryChain.add(inventoryInMaterial);
		inventoryChain.add(inventoryInMaterialProcessing);
		inventoryChain.add(inventoryOut);
		inventoryChain.add(inventoryOutProcessing);
		inventoryChain.add(inventoryFairy);
		inventoryChain.add(inventoryFairyFuel);

		energyTankProcessing = add(new DatamodelEnergy(this::markDirty, 0), "energyTankProcessing");
		energyTankHyleon = add(new DatamodelEnergy(this::markDirty, 100000), "energyTankHyleon");

		fairyValues = new DatamodelFairyValues(new int[] {
			0, -48, 0, 0, 0, 0,
		}, inventoryFairy);
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
	protected IInventoryMir51[] getInventoryExtract(int side, ItemStack itemStack)
	{
		return new IInventoryMir51[] {
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
	protected ResourceLocation getGuiTexture(ContainerMir52 container)
	{//machineMirageFairy
		return new ResourceLocation("miragecrops5" + ":" + "textures/gui/NULL_GUI_TEXTURE.png");
	}

	@Override
	protected boolean hasGui()
	{
		return true;
	}

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
			ContainerExtraSlotFairyGraph containerExtraSlot = new ContainerExtraSlotFairyGraph(
				LEFT + 9 * 2, TOP_CHEST, 9 * 2, 9 * 6, fairyValues);
			containerExtraSlot.renderer = new RendererFairyGraph();
			container.addContainerExtraSlot(containerExtraSlot, "labelFairyTypes");
		}

		InventoryMir51Chain inventoryChest = inventoryChain;
		IInventoryMir51 inventory2 = new InventoryMir51FromInventory(container.getPlayer().inventory,
			new SupplierPositionWorldFromTileEntity(this));
		InventoryMir51Trimmer inventoryPlayer = new InventoryMir51Trimmer(inventory2, 9, 27);
		InventoryMir51Trimmer inventoryHandle = new InventoryMir51Trimmer(inventory2, 0, 9);

		container.addInventory(inventoryInMaterial,
			new SupplierPositionContainerFlow(LEFT + 9 * 4, TOP_CHEST, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryInMaterialProcessing,
			(inventory, index, x, y) -> new SlotProcessing(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT + 9 * 8, TOP_CHEST, SHIFT, SHIFT, 9), false);
		container.addInventory(inventoryOut,
			new SupplierPositionContainerFlow(LEFT + 9 * 14, TOP_CHEST + 9 * 1, SHIFT, SHIFT, 2), false);
		container.addInventory(inventoryFairy,
			(inventory, index, x, y) -> new SlotFairy(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT, TOP_CHEST, SHIFT, SHIFT, 1), false);
		container.addInventory(inventoryFairyFuel,
			(inventory, index, x, y) -> new SlotFairyFuel(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT + 9 * 11, TOP_CHEST + 9 * 4, SHIFT, SHIFT, 1), false);

		container.addInventory(inventoryPlayer,
			new SupplierPositionContainerFlow(LEFT, TOP_INVENTORY, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryHandle,
			new SupplierPositionContainerFlow(LEFT, TOP_HOLDING, SHIFT, SHIFT, 9), true);

		container.setTransferInventories(inventoryInMaterial, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryOut, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryFairy, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryFairyFuel, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryPlayer, inventoryFairy, inventoryFairyFuel, inventoryInMaterial);
		container.setTransferInventories(inventoryHandle, inventoryFairy, inventoryFairyFuel, inventoryInMaterial);

		{
			ContainerExtraSlotDatamodel<DatamodelEnergy> containerExtraSlot =
				new ContainerExtraSlotDatamodel<DatamodelEnergy>(
					LEFT + 9 * 12 - 24 / 2, TOP_CHEST + 9 * 2 - 17 / 2 - 3, 24, 17, energyTankProcessing);
			containerExtraSlot.renderer = RendererEnergySlotProgress.instanceLeft;
			container.addContainerExtraSlot(containerExtraSlot, getName(energyTankProcessing));
		}
		{
			ContainerExtraSlotDatamodel<DatamodelEnergy> containerExtraSlot =
				new ContainerExtraSlotDatamodel<DatamodelEnergy>(
					LEFT + 9 * 12 - 0 / 2, TOP_CHEST + 9 * 4 - 0 / 2 - 3, 0, 0, energyTankHyleon);
			containerExtraSlot.renderer = RendererEnergySlotMeter.instance;
			container.addContainerExtraSlot(containerExtraSlot, getName(energyTankHyleon));
		}

	}

	@Override
	protected void tick()
	{
		if (HelpersSide.helper(this).isRemote()) return;

		ProcessingManager.bringRight(inventoryInMaterial);

		if (HelpersFairyType.isNotNegative(fairyValues.getIncreaser())) {

			new ProcessingManager(
				energyTankProcessing,
				inventoryInMaterialProcessing,
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

									onStart.accept(200);
								}
							}
						}

					}
				}

				@Override
				protected void processTick(DatamodelEnergy energyTankProcessing)
				{
					long need = energyTankProcessing.getCapacity() - energyTankProcessing.getAmount();

					need = Math.min(1, need);

					int rate = 20;

					long pop = popFuel(0, need * rate, i -> {},
						energyTankHyleon, inventoryFairyFuel, itemStack -> itemStack != null ? 10000 : 0);

					energyTankProcessing.setAmount(energyTankProcessing.getAmount() + pop / rate);
				}

			}.tick();

		}

	}

	@Override
	public String getDefaultName()
	{
		return "container.miragecrops5.mmfMacerator";
	}

}
