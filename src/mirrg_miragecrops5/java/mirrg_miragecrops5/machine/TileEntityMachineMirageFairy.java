package mirrg_miragecrops5.machine;

import mirrg.mir50.datamodels.DatamodelFluid;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotDatamodel;
import mirrg.mir50.gui.containerextraslots.ContainerExtraSlotLabel;
import mirrg.mir51.gui.renderers.RendererFluidSlot;
import mirrg.mir51.gui.renderers.RendererLabel;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir51.inventory.InventoryMir51Chain;
import mirrg.mir51.inventory.InventoryMir51FromInventory;
import mirrg.mir51.inventory.InventoryMir51Trimmer;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.SupplierPositionContainerFlow;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import api.mirrg.mir50.gui.renderer.EnumTextAlign;

public class TileEntityMachineMirageFairy extends TileEntityMMF
{

	public final IInventoryMir51 inventory;

	public final DatamodelFluid fluidTank;

	public TileEntityMachineMirageFairy()
	{
		inventory = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 8 * 3), "inventory");
		inventoryChain.add(inventory);

		fluidTank = add(new DatamodelFluid(this::markDirty, 16 * 1000), "fluidTank");
	}

	@Override
	protected IInventoryMir51[] getInventoryAccessible(int side)
	{
		return new IInventoryMir51[] {
			inventory,
		};
	}

	@Override
	protected IInventoryMir51[] getInventoryExtract(int side, ItemStack itemStack)
	{
		return getInventoryAccessible(side);
	}

	@Override
	protected IInventoryMir51[] getInventoryInsert(int side, ItemStack itemStack)
	{
		return getInventoryAccessible(side);
	}

	@Override
	protected ResourceLocation getGuiTexture(ContainerMir52 container)
	{//machineMirageFairy
		return new ResourceLocation("miragecrops5" + ":" + "textures/gui/NULL_GUI_TEXTURE.png");
	}

	@Override
	protected boolean hasGui()
	{
		return true;
	}

	@Override
	protected void prepareContainerSlots(ContainerMir52 container)
	{
		{
			ContainerExtraSlotLabel containerExtraSlot;

			containerExtraSlot = new ContainerExtraSlotLabel(getLocalizedName(),
				gui -> gui.getGuiWidth() / 2,
				gui -> 6,
				0x404040, EnumTextAlign.CENTER);
			containerExtraSlot.renderer = new RendererLabel(containerExtraSlot);
			container.addContainerExtraSlot(containerExtraSlot, "labelTileEntity");

			containerExtraSlot = new ContainerExtraSlotLabel(I18n.format("container.inventory"),
				gui -> 8,
				gui -> gui.getGuiHeight() - 96 + 2,
				0x404040, EnumTextAlign.LEFT);
			containerExtraSlot.renderer = new RendererLabel(containerExtraSlot);
			container.addContainerExtraSlot(containerExtraSlot, "labelInventory");
		}

		InventoryMir51Chain inventoryChest = inventoryChain;
		IInventoryMir51 inventory = new InventoryMir51FromInventory(container.getPlayer().inventory,
			getSupplierPosition());
		InventoryMir51Trimmer inventoryPlayer = new InventoryMir51Trimmer(inventory, 9, 27);
		InventoryMir51Trimmer inventoryHandle = new InventoryMir51Trimmer(inventory, 0, 9);

		container.addInventory(inventoryChest,
			new SupplierPositionContainerFlow(LEFT, TOP_CHEST, SHIFT, SHIFT, 8), false);
		container.addInventory(inventoryPlayer,
			new SupplierPositionContainerFlow(LEFT, TOP_INVENTORY, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryHandle,
			new SupplierPositionContainerFlow(LEFT, TOP_HOLDING, SHIFT, SHIFT, 9), true);

		container.setTransferInventories(inventoryChest, inventoryHandle, inventoryPlayer);
		container.setTransferInventories(inventoryPlayer, inventoryChest);
		container.setTransferInventories(inventoryHandle, inventoryChest);

		{
			ContainerExtraSlotDatamodel<DatamodelFluid> containerExtraSlot =
				new ContainerExtraSlotDatamodel<DatamodelFluid>(fluidTank, 152, 16, 16, 52);
			containerExtraSlot.renderer = RendererFluidSlot.instance;
			container.addContainerExtraSlot(containerExtraSlot, getName(fluidTank));
		}

	}

	@Override
	protected DatamodelFluid[] getFluidTank(ForgeDirection arg0)
	{
		return new DatamodelFluid[] {
			fluidTank
		};
	}

	@Override
	protected DatamodelFluid getFluidTankDrain(ForgeDirection arg0)
	{
		return fluidTank;
	}

	@Override
	protected DatamodelFluid getFluidTankDrain(ForgeDirection arg0, Fluid arg1)
	{
		return fluidTank;
	}

	@Override
	protected DatamodelFluid getFluidTankFill(ForgeDirection arg0, Fluid arg1)
	{
		return fluidTank;
	}

}
