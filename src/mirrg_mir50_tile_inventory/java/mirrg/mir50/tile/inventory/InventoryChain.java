package mirrg.mir50.tile.inventory;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * 複数のインベントリを結合し、新たな通し番号によるスロットへの参照を与えます。
 */
public class InventoryChain extends InventoryMir50
{

	protected ArrayList<IInventoryMir50> inventories = new ArrayList<IInventoryMir50>();

	public InventoryChain(TileEntity tileEntity)
	{
		super(tileEntity);
	}

	public void add(IInventory inventory)
	{
		if (inventory instanceof IInventoryMir50) {
			add((IInventoryMir50) inventory);
		} else {
			add(new InventoryMir50FromBasic(getTileEntity(), inventory));
		}
	}

	public void add(IInventoryMir50 inventory)
	{
		inventories.add(inventory);
	}

	//

	@Override
	public int getInventoryStackLimit(int globalSlotIndex)
	{
		int[] address = getAddress(globalSlotIndex);
		return inventories.get(address[0]).getInventoryStackLimit(address[1]);
	}

	//

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1)
	{
		for (IInventory inventory : inventories) {
			if (!inventory.isUseableByPlayer(var1)) return false;
		}
		return true;
	}

	@Override
	public void markDirty()
	{
		for (IInventory inventory : inventories) {
			inventory.markDirty();
		}
	}

	@Override
	public void openInventory()
	{
		for (IInventory inventory : inventories) {
			inventory.openInventory();
		}
	}

	@Override
	public void closeInventory()
	{
		for (IInventory inventory : inventories) {
			inventory.closeInventory();
		}
	}

	//

	@Override
	public ItemStack getStackInSlot(int globalSlotIndex)
	{
		int[] address = getAddress(globalSlotIndex);
		return inventories.get(address[0]).getStackInSlot(address[1]);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int globalSlotIndex)
	{
		int[] address = getAddress(globalSlotIndex);
		return inventories.get(address[0]).getStackInSlotOnClosing(address[1]);
	}

	@Override
	public ItemStack decrStackSize(int globalSlotIndex, int amount)
	{
		int[] address = getAddress(globalSlotIndex);
		return inventories.get(address[0]).decrStackSize(address[1], amount);
	}

	@Override
	public void setInventorySlotContents(int globalSlotIndex, ItemStack var2)
	{
		int[] address = getAddress(globalSlotIndex);
		inventories.get(address[0]).setInventorySlotContents(address[1], var2);
	}

	@Override
	public boolean isItemValidForSlot(int globalSlotIndex, ItemStack var2)
	{
		int[] address = getAddress(globalSlotIndex);
		return inventories.get(address[0]).isItemValidForSlot(address[1], var2);
	}

	//

	@Override
	public int getSizeInventory()
	{
		int size = 0;
		for (IInventory inventory : inventories) {
			size += inventory.getSizeInventory();
		}
		return size;
	}

	/**
	 * @return new int[] { inventoryIndex, localSlotIndex in inventory }
	 */
	public int[] getAddress(int globalSlotIndex)
	{
		if (globalSlotIndex < 0) return null;

		for (int i = 0; i < inventories.size(); i++) {
			IInventory inventory = inventories.get(i);

			if (globalSlotIndex < inventory.getSizeInventory()) {
				return new int[] {
					i,
					globalSlotIndex,
				};
			} else {
				globalSlotIndex -= inventory.getSizeInventory();
			}
		}

		return null;
	}

	public int getGlobalSlotIndex(int inventoryIndex, int localSlotIndexInInventory)
	{
		for (int i = 0; i < inventoryIndex; i++) {
			localSlotIndexInInventory += inventories.get(i).getSizeInventory();
		}
		return localSlotIndexInInventory;
	}

	public IInventory getInventory(int index)
	{
		return inventories.get(index);
	}

	public int[] getSlotsOfInventory(IInventory... inventoryList)
	{
		ArrayList<Integer> slots = new ArrayList<Integer>();

		int size = 0;
		for (IInventory inventory : inventories) {
			for (IInventory inventory2 : inventoryList) {
				if (inventory == inventory2) {
					for (int i = 0; i < inventory.getSizeInventory(); i++) {
						slots.add(size + i);
					}
				}
			}
			size += inventory.getSizeInventory();
		}

		int[] slots2 = new int[slots.size()];

		for (int i = 0; i < slots2.length; i++) {
			slots2[i] = slots.get(i);
		}

		return slots2;
	}

	public int getInventoryIndex(IInventory inventory)
	{
		return inventories.indexOf(inventory);
	}

	public IInventory getInventoryFromGlobalSlotIndex(int globalSlotIndex)
	{
		return inventories.get(getAddress(globalSlotIndex)[0]);
	}

	public int getInventoryCount()
	{
		return inventories.size();
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onBroken()
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void dropAll()
	{
		// TODO 自動生成されたメソッド・スタブ

	}

}
