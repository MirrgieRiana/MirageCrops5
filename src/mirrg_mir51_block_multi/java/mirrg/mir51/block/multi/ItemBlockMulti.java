package mirrg.mir51.block.multi;

import mirrg.mir50.block.adaptors.IAdaptorBlockNameExtra;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMulti extends ItemBlock
{

	public ItemBlockMulti(Block block)
	{
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return ((IVirtualClass) field_150939_a).getVirtualClass().cast(IAdaptorBlockNameExtra.class).get().getUnlocalizedName(itemStack);
	}

	@Override
	public int getMetadata(int metaId)
	{
		return metaId;
	}

}
