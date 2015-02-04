package mirrg.mir50.block.multi;

import mirrg.mir50.block.IAdaptorBlockName;
import net.minecraft.item.ItemStack;

public interface IAdaptorBlockNameExtra extends IAdaptorBlockName
{

	public String getLocalizedName(ItemStack itemStack);

	public String getUnlocalizedName(ItemStack itemStack);

}
