package mirrg_miragecrops5.machine;

import mirrg.mir50.block.AdaptorBlockEventsOverriding;
import mirrg.mir50.block.adaptors.AdaptorBlockTileEntityAutonomy;
import mirrg.mir50.guihandler.IGuiProvider;
import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRenderingAutonomy;
import mirrg.mir51.render.block.multiple.HelpersBlockMultipleRendering;
import mirrg.mir52.tile.ContainerMir53;
import mirrg.mir52.tile.GuiMir53;
import mirrg_miragecrops5.ModMirageCrops;
import mirrg_miragecrops5.ModuleCore;
import mirrg_miragecrops5.ModuleMirageCropsAbstract;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModuleMachine extends ModuleMirageCropsAbstract
{

	public static LoaderGuiHandler loaderGuiHandler = new LoaderGuiHandler();

	public static LoaderBlock loaderBlock_machineMirageFairy = new LoaderBlock();

	public ModuleMachine()
	{

		add(new Loader<Void>() {
			@Override
			protected void loadThisLoader(EnumLoadEventTiming loadEvent)
			{
				if (loadEvent == EnumLoadEventTiming.PreInit) {
					GameRegistry.registerTileEntity(TileEntityMachineMirageFairy.class, "MachineMirageFairy");
					loadCompleted();
				}
			}
		});

		loaderGuiHandler.guiId = 1;
		loaderGuiHandler.supplierMod = () -> ModMirageCrops.instance;
		add(loaderGuiHandler);

		process_loaderBlock(loaderBlock_machineMirageFairy, ModuleCore.loaderCreativeTab, ItemBlock.class, "machineMirageFairy", (blockMir50) -> {
			HelpersBlockMultipleRendering.make(blockMir50, blockMir50);

			AdaptorBlockMultipleRenderingAutonomy a = new AdaptorBlockMultipleRenderingAutonomy(blockMir50, blockMir50);
			a.appendIcon("miragecrops5:machineMirageFairy_0_3");
			a.appendIcon("miragecrops5:machineMirageFairy_1", 0xE8C831);
			blockMir50.virtualClass.override(a);

			blockMir50.virtualClass.override(
				new AdaptorBlockTileEntityAutonomy(blockMir50, blockMir50, (world, metadata) -> {
					return new TileEntityMachineMirageFairy();
				}));
			blockMir50.virtualClass.override(new AdaptorBlockEventsOverriding(blockMir50, blockMir50) {
				@Override
				public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float x2, float y2, float z2)
				{
					TileEntity tileEntity = world.getTileEntity(x, y, z);
					if (tileEntity == null) return false;
					if (tileEntity instanceof TileEntityMachineMirageFairy) {
						return ((TileEntityMachineMirageFairy) tileEntity).onBlockActivated(world, x, y, z, player, side, x2, y2, z2);
					}
					return false;
				}
			});

			blockMir50.virtualClass.register(IGuiProvider.class);
			blockMir50.virtualClass.override(new IGuiProvider() {
				@Override
				public GuiMir53 createGui(EntityPlayer player, World world, int x, int y, int z)
				{
					TileEntity tileEntity = world.getTileEntity(x, y, z);
					if (tileEntity == null) return null;
					if (tileEntity instanceof TileEntityMachineMirageFairy) {
						return ((TileEntityMachineMirageFairy) tileEntity).createGui(player, world, x, y, z);
					}
					return null;
				}

				@Override
				public ContainerMir53 createContainer(EntityPlayer player, World world, int x, int y, int z)
				{
					TileEntity tileEntity = world.getTileEntity(x, y, z);
					if (tileEntity == null) return null;
					if (tileEntity instanceof TileEntityMachineMirageFairy) {
						return ((TileEntityMachineMirageFairy) tileEntity).createContainer(player, world, x, y, z);
					}
					return null;
				}
			});

		});

	}

}
