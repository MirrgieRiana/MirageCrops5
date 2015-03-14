package mirrg_miragecrops5.machine.tile;

import java.util.function.Predicate;

import mirrg.mir50.datamodels.DatamodelDirection;
import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.datamodels.DatamodelFluid;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotDatamodel;
import mirrg.mir51.gui.renderers.RendererEnergySlotProgress;
import mirrg.mir51.gui.renderers.RendererEnergySlotProgress.EnumProgressAlign;
import mirrg.mir51.gui.renderers.RendererFluidSlot;
import mirrg.mir51.inventory.IInventoryCellMir51;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir51.modding.HelpersSide;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.SupplierPositionContainerFlow;
import mirrg_miragecrops5.fairytype.HelpersFairyType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import api.mirrg.mir50.gui.renderer.IRenderer;
import api.mirrg_miragecrops5.recipes.APIRegistryRecipe;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IMatcherRecipeFuel;

public class TileEntityMMFUrineMaker extends TileEntityMMFEasy implements ITileEntityHasDirection
{

	public final IInventoryMir51 inventoryInFood;

	public final DatamodelFluid fluidIn;
	public final IInventoryMir51 inventoryInhatchFluidIn;
	public final IInventoryMir51 inventoryOuthatchFluidIn;
	public final IInventoryMir51 inventoryOutFluidIn;

	public final DatamodelFluid fluidOut;
	public final IInventoryMir51 inventoryInhatchFluidOut;
	public final IInventoryMir51 inventoryOuthatchFluidOut;
	public final IInventoryMir51 inventoryOutFluidOut;

	public final DatamodelEnergy energyFood;

	public final DatamodelDirection direction;

	@Override
	public int getDirection()
	{
		return direction.direction;
	}

	public TileEntityMMFUrineMaker()
	{
		inventoryInFood = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 3), "inventoryInFood");

		fluidIn = add(new DatamodelFluid(this::markDirty, 16000), "fluidIn");
		inventoryInhatchFluidIn = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 2), "inventoryInhatchFluidIn");
		inventoryOuthatchFluidIn = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryOuthatchFluidIn");
		inventoryOutFluidIn = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryOutFluidIn");
		inventoryChain.add(inventoryInhatchFluidIn);
		inventoryChain.add(inventoryOuthatchFluidIn);
		inventoryChain.add(inventoryOutFluidIn);

		fluidOut = add(new DatamodelFluid(this::markDirty, 16000), "fluidOut");
		inventoryInhatchFluidOut = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 2), "inventoryInhatchFluidOut");
		inventoryOuthatchFluidOut = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryOuthatchFluidOut");
		inventoryOutFluidOut = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryOutFluidOut");
		inventoryChain.add(inventoryInhatchFluidOut);
		inventoryChain.add(inventoryOuthatchFluidOut);
		inventoryChain.add(inventoryOutFluidOut);

		inventoryChain.add(inventoryInFood);

		energyFood = add(new DatamodelEnergy(this::markDirty, 100000), "energyFood");

		direction = add(new DatamodelDirection(this::markDirty, ForgeDirection.EAST.ordinal()), "direction");
	}

	@Override
	public String getDefaultName()
	{
		return "container.miragecrops5.mmfUrineMaker";
	}

	@Override
	protected int[] getFairyValues()
	{
		return new int[] {
			0, 0, -9, 0, 0, 0,
		};
	}

	@Override
	protected IInventoryMir51[] getInventoryAccessible(int side)
	{
		return new IInventoryMir51[] {
			inventoryInFood,
		};
	}

	@Override
	protected IInventoryMir51[] getInventoryInsert(int side, ItemStack itemStack)
	{
		return new IInventoryMir51[] {
			inventoryInFood,
		};
	}

	public final static IRenderer<ContainerExtraSlotDatamodel<DatamodelEnergy>> rendererFuel =
		new RendererEnergySlotProgress("fuel", EnumProgressAlign.DOWN);

	@Override
	protected void prepareContainerSlots(ContainerMir52 container)
	{
		super.prepareContainerSlots(container);

		container.addInventory(inventoryInFood,
			new SupplierPositionContainerFlow(LEFT + 9 * 8, TOP_CHEST + 9 * 0, SHIFT, SHIFT, 1), true);

		{
			ContainerExtraSlotDatamodel<DatamodelFluid> containerExtraSlot =
				new ContainerExtraSlotDatamodel<DatamodelFluid>(LEFT + 9 * 6, TOP_CHEST + 9 * 0, SHIFT - 2, SHIFT * 3 - 2, fluidIn);
			containerExtraSlot.renderer = RendererFluidSlot.instance;
			container.addContainerExtraSlot(containerExtraSlot, getName(fluidIn));
		}
		container.addInventory(inventoryInhatchFluidIn,
			new SupplierPositionContainerFlow(LEFT + 9 * 4, TOP_CHEST + 9 * 0, SHIFT, SHIFT, 1), true);
		container.addInventory(inventoryOutFluidIn,
			new SupplierPositionContainerFlow(LEFT + 9 * 4, TOP_CHEST + 9 * 4, SHIFT, SHIFT, 9), false);

		{
			ContainerExtraSlotDatamodel<DatamodelFluid> containerExtraSlot =
				new ContainerExtraSlotDatamodel<DatamodelFluid>(LEFT + 9 * 14, TOP_CHEST + 9 * 0, SHIFT - 2, SHIFT * 3 - 2, fluidOut);
			containerExtraSlot.renderer = RendererFluidSlot.instance;
			container.addContainerExtraSlot(containerExtraSlot, getName(fluidOut));
		}
		container.addInventory(inventoryInhatchFluidOut,
			new SupplierPositionContainerFlow(LEFT + 9 * 16, TOP_CHEST + 9 * 0, SHIFT, SHIFT, 1), true);
		container.addInventory(inventoryOutFluidOut,
			new SupplierPositionContainerFlow(LEFT + 9 * 16, TOP_CHEST + 9 * 4, SHIFT, SHIFT, 9), false);

		container.setTransferInventories(inventoryInFood, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryInhatchFluidIn, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryOutFluidIn, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryInhatchFluidOut, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryOutFluidOut, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryPlayer.get(), inventoryFairy, inventoryFairyFuel, inventoryInFood, inventoryInhatchFluidIn, inventoryInhatchFluidOut);
		container.setTransferInventories(inventoryHandle.get(), inventoryFairy, inventoryFairyFuel, inventoryInFood, inventoryInhatchFluidIn, inventoryInhatchFluidOut);

		{
			ContainerExtraSlotDatamodel<DatamodelEnergy> containerExtraSlot =
				new ContainerExtraSlotDatamodel<DatamodelEnergy>(
					LEFT + 9 * 12 - 14 / 2, TOP_CHEST + 9 * 1 - 14 / 2, 14, 14, energyFood);
			containerExtraSlot.renderer = rendererFuel;
			container.addContainerExtraSlot(containerExtraSlot, getName(energyFood));
		}
	}

	@Override
	protected DatamodelFluid[] getFluidTank(ForgeDirection arg0)
	{
		return new DatamodelFluid[] {
			fluidIn,
			fluidOut,
		};
	}

	@Override
	protected DatamodelFluid getFluidTankDrain(ForgeDirection arg0)
	{
		return fluidOut;
	}

	@Override
	protected DatamodelFluid getFluidTankDrain(ForgeDirection arg0, Fluid arg1)
	{
		return fluidOut;
	}

	@Override
	protected DatamodelFluid getFluidTankFill(ForgeDirection arg0, Fluid arg1)
	{
		return fluidIn;
	}

	protected static void tryTransferFluid(DatamodelFluid tank, Predicate<Fluid> predicateIsFluidFillable,
		IInventoryCellMir51 inhatch, IInventoryCellMir51 outhatch, boolean fillable, boolean drainable)
	{
		if (!outhatch.isEmpty()) return;

		ItemStack in = inhatch.getStackInSlot();
		if (in == null) return;

		if (FluidContainerRegistry.isEmptyContainer(in)) {
			// drain
			if (!drainable) return;

			if (tank.isEmpty()) return;
			int capacity = FluidContainerRegistry.getContainerCapacity(tank.fluidStack, in);
			if (capacity == 0) return;

			FluidStack drained = tank.drain(capacity, false);
			if (drained == null) return;
			if (capacity == drained.amount) {
				tank.drain(capacity, true);
				ItemStack split = inhatch.decrStackSize(1);
				outhatch.setInventorySlotContents(
					FluidContainerRegistry.fillFluidContainer(new FluidStack(tank.fluidStack.getFluid(), capacity), split));
			}

		} else if (FluidContainerRegistry.isFilledContainer(in)) {
			// fill
			if (!fillable) return;

			FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(in);
			if (fluidStack == null) return;
			if (fluidStack.getFluid() == null) return;
			if (fluidStack.amount == 0) return;
			if (!predicateIsFluidFillable.test(fluidStack.getFluid())) return;

			if (fluidStack.amount == tank.fill(fluidStack, false)) {
				tank.fill(fluidStack, true);
				ItemStack split = inhatch.decrStackSize(1);
				outhatch.setInventorySlotContents(FluidContainerRegistry.drainFluidContainer(split));
			}

		}

	}

	private static void extracted(DatamodelFluid tank, Predicate<Fluid> predicateIsFluidFillable,
		IInventoryMir51 inventoryInhatch, IInventoryMir51 inventoryOuthatch, IInventoryMir51 inventoryOut,
		boolean fillable, boolean drainable)
	{
		ProcessingManager.bringRight(inventoryInhatch);
		tryTransferFluid(tank, predicateIsFluidFillable,
			inventoryInhatch.getLastInventoryCell(),
			inventoryOuthatch.getLastInventoryCell(),
			fillable, drainable);
		ProcessingManager.tryMergeInventory(inventoryOuthatch, inventoryOut, true);
	}

	@Override
	protected void tick()
	{
		if (HelpersSide.helper(this).isRemote()) return;

		ProcessingManager.bringRight(inventoryInFood);

		if (HelpersFairyType.isNotNegative(fairyValues.getIncreaser())) {

			extracted(fluidIn, fluid -> fluid == FluidRegistry.getFluid("water"),
				inventoryInhatchFluidIn, inventoryOuthatchFluidIn, inventoryOutFluidIn, true, false);
			extracted(fluidOut, fluid -> true,
				inventoryInhatchFluidOut, inventoryOuthatchFluidOut, inventoryOutFluidOut, false, true);

			{
				int cost = 1;
				int product = 1;

				FluidStack drain = fluidIn.drain(new FluidStack(FluidRegistry.getFluid("water"), cost), false);
				if (drain != null && drain.amount == cost) {
					if (fluidOut.fill(new FluidStack(FluidRegistry.getFluid("urine"), product), false) == product) {

						int popFood = (int) ProcessingManager.popFuel(2, 2, null, energyFood, inventoryInFood,
							itemStack -> APIRegistryRecipe.registryRecipeFoodValue.matcher(itemStack).map(IMatcherRecipeFuel::getOutput).orElse(0) * 200);

						int pop = (int) popFairyFuel(2, 2);

						if (pop == 2 && popFood == 2) {
							fluidIn.drain(new FluidStack(FluidRegistry.getFluid("water"), cost), true);
							fluidOut.fill(new FluidStack(FluidRegistry.getFluid("urine"), product), true);
						} else {
							energyFood.amount += popFood;
							energyTankHyleon.amount += pop;
						}

					}
				}
			}

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
