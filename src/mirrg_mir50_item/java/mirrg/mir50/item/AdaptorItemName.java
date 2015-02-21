package mirrg.mir50.item;

import java.util.List;

import mirrg.p.adaptor.Adaptor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemName extends Adaptor<ItemMir50> implements IAdaptorItemName
{

	public AdaptorItemName(ItemMir50 owner)
	{
		super(owner);
	}

	@Override
	public Item setUnlocalizedName(String unlocalizedName)
	{
		this.owner.setField_unlocalizedName(unlocalizedName);
		return this.owner;
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack itemStack)
	{
		String s = owner.accessor_IAdaptorItemName.get().getUnlocalizedName(itemStack);
		return s == null ? "" : StatCollector.translateToLocal(s);
	}

	@Override
	public String getUnlocalizedName()
	{
		return "item." + this.owner.getField_unlocalizedName();
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return "item." + this.owner.getField_unlocalizedName();
	}

	@Override
	public String getItemStackDisplayName(ItemStack itemStack)
	{
		return ("" + StatCollector.translateToLocal(owner.getUnlocalizedNameInefficiently(itemStack) + ".name")).trim();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> strings, boolean shift)
	{

	}

}
