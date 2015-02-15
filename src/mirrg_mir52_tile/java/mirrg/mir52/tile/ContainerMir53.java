package mirrg.mir52.tile;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntUnaryOperator;

import mirrg.mir50.tile.inventory.EnergySlot;
import mirrg.mir50.tile.inventory.FluidSlot;
import mirrg.mir51.inventory.ISimpleInventoryMir51;
import mirrg.mir52.inventories.HelpersSimpleInventoryMir51;
import mirrg.mir52.inventories.SimpleInventoryChain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMir53 extends Container
{

	protected final EntityPlayer player;
	protected final ITileEntityMir53 tileEntity;
	protected final PointerBlock pointerBlock;
	protected final ArrayList<FluidSlot> fluidSlots = new ArrayList<FluidSlot>();
	protected final ArrayList<EnergySlot> energySlots = new ArrayList<EnergySlot>();
	protected final SimpleInventoryChain inventoryChain;
	protected final Hashtable<Integer, int[]> transferInventoriesTable = new Hashtable<>();
	protected final Hashtable<Integer, Boolean> inventoryInverseTable = new Hashtable<>();

	public ContainerMir53(EntityPlayer player, ITileEntityMir53 tileEntity, PointerBlock pointerBlock)
	{
		this.player = player;
		this.tileEntity = tileEntity;
		this.pointerBlock = pointerBlock;
		inventoryChain = new SimpleInventoryChain(tileEntity.getTileEntity());
	}

	public EntityPlayer getPlayer()
	{
		return player;
	}

	public ITileEntityMir53 getTileEntity()
	{
		return tileEntity;
	}

	public PointerBlock getBlockPointer()
	{
		return pointerBlock;
	}

	public List<Slot> getSlots()
	{
		return inventorySlots;
	}

	public List<FluidSlot> getFluidSlots()
	{
		return fluidSlots;
	}

	public List<EnergySlot> getEnergySlots()
	{
		return energySlots;
	}

	public void addInventory(IInventory inventory, ISupplierPosition supplierPosition, boolean inverse)
	{
		addInventory(inventory, Slot::new, supplierPosition::getX, supplierPosition::getY, inverse);
	}

	public void addInventory(IInventory inventory, ICreatorSlot creatorSlot, ISupplierPosition supplierPosition, boolean inverse)
	{
		addInventory(inventory, creatorSlot, supplierPosition::getX, supplierPosition::getY, inverse);
	}

	public void addInventory(IInventory inventory, IntUnaryOperator x, IntUnaryOperator y, boolean inverse)
	{
		addInventory(inventory, Slot::new, x, y, inverse);
	}

	public void addInventory(IInventory inventory, ICreatorSlot creatorSlot, IntUnaryOperator x, IntUnaryOperator y, boolean inverse)
	{
		inventoryChain.add(HelpersSimpleInventoryMir51.make(inventory, tileEntity.getTileEntity()));

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			super.addSlotToContainer(creatorSlot.create(inventory, i, x.applyAsInt(i), y.applyAsInt(i)));
		}

		inventoryInverseTable.put(inventoryChain.getSizeChain() - 1, inverse);
	}

	/**
	 * @deprecated
	 * @see {@link #addInventory(IInventory)}
	 */
	@Deprecated
	@Override
	protected Slot addSlotToContainer(Slot p_addSlotToContainer_1_)
	{
		return super.addSlotToContainer(p_addSlotToContainer_1_);
	}

	public void addFluidSlot(FluidSlot fluidSlot)
	{
		fluidSlots.add(fluidSlot);
	}

	public void addEnergySlot(EnergySlot energySlot)
	{
		energySlots.add(energySlot);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		if (pointerBlock.getTileEntity() != tileEntity) return false;
		return player.getDistanceSq(pointerBlock.x + 0.5D, pointerBlock.y + 0.5D, pointerBlock.z + 0.5D) <= 8 * 8;
	}

	public void setTransferInventories(ISimpleInventoryMir51 source, ISimpleInventoryMir51... destinations)
	{
		int[] destinationInventoryIndexes = new int[destinations.length];
		for (int i = 0; i < destinationInventoryIndexes.length; i++) {
			destinationInventoryIndexes[i] = inventoryChain.getInventoryIndex(destinations[i]);
		}

		setTransferInventories(inventoryChain.getInventoryIndex(source), destinationInventoryIndexes);
	}

	public void setTransferInventories(int sourceInventoryIndex, int... destinationInventoryIndexes)
	{
		transferInventoriesTable.put(sourceInventoryIndex, destinationInventoryIndexes);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
	{
		Slot slot = (Slot) this.inventorySlots.get(slotId);
		if (slot == null) return null;
		if (!slot.getHasStack()) return null;

		int sourceInventoryIndex = inventoryChain.getAddress(slotId)[0];
		int[] transferInventoryIndexes = transferInventoriesTable.get(sourceInventoryIndex);

		ItemStack itemstack1 = slot.getStack();
		ItemStack itemstack = itemstack1.copy();

		boolean moved = false;

		for (int i = 0; i < transferInventoryIndexes.length; i++) {
			int transferInventoryIndex = transferInventoryIndexes[i];
			ISimpleInventoryMir51 transferInventory = inventoryChain.getInventory(transferInventoryIndex);

			if (mergeItemStack(itemstack1,
				inventoryChain.getGlobalSlotIndex(transferInventoryIndex, 0),
				inventoryChain.getGlobalSlotIndex(transferInventoryIndex, transferInventory.getSizeInventory()),
				inventoryInverseTable.get(transferInventoryIndex))) {
				// 移動が行われた
				moved = true;
			}
			if (itemstack1.stackSize == 0) {
				// 移動が完了した
				break;
			}

		}

		if (!moved) return null;

		if (itemstack1.stackSize == 0) {
			slot.putStack((ItemStack) null);
		} else {
			slot.onSlotChanged();
		}

		return itemstack;
	}

	@Override
	protected boolean mergeItemStack(ItemStack stack, int start, int end, boolean inverse)
	{
		return HelpersContainer.mergeItemStackIntoInventory(((List<Slot>) inventorySlots)::get, stack, start, end, inverse);
	}

	/**
	 * aからbまでの整数を返す。3と5なら、3,4,5。
	 */
	public static class Range implements Iterable<Integer>
	{

		int a;
		int b;
		boolean bToA;

		public Range(int a, int b)
		{
			this(a, b, false);
		}

		public Range(int a, int b, boolean bToA)
		{
			if (a > b) {
				int tmp = a;
				a = b;
				b = tmp;
				bToA = !bToA;
			}

			this.a = a;
			this.b = b;
			this.bToA = bToA;
		}

		@Override
		public Iterator<Integer> iterator()
		{
			return new Iterator<Integer>() {

				int next = bToA ? b : a;

				@Override
				public boolean hasNext()
				{
					return bToA ? next >= a : next <= b;
				}

				@Override
				public Integer next()
				{
					return bToA ? next-- : next++;
				}

			};
		}
	}

}
