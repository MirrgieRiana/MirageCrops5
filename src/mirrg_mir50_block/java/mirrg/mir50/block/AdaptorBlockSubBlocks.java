package mirrg.mir50.block;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockSubBlocks extends AdaptorBlock implements IAdaptorBlockSubBlocks
{

	public AdaptorBlockSubBlocks(BlockMir50 blockMir50)
	{
		super(blockMir50);
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List itemStacks)
	{
		itemStacks.add(new ItemStack(item, 1, 0));
	}

}
