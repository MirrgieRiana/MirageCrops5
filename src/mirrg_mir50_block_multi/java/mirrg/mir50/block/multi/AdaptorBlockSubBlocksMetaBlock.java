package mirrg.mir50.block.multi;

import java.util.List;

import mirrg.mir50.block.AdaptorBlockSubBlocksOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockSubBlocksMetaBlock extends AdaptorBlockSubBlocksOverriding
{

	protected final MetaBlock metaBlock;

	public AdaptorBlockSubBlocksMetaBlock(BlockMir50 owner, IVirtualClass superObject, MetaBlock metaBlock)
	{
		super(owner, superObject);
		this.metaBlock = metaBlock;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List itemStacks)
	{
		itemStacks.add(new ItemStack(this.metaBlock.blockMir50, 1, this.metaBlock.meta));
	}

}
