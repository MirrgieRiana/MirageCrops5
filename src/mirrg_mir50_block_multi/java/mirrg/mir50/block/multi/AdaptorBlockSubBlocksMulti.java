package mirrg.mir50.block.multi;

import java.util.List;

import mirrg.mir50.block.AdaptorBlockSubBlocks;
import mirrg.mir50.block.BlockMir50;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockSubBlocksMulti extends AdaptorBlockSubBlocks
{

	protected ContainerMetaBlock containerMetaBlock;

	public AdaptorBlockSubBlocksMulti(BlockMir50 blockMir50, ContainerMetaBlock containerMetaBlock)
	{
		super(blockMir50);
		this.containerMetaBlock = containerMetaBlock;
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List itemStacks)
	{
		containerMetaBlock.forEach(metaBlock -> {
			if (metaBlock != null) {
				if (metaBlock.adaptorBlockSubBlocks != null) {
					metaBlock.adaptorBlockSubBlocks.getSubBlocks(item, creativeTab, itemStacks);
				} else {
					itemStacks.add(new ItemStack(item, 1, metaBlock.meta));
				}
			}
		});
	}

}
