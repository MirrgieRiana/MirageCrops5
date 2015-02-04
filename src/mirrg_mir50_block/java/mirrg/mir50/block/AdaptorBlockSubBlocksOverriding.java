package mirrg.mir50.block;

import java.util.List;

import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockSubBlocksOverriding extends AdaptorBlockSubBlocks
{

	protected final IAdaptorBlockSubBlocks _super_IAdaptorBlockSubBlocks;

	public AdaptorBlockSubBlocksOverriding(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner);
		this._super_IAdaptorBlockSubBlocks = superObject.getVirtualClass().getCurrentImplementation(IAdaptorBlockSubBlocks.class);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List itemStacks)
	{
		this._super_IAdaptorBlockSubBlocks.getSubBlocks(item, creativeTab, itemStacks);
	}

}
