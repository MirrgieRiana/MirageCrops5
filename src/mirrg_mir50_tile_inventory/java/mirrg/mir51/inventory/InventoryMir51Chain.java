package mirrg.mir51.inventory;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;

/**
 * 複数のインベントリを結合し、新たな通し番号によるスロットへの参照を与えます。
 */
public class InventoryMir51Chain extends InventoryMir51SimpleAbstract
{

	protected ArrayList<IInventoryMir51> inventories = new ArrayList<>();
	protected IInventoryMir51 mainInventory;

	public List<IInventoryMir51> getInventories()
	{
		return inventories;
	}

	public IInventoryMir51 getMainInventory()
	{
		return mainInventory;
	}

	public void add(IInventoryMir51 inventory)
	{
		if (inventories.isEmpty()) {
			mainInventory = inventory;
		}
		inventories.add(inventory);
	}

	//////////////////////////////////////////////////////////////////

	public int[] getSlotsOfInventory(IInventoryMir51... inventoryList)
	{
		ArrayList<Integer> slots = new ArrayList<Integer>();

		int size = 0;
		for (IInventoryMir51 inventory : inventories) {
			for (IInventoryMir51 inventory2 : inventoryList) {
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

	/////////////////////////////////////////////////////////////////////////

	@Override
	public void markDirty()
	{
		for (IInventoryMir51 inventory : getInventories()) {
			inventory.markDirty();
		}
	}

	@Override
	public void openInventory()
	{
		for (IInventoryMir51 inventory : getInventories()) {
			inventory.openInventory();
		}
	}

	@Override
	public void closeInventory()
	{
		for (IInventoryMir51 inventory : getInventories()) {
			inventory.closeInventory();
		}
	}

	@Override
	public int getSizeInventory()
	{
		int t = 0;
		for (IInventoryMir51 inventory : getInventories()) {
			t += inventory.getSizeInventory();
		}
		return t;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		for (IInventoryMir51 inventory : getInventories()) {
			if (!inventory.isUseableByPlayer(player)) return false;
		}
		return true;
	}

	@Override
	public IInventoryCellMir51 getInventoryCell(int globalSlotIndex)
	{
		int[] address = getAddress(globalSlotIndex);
		return getInventories().get(address[0]).getInventoryCell(address[1]);
	}

	//////////////////////////////////////////////////////////////

	@SuppressWarnings("deprecation")
	@Deprecated
	@Override
	public int getInventoryStackLimit()
	{
		return getMainInventory().getInventoryStackLimit();
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return getMainInventory().hasCustomInventoryName();
	}

	@Override
	public String getCustomInventoryName()
	{
		return getMainInventory().getCustomInventoryName();
	}

	@Override
	public String getDefaultName()
	{
		return getMainInventory().getDefaultName();
	}

	@Override
	public String getLocalizedName()
	{
		return getMainInventory().getLocalizedName();
	}

	@Override
	public String getInventoryName()
	{
		return getMainInventory().getInventoryName();
	}

	/////////////////////////////////////////////////////////////////////////

	public IInventoryMir51 getInventory(int index)
	{
		return getInventories().get(index);
	}

	public int getSizeChain()
	{
		return getInventories().size();
	}

	public Integer getInventoryIndex(IInventoryMir51 inventory)
	{
		int indexOf = getInventories().indexOf(inventory);
		return indexOf < 0 ? null : indexOf;
	}

	/**
	 * @return new int[] { inventoryIndex, localSlotIndexInInventory }
	 */
	public int[] getAddress(int globalSlotIndex)
	{
		if (globalSlotIndex < 0) return null;

		for (int i = 0; i < getInventories().size(); i++) {
			IInventoryMir51 inventory = getInventories().get(i);

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
			localSlotIndexInInventory += getInventories().get(i).getSizeInventory();
		}
		return localSlotIndexInInventory;
	}

	public IInventoryMir51 getInventoryFromGlobalSlotIndex(int globalSlotIndex)
	{
		return getInventories().get(getAddress(globalSlotIndex)[0]);
	}

}
