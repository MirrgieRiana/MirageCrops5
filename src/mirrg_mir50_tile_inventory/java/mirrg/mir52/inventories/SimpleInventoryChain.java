package mirrg.mir52.inventories;

import java.util.ArrayList;

import mirrg.mir51.inventory.IInventoryCellMir51;
import mirrg.mir51.inventory.ISimpleInventoryMir51;
import mirrg.mir51.inventory.SimpleInventoryMir51;
import net.minecraft.tileentity.TileEntity;

/**
 * 複数のインベントリを結合し、新たな通し番号によるスロットへの参照を与えます。
 */
public class SimpleInventoryChain extends SimpleInventoryMir51
{

	protected ArrayList<ISimpleInventoryMir51> inventories = new ArrayList<ISimpleInventoryMir51>();
	protected ISimpleInventoryMir51 mainInventory;

	public SimpleInventoryChain(TileEntity tileEntity)
	{
		super(tileEntity);
	}

	public void add(ISimpleInventoryMir51 inventory)
	{
		if (inventories.isEmpty()) {
			mainInventory = inventory;
		}
		inventories.add(inventory);
	}

	@SuppressWarnings("deprecation")
	@Deprecated
	@Override
	public int getInventoryStackLimit()
	{
		return mainInventory.getInventoryStackLimit();
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return mainInventory.hasCustomInventoryName();
	}

	@Override
	public String getCustomInventoryName()
	{
		return mainInventory.getCustomInventoryName();
	}

	@Override
	public String getDefaultName()
	{
		return mainInventory.getDefaultName();
	}

	@Override
	public void markDirty()
	{
		for (ISimpleInventoryMir51 inventory : inventories) {
			inventory.markDirty();
		}
	}

	@Override
	public void openInventory()
	{
		for (ISimpleInventoryMir51 inventory : inventories) {
			inventory.openInventory();
		}
	}

	@Override
	public void closeInventory()
	{
		for (ISimpleInventoryMir51 inventory : inventories) {
			inventory.closeInventory();
		}
	}

	//////////////////////////////////////////////////////////////////

	@Override
	public IInventoryCellMir51 getInventoryCell(int globalSlotIndex)
	{
		int[] address = getAddress(globalSlotIndex);
		return inventories.get(address[0]).getInventoryCell(address[1]);
	}

	@Override
	public int getSizeInventory()
	{
		return inventories.stream().mapToInt(inventory -> inventory.getSizeInventory()).sum();
	}

	/**
	 * @return new int[] { inventoryIndex, localSlotIndex in inventory }
	 */
	public int[] getAddress(int globalSlotIndex)
	{
		if (globalSlotIndex < 0) return null;

		for (int i = 0; i < inventories.size(); i++) {
			ISimpleInventoryMir51 inventory = inventories.get(i);

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

	public int[] getSlotsOfInventory(ISimpleInventoryMir51... inventoryList)
	{
		ArrayList<Integer> slots = new ArrayList<Integer>();

		int size = 0;
		for (ISimpleInventoryMir51 inventory : inventories) {
			for (ISimpleInventoryMir51 inventory2 : inventoryList) {
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

	public int getSizeChain()
	{
		return inventories.size();
	}

	public Integer getInventoryIndex(ISimpleInventoryMir51 inventory)
	{
		int indexOf = inventories.indexOf(inventory);
		return indexOf < 0 ? null : indexOf;
	}

	public ISimpleInventoryMir51 getInventoryFromGlobalSlotIndex(int globalSlotIndex)
	{
		return inventories.get(getAddress(globalSlotIndex)[0]);
	}

	public int getGlobalSlotIndex(int inventoryIndex, int localSlotIndexInInventory)
	{
		for (int i = 0; i < inventoryIndex; i++) {
			localSlotIndexInInventory += inventories.get(i).getSizeInventory();
		}
		return localSlotIndexInInventory;
	}

	public ISimpleInventoryMir51 getInventory(int index)
	{
		return inventories.get(index);
	}

}
