package mirrg.mir50.block;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockSubBlocksOverriding extends AdaptorBlockSubBlocks
{

	protected final IAdaptorBlockSubBlocks _super_IAdaptorBlockSubBlocks;

	public AdaptorBlockSubBlocksOverriding(BlockMir50 owner)
	{
		super(owner);
		_super_IAdaptorBlockSubBlocks = owner.getVirtualClass().getCurrentImplementation(IAdaptorBlockSubBlocks.class);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List itemStacks)
	{
		_super_IAdaptorBlockSubBlocks.getSubBlocks(item, creativeTab, itemStacks);
	}

}
