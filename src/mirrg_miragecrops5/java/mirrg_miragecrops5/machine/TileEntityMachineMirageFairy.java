package mirrg_miragecrops5.machine;

import mirrg.mir50.inventory.HelpersSimpleInventory;
import mirrg.mir50.tile.inventory.FluidSlot;
import mirrg.mir50.tile.inventory.FluidTank;
import mirrg.mir51.inventory.ISimpleInventoryMir51;
import mirrg.mir52.inventories.HelpersSimpleInventoryMir51;
import mirrg.mir52.inventories.SimpleInventoryChain;
import mirrg.mir52.inventories.SimpleInventoryMir51;
import mirrg.mir52.inventories.SimpleInventoryTrimmer;
import mirrg.mir52.tile.ContainerMir53;
import mirrg.mir52.tile.SupplierPositionFlow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;

public class TileEntityMachineMirageFairy extends TileEntityMMF
{

	public final ISimpleInventoryMir51 inventory;

	public final FluidTank fluidTank;

	public TileEntityMachineMirageFairy()
	{
		inventory = add(new SimpleInventoryMir51(8 * 3, this), "inventory");
		inventoryChain.add(inventory);

		fluidTank = add(new FluidTank(this::markDirty, 16 * 1000), "fluidTank");
	}

	@Override
	protected ISimpleInventoryMir51[] getInventoryAccessible(int side)
	{
		return new ISimpleInventoryMir51[] {
			inventory,
		};
	}

	@Override
	protected ISimpleInventoryMir51[] getInventoryExtract(int side, ItemStack itemStack)
	{
		return getInventoryAccessible(side);
	}

	@Override
	protected ISimpleInventoryMir51[] getInventoryInsert(int side, ItemStack itemStack)
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
		SimpleInventoryChain inventoryChest = inventoryChain;
		ISimpleInventoryMir51 inventory = HelpersSimpleInventoryMir51.make(container.getPlayer().inventory, this);
		SimpleInventoryTrimmer inventoryPlayer = new SimpleInventoryTrimmer(this, inventory, 9, 27);
		SimpleInventoryTrimmer inventoryHandle = new SimpleInventoryTrimmer(this, inventory, 0, 9);

		container.addInventory(HelpersSimpleInventory.unmake(inventoryChest),
			new SupplierPositionFlow(LEFT, TOP_CHEST, SHIFT, SHIFT, 8), false);
		container.addInventory(HelpersSimpleInventory.unmake(inventoryPlayer),
			new SupplierPositionFlow(LEFT, TOP_INVENTORY, SHIFT, SHIFT, 9), true);
		container.addInventory(HelpersSimpleInventory.unmake(inventoryHandle),
			new SupplierPositionFlow(LEFT, TOP_HOLDING, SHIFT, SHIFT, 9), true);

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
