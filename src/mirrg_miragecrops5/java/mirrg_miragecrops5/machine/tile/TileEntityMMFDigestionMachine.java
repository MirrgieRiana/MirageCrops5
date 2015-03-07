package mirrg_miragecrops5.machine.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.IntConsumer;

import mirrg.mir50.datamodels.DatamodelDirection;
import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir51.modding.HelpersSide;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.SupplierPositionContainerFlow;
import mirrg_miragecrops5.fairytype.HelpersFairyType;
import mirrg_miragecrops5.machine.container.SlotProcessing;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import api.mirrg_miragecrops5.recipes.APIRegistryRecipe;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IMatcherRecipeFuel;

public class TileEntityMMFDigestionMachine extends TileEntityMMFEasySolid
{

	public final IInventoryMir51 inventoryInMaterial;
	public final IInventoryMir51 inventoryInMaterialProcessing;

	public final DatamodelDirection direction;

	public TileEntityMMFDigestionMachine()
	{
		inventoryInMaterial = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 2), "inventoryInMaterial");
		inventoryInMaterialProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInMaterialProcessing");

		inventoryChain.add(inventoryInMaterial);
		inventoryChain.add(inventoryInMaterialProcessing);

		direction = add(new DatamodelDirection(this::markDirty, ForgeDirection.EAST.ordinal()), "direction");
	}

	@Override
	public String getDefaultName()
	{
		return "container.miragecrops5.mmfDigestionMachine";
	}

	@Override
	protected int[] getFairyValues()
	{
		return new int[] {
			0, 0, -7, 0, 0, 0,
		};
	}

	@Override
	protected IInventoryMir51[] getInventoryAccessible(int side)
	{
		return new IInventoryMir51[] {
			inventoryOut,
			inventoryInMaterial,
		};
	}

	@Override
	protected IInventoryMir51[] getInventoryInsert(int side, ItemStack itemStack)
	{
		return new IInventoryMir51[] {
			inventoryInMaterial,
		};
	}

	@Override
	protected void prepareContainerSlots(ContainerMir52 container)
	{
		super.prepareContainerSlots(container);

		container.addInventory(inventoryInMaterial,
			new SupplierPositionContainerFlow(LEFT + 9 * 4, TOP_CHEST + 9 * 2, SHIFT, SHIFT, 9), true);
		container.addInventory(inventoryInMaterialProcessing,
			(inventory, index, x, y) -> new SlotProcessing(inventory, index, x, y),
			new SupplierPositionContainerFlow(LEFT + 9 * 8, TOP_CHEST + 9 * 2, SHIFT, SHIFT, 9), false);

		container.setTransferInventories(inventoryInMaterial, inventoryHandle.get(), inventoryPlayer.get());
		container.setTransferInventories(inventoryPlayer.get(), inventoryFairy, inventoryFairyFuel, inventoryInMaterial);
		container.setTransferInventories(inventoryHandle.get(), inventoryFairy, inventoryFairyFuel, inventoryInMaterial);
	}

	@Override
	protected void tick()
	{
		if (HelpersSide.helper(this).isRemote()) return;

		ProcessingManager.bringRight(inventoryInMaterial);

		if (HelpersFairyType.isNotNegative(fairyValues.getIncreaser())) {

			new ProcessingManager(
				energyTankProcessing,
				new IInventoryMir51[] {
					inventoryInMaterialProcessing,
				},
				inventoryOutProcessing,
				inventoryOut) {

				@Override
				protected void tryStartProcess(IntConsumer onStart)
				{
					ItemStack itemStack = getLastStack(inventoryInMaterial);
					if (itemStack == null) return;

					Optional<IMatcherRecipeFuel> optionalMatcher = APIRegistryRecipe.registryRecipeFoodValue.matcher(itemStack);
					if (!optionalMatcher.isPresent()) return;
					IMatcherRecipeFuel matcher = optionalMatcher.get();

					int foodValue = matcher.getOutput();
					if (foodValue <= 0) return;

					List<ItemStack> outputs = new ArrayList<>();
					outputs.add(HelpersOreDictionary.copyOrThrow("craftingFairyWastesTier1", foodValue));

					{
						// startable

						// 素材を必要数取り出して進行中スロットに設置する
						ItemStack out = matcher.consume();

						inventoryInMaterial.getInventoryCell(inventoryInMaterial.getSizeInventory() - 1).decrStackSize(0);
						inventoryInMaterialProcessing.getInventoryCell(0).setInventorySlotContents(out);

						// 出力を一時バッファに設置する
						for (int i = 0; i < Math.min(inventoryOutProcessing.getSizeInventory(), outputs.size()); i++) {
							inventoryOutProcessing.setInventorySlotContents(i, outputs.get(i).copy());
						}

						onStart.accept((40 * foodValue) * 1000);
					}
				}

				@Override
				protected void processTick(DatamodelEnergy energyTankProcessing)
				{
					long need = energyTankProcessing.getCapacity() - energyTankProcessing.getAmount();

					need = Math.min(1000, need);

					int rate = 1;

					long pop = popFairyFuel(0, need * rate);

					energyTankProcessing.setAmount(energyTankProcessing.getAmount() + pop / rate);
				}

			}.tick();

		}

	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
		if (itemStack.hasDisplayName()) setCustomInventoryName(itemStack.getDisplayName());

		int l = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (l == 0) direction.direction = 2;
		else if (l == 1) direction.direction = 5;
		else if (l == 2) direction.direction = 3;
		else if (l == 3) direction.direction = 4;

	}

}
