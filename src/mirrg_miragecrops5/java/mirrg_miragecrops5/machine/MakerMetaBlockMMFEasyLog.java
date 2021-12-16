package mirrg_miragecrops5.machine;

import java.util.function.BiConsumer;

import mirrg.h.struct.Struct1;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.adaptors.AdaptorBlockIconRegister;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import mirrg.mir51.block.multi.MetaBlock;
import mirrg_miragecrops5.machine.HelpersDirection.EnumDirection;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class MakerMetaBlockMMFEasyLog extends MakerMetaBlock
{

	public static BiConsumer<BlockMir50, MetaBlock> createMakerRenderer(
		String textureNameBack, String textureNameSide, String textureNameFront)
	{
		return (blockMir50, metaBlock) -> {

			Struct1<IIcon> iconBack = new Struct1<>(null);
			Struct1<IIcon> iconSide = new Struct1<>(null);
			Struct1<IIcon> iconFront = new Struct1<>(null);
			metaBlock.virtualClass.override(new AdaptorBlockIconRegister(blockMir50, metaBlock, iconRegister -> {
				iconBack.x = iconRegister.registerIcon(textureNameBack);
				iconSide.x = iconRegister.registerIcon(textureNameSide);
				iconFront.x = iconRegister.registerIcon(textureNameFront);
			}, false));

			metaBlock.virtualClass.override(new AdaptorBlockMultipleRenderingFromConsumer(blockMir50, metaBlock,
				event -> handler -> {
					EnumDirection machineSide = HelpersDirection.subtract(event.getSide(), event.getDirection());

					IIcon backgroundIcon = Blocks.log.getIcon(event.getSide(), 0);
					if (event.isOnWorld()) {
						Block block = event.getBlockAccess().getBlock(event.getX(), event.getY() + 1, event.getZ());
						if (block == event.getBlockAccess().getBlock(event.getX(), event.getY() - 1, event.getZ())) {
							int metadata = event.getBlockAccess().getBlockMetadata(event.getX(), event.getY() + 1, event.getZ());
							if (metadata == event.getBlockAccess().getBlockMetadata(event.getX(), event.getY() - 1, event.getZ())) {

								ItemStack drop = new ItemStack(Item.getItemFromBlock(block), 1, metadata);
								if (HelpersOreDictionary.isOre(drop, "logWood")) {
									backgroundIcon = block.getIcon(event.getSide(), metadata);
								}

							}
						}
					}

					handler.accept(backgroundIcon);

					if (machineSide == HelpersDirection.UP) {

					} else if (machineSide == HelpersDirection.DOWN) {

					} else if (machineSide == HelpersDirection.FRONT) {
						handler.accept(iconFront.x);
					} else if (machineSide == HelpersDirection.BACK) {
						handler.accept(iconBack.x);
					} else {
						handler.accept(iconSide.x);
					}
				}));

		};
	}

}
