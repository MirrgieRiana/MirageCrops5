package mirrg_miragecrops5.machine;

import mirrg.mir50.tile.inventory.FluidSlot;
import mirrg.mir50.tile.inventory.FluidTank;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir51.inventory.InventoryMir51Chain;
import mirrg.mir51.inventory.InventoryMir51FromInventory;
import mirrg.mir51.inventory.InventoryMir51Trimmer;
import mirrg.mir53.gui.container.ContainerMir53;
import mirrg.mir53.gui.container.SupplierPositionContainerFlow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;

public class TileEntityMachineMirageFairy extends TileEntityMMF
{

	public final IInventoryMir51 inventory;

	public final FluidTank fluidTank;

	public TileEntityMachineMirageFairy()
	{
		inventory = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 8 * 3), "inventory");
		inventoryChain.add(inventory);

		fluidTank = add(new FluidTank(this::markDirty, 16 * 1000), "fluidTank");
	}

	@Override
	protected IInventoryMir51[] getInventoryAccessible(int side)
	{
		return new IInventoryMir51[] {
			inventory,
		};
	}

	@Override
	protected IInventoryMir51[] getInventoryExtract(int side, ItemStack itemStack)
	{
		return getInventoryAccessible(side);
	}

	@Override
	protected IInventoryMir51[] getInventoryInsert(int side, ItemStack itemStack)
	{
		return getInventoryAccessible(side);
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
		InventoryMir51Chain inventoryChest = inventoryChain;
		IInventoryMir51 inventory = new InventoryMir51FromInventory(container.getPlayer().inventory,
			getSupplierPosition());
		InventoryMir51Trimmer inventoryPlayer = new InventoryMir51Trimmer(inventory, 9, 27);
		InventoryMir51Trimmer inventoryHandle = new InventoryMir51Trimmer(inventory, 0, 9);

		container.addInventory(inventoryChest,
			new SupplierPositionContainerFlow(LEFT, TOP_CHEST, SHIFT, SHIFT, 8), false);
		container.addInventory(inventoryPlayer,
			new SupplierPositionContainerFlow(LEFT, TOP_INVENTORY, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryHandle,
			new SupplierPositionContainerFlow(LEFT, TOP_HOLDING, SHIFT, SHIFT, 9), true);

		container.setTransferInventories(inventoryChest, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryPlayer, inventoryChest);
		container.setTransferInventories(inventoryHandle, inventoryChest);

		container.addContainerExtraSlot(new FluidSlot(fluidTank, 152, 16, 16, 52),
			getName(fluidTank));
	}

	@Override
	protected FluidTank[] getFluidTank(ForgeDirection arg0)
	{
		return new FluidTank[] {
			fluidTank
		};
	}

	@Override
	protected FluidTank getFluidTankDrain(ForgeDirection arg0)
	{
		return fluidTank;
	}

	@Override
	protected FluidTank getFluidTankDrain(ForgeDirection arg0, Fluid arg1)
	{
		return fluidTank;
	}

	@Override
	protected FluidTank getFluidTankFill(ForgeDirection arg0, Fluid arg1)
	{
		return fluidTank;
	}

}
