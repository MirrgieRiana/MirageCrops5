package mirrg.mir50.datamodels;

import api.mirrg.mir50.datamodel.IDatamodel;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class DatamodelFluid implements IFluidTank, IDatamodel<DatamodelFluid>
{

	public FluidStack fluidStack;
	protected Runnable runnableMarkDirty;
	private int capacity;
	public boolean dirty;

	public DatamodelFluid(Runnable runnableMarkDirty, int capacity)
	{
		this.runnableMarkDirty = runnableMarkDirty;
		this.capacity = capacity;
	}

	@Override
	public int getCapacity()
	{
		return capacity;
	}

	@Override
	public void markDirty()
	{
		runnableMarkDirty.run();
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		if (fluidStack != null) {
			fluidStack.writeToNBT(tag);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		fluidStack = FluidStack.loadFluidStackFromNBT(tag);
	}

	@Override
	public boolean isEmpty()
	{
		if (fluidStack == null) return true;
		if (fluidStack.getFluid() == null) return true;
		if (fluidStack.amount == 0) return true;
		return false;
	}

	public FluidStack getValidatedFluidStack()
	{
		return isEmpty() ? null : fluidStack;
	}

	public boolean canFill(Fluid fluid)
	{
		if (fluid == null) return false;
		if (isEmpty()) return true;
		return fluid.equals(this.fluidStack.getFluid());
	}

	public boolean canDrain(Fluid fluid)
	{
		if (fluid == null) return false;
		if (isEmpty()) return false;
		return fluid.equals(this.fluidStack.getFluid());
	}

	public boolean canDrain(FluidStack fluidStack)
	{
		if (fluidStack == null) return false;
		if (!canDrain(fluidStack.getFluid())) return false;
		return true;
	}

	public boolean canFill(FluidStack fluidStack)
	{
		if (fluidStack == null) return false;
		if (!canFill(fluidStack.getFluid())) return false;
		return true;
	}

	public int getSpace()
	{
		if (isEmpty()) return getCapacity();
		return getCapacity() - fluidStack.amount;
	}

	@Override
	public int fill(FluidStack paramFluidStack, boolean paramBoolean)
	{
		if (!canFill(paramFluidStack)) return 0;
		int moveAmount = Math.min(getSpace(), paramFluidStack.amount);

		if (paramBoolean) {
			if (isEmpty()) {
				fluidStack = new FluidStack(paramFluidStack.getFluid(), 0);
			}
			fluidStack.amount += moveAmount;
			markDirty();
		}

		return moveAmount;
	}

	@Override
	public FluidStack drain(int paramInt, boolean paramBoolean)
	{
		if (isEmpty()) return null;
		int moveAmount = Math.min(fluidStack.amount, paramInt);

		if (paramBoolean) {
			fluidStack.amount -= moveAmount;
			markDirty();
		}

		return new FluidStack(fluidStack.getFluid(), moveAmount);
	}

	public FluidStack drain(FluidStack arg1, boolean arg2)
	{
		if (isEmpty()) return null;
		if (arg1 == null) return null;
		if (!fluidStack.getFluid().equals(arg1.getFluid())) return null;
		return drain(arg1.amount, arg2);
	}

	@Override
	public FluidStack getFluid()
	{
		return fluidStack;
	}

	@Override
	public int getFluidAmount()
	{
		return fluidStack == null ? 0 : fluidStack.amount;
	}

	@Override
	public FluidTankInfo getInfo()
	{
		return new FluidTankInfo(fluidStack, capacity);
	}

	@Override
	public DatamodelFluid copy()
	{
		DatamodelFluid fluidTank = new DatamodelFluid(runnableMarkDirty, capacity);
		fluidTank.fluidStack = fluidStack != null ? fluidStack.copy() : null;
		return fluidTank;
	}

	@Override
	public boolean isEqualContent(DatamodelFluid other)
	{
		return fluidStack.isFluidEqual(other.fluidStack)
			&& fluidStack.amount == other.fluidStack.amount;
	}

}
