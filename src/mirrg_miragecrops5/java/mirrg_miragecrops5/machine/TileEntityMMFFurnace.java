package mirrg_miragecrops5.machine;

import mirrg.mir50.tile.inventory.EnergySlot;
import mirrg.mir50.tile.inventory.EnergyTank;
import mirrg.mir51.gui.renderers.RendererEnergySlotMeter;
import mirrg.mir51.inventory.ISimpleInventoryMir51;
import mirrg.mir52.inventories.HelpersSimpleInventoryMir51;
import mirrg.mir52.inventories.SimpleInventoryMir51Base;
import mirrg.mir52.inventories.SimpleInventoryTrimmer;
import mirrg.mir52.tile.ContainerMir53;
import mirrg.mir52.tile.HelpersContainer;
import mirrg.mir52.tile.SupplierPositionFlow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityMMFFurnace extends TileEntityMMF
{

	public final ISimpleInventoryMir51 inventoryInMaterial;
	public final ISimpleInventoryMir51 inventoryInMaterialProcessing;
	public final ISimpleInventoryMir51 inventoryInFuel;
	public final ISimpleInventoryMir51 inventoryOut;
	public final ISimpleInventoryMir51 inventoryOutProcessing;
	public final ISimpleInventoryMir51 inventoryFairy;
	public final ISimpleInventoryMir51 inventoryFairyFuel;

	public final EnergyTank energyTankProcessing;
	public final EnergyTank energyTankFuel;
	public final EnergyTank energyTankHyleon;

	protected int ticker = 0;

	public TileEntityMMFFurnace()
	{
		inventoryInMaterial = add(new SimpleInventoryMir51Base(this, 2), "inventoryInMaterial");
		inventoryInMaterialProcessing = add(new SimpleInventoryMir51Base(this, 1), "inventoryInMaterialProcessing");
		inventoryInFuel = add(new SimpleInventoryMir51Base(this, 3), "inventoryInFuel");
		inventoryOut = add(new SimpleInventoryMir51Base(this, 4), "inventoryOut");
		inventoryOutProcessing = add(new SimpleInventoryMir51Base(this, 4), "inventoryOutProcessing");
		inventoryFairy = add(new SimpleInventoryMir51Base(this, 3), "inventoryFairy");
		inventoryFairyFuel = add(new SimpleInventoryMir51Base(this, 1), "inventoryFairyFuel");

		inventoryChain.add(inventoryInMaterial);
		inventoryChain.add(inventoryInMaterialProcessing);
		inventoryChain.add(inventoryInFuel);
		inventoryChain.add(inventoryOut);
		inventoryChain.add(inventoryOutProcessing);
		inventoryChain.add(inventoryFairy);
		inventoryChain.add(inventoryFairyFuel);

		energyTankProcessing = add(new EnergyTank(this::markDirty, 0), "energyTankProcessing");
		energyTankFuel = add(new EnergyTank(this::markDirty, 0), "energyTankFuel");
		energyTankHyleon = add(new EnergyTank(this::markDirty, 100000), "energyTankHyleon");
	}

	@Override
	protected ISimpleInventoryMir51[] getInventoryAccessible(int side)
	{
		return new ISimpleInventoryMir51[] {
			inventoryOut,
			inventoryInFuel,
			inventoryInMaterial,
		};
	}

	@Override
	protected ISimpleInventoryMir51[] getInventoryExtract(int side, ItemStack itemStack)
	{
		return new ISimpleInventoryMir51[] {
			inventoryOut,
		};
	}

	@Override
	protected ISimpleInventoryMir51[] getInventoryInsert(int side, ItemStack itemStack)
	{
		if (side == ForgeDirection.DOWN.ordinal()) {
			return new ISimpleInventoryMir51[] {
				inventoryInFuel,
			};
		} else {
			return new ISimpleInventoryMir51[] {
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
		ISimpleInventoryMir51 inventory2 = HelpersSimpleInventoryMir51.make(container.getPlayer().inventory, this);
		SimpleInventoryTrimmer inventoryPlayer = new SimpleInventoryTrimmer(this, inventory2, 9, 27);
		SimpleInventoryTrimmer inventoryHandle = new SimpleInventoryTrimmer(this, inventory2, 0, 9);

		container.addInventory(inventoryInMaterial,
			new SupplierPositionFlow(LEFT + 9 * 4, TOP_CHEST, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryInMaterialProcessing,
			(inventory, index, x, y) -> new SlotProcessing(inventory, index, x, y),
			new SupplierPositionFlow(LEFT + 9 * 8, TOP_CHEST, SHIFT, SHIFT, 9), false);
		container.addInventory(inventoryInFuel,
			new SupplierPositionFlow(LEFT + 9 * 4, TOP_CHEST + 9 * 4, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryOut,
			new SupplierPositionFlow(LEFT + 9 * 14, TOP_CHEST + 9 * 1, SHIFT, SHIFT, 2), false);
		container.addInventory(inventoryFairy,
			(inventory, index, x, y) -> new SlotFairy(inventory, index, x, y),
			new SupplierPositionFlow(LEFT, TOP_CHEST, SHIFT, SHIFT, 1), false);
		container.addInventory(inventoryFairyFuel,
			(inventory, index, x, y) -> new SlotFairyFuel(inventory, index, x, y),
			new SupplierPositionFlow(LEFT + 9 * 11, TOP_CHEST + 9 * 4, SHIFT, SHIFT, 1), false);

		container.addInventory(inventoryPlayer,
			new SupplierPositionFlow(LEFT, TOP_INVENTORY, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryHandle,
			new SupplierPositionFlow(LEFT, TOP_HOLDING, SHIFT, SHIFT, 9), true);

		container.setTransferInventories(inventoryInMaterial, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryInFuel, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryOut, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryFairy, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryFairyFuel, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryPlayer, inventoryFairy, inventoryFairyFuel, inventoryInMaterial, inventoryInFuel);
		container.setTransferInventories(inventoryHandle, inventoryFairy, inventoryFairyFuel, inventoryInMaterial, inventoryInFuel);

		{
			int w = 24, h = 17;
			container.addEnergySlot(new EnergySlot(energyTankProcessing, LEFT + 9 * 12 - w / 2, TOP_CHEST + 9 * 2 - h / 2 - 3, w, h));
		}
		{
			int w = 14, h = 14;
			container.addEnergySlot(new EnergySlotWithRenderer(energyTankFuel, LEFT + 9 * 9 - w / 2, TOP_CHEST + 9 * 3 - h / 2, w, h,
				EnergySlotWithRenderer.rendererFuel));
		}
		{
			int w = 0, h = 0;
			container.addEnergySlot(new EnergySlotWithRenderer(energyTankHyleon, LEFT + 9 * 12 - w / 2, TOP_CHEST + 9 * 4 - h / 2 - 3, w, h,
				RendererEnergySlotMeter.instance));
		}

	}

	@Override
	protected void tick()
	{
		//if (HelpersSide.helper(this).isRemote()) return;

		{
			ISimpleInventoryMir51 inventory = inventoryInMaterial;

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
			ISimpleInventoryMir51 inventory = inventoryInFuel;

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
				energyTankProcessing.amount = 0;
				energyTankProcessing.capacity = 200;
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
			energyTankProcessing.amount = 0;
			energyTankProcessing.capacity = 0;
		}

	}

	public boolean isWaiting()
	{
		return energyTankProcessing.capacity <= 0;
	}

	public boolean isProcessing()
	{
		return energyTankProcessing.capacity > 0
			&& energyTankProcessing.amount < energyTankProcessing.capacity;
	}

	public boolean isOutputting()
	{
		return energyTankProcessing.capacity > 0
			&& energyTankProcessing.amount >= energyTankProcessing.capacity
			&& !isEmpty(inventoryOutProcessing);
	}

	public boolean isOutputted()
	{
		return energyTankProcessing.capacity > 0
			&& energyTankProcessing.amount >= energyTankProcessing.capacity
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
		long need = energyTankProcessing.capacity - energyTankProcessing.amount;

		need = Math.min(1, need);

		long pop = popFuel(0, need);

		energyTankProcessing.amount += pop;
	}

	/**
	 * 燃料ゲージからmin～maxの量を抽出する。
	 * maxまで取れない場合、燃料を消費してゲージを増加{@link #tryBurnFuel()}してmaxまで抽出できるようにする。
	 * それでもmaxまで取れない場合、min以上残っているなら全て抽出し、min未満しか残量がない場合、何も抽出しない。
	 */
	protected long popFuel(long min, long max)
	{
		if (energyTankFuel.amount >= max) {
			energyTankFuel.amount -= max;
			return max;
		} else {
			// 燃料が足りない

			if (tryBurnFuel()) {
				return popFuel(min, max);
			} else {
				if (energyTankFuel.amount >= min) {
					long amount = energyTankFuel.amount;
					energyTankFuel.amount -= amount;
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

				energyTankFuel.capacity = energyTankFuel.amount;
				energyTankFuel.amount += fuelValue;
				energyTankFuel.capacity += fuelValue;

				return true;
			}
		}

		return false;
	}

	//

	/**
	 * @return 全てのアイテムがfromから移動した。
	 */
	public static boolean tryMergeInventory(ISimpleInventoryMir51 from, ISimpleInventoryMir51 to, boolean inverse)
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

	public static ItemStack getLastStack(ISimpleInventoryMir51 inventory)
	{
		return inventory.getInventoryCell(inventory.getSizeInventory() - 1).getStackInSlot();
	}

	public static void clearInventory(ISimpleInventoryMir51 inventory)
	{
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			inventory.getInventoryCell(i).setInventorySlotContents(null);
		}
	}

	public static boolean isEmpty(ISimpleInventoryMir51 inventory)
	{
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getInventoryCell(i).getStackInSlot() != null) return false;
		}
		return true;
	}
}
