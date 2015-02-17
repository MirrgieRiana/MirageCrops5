package mirrg.mir53.gui.container;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

import mirrg.mir50.gui.container.IContainerExtraSlot;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Chain;
import mirrg_miragecrops5.machine.MessageNamedTag;
import mirrg_miragecrops5.machine.ModuleMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerMir53 extends Container
{

	public ContainerMir53(EntityPlayer player, Predicate<EntityPlayer> predicateCanInteractWith)
	{
		this.player = player;
		this.predicateCanInteractWith = predicateCanInteractWith;
	}

	//

	public final EntityPlayer player;

	public EntityPlayer getPlayer()
	{
		return player;
	}

	protected Predicate<EntityPlayer> predicateCanInteractWith;

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return predicateCanInteractWith.test(player);
	}

	//

	private ArrayList<IContainerExtraSlot> containerExtraSlots = new ArrayList<>();
	protected ArrayList<String> containerExtraSlotNames = new ArrayList<>();

	public void addContainerExtraSlot(IContainerExtraSlot containerExtraSlot, String containerExtraSlotName)
	{
		getContainerExtraSlots().add(containerExtraSlot);
		containerExtraSlotNames.add(containerExtraSlotName);
	}

	//

	public List<IContainerExtraSlot> getContainerExtraSlots()
	{
		return containerExtraSlots;
	}

	public IContainerExtraSlot getContainerExtraSlot(String name)
	{
		for (int i = 0; i < containerExtraSlots.size(); i++) {
			if (containerExtraSlotNames.get(i).equals(name)) return containerExtraSlots.get(i);
		}
		return null;
	}

	protected InventoryMir51Chain inventoryChain = new InventoryMir51Chain();
	protected Hashtable<Integer, Boolean> inventoryInverseTable = new Hashtable<>();

	@FunctionalInterface
	public static interface ICreatorSlot
	{

		public Slot create(IInventory inventory, int index, int x, int y);

	}

	public void addInventory(IInventoryMir51 inventory, ISupplierPositionContainer supplierPosition, boolean inverse)
	{
		addInventory(inventory, Slot::new, supplierPosition::getX, supplierPosition::getY, inverse);
	}

	public void addInventory(IInventoryMir51 inventory, ICreatorSlot creatorSlot, ISupplierPositionContainer supplierPosition, boolean inverse)
	{
		addInventory(inventory, creatorSlot, supplierPosition::getX, supplierPosition::getY, inverse);
	}

	public void addInventory(IInventoryMir51 inventory, IntUnaryOperator x, IntUnaryOperator y, boolean inverse)
	{
		addInventory(inventory, Slot::new, x, y, inverse);
	}

	public void addInventory(IInventoryMir51 inventory, ICreatorSlot creatorSlot, IntUnaryOperator x, IntUnaryOperator y, boolean inverse)
	{
		inventoryChain.add(inventory);

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			super.addSlotToContainer(creatorSlot.create(inventory, i, x.applyAsInt(i), y.applyAsInt(i)));
		}

		inventoryInverseTable.put(inventoryChain.getSizeChain() - 1, inverse);
	}

	public List<Slot> getInventorySlots()
	{
		return inventorySlots;
	}

	/**
	 * @deprecated {@link #addInventory(IInventory)}
	 */
	@Deprecated
	@Override
	protected Slot addSlotToContainer(Slot p_addSlotToContainer_1_)
	{
		return super.addSlotToContainer(p_addSlotToContainer_1_);
	}

	//

	protected final Hashtable<Integer, int[]> transferInventoriesTable = new Hashtable<>();

	public void setTransferInventories(IInventoryMir51 source, IInventoryMir51... destinations)
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

	//

	@Deprecated
	@Override
	protected boolean mergeItemStack(ItemStack stack, int start, int end, boolean inverse)
	{
		return HelpersContainer.mergeItemStackIntoInventory(((List<Slot>) inventorySlots)::get, stack, start, end, inverse);
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
			IInventoryMir51 transferInventory = inventoryChain.getInventory(transferInventoryIndex);

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
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int i = 0; i < getContainerExtraSlots().size(); i++) {
			IContainerExtraSlot containerExtraSlot = getContainerExtraSlots().get(i);
			if (containerExtraSlot.isDirtyAndSaveSnapshot()) {

				for (int j = 0; j < this.crafters.size(); j++) {
					ICrafting icrafting = (ICrafting) this.crafters.get(j);
					if (icrafting instanceof EntityPlayerMP) {
						EntityPlayerMP player = (EntityPlayerMP) icrafting;

						String name = containerExtraSlotNames.get(i);

						NBTTagCompound nbt = new NBTTagCompound();
						containerExtraSlot.writeToNBT(nbt);

						ModuleMachine.loaderSimpleNetworkWrapper.get().sendTo(new MessageNamedTag(name, nbt), player);

					}
				}

			}
		}

	}

}
