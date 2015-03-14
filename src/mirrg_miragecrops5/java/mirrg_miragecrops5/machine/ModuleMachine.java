package mirrg_miragecrops5.machine;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

import mirrg.h.struct.Struct1;
import mirrg.h.struct.Tuple;
import mirrg.he.math.HelpersColor;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.adaptors.AdaptorBlockIconRegister;
import mirrg.mir50.block.adaptors.AdaptorBlockNameAutonomy;
import mirrg.mir50.block.adaptors.AdaptorBlockNameExtra;
import mirrg.mir50.block.adaptors.AdaptorBlockTileEntityAutonomy;
import mirrg.mir50.block.adaptors.IAdaptorBlockNameExtra;
import mirrg.mir50.guihandler.GuiHandler;
import mirrg.mir50.guihandler.IGuiProvider;
import mirrg.mir50.icon.HelpersIcon;
import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import mirrg.mir50.render.block.switcher.AdaptorBlockRenderingSwitcher;
import mirrg.mir50.render.block.switcher.AdaptorBlockRenderingSwitcherFromHandler;
import mirrg.mir50.render.block.switcher.AdaptorBlockRenderingSwitcherMulti;
import mirrg.mir50.render.block.switcher.HelpersRenderBlockRenderingSwitcher;
import mirrg.mir50.render.block.switcher.IAdaptorBlockRenderingSwitcher;
import mirrg.mir51.block.multi.AdaptorBlockHarvestMetaBlock;
import mirrg.mir51.block.multi.AdaptorBlockSubBlocksMetaBlock;
import mirrg.mir51.block.multi.ContainerMetaBlock;
import mirrg.mir51.block.multi.HelpersBlockMulti;
import mirrg.mir51.block.multi.ItemBlockMulti;
import mirrg.mir51.block.multi.MetaBlock;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.mir51.loaders.LoaderGuiHandler;
import mirrg.mir51.loaders.LoaderOreDictionary;
import mirrg.mir51.loaders.LoaderRecipe;
import mirrg.mir51.loaders.LoaderSimpleNetworkWrapper;
import mirrg.mir51.loaders.LoaderTileEntity;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRenderingAutonomy;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRenderingOverriding;
import mirrg.mir51.render.block.multiple.HelpersBlockMultipleRendering;
import mirrg.mir51.render.block.multiple.IConsumerMultipleRendering;
import mirrg.mir52.gui.HelpersContainerMir52;
import mirrg.mir52.render.block.multiple.multi.HelpersBlockMultipleRenderingMulti;
import mirrg.p.virtualclass.IVirtualClass;
import mirrg_miragecrops5.ModMirageCrops;
import mirrg_miragecrops5.ModuleCore;
import mirrg_miragecrops5.ModuleMirageCropsAbstract;
import mirrg_miragecrops5.fairytype.RegistryFairyType;
import mirrg_miragecrops5.machine.recipe.fuel.RegistryRecipeFuel;
import mirrg_miragecrops5.machine.recipe.writingdesk.RegistryRecipeWritingDesk;
import mirrg_miragecrops5.machine.tile.TileEntityMMFCarbonizationFurnace;
import mirrg_miragecrops5.machine.tile.TileEntityMMFDigestionMachine;
import mirrg_miragecrops5.machine.tile.TileEntityMMFFurnace;
import mirrg_miragecrops5.machine.tile.TileEntityMMFMacerator;
import mirrg_miragecrops5.machine.tile.TileEntityMMFSpiritDeveloper;
import mirrg_miragecrops5.machine.tile.TileEntityMMFUrineMaker;
import mirrg_miragecrops5.machine.tile.TileEntityMachineMirageFairy;
import mirrg_miragecrops5.machine.tile.TileEntityWritingDesk;
import mirrg_miragecrops5.material.HelpersModuleMaterial;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import api.mirrg_miragecrops5.recipes.APIRegistryRecipe;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IHandlerRecipeFuel;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IMatcherRecipeFuel;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IRecipeFuel;
import api.mirrg_miragecrops5.recipes.InterfacesRecipeFuel.IRegistryRecipeFuel;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModuleMachine extends ModuleMirageCropsAbstract
{

	public static LoaderGuiHandler loaderGuiHandler = new LoaderGuiHandler(i -> new GuiHandler(i));

	public static LoaderBlock loaderBlock_machineMirageFairy = new LoaderBlock();
	public static LoaderBlock loaderBlock_instrumentMirageFairy = new LoaderBlock();

	public static LoaderSimpleNetworkWrapper loaderSimpleNetworkWrapper = new LoaderSimpleNetworkWrapper();
	public static int loaderSimpleNetworkWrapper_counter = 0;

	//

	protected ArrayList<Tuple<String, Supplier<ItemStack>>> scheduleRegisterOreDictionary = new ArrayList<>();
	protected ArrayList<Runnable> scheduleRegisterRecipe = new ArrayList<>();

	@SubscribeEvent
	public void handle(TextureStitchEvent.Pre event)
	{
		if (event.map.getTextureType() == HelpersIcon.BLOCKS) {
			{
				Fluid fluid = FluidRegistry.getFluid("urine");
				fluid.setIcons(
					event.map.registerIcon("miragecrops5:" + fluid.getName() + "_still"),
					event.map.registerIcon("miragecrops5:" + fluid.getName() + "_flow"));
			}
			{
				Fluid fluid = FluidRegistry.getFluid("spinachjuice");
				fluid.setIcons(
					event.map.registerIcon("miragecrops5:" + fluid.getName() + "_still"),
					event.map.registerIcon("miragecrops5:" + fluid.getName() + "_flow"));
			}
		}
	}

	public ModuleMachine()
	{

		add(new Loader<Void>() {
			@Override
			protected void loadThisLoader(EnumLoadEventTiming loadEvent)
			{
				if (loadEvent == EnumLoadEventTiming.Created) {

					APIRegistryRecipe.registryRecipeFairyFuel(new RegistryRecipeFuel());
					APIRegistryRecipe.registryRecipeFoodValue(new RegistryRecipeFuel());
					APIRegistryRecipe.registryRecipeWritingDesk(new RegistryRecipeWritingDesk());

					loadCompleted();
				}
			}
		});

		add(new LoaderRecipe(() -> {

			APIRegistryRecipe.registryRecipeFairyFuel.addRecipe("blockCalcite", 9000);
			APIRegistryRecipe.registryRecipeFairyFuel.addRecipe("gemCalcite", 1000);
			APIRegistryRecipe.registryRecipeFairyFuel.addRecipe("dustCalcite", 1000);
			APIRegistryRecipe.registryRecipeFairyFuel.addRecipe("dustSmallCalcite", 250);
			APIRegistryRecipe.registryRecipeFairyFuel.addRecipe("dustTinyCalcite", 111);

		}));

		add(new LoaderRecipe(() -> {

			IRegistryRecipeFuel rrfv = APIRegistryRecipe.registryRecipeFoodValue;

			rrfv.addHandler(new IHandlerRecipeFuel() {
				@Override
				public Optional<IMatcherRecipeFuel> matcher(ItemStack input)
				{
					if (input == null) return Optional.empty();
					if (!(input.getItem() instanceof ItemFood)) return Optional.empty();

					return Optional.of(new IMatcherRecipeFuel() {
						@Override
						public Integer getOutput()
						{
							return ((ItemFood) input.getItem()).func_150905_g(input);
						}

						@Override
						public ItemStack consume()
						{
							input.stackSize--;
							ItemStack cost = input.copy();
							cost.stackSize = 1;
							return cost;
						}
					});
				}

				@Override
				public Stream<IRecipeFuel> getRecipesToShow()
				{
					return new ArrayList<IRecipeFuel>().stream();
				}
			});

			rrfv.addRecipe(new ItemStack(Items.nether_wart), 3, 1);
			rrfv.addRecipe(new ItemStack(Items.fermented_spider_eye), 3, 1);

			rrfv.addRecipe(new ItemStack(Items.pumpkin_seeds), 2, 1);
			rrfv.addRecipe(new ItemStack(Items.melon_seeds), 2, 1);
			rrfv.addRecipe(new ItemStack(Items.wheat_seeds), 2, 1);
			rrfv.addRecipe(new ItemStack(Items.dye, 1, 3), 2, 1);
			rrfv.addRecipe(new ItemStack(Items.wheat), 2, 1);
			rrfv.addRecipe(new ItemStack(Blocks.hay_block), 2, 9);
			rrfv.addRecipe(new ItemStack(Items.egg), 2, 1);
			rrfv.addRecipe(new ItemStack(Items.reeds), 2, 1);
			rrfv.addRecipe(new ItemStack(Items.dye, 1, 0), 2, 1);

			rrfv.addRecipe(new ItemStack(Blocks.cactus), 1);
			rrfv.addRecipe(new ItemStack(Items.dye, 1, 2), 1);

			rrfv.addRecipe(new ItemStack(Items.speckled_melon), 2);
			rrfv.addRecipe(new ItemStack(Blocks.melon_block), 18);

			rrfv.addRecipe(new ItemStack(Items.milk_bucket), 2);
			rrfv.addRecipe(new ItemStack(Items.carrot_on_a_stick), 4);
			rrfv.addRecipe(new ItemStack(Items.cake), 12);
			rrfv.addRecipe(new ItemStack(Blocks.pumpkin), 5);
			rrfv.addRecipe(new ItemStack(Blocks.lit_pumpkin), 5);

		}));

		add(new LoaderRecipe(() -> {

			APIRegistryRecipe.registryRecipeWritingDesk.addRecipe(
				new ItemStack(Items.writable_book),
				RegistryFairyType.registry.get("cobblestone"),
				HelpersOreDictionary.copyOrThrow("craftingBookMirageToolIndustrial"));

			APIRegistryRecipe.registryRecipeWritingDesk.addRecipe(
				new ItemStack(Items.writable_book),
				RegistryFairyType.registry.get("stone"),
				HelpersOreDictionary.copyOrThrow("craftingBookMirageFairy"));

		}));

		loaderSimpleNetworkWrapper.channelName = ModMirageCrops.MODID;
		add(loaderSimpleNetworkWrapper);

		add(HelpersContainerMir52.init());

		loaderGuiHandler.guiId = 1;
		loaderGuiHandler.supplierMod = () -> ModMirageCrops.instance;
		add(loaderGuiHandler);

		add(new LoaderTileEntity(TileEntityMachineMirageFairy.class, "MachineMirageFairy"));
		add(new LoaderTileEntity(TileEntityMMFFurnace.class, "MMFFurnace"));
		add(new LoaderTileEntity(TileEntityMMFMacerator.class, "MMFMacerator"));
		add(new LoaderTileEntity(TileEntityMMFSpiritDeveloper.class, "MMFSpiritDeveloper"));
		add(new LoaderTileEntity(TileEntityMMFCarbonizationFurnace.class, "MMFCarbonizationFurnace"));
		add(new LoaderTileEntity(TileEntityMMFDigestionMachine.class, "MMFDigestionMachine"));
		add(new LoaderTileEntity(TileEntityWritingDesk.class, "WritingDesk"));
		add(new LoaderTileEntity(TileEntityMMFUrineMaker.class, "MMFUrineMaker"));

		{
			Fluid fluid = new Fluid("urine");
			FluidRegistry.registerFluid(fluid);
		}
		{
			Fluid fluid = new Fluid("spinachjuice");
			FluidRegistry.registerFluid(fluid);
		}

		HelpersRenderBlockRenderingSwitcher.init(this);

		process_loaderBlock(loaderBlock_instrumentMirageFairy, ModuleCore.loaderCreativeTab, ItemBlockMulti.class, "instrumentMirageFairy", (blockMir50) -> {

			ContainerMetaBlock containerMetaBlock = new ContainerMetaBlock(16);

			HelpersBlockMulti.make(blockMir50, blockMir50, containerMetaBlock);
			HelpersBlockMultipleRendering.make(blockMir50, blockMir50);
			HelpersBlockMultipleRenderingMulti.make(blockMir50, blockMir50, containerMetaBlock);
			HelpersRenderBlockRenderingSwitcher.make(blockMir50, blockMir50, true, false, false);

			blockMir50.virtualClass.override(new AdaptorBlockRenderingSwitcherMulti(blockMir50, containerMetaBlock));

			blockMir50.setHardness(1.5F);
			blockMir50.setResistance(10.0F);

			blockMir50.setBlockTextureName("minecraft:stone");

			blockMir50.virtualClass.register(IGuiProvider.class);
			blockMir50.virtualClass.override(new GuiProviderTileEntityMir53());

			blockMir50.virtualClass.override(new AdaptorBlockEventsTileEntityMMF(blockMir50, blockMir50));

			blockMir50.setLightOpacity(0);

			//

			{
				MetaBlock metaBlock = createMetaBlock(blockMir50, 0, "mmfSpiritDeveloper",
					() -> new TileEntityMMFSpiritDeveloper(), a -> {
						a.appendIcon("miragecrops5:004");
					});

				MakerMetaBlockMMFSpiritDeveloper.makeRenderer(blockMir50, metaBlock);

				addMetaBlock(containerMetaBlock, metaBlock);
			}
			addMetaBlock(containerMetaBlock, createMetaBlock(blockMir50, 1, "writingDesk",
				() -> new TileEntityWritingDesk(), a -> {
					a.appendIcon("miragecrops5:machineMirageFairy_0_0");
					a.appendIcon("miragecrops5:machineMirageFairy_1", 0x8E45F0);
				}));

		});

		process_loaderBlock(loaderBlock_machineMirageFairy, ModuleCore.loaderCreativeTab, ItemBlockMulti.class, "machineMirageFairy", (blockMir50) -> {

			ContainerMetaBlock containerMetaBlock = new ContainerMetaBlock(16);

			HelpersBlockMulti.make(blockMir50, blockMir50, containerMetaBlock);
			HelpersBlockMultipleRendering.make(blockMir50, blockMir50);
			HelpersBlockMultipleRenderingMulti.make(blockMir50, blockMir50, containerMetaBlock);
			HelpersRenderBlockRenderingSwitcher.make(blockMir50, blockMir50);

			blockMir50.virtualClass.override(new AdaptorBlockRenderingSwitcherMulti(blockMir50, containerMetaBlock));

			blockMir50.setHardness(1.5F);
			blockMir50.setResistance(10.0F);

			blockMir50.setBlockTextureName("minecraft:stone");

			blockMir50.virtualClass.register(IGuiProvider.class);
			blockMir50.virtualClass.override(new GuiProviderTileEntityMir53());

			blockMir50.virtualClass.override(new AdaptorBlockEventsTileEntityMMF(blockMir50, blockMir50));

			//

			addMetaBlock(containerMetaBlock, createMetaBlock(blockMir50, 0, "machineMirageFairy",
				() -> new TileEntityMachineMirageFairy(), a -> {
					a.appendIcon("miragecrops5:machineMirageFairy_0_3");
					a.appendIcon("miragecrops5:machineMirageFairy_1", 0xE8C831);
				}));
			{
				MetaBlock metaBlock = createMetaBlock(blockMir50, 1, "mmfFurnace",
					() -> new TileEntityMMFFurnace(), a -> {});

				Struct1<IIcon> iconTop = new Struct1<>(null);
				Struct1<IIcon> iconSide = new Struct1<>(null);
				Struct1<IIcon> iconFrame = new Struct1<>(null);
				Struct1<IIcon> iconFront = new Struct1<>(null);
				Struct1<IIcon> iconFrontFrame = new Struct1<>(null);
				metaBlock.virtualClass.override(new AdaptorBlockIconRegister(blockMir50, metaBlock, iconRegister -> {
					iconTop.x = iconRegister.registerIcon("miragecrops5:machineMirageFairy_0_1");
					iconSide.x = iconRegister.registerIcon("miragecrops5:blockCalcite");
					iconFrame.x = iconRegister.registerIcon("miragecrops5:machineMirageFairy_1");
					iconFront.x = iconRegister.registerIcon("miragecrops5:machineMirageFairy_3_furnace");
					iconFrontFrame.x = iconRegister.registerIcon("miragecrops5:machineMirageFairy_4_furnace");
				}, false));
				IntFunction<IntFunction<Consumer<IConsumerMultipleRendering>>> defaultConsumer = direction -> side -> handler -> {
					if (HelpersDirection.subtract(side, direction) == HelpersDirection.UP) {
						handler.accept(iconTop.x);
					} else {
						handler.accept(iconSide.x);
					}
					if (HelpersDirection.subtract(side, direction) == HelpersDirection.FRONT) {
						handler.accept(iconFront.x);
						handler.accept(iconFrontFrame.x, HelpersColor.multiplicate(
							HelpersModuleMaterial.registryMaterialProperty.getColor("iron"), 1.2));
					}
					handler.accept(iconFrame.x, HelpersColor.multiplicate(
						HelpersModuleMaterial.registryMaterialProperty.getColor("iron"), 0.9));
				};
				metaBlock.virtualClass.override(new AdaptorBlockMultipleRenderingOverriding(blockMir50, metaBlock) {

					@Override
					@SideOnly(Side.CLIENT)
					public Consumer<IConsumerMultipleRendering> getMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, int side)
					{
						TileEntity tileEntity = blockAccess.getTileEntity(x, y, z);
						if (tileEntity != null) {
							if (tileEntity instanceof TileEntityMMFFurnace) {
								TileEntityMMFFurnace tileEntityMMFFurnace = (TileEntityMMFFurnace) tileEntity;

								return defaultConsumer.apply(tileEntityMMFFurnace.direction.direction).apply(side);
							}
						}

						return defaultConsumer.apply(ForgeDirection.EAST.ordinal()).apply(side);
					}

					@Override
					@SideOnly(Side.CLIENT)
					public Consumer<IConsumerMultipleRendering> getMultipleRendering(int metadata, int side)
					{
						return defaultConsumer.apply(ForgeDirection.EAST.ordinal()).apply(side);
					}

				});
				addMetaBlock(containerMetaBlock, metaBlock);
			}
			addMetaBlock(containerMetaBlock, createMetaBlock(blockMir50, 2, "mmfMacerator",
				() -> new TileEntityMMFMacerator(), a -> {
					a.appendIcon("miragecrops5:fairyblock_0");
				}));
			addMetaBlock(containerMetaBlock, createMetaBlock(blockMir50, 4, "mmfCarbonizationFurnace",
				() -> new TileEntityMMFCarbonizationFurnace(), a -> {
					a.appendIcon("miragecrops5:004");
					a.appendIcon("miragecrops5:005", 0x4E2914);
					//0x008900);
			}));
			{
				MetaBlock metaBlock = createMetaBlock(blockMir50, 5, "mmfDigestionMachine",
					() -> new TileEntityMMFDigestionMachine(), a -> {});

				Struct1<IIcon> iconTop = new Struct1<>(null);
				Struct1<IIcon> iconBack = new Struct1<>(null);
				Struct1<IIcon> iconSide = new Struct1<>(null);
				Struct1<IIcon> iconFront = new Struct1<>(null);
				metaBlock.virtualClass.override(new AdaptorBlockIconRegister(blockMir50, metaBlock, iconRegister -> {
					iconTop.x = iconRegister.registerIcon("minecraft:pumpkin_top");
					iconBack.x = iconRegister.registerIcon("minecraft:pumpkin_side");
					iconSide.x = iconRegister.registerIcon("miragecrops5:pumpkin_side_window");
					iconFront.x = iconRegister.registerIcon("miragecrops5:pumpkin_front_entrance");
				}, false));
				IntFunction<IntFunction<Consumer<IConsumerMultipleRendering>>> defaultConsumer = direction -> side -> handler -> {
					if (HelpersDirection.subtract(side, direction) == HelpersDirection.UP) {
						handler.accept(iconTop.x);
					} else if (HelpersDirection.subtract(side, direction) == HelpersDirection.DOWN) {
						handler.accept(iconTop.x);
					} else if (HelpersDirection.subtract(side, direction) == HelpersDirection.FRONT) {
						handler.accept(iconFront.x);
					} else if (HelpersDirection.subtract(side, direction) == HelpersDirection.BACK) {
						handler.accept(iconBack.x);
					} else {
						handler.accept(iconSide.x);
					}
				};
				metaBlock.virtualClass.override(new AdaptorBlockMultipleRenderingOverriding(blockMir50, metaBlock) {

					@Override
					@SideOnly(Side.CLIENT)
					public Consumer<IConsumerMultipleRendering> getMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, int side)
					{
						TileEntity tileEntity = blockAccess.getTileEntity(x, y, z);
						if (tileEntity != null) {
							if (tileEntity instanceof TileEntityMMFDigestionMachine) {
								TileEntityMMFDigestionMachine tileEntityMMFFurnace = (TileEntityMMFDigestionMachine) tileEntity;
								int direction = tileEntityMMFFurnace.direction.direction;

								return defaultConsumer.apply(tileEntityMMFFurnace.direction.direction).apply(side);
							}
						}

						return defaultConsumer.apply(ForgeDirection.EAST.ordinal()).apply(side);
					}

					@Override
					@SideOnly(Side.CLIENT)
					public Consumer<IConsumerMultipleRendering> getMultipleRendering(int metadata, int side)
					{
						return defaultConsumer.apply(ForgeDirection.EAST.ordinal()).apply(side);
					}

				});
				addMetaBlock(containerMetaBlock, metaBlock);
			}
			addMetaBlock(containerMetaBlock, createMetaBlock(blockMir50, 7, "mmfUrineMaker",
				() -> new TileEntityMMFUrineMaker(), a -> {
					a.appendIcon("miragecrops5:machineMirageFairy_0_0");
					a.appendIcon("miragecrops5:machineMirageFairy_1", 0xddff55);
				}));

		});

		add(new LoaderOreDictionary(() -> {
			for (Tuple<String, Supplier<ItemStack>> entry : scheduleRegisterOreDictionary) {
				OreDictionary.registerOre(entry.getX(), entry.getY().get());
			}
		}));

		add(new LoaderRecipe(() -> {
			scheduleRegisterRecipe.forEach(r -> r.run());
		}));

	}

	private static void addMetaBlock(ContainerMetaBlock containerMetaBlock, MetaBlock metaBlock)
	{
		containerMetaBlock.add(metaBlock.getMeta(), metaBlock);
	}

	private MetaBlock createMetaBlock(
		BlockMir50 blockMir50,
		int metaId,
		String unlocalizedName,
		Supplier<TileEntity> supplierTileEntity,
		Consumer<AdaptorBlockMultipleRenderingAutonomy> consumerA)
	{
		MetaBlock metaBlock = new MetaBlock(blockMir50, metaId);

		metaBlock.virtualClass.override(new AdaptorBlockSubBlocksMetaBlock(blockMir50, metaBlock, metaBlock));
		metaBlock.virtualClass.override(new AdaptorBlockHarvestMetaBlock(blockMir50, metaBlock));

		metaBlock.virtualClass.override(new AdaptorBlockNameAutonomy(blockMir50, metaBlock, unlocalizedName));
		metaBlock.virtualClass.register(IAdaptorBlockNameExtra.class, new AdaptorBlockNameExtra(blockMir50, metaBlock));

		metaBlock.virtualClass.register(IAdaptorBlockRenderingSwitcher.class, new AdaptorBlockRenderingSwitcher());
		metaBlock.virtualClass.override(new AdaptorBlockRenderingSwitcherFromHandler(metaBlock, HelpersBlockMultipleRendering.loader));

		consumerA.accept(HelpersBlockMultipleRendering.makeAutonomy(metaBlock, blockMir50));

		makeMetaBlockHasTileEntity(metaBlock, blockMir50, supplierTileEntity);

		scheduleRegisterOreDictionary.add(new Tuple<>(unlocalizedName, () -> new ItemStack(blockMir50, 1, metaId)));

		return metaBlock;
	}

	private void makeMetaBlockHasTileEntity(IVirtualClass virtualClass, BlockMir50 blockMir50, Supplier<TileEntity> supplierTileEntity)
	{
		virtualClass.getVirtualClass().override(
			new AdaptorBlockTileEntityAutonomy(blockMir50, virtualClass, (world, metadata) -> {
				return supplierTileEntity.get();
			}));
	}

}
