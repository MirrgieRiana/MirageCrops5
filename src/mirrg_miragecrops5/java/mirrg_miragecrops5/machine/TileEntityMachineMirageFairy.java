package mirrg_miragecrops5.machine;

import mirrg.mir50.tile.inventory.FluidSlot;
import mirrg.mir50.tile.inventory.FluidTank;
import mirrg.mir50.tile.inventory.Inventory;
import mirrg.mir50.tile.inventory.InventoryChain;
import mirrg.mir50.tile.inventory.InventoryTrimmer;
import mirrg.mir52.tile.ContainerMir53;
import mirrg.mir52.tile.TileEntityMir53Connected;
import mirrg_miragecrops5.ModMirageCrops;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;

public class TileEntityMachineMirageFairy extends TileEntityMir53Connected
{

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float x2, float y2, float z2)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null) return false;

		if (true) {
			if (!world.isRemote) {
				player.openGui(ModMirageCrops.instance, ModuleMachine.loaderGuiHandler.guiId, world, x, y, z);
			}
			return true;
		}

		return false;
	}

	//////////////////////////////////////////

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

		container.addInventory(inventoryChest, id -> 8 + (id % 8) * 18, id -> 16 + (id / 8) * 18, false);
		container.addInventory(inventoryPlayer, id -> 8 + (id % 9) * 18, id -> 84 + (id / 9) * 18, true);
		container.addInventory(inventoryHandle, id -> 8 + (id % 9) * 18, id -> 142, true);

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
