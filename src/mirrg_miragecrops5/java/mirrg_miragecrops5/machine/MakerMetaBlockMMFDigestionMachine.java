package mirrg_miragecrops5.machine;

import mirrg.h.struct.Struct1;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.adaptors.AdaptorBlockIconRegister;
import mirrg.mir51.block.multi.MetaBlock;
import mirrg_miragecrops5.machine.HelpersDirection.EnumDirection;
import net.minecraft.util.IIcon;

public class MakerMetaBlockMMFDigestionMachine extends MakerMetaBlock
{

	public static void makeRenderer(BlockMir50 blockMir50, MetaBlock metaBlock)
	{

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

		metaBlock.virtualClass.override(new AdaptorBlockMultipleRenderingFromConsumer(blockMir50, metaBlock,
			event -> handler -> {
				EnumDirection machineSide = HelpersDirection.subtract(event.getSide(), event.getDirection());

				if (machineSide == HelpersDirection.UP) {
					handler.accept(iconTop.x);
				} else if (machineSide == HelpersDirection.DOWN) {
					handler.accept(iconTop.x);
				} else if (machineSide == HelpersDirection.FRONT) {
					handler.accept(iconFront.x);
				} else if (machineSide == HelpersDirection.BACK) {
					handler.accept(iconBack.x);
				} else {
					handler.accept(iconSide.x);
				}
			}));

	}

}
