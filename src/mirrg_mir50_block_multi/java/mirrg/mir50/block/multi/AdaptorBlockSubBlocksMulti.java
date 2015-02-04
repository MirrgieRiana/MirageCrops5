package mirrg.mir50.block.multi;

import java.util.List;

import mirrg.mir50.block.AdaptorBlockSubBlocksOverriding;
import mirrg.mir50.block.BlockMir50;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockSubBlocksMulti extends AdaptorBlockSubBlocksOverriding
{

	protected ContainerMetaBlock containerMetaBlock;

	public AdaptorBlockSubBlocksMulti(BlockMir50 owner, ContainerMetaBlock containerMetaBlock)
	{
		super(owner);
		this.containerMetaBlock = containerMetaBlock;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List itemStacks)
	{
		containerMetaBlock.forEach(metaBlock -> {
			if (metaBlock != null) {
				metaBlock.accessor_IAdaptorBlockSubBlocks.get().getSubBlocks(item, creativeTab, itemStacks);
			}
		});
	}

}
