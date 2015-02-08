package mirrg.mir50.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IAdaptorItemName
{

	public Item setUnlocalizedName(String unlocalizedName);

	public String getUnlocalizedNameInefficiently(ItemStack itemStack);

	public String getUnlocalizedName();

	public String getUnlocalizedName(ItemStack itemStack);

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> strings, boolean shift);

}
