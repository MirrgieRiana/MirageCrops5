package mirrg_miragecrops5.machine;

import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotRectangle;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg_miragecrops5.ModuleCore;
import mirrg_miragecrops5.fairytype.FairyType;
import mirrg_miragecrops5.fairytype.RegistryFairyType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import api.mirrg.mir50.net.NBTTypes;

class ContainerExtraSlotFairyGraph extends ContainerExtraSlotRectangle
{

	protected IInventoryMir51 inventory;
	public int[] values = new int[6];

	public ContainerExtraSlotFairyGraph(int x, int y, int w, int h, IInventoryMir51 inventory)
	{
		super(x, y, w, h);
		this.inventory = inventory;
	}

	public int getFairyValue(int index)
	{
		int t = values[index];

		for (int i2 = 0; i2 < inventory.getSizeInventory(); i2++) {
			ItemStack itemStack = inventory.getStackInSlot(i2);

			if (itemStack != null) {
				Item item = itemStack.getItem();

				if (item == ModuleCore.loaderItem_craftingToolMirageFairy.get()) {
					NBTTagCompound tag = itemStack.getTagCompound();

					if (tag != null) {
						if (tag.hasKey("type", NBTTypes.STRING)) {
							String typeName = tag.getString("type");
							FairyType fairyType = RegistryFairyType.get(typeName);

							if (fairyType != null) {
								t += fairyType.getValue(index) * itemStack.stackSize;
							}
						}
					}
				}
			}
		}

		return t;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{

	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{

	}

	@Override
	public boolean isDirtyAndSaveSnapshot()
	{
		return false;
	}

}
