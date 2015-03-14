package mirrg_miragecrops5.machine;

import mirrg.h.struct.Struct1;
import mirrg.he.math.HelpersColor;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.adaptors.AdaptorBlockIconRegister;
import mirrg.mir51.block.multi.MetaBlock;
import mirrg_miragecrops5.material.HelpersModuleMaterial;
import net.minecraft.util.IIcon;

public class MakerMetaBlockMMFFurnace extends MakerMetaBlock
{

	public static void makeRenderer(BlockMir50 blockMir50, MetaBlock metaBlock)
	{

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

		metaBlock.virtualClass.override(new AdaptorBlockMultipleRenderingFromConsumer(blockMir50, metaBlock,
			(direction, side) -> handler -> {
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
			}));

	}

}
