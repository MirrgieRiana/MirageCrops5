package mirrg.mir52.tile;

import java.util.ArrayList;

import mirrg.h.struct.Tuple;
import mirrg.mir50.tile.inventory.EnergyTank;
import mirrg.mir50.tile.inventory.FluidTank;
import mirrg.mir51.inventory.ISimpleInventoryMir51;
import mirrg.mir52.inventories.SimpleInventoryChain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import api.mirrg.mir50.net.NBTTypes;
import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile.PipeType;

public class TileEntityMir53Connected extends TileEntityMir53 implements IPipeConnection, IFluidHandler, ISidedInventory
{

	@Override
	public void writeToNBT(NBTTagCompound p_145841_1_)
	{
		super.writeToNBT(p_145841_1_);

		for (Tuple<ISimpleInventoryMir51, String> entry : inventories) {

			NBTTagCompound tag = new NBTTagCompound();
			entry.getX().writeToNBT(tag);
			p_145841_1_.setTag(entry.getY(), tag);

		}

		for (Tuple<FluidTank, String> entry : fluidTanks) {

			NBTTagCompound tag = new NBTTagCompound();
			entry.getX().writeToNBT(tag);
			p_145841_1_.setTag(entry.getY(), tag);

		}

		for (Tuple<EnergyTank, String> entry : energyTanks) {

			NBTTagCompound tag = new NBTTagCompound();
			entry.getX().writeToNBT(tag);
			p_145841_1_.setTag(entry.getY(), tag);

		}

	}

	@Override
	public void readFromNBT(NBTTagCompound p_145839_1_)
	{
		super.readFromNBT(p_145839_1_);

		for (Tuple<ISimpleInventoryMir51, String> entry : inventories) {

			if (p_145839_1_.hasKey(entry.getY(), NBTTypes.COMPOUND)) {
				NBTTagCompound tag = p_145839_1_.getCompoundTag(entry.getY());
				entry.getX().readFromNBT(tag);
			} else {
				for (int i = 0; i < entry.getX().getSizeInventory(); i++) {
					entry.getX().getInventoryCell(i).clearInventory();
				}
			}

		}

		for (Tuple<FluidTank, String> entry : fluidTanks) {

			if (p_145839_1_.hasKey(entry.getY(), NBTTypes.COMPOUND)) {
				NBTTagCompound tag = p_145839_1_.getCompoundTag(entry.getY());
				entry.getX().readFromNBT(tag);
			} else {
				entry.getX().fluidStack = null;
			}

		}

		for (Tuple<EnergyTank, String> entry : energyTanks) {

			if (p_145839_1_.hasKey(entry.getY(), NBTTypes.COMPOUND)) {
				NBTTagCompound tag = p_145839_1_.getCompoundTag(entry.getY());
				entry.getX().readFromNBT(tag);
			} else {
				entry.getX().amount = 0;
				entry.getX().capacity = entry.getX().getDefaultCapacity();
			}

		}

	}

	////////////////////////////// IPipeConnection //////////////////////////////

	@Override
	public ConnectOverride overridePipeConnection(PipeType type, ForgeDirection with)
	{
		return ConnectOverride.DEFAULT;
	}

	////////////////////////////// IFluidHandler //////////////////////////////

	protected ArrayList<Tuple<FluidTank, String>> fluidTanks = new ArrayList<Tuple<FluidTank, String>>();

	protected <T extends FluidTank> T add(T fluidTank, String tagName)
	{
		fluidTanks.add(new Tuple<FluidTank, String>(fluidTank, tagName));
		return fluidTank;
	}

	protected FluidTank getFluidTankFill(ForgeDirection arg0, Fluid arg1)
	{
		return null;
	}

	protected FluidTank getFluidTankDrain(ForgeDirection arg0, Fluid arg1)
	{
		return null;
	}

	protected FluidTank getFluidTankDrain(ForgeDirection arg0)
	{
		return null;
	}

	protected FluidTank[] getFluidTank(ForgeDirection arg0)
	{
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection arg0, Fluid arg1)
	{
		FluidTank fluidTank = getFluidTankFill(arg0, arg1);
		if (fluidTank == null) return false;
		return fluidTank.canFill(arg1);
	}

	@Override
	public int fill(ForgeDirection arg0, FluidStack arg1, boolean arg2)
	{
		FluidTank fluidTank = getFluidTankFill(arg0, arg1.getFluid());
		if (fluidTank == null) return 0;
		return fluidTank.fill(arg1, arg2);
	}

	@Override
	public boolean canDrain(ForgeDirection arg0, Fluid arg1)
	{
		FluidTank fluidTank = getFluidTankDrain(arg0, arg1);
		if (fluidTank == null) return false;
		return fluidTank.canDrain(arg1);
	}

	@Override
	public FluidStack drain(ForgeDirection arg0, FluidStack arg1, boolean arg2)
	{
		FluidTank fluidTank = getFluidTankDrain(arg0, arg1.getFluid());
		if (fluidTank == null) return null;
		return fluidTank.drain(arg1, arg2);
	}

	@Override
	public FluidStack drain(ForgeDirection arg0, int arg1, boolean arg2)
	{
		FluidTank fluidTank = getFluidTankDrain(arg0);
		if (fluidTank == null) return null;
		return fluidTank.drain(arg1, arg2);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection arg0)
	{
		FluidTank[] fluidTanks = getFluidTank(arg0);
		if (fluidTanks == null) return new FluidTankInfo[] {};
		FluidTankInfo[] fluidTankInfos = new FluidTankInfo[fluidTanks.length];
		for (int i = 0; i < fluidTanks.length; i++) {
			fluidTankInfos[i] = fluidTanks[i].getInfo();
		}
		return fluidTankInfos;
	}

	////////////////////////////// ISidedInventory //////////////////////////////

	public final SimpleInventoryChain inventoryChain = new SimpleInventoryChain(this);

	protected ArrayList<Tuple<ISimpleInventoryMir51, String>> inventories = new ArrayList<Tuple<ISimpleInventoryMir51, String>>();

	protected <T extends ISimpleInventoryMir51> T add(T inventory, String tagName)
	{
		inventories.add(new Tuple<ISimpleInventoryMir51, String>(inventory, tagName));
		return inventory;
	}

	@Override
	public void openInventory()
	{
		inventoryChain.openInventory();
	}

	@Override
	public void closeInventory()
	{
		inventoryChain.closeInventory();
	}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1)
	{
		return inventoryChain.getInventoryCell(arg0).decrStackSize(arg1);
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getInventoryStackLimit()
	{
		return inventoryChain.getInventoryStackLimit();
	}

	@Override
	public int getSizeInventory()
	{
		return inventoryChain.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int arg0)
	{
		return inventoryChain.getInventoryCell(arg0).getStackInSlot();
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int arg0)
	{
		return inventoryChain.getInventoryCell(arg0).getStackInSlotOnClosing();
	}

	@Override
	public boolean isItemValidForSlot(int arg0, ItemStack arg1)
	{
		return inventoryChain.getInventoryCell(arg0).isItemValidForSlot(arg1);
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0)
	{
		return inventoryChain.isUseableByPlayer(arg0);
	}

	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1)
	{
		inventoryChain.getInventoryCell(arg0).setInventorySlotContents(arg1);
	}

	//

	protected ISimpleInventoryMir51[] getInventoryInsert(int side, ItemStack itemStack)
	{
		return null;
	}

	protected ISimpleInventoryMir51[] getInventoryExtract(int side, ItemStack itemStack)
	{
		return null;
	}

	protected ISimpleInventoryMir51[] getInventoryAccessible(int side)
	{
		return null;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		ISimpleInventoryMir51[] invenotries = getInventoryAccessible(side);
		if (invenotries == null) return new int[0];
		return inventoryChain.getSlotsOfInventory(invenotries);
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStack, int side)
	{
		ISimpleInventoryMir51[] invenotries = getInventoryInsert(side, itemStack);
		if (invenotries == null) return false;

		ISimpleInventoryMir51 inventoryFromGlobalSlotIndex = inventoryChain.getInventoryFromGlobalSlotIndex(slot);
		for (ISimpleInventoryMir51 invenotryInChain : invenotries) {
			if (inventoryFromGlobalSlotIndex == invenotryInChain) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, int side)
	{
		ISimpleInventoryMir51[] invenotries = getInventoryExtract(side, itemStack);
		if (invenotries == null) return false;

		ISimpleInventoryMir51 inventoryFromGlobalSlotIndex = inventoryChain.getInventoryFromGlobalSlotIndex(slot);
		for (ISimpleInventoryMir51 invenotryInChain : invenotries) {
			if (inventoryFromGlobalSlotIndex == invenotryInChain) {
				return true;
			}
		}

		return false;
	}

	//////////////////////////////////// EnergyTank ////////////////////////////////////

	protected ArrayList<Tuple<EnergyTank, String>> energyTanks = new ArrayList<Tuple<EnergyTank, String>>();

	protected <T extends EnergyTank> T add(T energyTank, String tagName)
	{
		energyTanks.add(new Tuple<EnergyTank, String>(energyTank, tagName));
		return energyTank;
	}

}
