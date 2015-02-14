package mirrg_miragecrops5.machine;

import mirrg.mir50.tile.inventory.FluidSlot;
import mirrg.mir50.tile.inventory.FluidTank;
import mirrg.mir50.tile.inventory.Inventory;
import mirrg.mir50.tile.inventory.InventoryChain;
import mirrg.mir50.tile.inventory.InventoryTrimmer;
import mirrg.mir52.tile.ContainerMir53;
import mirrg.mir52.tile.SupplierPositionFlow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;

public class TileEntityMachineMirageFairy extends TileEntityMMF
{

	public final Inventory inventory;

	public final FluidTank fluidTank;

	public TileEntityMachineMirageFairy()
	{
		inventory = add(new Inventory(this, 8 * 3), "inventory");
		inventoryChain.add(inventory);

		fluidTank = add(new FluidTank(this, 16 * 1000), "fluidTank");
	}

	@Override
	protected Inventory[] getInventoryAccessible(int side)
	{
		return new Inventory[] {
			inventory,
		};
	}

	@Override
	protected Inventory[] getInventoryExtract(int side, ItemStack itemStack)
	{
		return getInventoryAccessible(side);
	}

	@Override
	protected Inventory[] getInventoryInsert(int side, ItemStack itemStack)
	{
		return getInventoryAccessible(side);
	}

	@Override
	protected ResourceLocation getGuiTexture(ContainerMir53 container)
	{
		return new ResourceLocation("miragecrops5" + ":" + "textures/gui/machineMirageFairy.png");
	}

	@Override
	protected boolean hasGui()
	{
		return true;
	}

	@Override
	protected void prepareContainerSlots(ContainerMir53 container)
	{
		InventoryChain inventoryChest = inventoryChain;
		InventoryTrimmer inventoryPlayer = new InventoryTrimmer(container.getPlayer().inventory, 9, 27);
		InventoryTrimmer inventoryHandle = new InventoryTrimmer(container.getPlayer().inventory, 0, 9);

		container.addInventory(inventoryChest, new SupplierPositionFlow(LEFT, TOP_CHEST, SHIFT, SHIFT, 8), false);
		container.addInventory(inventoryPlayer, new SupplierPositionFlow(LEFT, TOP_INVENTORY, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryHandle, new SupplierPositionFlow(LEFT, TOP_HOLDING, SHIFT, SHIFT, 9), true);

		container.setTransferInventories(inventoryChest, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryPlayer, inventoryChest);
		container.setTransferInventories(inventoryHandle, inventoryChest);

		container.addFluidSlot(new FluidSlot(fluidTank, 152, 16, 16, 52));
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
