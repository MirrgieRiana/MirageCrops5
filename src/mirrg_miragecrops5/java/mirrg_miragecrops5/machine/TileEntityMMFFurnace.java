package mirrg_miragecrops5.machine;

import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.gui.container.HelpersContainer;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotDatamodel;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotLabel;
import mirrg.mir50.world.pointer.SupplierPositionWorldFromTileEntity;
import mirrg.mir51.gui.renderers.RendererEnergySlotMeter;
import mirrg.mir51.gui.renderers.RendererEnergySlotProgress;
import mirrg.mir51.gui.renderers.RendererEnergySlotProgress.EnumProgressAlign;
import mirrg.mir51.gui.renderers.RendererLabel;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir51.inventory.InventoryMir51Chain;
import mirrg.mir51.inventory.InventoryMir51FromInventory;
import mirrg.mir51.inventory.InventoryMir51Trimmer;
import mirrg.mir51.modding.HelpersSide;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.SupplierPositionContainerFlow;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import api.mirrg.mir50.gui.renderer.EnumTextAlign;
import api.mirrg.mir50.gui.renderer.IRenderer;

public class TileEntityMMFFurnace extends TileEntityMMF
{

	public final IInventoryMir51 inventoryInMaterial;
	public final IInventoryMir51 inventoryInMaterialProcessing;
	public final IInventoryMir51 inventoryInFuel;
	public final IInventoryMir51 inventoryOut;
	public final IInventoryMir51 inventoryOutProcessing;
	public final IInventoryMir51 inventoryFairy;
	public final IInventoryMir51 inventoryFairyFuel;

	public final DatamodelEnergy energyTankProcessing;
	public final DatamodelEnergy energyTankFuel;
	public final DatamodelEnergy energyTankHyleon;

	protected int ticker = 0;

	public TileEntityMMFFurnace()
	{
		inventoryInMaterial = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 2), "inventoryInMaterial");
		inventoryInMaterialProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInMaterialProcessing");
		inventoryInFuel = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 3), "inventoryInFuel");
		inventoryOut = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 4), "inventoryOut");
		inventoryOutProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 4), "inventoryOutProcessing");
		inventoryFairy = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 3), "inventoryFairy");
		inventoryFairyFuel = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryFairyFuel");

		inventoryChain.add(inventoryInMaterial);
		inventoryChain.add(inventoryInMaterialProcessing);
		inventoryChain.add(inventoryInFuel);
		inventoryChain.add(inventoryOut);
		inventoryChain.add(inventoryOutProcessing);
		inventoryChain.add(inventoryFairy);
		inventoryChain.add(inventoryFairyFuel);

		energyTankProcessing = add(new DatamodelEnergy(this::markDirty, 0), "energyTankProcessing");
		energyTankFuel = add(new DatamodelEnergy(this::markDirty, 0), "energyTankFuel");
		energyTankHyleon = add(new DatamodelEnergy(this::markDirty, 100000), "energyTankHyleon");
	}

	@Override
	protected IInventoryMir51[] getInventoryAccessible(int side)
	{
		return new IInventoryMir51[] {
			inventoryOut,
			inventoryInFuel,
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
		if (side == ForgeDirection.DOWN.ordinal()) {
			return new IInventoryMir51[] {
				inventoryInFuel,
			};
		} else {
			return new IInventoryMir51[] {
				inventoryInMaterial,
			};
		}
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

	public final static IRenderer<ContainerExtraSlotDatamodel<DatamodelEnergy>> rendererFuel =
		new RendererEnergySlotProgress("fuel", EnumProgressAlign.DOWN);

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
				LEFT + 9 * 2, TOP_CHEST, 9 * 2, 9 * 6, inventoryFairy);
			containerExtraSlot.values[1] = -1;
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
		container.addInventory(inventoryInFuel,
			new SupplierPositionContainerFlow(LEFT + 9 * 4, TOP_CHEST + 9 * 4, SHIFT, SHIFT, 9), true);
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
		container.setTransferInventories(inventoryInFuel, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryOut, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryFairy, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryFairyFuel, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryPlayer, inventoryFairy, inventoryFairyFuel, inventoryInMaterial, inventoryInFuel);
		container.setTransferInventories(inventoryHandle, inventoryFairy, inventoryFairyFuel, inventoryInMaterial, inventoryInFuel);

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
					LEFT + 9 * 9 - 14 / 2, TOP_CHEST + 9 * 3 - 14 / 2, 14, 14, energyTankFuel);
			containerExtraSlot.renderer = rendererFuel;
			container.addContainerExtraSlot(containerExtraSlot, getName(energyTankFuel));
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

		{
			IInventoryMir51 inventory = inventoryInMaterial;

			for (int i = inventory.getSizeInventory() - 1; i >= 1; i--) {
				ItemStack right = inventory.getInventoryCell(i).getStackInSlot();
				ItemStack left = inventory.getInventoryCell(i - 1).getStackInSlot();
				if (right == null) {
					if (left != null) {
						inventory.getInventoryCell(i - 1).setInventorySlotContents(null);
						inventory.getInventoryCell(i).setInventorySlotContents(left);
						//inventory.markDirty();
						break;
					}
				}
			}
		}

		{
			IInventoryMir51 inventory = inventoryInFuel;

			for (int i = inventory.getSizeInventory() - 1; i >= 1; i--) {
				ItemStack right = inventory.getInventoryCell(i).getStackInSlot();
				ItemStack left = inventory.getInventoryCell(i - 1).getStackInSlot();
				if (right == null) {
					if (left != null) {
						inventory.getInventoryCell(i - 1).setInventorySlotContents(null);
						inventory.getInventoryCell(i).setInventorySlotContents(left);
						//inventory.markDirty();
						break;
					}
				}
			}
		}

		/*
		if (isProcessing()) {

		} else {
			tryStartProcess();
		}

		if (energyTankFuel.amount > 0) {
			energyTankFuel.amount--;
			energyTankFuel.setDirty();

			energyTankProcessing.amount++;
			if (energyTankProcessing.amount >= energyTankProcessing.capacity) {
				energyTankProcessing.amount = 0;
				energyTankProcessing.setDirty();

				// slot reset
				clearInventory(inventoryInMaterialProcessing);

				// output
				//inventoryOut.;
				HelpersContainer.mergeItemStack(inventorySlots, stack, start, end, true);

			}

		}

		{
			ItemStack itemStack = inventoryInMaterial.getStackInSlot(inventoryInMaterial.getSizeInventory() - 1);
			if (itemStack != null) {

				ItemStack smeltingResult = FurnaceRecipes.smelting().getSmeltingResult(itemStack);
				if (smeltingResult != null) {
					ItemStack copy = itemStack.copy();
					inventoryInMaterial.decrStackSize(inventoryInMaterial.getSizeInventory() - 1, 1);
					inventoryInMaterialProcessing.setInventorySlotContents(0, copy);
				}

				//GameRegistry.addSmelting(itemStack);
				//FurnaceRecipes.smelting();

			}
		}

		if (energyTankFuel.amount == 0) {

			ItemStack itemStack = inventoryInFuel.getStackInSlot(inventoryInFuel.getSizeInventory() - 1);

			if (itemStack != null) {
				int fuelValue = TileEntityFurnace.getItemBurnTime(itemStack);
				if (fuelValue > 0) {

					inventoryInFuel.decrStackSize(inventoryInFuel.getSizeInventory() - 1, 1);

					energyTankFuel.amount += fuelValue;
					energyTankFuel.capacity = fuelValue;
				}
			}

		}
		*/

		if (isWaiting()) {
			tryStartProcess(() -> {
				energyTankProcessing.setAmount(0);
				energyTankProcessing.setCapacity(200);
			});
		}

		if (isProcessing()) {
			processTick();
		}

		if (isOutputting()) {
			clearInventory(inventoryInMaterialProcessing);
			tryMergeInventory(inventoryOutProcessing, inventoryOut, true);
		}

		if (isOutputted()) {
			energyTankProcessing.setAmount(0);
			energyTankProcessing.setCapacity(0);
		}

	}

	public boolean isWaiting()
	{
		return energyTankProcessing.getCapacity() <= 0;
	}

	public boolean isProcessing()
	{
		return energyTankProcessing.getCapacity() > 0
			&& energyTankProcessing.getAmount() < energyTankProcessing.getCapacity();
	}

	public boolean isOutputting()
	{
		return energyTankProcessing.getCapacity() > 0
			&& energyTankProcessing.getAmount() >= energyTankProcessing.getCapacity()
			&& !isEmpty(inventoryOutProcessing);
	}

	public boolean isOutputted()
	{
		return energyTankProcessing.getCapacity() > 0
			&& energyTankProcessing.getAmount() >= energyTankProcessing.getCapacity()
			&& isEmpty(inventoryOutProcessing);
	}

	/**
	 * 実行ステート：waiting
	 */
	protected void tryStartProcess(Runnable onStart)
	{
		ItemStack itemStack = getLastStack(inventoryInMaterial);
		if (itemStack != null) {
			ItemStack smeltingResult = FurnaceRecipes.smelting().getSmeltingResult(itemStack);
			int cost = 1;

			if (smeltingResult != null) {
				if (itemStack.stackSize >= cost) {
					// startable

					// 素材を必要数取り出して進行中スロットに設置する
					ItemStack copy = itemStack.copy();
					inventoryInMaterial.getInventoryCell(inventoryInMaterial.getSizeInventory() - 1).decrStackSize(cost);
					copy.stackSize = cost;
					inventoryInMaterialProcessing.getInventoryCell(0).setInventorySlotContents(copy);

					// 出力を一時バッファに設置する
					inventoryOutProcessing.getInventoryCell(0).setInventorySlotContents(smeltingResult.copy());

					onStart.run();
				}
			}

		}
	}

	/**
	 * 実行ステート：isProcessing
	 */
	protected void processTick()
	{
		long need = energyTankProcessing.getCapacity() - energyTankProcessing.getAmount();

		need = Math.min(1, need);

		long pop = popFuel(0, need);

		energyTankProcessing.setAmount(energyTankProcessing.getAmount() + pop);
	}

	/**
	 * 燃料ゲージからmin～maxの量を抽出する。
	 * maxまで取れない場合、燃料を消費してゲージを増加{@link #tryBurnFuel()}してmaxまで抽出できるようにする。
	 * それでもmaxまで取れない場合、min以上残っているなら全て抽出し、min未満しか残量がない場合、何も抽出しない。
	 */
	protected long popFuel(long min, long max)
	{
		if (energyTankFuel.getAmount() >= max) {
			energyTankFuel.setAmount(energyTankFuel.getAmount() - max);
			return max;
		} else {
			// 燃料が足りない

			if (tryBurnFuel()) {
				return popFuel(min, max);
			} else {
				if (energyTankFuel.getAmount() >= min) {
					long amount = energyTankFuel.getAmount();
					energyTankFuel.setAmount(energyTankFuel.getAmount() - amount);
					return amount;
				} else {
					// 燃料がちっとも足りない

					return 0;
				}
			}
		}
	}

	/**
	 * 燃料スロットの末尾のアイテムが燃料なら、燃料を1個消費して燃料ゲージを増加させる。
	 *
	 * @return 燃料ゲージの増加が行われた場合。
	 */
	protected boolean tryBurnFuel()
	{
		ItemStack itemStack = getLastStack(inventoryInFuel);

		if (itemStack != null) {
			int fuelValue = TileEntityFurnace.getItemBurnTime(itemStack);
			if (fuelValue > 0) {

				inventoryInFuel.getInventoryCell(inventoryInFuel.getSizeInventory() - 1).decrStackSize(1);

				energyTankFuel.setCapacity(energyTankFuel.getAmount());
				energyTankFuel.setAmount(energyTankFuel.getAmount() + fuelValue);
				energyTankFuel.setCapacity(energyTankFuel.getCapacity() + fuelValue);

				return true;
			}
		}

		return false;
	}

	//

	/**
	 * @return 全てのアイテムがfromから移動した。
	 */
	public static boolean tryMergeInventory(IInventoryMir51 from, IInventoryMir51 to, boolean inverse)
	{
		for (int i = 0; i < from.getSizeInventory(); i++) {
			ItemStack itemStack = from.getInventoryCell(i).getStackInSlot();
			if (itemStack != null) {

				HelpersContainer.mergeItemStackIntoInventory(
					i2 -> () -> to.getInventoryCell(i2).getStackInSlot(),
					i2 -> s -> to.getInventoryCell(i2).setInventorySlotContents(s),
					i2 -> s -> to.getInventoryCell(i2).isItemValidForSlot(s),
					i2 -> to::getInventoryStackLimit,
					i2 -> to::markDirty,
					itemStack, 0, to.getSizeInventory(), inverse);

				if (itemStack.stackSize == 0) {
					from.getInventoryCell(i).setInventorySlotContents(null);
					//from.markDirty();
				} else {
					return false;
				}

			}
		}

		return true;
	}

	public static ItemStack getLastStack(IInventoryMir51 inventory)
	{
		return inventory.getInventoryCell(inventory.getSizeInventory() - 1).getStackInSlot();
	}

	public static void clearInventory(IInventoryMir51 inventory)
	{
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			inventory.getInventoryCell(i).setInventorySlotContents(null);
		}
	}

	public static boolean isEmpty(IInventoryMir51 inventory)
	{
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getInventoryCell(i).getStackInSlot() != null) return false;
		}
		return true;
	}

	@Override
	public String getDefaultName()
	{
		return "container.miragecrops5.mmfFurnace";
	}

}