package mirrg_miragecrops5.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.function.ToIntFunction;

import mirrg.mir50.datamodels.DatamodelEnergy;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import mirrg.mir51.inventory.IInventoryMir51;
import mirrg.mir51.inventory.InventoryMir51Base;
import mirrg.mir51.modding.HelpersSide;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.SupplierPositionContainerFlow;
import mirrg_miragecrops5.fairytype.HelpersFairyType;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class TileEntityMMFDigestionMachine extends TileEntityMMFEasy
{

	public final IInventoryMir51 inventoryInMaterial;
	public final IInventoryMir51 inventoryInMaterialProcessing;

	public TileEntityMMFDigestionMachine()
	{
		inventoryInMaterial = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 2), "inventoryInMaterial");
		inventoryInMaterialProcessing = add(new InventoryMir51Base(this::markDirty, getSupplierPosition(), 1), "inventoryInMaterialProcessing");

		inventoryChain.add(inventoryInMaterial);
		inventoryChain.add(inventoryInMaterialProcessing);
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
			-19, 0, -43, 0, 0, 0,
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

	public static ArrayList<ToIntFunction<ItemStack>> handlers_RecipeDigestionMachine = new ArrayList<>();

	public static int getFoodValue(ItemStack itemStack)
	{
		int theValue = 0;
		for (ToIntFunction<ItemStack> handler : handlers_RecipeDigestionMachine) {
			theValue = Math.max(theValue, handler.applyAsInt(itemStack));
		}
		return theValue;
	}

	//protected static ArrayList<Tuple<Tuple<OreMatcher, Integer>, Integer>> recipes = new ArrayList<>();

	static {

		handlers_RecipeDigestionMachine.add(is -> is.getItem() instanceof ItemFood ? ((ItemFood) is.getItem()).func_150905_g(is) : 0);

		/*
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.apple), 1), 9));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.baked_potato), 1), 9));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.beef), 1), 9));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.bread), 1), 9));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.cake), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.carrot), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.carrot_on_a_stick), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.chicken), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.cooked_beef), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.cooked_chicken), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.cooked_fished), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.cooked_porkchop), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.cookie), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.egg), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.fish), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.ghast_tear), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.golden_apple), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.golden_carrot), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.melon), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.melon_seeds), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.milk_bucket), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.mushroom_stew), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.nether_wart), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.poisonous_potato), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.porkchop), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.potato), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.pumpkin_pie), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.pumpkin_seeds), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.reeds), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.rotten_flesh), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.speckled_melon), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.sugar), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.wheat), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Items.wheat_seeds), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Blocks.brown_mushroom), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Blocks.brown_mushroom_block), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Blocks.cactus), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Blocks.cake), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Blocks.carrots), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Blocks.dragon_egg), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Blocks.melon_block), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(Blocks.pumpkin), 1), () -> new ItemStack(Blocks.dirt)));
		recipes.add(new Tuple<>(new Tuple<>(new OreMatcher(new ItemStack(Items.dye, 1, 3)), 1), () -> new ItemStack(Blocks.dirt)));
		*/
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

					int foodValue = getFoodValue(itemStack);
					if (foodValue <= 0) return;

					List<ItemStack> outputs = new ArrayList<>();
					outputs.add(HelpersOreDictionary.copyOrThrow("craftingFairyWastesTier1", foodValue));
					int cost = 1;

					if (outputs != null) {
						if (itemStack.stackSize >= cost) {
							// startable

							// 素材を必要数取り出して進行中スロットに設置する
							ItemStack copy = itemStack.copy();
							inventoryInMaterial.getInventoryCell(inventoryInMaterial.getSizeInventory() - 1).decrStackSize(cost);
							copy.stackSize = cost;
							inventoryInMaterialProcessing.getInventoryCell(0).setInventorySlotContents(copy);

							// 出力を一時バッファに設置する
							for (int i = 0; i < Math.min(inventoryOutProcessing.getSizeInventory(), outputs.size()); i++) {
								inventoryOutProcessing.setInventorySlotContents(i, outputs.get(i).copy());
							}

							onStart.accept((40 * foodValue) * 1000);
						}
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
}
