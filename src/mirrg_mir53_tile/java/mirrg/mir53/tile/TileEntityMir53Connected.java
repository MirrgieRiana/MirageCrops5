package mirrg.mir53.tile;

import java.util.ArrayList;

import mirrg.h.struct.Tuple;
import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.datamodels.DatamodelFluid;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Chain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import api.mirrg.mir50.datamodel.IDatamodel;
import api.mirrg.mir50.net.NBTTypes;
import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile.PipeType;

public class TileEntityMir53Connected extends TileEntityMir53 implements IPipeConnection, IFluidHandler, ISidedInventory
{

	public void writeToNBT(String componentName, NBTTagCompound p_145841_1_)
	{

		for (Tuple<IInventoryMir51, String> entry : inventories) {

			if (entry.getY().equals(componentName)) {
				entry.getX().writeToNBT(p_145841_1_);
				return;
			}

		}

		for (Tuple<DatamodelFluid, String> entry : fluidTanks) {

			if (entry.getY().equals(componentName)) {
				entry.getX().writeToNBT(p_145841_1_);
				return;
			}

		}

		for (Tuple<DatamodelEnergy, String> entry : energyTanks) {

			if (entry.getY().equals(componentName)) {
				entry.getX().writeToNBT(p_145841_1_);
				return;
			}

		}

		for (Tuple<IDatamodel<?>, String> entry : datamodels) {

			if (entry.getY().equals(componentName)) {
				entry.getX().writeToNBT(p_145841_1_);
				return;
			}

		}

	}

	public void readFromNBT(String componentName, NBTTagCompound p_145839_1_)
	{

		for (Tuple<IInventoryMir51, String> entry : inventories) {

			if (entry.getY().equals(componentName)) {
				entry.getX().readFromNBT(p_145839_1_);
				return;
			}

		}

		for (Tuple<DatamodelFluid, String> entry : fluidTanks) {

			if (entry.getY().equals(componentName)) {
				entry.getX().readFromNBT(p_145839_1_);
				return;
			}

		}

		for (Tuple<DatamodelEnergy, String> entry : energyTanks) {

			if (entry.getY().equals(componentName)) {
				entry.getX().readFromNBT(p_145839_1_);
				return;
			}

		}

		for (Tuple<IDatamodel<?>, String> entry : datamodels) {

			if (entry.getY().equals(componentName)) {
				entry.getX().readFromNBT(p_145839_1_);
				return;
			}

		}

	}

	@Override
	public void writeToNBT(NBTTagCompound p_145841_1_)
	{
		super.writeToNBT(p_145841_1_);

		for (Tuple<IInventoryMir51, String> entry : inventories) {

			NBTTagCompound tag = new NBTTagCompound();
			entry.getX().writeToNBT(tag);
			p_145841_1_.setTag(entry.getY(), tag);

		}

		for (Tuple<DatamodelFluid, String> entry : fluidTanks) {

			NBTTagCompound tag = new NBTTagCompound();
			entry.getX().writeToNBT(tag);
			p_145841_1_.setTag(entry.getY(), tag);

		}

		for (Tuple<DatamodelEnergy, String> entry : energyTanks) {

			NBTTagCompound tag = new NBTTagCompound();
			entry.getX().writeToNBT(tag);
			p_145841_1_.setTag(entry.getY(), tag);

		}

		for (Tuple<IDatamodel<?>, String> entry : datamodels) {

			NBTTagCompound tag = new NBTTagCompound();
			entry.getX().writeToNBT(tag);
			p_145841_1_.setTag(entry.getY(), tag);

		}

	}

	@Override
	public void readFromNBT(NBTTagCompound p_145839_1_)
	{
		super.readFromNBT(p_145839_1_);

		for (Tuple<IInventoryMir51, String> entry : inventories) {

			if (p_145839_1_.hasKey(entry.getY(), NBTTypes.COMPOUND)) {
				NBTTagCompound tag = p_145839_1_.getCompoundTag(entry.getY());
				entry.getX().readFromNBT(tag);
			} else {
				entry.getX().reset();
			}

		}

		for (Tuple<DatamodelFluid, String> entry : fluidTanks) {

			if (p_145839_1_.hasKey(entry.getY(), NBTTypes.COMPOUND)) {
				NBTTagCompound tag = p_145839_1_.getCompoundTag(entry.getY());
				entry.getX().readFromNBT(tag);
			} else {
				entry.getX().reset();
			}

		}

		for (Tuple<DatamodelEnergy, String> entry : energyTanks) {

			if (p_145839_1_.hasKey(entry.getY(), NBTTypes.COMPOUND)) {
				NBTTagCompound tag = p_145839_1_.getCompoundTag(entry.getY());
				entry.getX().readFromNBT(tag);
			} else {
				entry.getX().reset();
			}

		}

		for (Tuple<IDatamodel<?>, String> entry : datamodels) {

			if (p_145839_1_.hasKey(entry.getY(), NBTTypes.COMPOUND)) {
				NBTTagCompound tag = p_145839_1_.getCompoundTag(entry.getY());
				entry.getX().readFromNBT(tag);
			} else {
				entry.getX().reset();
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

	protected ArrayList<Tuple<DatamodelFluid, String>> fluidTanks = new ArrayList<Tuple<DatamodelFluid, String>>();

	protected <T extends DatamodelFluid> T add(T fluidTank, String tagName)
	{
		fluidTanks.add(new Tuple<DatamodelFluid, String>(fluidTank, tagName));
		return fluidTank;
	}

	protected DatamodelFluid getFluidTankFill(ForgeDirection arg0, Fluid arg1)
	{
		return null;
	}

	protected DatamodelFluid getFluidTankDrain(ForgeDirection arg0, Fluid arg1)
	{
		return null;
	}

	protected DatamodelFluid getFluidTankDrain(ForgeDirection arg0)
	{
		return null;
	}

	protected DatamodelFluid[] getFluidTank(ForgeDirection arg0)
	{
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection arg0, Fluid arg1)
	{
		DatamodelFluid fluidTank = getFluidTankFill(arg0, arg1);
		if (fluidTank == null) return false;
		return fluidTank.canFill(arg1);
	}

	@Override
	public int fill(ForgeDirection arg0, FluidStack arg1, boolean arg2)
	{
		DatamodelFluid fluidTank = getFluidTankFill(arg0, arg1.getFluid());
		if (fluidTank == null) return 0;
		return fluidTank.fill(arg1, arg2);
	}

	@Override
	public boolean canDrain(ForgeDirection arg0, Fluid arg1)
	{
		DatamodelFluid fluidTank = getFluidTankDrain(arg0, arg1);
		if (fluidTank == null) return false;
		return fluidTank.canDrain(arg1);
	}

	@Override
	public FluidStack drain(ForgeDirection arg0, FluidStack arg1, boolean arg2)
	{
		DatamodelFluid fluidTank = getFluidTankDrain(arg0, arg1.getFluid());
		if (fluidTank == null) return null;
		return fluidTank.drain(arg1, arg2);
	}

	@Override
	public FluidStack drain(ForgeDirection arg0, int arg1, boolean arg2)
	{
		DatamodelFluid fluidTank = getFluidTankDrain(arg0);
		if (fluidTank == null) return null;
		return fluidTank.drain(arg1, arg2);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection arg0)
	{
		DatamodelFluid[] fluidTanks = getFluidTank(arg0);
		if (fluidTanks == null) return new FluidTankInfo[] {};
		FluidTankInfo[] fluidTankInfos = new FluidTankInfo[fluidTanks.length];
		for (int i = 0; i < fluidTanks.length; i++) {
			fluidTankInfos[i] = fluidTanks[i].getInfo();
		}
		return fluidTankInfos;
	}

	////////////////////////////// ISidedInventory //////////////////////////////

	public final InventoryMir51Chain inventoryChain = new InventoryMir51Chain();

	protected ArrayList<Tuple<IInventoryMir51, String>> inventories = new ArrayList<Tuple<IInventoryMir51, String>>();

	protected <T extends IInventoryMir51> T add(T inventory, String tagName)
	{
		inventories.add(new Tuple<IInventoryMir51, String>(inventory, tagName));
		return inventory;
	}

	@Override
	public void onBroken()
	{
		inventoryChain.onBroken();
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

	protected IInventoryMir51[] getInventoryInsert(int side, ItemStack itemStack)
	{
		return null;
	}

	protected IInventoryMir51[] getInventoryExtract(int side, ItemStack itemStack)
	{
		return null;
	}

	protected IInventoryMir51[] getInventoryAccessible(int side)
	{
		return null;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		IInventoryMir51[] invenotries = getInventoryAccessible(side);
		if (invenotries == null) return new int[0];
		return inventoryChain.getSlotsOfInventory(invenotries);
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStack, int side)
	{
		IInventoryMir51[] invenotries = getInventoryInsert(side, itemStack);
		if (invenotries == null) return false;

		IInventoryMir51 inventoryFromGlobalSlotIndex = inventoryChain.getInventoryFromGlobalSlotIndex(slot);
		for (IInventoryMir51 invenotryInChain : invenotries) {
			if (inventoryFromGlobalSlotIndex == invenotryInChain) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, int side)
	{
		IInventoryMir51[] invenotries = getInventoryExtract(side, itemStack);
		if (invenotries == null) return false;

		IInventoryMir51 inventoryFromGlobalSlotIndex = inventoryChain.getInventoryFromGlobalSlotIndex(slot);
		for (IInventoryMir51 invenotryInChain : invenotries) {
			if (inventoryFromGlobalSlotIndex == invenotryInChain) {
				return true;
			}
		}

		return false;
	}

	//////////////////////////////////// EnergyTank ////////////////////////////////////

	protected ArrayList<Tuple<DatamodelEnergy, String>> energyTanks = new ArrayList<Tuple<DatamodelEnergy, String>>();

	protected <T extends DatamodelEnergy> T add(T energyTank, String tagName)
	{
		energyTanks.add(new Tuple<DatamodelEnergy, String>(energyTank, tagName));
		return energyTank;
	}

	public String getName(DatamodelFluid fluidTank)
	{
		for (Tuple<DatamodelFluid, String> entry : fluidTanks) {
			if (entry.getX() == fluidTank) {
				return entry.getY();
			}
		}
		return null;
	}

	public String getName(DatamodelEnergy energyTank)
	{
		for (Tuple<DatamodelEnergy, String> entry : energyTanks) {
			if (entry.getX() == energyTank) {
				return entry.getY();
			}
		}
		return null;
	}

	public String getName(IInventoryMir51 inventory)
	{
		for (Tuple<IInventoryMir51, String> entry : inventories) {
			if (entry.getX() == inventory) {
				return entry.getY();
			}
		}
		return null;
	}

	public DatamodelFluid getFluidTank(String name)
	{
		for (Tuple<DatamodelFluid, String> entry : fluidTanks) {
			if (entry.getY().equals(name)) {
				return entry.getX();
			}
		}
		return null;
	}

	public DatamodelEnergy getEnergyTank(String name)
	{
		for (Tuple<DatamodelEnergy, String> entry : energyTanks) {
			if (entry.getY().equals(name)) {
				return entry.getX();
			}
		}
		return null;
	}

	public IInventoryMir51 getInventory(String name)
	{
		for (Tuple<IInventoryMir51, String> entry : inventories) {
			if (entry.getY().equals(name)) {
				return entry.getX();
			}
		}
		return null;
	}

	//////////////////////////////////// Other Datamodels ////////////////////////////////////

	protected ArrayList<Tuple<IDatamodel<?>, String>> datamodels = new ArrayList<Tuple<IDatamodel<?>, String>>();

	protected <T extends IDatamodel<?>> T add(T datamodel, String tagName)
	{
		datamodels.add(new Tuple<>(datamodel, tagName));
		return datamodel;
	}

}
