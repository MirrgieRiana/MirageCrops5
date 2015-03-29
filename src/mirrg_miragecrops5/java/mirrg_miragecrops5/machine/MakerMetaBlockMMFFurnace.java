package mirrg_miragecrops5.machine;

import mirrg.h.struct.Struct1;
import mirrg.he.math.HelpersColor;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.adaptors.AdaptorBlockIconRegister;
import mirrg.mir51.block.multi.MetaBlock;
import mirrg_miragecrops5.machine.HelpersDirection.EnumDirection;
import mirrg_miragecrops5.material.HelpersModuleMaterial;
import net.minecraft.util.IIcon;

public class MakerMetaBlockMMFFurnace extends MakerMetaBlock
{

	public static void makeRenderer(BlockMir50 blockMir50, MetaBlock metaBlock)
	{

		Struct1<IIcon> iconTop = new Struct1<>(null);
		Struct1<IIcon> iconSide = new Struct1<>(null);
		Struct1<IIcon> iconFrame = new Struct1<>(null);
		Struct1<IIcon> iconOverlayFront = new Struct1<>(null);
		metaBlock.virtualClass.override(new AdaptorBlockIconRegister(blockMir50, metaBlock, iconRegister -> {
			iconTop.x = iconRegister.registerIcon("miragecrops5:machineMirageFairy_0_1");
			iconSide.x = iconRegister.registerIcon("miragecrops5:blockCalcite");
			iconFrame.x = iconRegister.registerIcon("miragecrops5:machineMirageFairy_1");
			iconOverlayFront.x = iconRegister.registerIcon("miragecrops5:overlay_front_furnace");
		}, false));

		metaBlock.virtualClass.override(new AdaptorBlockMultipleRenderingFromConsumer(blockMir50, metaBlock,
			event -> handler -> {
				EnumDirection machineSide = HelpersDirection.subtract(event.getSide(), event.getDirection());

				if (machineSide == HelpersDirection.UP) {
					handler.accept(iconTop.x);
				} else {
					handler.accept(iconSide.x);
				}
				if (machineSide == HelpersDirection.FRONT) {
					handler.accept(iconOverlayFront.x);
				}
				handler.accept(iconFrame.x, HelpersColor.multiplicate(
					HelpersModuleMaterial.registryMaterialProperty.getColor("iron"), 0.9));
			}));

	}

}
