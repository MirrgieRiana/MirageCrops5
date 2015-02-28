package mirrg_miragecrops5.machine;

import java.util.function.Supplier;

import mirrg.mir50.block.AdaptorBlockEventsOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.adaptors.AdaptorBlockTileEntityAutonomy;
import mirrg.mir50.guihandler.GuiHandler;
import mirrg.mir50.guihandler.IGuiProvider;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.mir51.loaders.LoaderGuiHandler;
import mirrg.mir51.loaders.LoaderRecipe;
import mirrg.mir51.loaders.LoaderSimpleNetworkWrapper;
import mirrg.mir51.loaders.LoaderTileEntity;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRenderingAutonomy;
import mirrg.mir51.render.block.multiple.HelpersBlockMultipleRendering;
import mirrg.mir52.gui.ContainerMir52;
import mirrg.mir52.gui.GuiMir52;
import mirrg.mir52.gui.HelpersContainerMir52;
import mirrg.mir53.tile.TileEntityMir53;
import mirrg.p.virtualclass.IVirtualClass;
import mirrg_miragecrops5.ModMirageCrops;
import mirrg_miragecrops5.ModuleCore;
import mirrg_miragecrops5.ModuleMirageCropsAbstract;
import mirrg_miragecrops5.machine.tile.TileEntityMMF;
import mirrg_miragecrops5.machine.tile.TileEntityMMFCarbonizationFurnace;
import mirrg_miragecrops5.machine.tile.TileEntityMMFDigestionMachine;
import mirrg_miragecrops5.machine.tile.TileEntityMMFFurnace;
import mirrg_miragecrops5.machine.tile.TileEntityMMFMacerator;
import mirrg_miragecrops5.machine.tile.TileEntityMMFSpiritDeveloper;
import mirrg_miragecrops5.machine.tile.TileEntityMachineMirageFairy;
import mirrg_miragecrops5.machine.tile.TileEntityWritingDesk;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import api.mirrg_miragecrops5.recipes.APIRegistryRecipe;

public class ModuleMachine extends ModuleMirageCropsAbstract
{

	public static LoaderGuiHandler loaderGuiHandler = new LoaderGuiHandler(i -> new GuiHandler(i));

	public static LoaderBlock loaderBlock_machineMirageFairy = new LoaderBlock();
	public static LoaderBlock loaderBlock_mmfFurnace = new LoaderBlock();
	public static LoaderBlock loaderBlock_mmfMacerator = new LoaderBlock();
	public static LoaderBlock loaderBlock_mmfSpiritDeveloper = new LoaderBlock();
	public static LoaderBlock loaderBlock_mmfCarbonizationFurnace = new LoaderBlock();
	public static LoaderBlock loaderBlock_mmfDigestionMachine = new LoaderBlock();
	public static LoaderBlock loaderBlock_writingDesk = new LoaderBlock();

	public static LoaderSimpleNetworkWrapper loaderSimpleNetworkWrapper = new LoaderSimpleNetworkWrapper();
	public static int loaderSimpleNetworkWrapper_counter = 0;

	public ModuleMachine()
	{

		APIRegistryRecipe.set(new RegistryRecipeFairyFuel());

		loaderSimpleNetworkWrapper.channelName = ModMirageCrops.MODID;
		add(loaderSimpleNetworkWrapper);

		add(HelpersContainerMir52.init());

		loaderGuiHandler.guiId = 1;
		loaderGuiHandler.supplierMod = () -> ModMirageCrops.instance;
		add(loaderGuiHandler);

		add(new LoaderTileEntity(TileEntityMachineMirageFairy.class, "MachineMirageFairy"));
		process_loaderBlock(loaderBlock_machineMirageFairy, ModuleCore.loaderCreativeTab, ItemBlock.class, "machineMirageFairy", (blockMir50) -> {
			HelpersBlockMultipleRendering.make(blockMir50, blockMir50);

			AdaptorBlockMultipleRenderingAutonomy a = new AdaptorBlockMultipleRenderingAutonomy(blockMir50, blockMir50);
			a.appendIcon("miragecrops5:machineMirageFairy_0_3");
			a.appendIcon("miragecrops5:machineMirageFairy_1", 0xE8C831);
			blockMir50.virtualClass.override(a);

			makeBlockHasTileEntity(blockMir50, () -> new TileEntityMachineMirageFairy());
			blockMir50.virtualClass.override(new AdaptorBlockEventsTileEntityMMF(blockMir50, blockMir50));

			blockMir50.virtualClass.register(IGuiProvider.class);
			blockMir50.virtualClass.override(new GuiProviderTileEntityMir53());
		});

		add(new LoaderTileEntity(TileEntityMMFFurnace.class, "MMFFurnace"));
		process_loaderBlock(loaderBlock_mmfFurnace, ModuleCore.loaderCreativeTab, ItemBlock.class, "mmfFurnace", (blockMir50) -> {
			HelpersBlockMultipleRendering.make(blockMir50, blockMir50);

			AdaptorBlockMultipleRenderingAutonomy a = new AdaptorBlockMultipleRenderingAutonomy(blockMir50, blockMir50);
			a.appendIcon("miragecrops5:machineMirageFairy_0_1");
			a.appendIcon("miragecrops5:machineMirageFairy_1", 0x990000);
			blockMir50.virtualClass.override(a);

			makeBlockHasTileEntity(blockMir50, () -> new TileEntityMMFFurnace());
			blockMir50.virtualClass.override(new AdaptorBlockEventsTileEntityMMF(blockMir50, blockMir50));

			blockMir50.virtualClass.register(IGuiProvider.class);
			blockMir50.virtualClass.override(new GuiProviderTileEntityMir53());
		});

		add(new LoaderTileEntity(TileEntityMMFMacerator.class, "MMFMacerator"));
		process_loaderBlock(loaderBlock_mmfMacerator, ModuleCore.loaderCreativeTab, ItemBlock.class, "mmfMacerator", (blockMir50) -> {
			HelpersBlockMultipleRendering.make(blockMir50, blockMir50);

			AdaptorBlockMultipleRenderingAutonomy a = new AdaptorBlockMultipleRenderingAutonomy(blockMir50, blockMir50);
			a.appendIcon("miragecrops5:machineMirageFairy_0_2");
			a.appendIcon("miragecrops5:machineMirageFairy_1", 0xD8A817);
			blockMir50.virtualClass.override(a);

			makeBlockHasTileEntity(blockMir50, () -> new TileEntityMMFMacerator());
			blockMir50.virtualClass.override(new AdaptorBlockEventsTileEntityMMF(blockMir50, blockMir50));

			blockMir50.virtualClass.register(IGuiProvider.class);
			blockMir50.virtualClass.override(new GuiProviderTileEntityMir53());
		});

		add(new LoaderTileEntity(TileEntityMMFSpiritDeveloper.class, "MMFSpiritDeveloper"));
		process_loaderBlock(loaderBlock_mmfSpiritDeveloper, ModuleCore.loaderCreativeTab, ItemBlock.class, "mmfSpiritDeveloper", (blockMir50) -> {
			HelpersBlockMultipleRendering.make(blockMir50, blockMir50);

			AdaptorBlockMultipleRenderingAutonomy a = new AdaptorBlockMultipleRenderingAutonomy(blockMir50, blockMir50);
			a.appendIcon("miragecrops5:machineMirageFairy_0_3");
			a.appendIcon("miragecrops5:machineMirageFairy_1", 0x264797);
			blockMir50.virtualClass.override(a);

			makeBlockHasTileEntity(blockMir50, () -> new TileEntityMMFSpiritDeveloper());
			blockMir50.virtualClass.override(new AdaptorBlockEventsTileEntityMMF(blockMir50, blockMir50));

			blockMir50.virtualClass.register(IGuiProvider.class);
			blockMir50.virtualClass.override(new GuiProviderTileEntityMir53());
		});

		add(new LoaderTileEntity(TileEntityMMFCarbonizationFurnace.class, "MMFCarbonizationFurnace"));
		process_loaderBlock(loaderBlock_mmfCarbonizationFurnace, ModuleCore.loaderCreativeTab, ItemBlock.class, "mmfCarbonizationFurnace", (blockMir50) -> {
			HelpersBlockMultipleRendering.make(blockMir50, blockMir50);

			AdaptorBlockMultipleRenderingAutonomy a = new AdaptorBlockMultipleRenderingAutonomy(blockMir50, blockMir50);
			a.appendIcon("miragecrops5:machineMirageFairy_0_2");
			a.appendIcon("miragecrops5:machineMirageFairy_1", 0x3468F3);
			blockMir50.virtualClass.override(a);

			makeBlockHasTileEntity(blockMir50, () -> new TileEntityMMFCarbonizationFurnace());
			blockMir50.virtualClass.override(new AdaptorBlockEventsTileEntityMMF(blockMir50, blockMir50));

			blockMir50.virtualClass.register(IGuiProvider.class);
			blockMir50.virtualClass.override(new GuiProviderTileEntityMir53());
		});

		add(new LoaderTileEntity(TileEntityMMFDigestionMachine.class, "MMFDigestionMachine"));
		process_loaderBlock(loaderBlock_mmfDigestionMachine, ModuleCore.loaderCreativeTab, ItemBlock.class, "mmfDigestionMachine", (blockMir50) -> {
			HelpersBlockMultipleRendering.make(blockMir50, blockMir50);

			AdaptorBlockMultipleRenderingAutonomy a = new AdaptorBlockMultipleRenderingAutonomy(blockMir50, blockMir50);
			a.appendIcon("miragecrops5:machineMirageFairy_0_2");
			a.appendIcon("miragecrops5:machineMirageFairy_1", 0xF87B60);
			blockMir50.virtualClass.override(a);

			makeBlockHasTileEntity(blockMir50, () -> new TileEntityMMFDigestionMachine());
			blockMir50.virtualClass.override(new AdaptorBlockEventsTileEntityMMF(blockMir50, blockMir50));

			blockMir50.virtualClass.register(IGuiProvider.class);
			blockMir50.virtualClass.override(new GuiProviderTileEntityMir53());
		});

		add(new LoaderTileEntity(TileEntityWritingDesk.class, "WritingDesk"));
		process_loaderBlock(loaderBlock_writingDesk, ModuleCore.loaderCreativeTab, ItemBlock.class, "writingDesk", (blockMir50) -> {
			HelpersBlockMultipleRendering.make(blockMir50, blockMir50);

			AdaptorBlockMultipleRenderingAutonomy a = new AdaptorBlockMultipleRenderingAutonomy(blockMir50, blockMir50);
			a.appendIcon("miragecrops5:machineMirageFairy_0_0");
			a.appendIcon("miragecrops5:machineMirageFairy_1", 0x8E45F0);
			blockMir50.virtualClass.override(a);

			makeBlockHasTileEntity(blockMir50, () -> new TileEntityWritingDesk());
			blockMir50.virtualClass.override(new AdaptorBlockEventsTileEntityMMF(blockMir50, blockMir50));

			blockMir50.virtualClass.register(IGuiProvider.class);
			blockMir50.virtualClass.override(new GuiProviderTileEntityMir53());
		});

		add(new LoaderRecipe(() -> {

			APIRegistryRecipe.registryRecipeFairyFuel.addRecipe("gemCalcite", 1000);
			APIRegistryRecipe.registryRecipeFairyFuel.addRecipe("dustCalcite", 1000);
			APIRegistryRecipe.registryRecipeFairyFuel.addRecipe("dustSmallCalcite", 250);
			APIRegistryRecipe.registryRecipeFairyFuel.addRecipe("dustTinyCalcite", 111);

		}));

	}

	private void makeBlockHasTileEntity(BlockMir50 blockMir50, Supplier<TileEntity> supplierTileEntity)
	{
		blockMir50.virtualClass.override(
			new AdaptorBlockTileEntityAutonomy(blockMir50, blockMir50, (world, metadata) -> {
				return supplierTileEntity.get();
			}));
	}

	protected static class AdaptorBlockEventsTileEntityMMF extends AdaptorBlockEventsOverriding
	{

		public AdaptorBlockEventsTileEntityMMF(BlockMir50 owner, IVirtualClass superObject)
		{
			super(owner, superObject);
		}

		@Override
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float x2, float y2, float z2)
		{
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			if (tileEntity == null) return false;
			if (tileEntity instanceof TileEntityMMF) {
				return ((TileEntityMMF) tileEntity).onActivated(world, x, y, z, player, side, x2, y2, z2);
			}
			return false;
		}

	}

	protected static class GuiProviderTileEntityMir53 implements IGuiProvider
	{

		@Override
		public GuiMir52 createGui(EntityPlayer player, World world, int x, int y, int z)
		{
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			if (tileEntity == null) return null;
			if (tileEntity instanceof TileEntityMir53) {
				return ((TileEntityMir53) tileEntity).createGui(player, world, x, y, z);
			}
			return null;
		}

		@Override
		public ContainerMir52 createContainer(EntityPlayer player, World world, int x, int y, int z)
		{
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			if (tileEntity == null) return null;
			if (tileEntity instanceof TileEntityMir53) {
				return ((TileEntityMir53) tileEntity).createContainer(player, world, x, y, z);
			}
			return null;
		}

	}

}
