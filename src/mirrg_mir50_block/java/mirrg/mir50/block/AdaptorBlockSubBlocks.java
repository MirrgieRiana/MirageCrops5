package mirrg.mir50.block;

import java.util.List;

import mirrg.p.adaptor.Adaptor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockSubBlocks extends Adaptor<BlockMir50> implements IAdaptorBlockSubBlocks
{

	public AdaptorBlockSubBlocks(BlockMir50 owner)
	{
		super(owner);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List<ItemStack> itemStacks)
	{
		itemStacks.add(new ItemStack(item, 1, 0));
	}

}
