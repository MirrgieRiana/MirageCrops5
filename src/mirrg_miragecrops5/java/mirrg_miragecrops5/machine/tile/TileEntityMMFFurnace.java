package mirrg_miragecrops5.machine.tile;

import java.util.function.IntConsumer;

import mirrg.mir50.datamodels.DatamodelDirection;
import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotDatamodel;
import mirrg.mir51.gui.renderers.RendererEnergySlotProgress;
import mirrg.mir51.gui.renderers.RendererEnergySlotProgress.EnumProgressAlign;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir51.modding.HelpersSide;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.SupplierPositionContainerFlow;
import mirrg_miragecrops5.fairytype.HelpersFairyType;
import mirrg_miragecrops5.machine.container.SlotProcessing;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import api.mirrg.mir50.gui.renderer.IRenderer;

public class TileEntityMMFFurnace extends TileEntityMMFEasy
{

	public final IInventoryMir51 inventoryInMaterial;
	public final IInventoryMir51 inventoryInMaterialProcessing;
	public final IInventoryMir51 inventoryInFuel;

	public final DatamodelEnergy energyTankFuel;

	public final DatamodelDirection direction;

	public TileEntityMMFFurnace()
	{
		inventoryInMaterial = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 2), "inventoryInMaterial");
		inventoryInMaterialProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInMaterialProcessing");
		inventoryInFuel = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 3), "inventoryInFuel");

		inventoryChain.add(inventoryInMaterial);
		inventoryChain.add(inventoryInMaterialProcessing);
		inventoryChain.add(inventoryInFuel);

		energyTankFuel = add(new DatamodelEnergy(this::markDirty, 0), "energyTankFuel");
		direction = add(new DatamodelDirection(this::markDirty, ForgeDirection.EAST.ordinal()), "direction");
	}

	@Override
	public String getDefaultName()
	{
		return "container.miragecrops5.mmfFurnace";
	}

	@Override
	protected int[] getFairyValues()
	{
		return new int[] {
			0, 0, 0, 0, -4, 0,
		};
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

	public final static IRenderer<ContainerExtraSlotDatamodel<DatamodelEnergy>> rendererFuel =
		new RendererEnergySlotProgress("fuel", EnumProgressAlign.DOWN);

	@Override
	protected void prepareContainerSlots(ContainerMir52 container)
	{
		super.prepareContainerSlots(container);

		container.addInventory(inventoryInMaterial,
			new SupplierPositionContainerFlow(LEFT + 9 * 4, TOP_CHEST, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryInMaterialProcessing,
			(inventory, index, x, y) -> new SlotProcessing(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT + 9 * 8, TOP_CHEST, SHIFT, SHIFT, 9), false);
		container.addInventory(inventoryInFuel,
			new SupplierPositionContainerFlow(LEFT + 9 * 4, TOP_CHEST + 9 * 4, SHIFT, SHIFT, 9), true);

		container.setTransferInventories(inventoryInMaterial, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryInFuel, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryPlayer.get(), inventoryFairy, inventoryFairyFuel, inventoryInMaterial, inventoryInFuel);
		container.setTransferInventories(inventoryHandle.get(), inventoryFairy, inventoryFairyFuel, inventoryInMaterial, inventoryInFuel);

		{
			ContainerExtraSlotDatamodel<DatamodelEnergy> containerExtraSlot =
				new ContainerExtraSlotDatamodel<DatamodelEnergy>(
					LEFT + 9 * 9 - 14 / 2, TOP_CHEST + 9 * 3 - 14 / 2, 14, 14, energyTankFuel);
			containerExtraSlot.renderer = rendererFuel;
			container.addContainerExtraSlot(containerExtraSlot, getName(energyTankFuel));
		}
	}

	@Override
	protected void tick()
	{
		if (HelpersSide.helper(this).isRemote()) return;

		ProcessingManager.bringRight(inventoryInMaterial);
		ProcessingManager.bringRight(inventoryInFuel);

		int[] energyTankFuelDecred = new int[1];

		new ProcessingManager(
			energyTankProcessing,
			new IInventoryMir51[] {
				inventoryInMaterialProcessing,
			},
			inventoryOutProcessing,
			inventoryOut) {

			@Override
			protected void tryStartProcess(IntConsumer onStart)
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

							onStart.accept(200 * 1000);
						}
					}

				}
			}

			@Override
			protected void processTick(DatamodelEnergy energyTankProcessing)
			{
				long need = energyTankProcessing.getCapacity() - energyTankProcessing.getAmount();

				need = Math.min(1000, need);

				long pop = popFuel(0, need, amount -> {
					energyTankFuelDecred[0] += amount;
				}, energyTankFuel, inventoryInFuel, itemStack -> TileEntityFurnace.getItemBurnTime(itemStack) * 1000);

				energyTankProcessing.setAmount(energyTankProcessing.getAmount() + pop);
			}

		}.tick();

		long cooldown = Math.min(100 - energyTankFuelDecred[0], energyTankFuel.amount);

		if (cooldown > 0) {
			long pop = 0;
			if (HelpersFairyType.isNotNegative(fairyValues.getIncreaser())) {
				pop = popFairyFuel(0, cooldown);
			}
			cooldown -= pop;

			energyTankFuel.amount -= cooldown;
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
		if (itemStack.hasDisplayName()) setCustomInventoryName(itemStack.getDisplayName());

		int l = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (l == 0) direction.direction = 2;
		else if (l == 1) direction.direction = 5;
		else if (l == 2) direction.direction = 3;
		else if (l == 3) direction.direction = 4;

	}

}
