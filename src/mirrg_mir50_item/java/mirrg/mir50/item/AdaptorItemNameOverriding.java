package mirrg.mir50.item;

import java.util.List;

import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemNameOverriding extends AdaptorItemName
{

	protected final IAdaptorItemName _super_IAdaptorItemName;

	public AdaptorItemNameOverriding(ItemMir50 owner, IVirtualClass superObject)
	{
		super(owner);
		this._super_IAdaptorItemName = superObject.getVirtualClass().getCurrentImplementation(IAdaptorItemName.class);
	}

	@Override
	public Item setUnlocalizedName(String unlocalizedName)
	{
		return _super_IAdaptorItemName.setUnlocalizedName(unlocalizedName);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack itemStack)
	{
		return _super_IAdaptorItemName.getUnlocalizedNameInefficiently(itemStack);
	}

	@Override
	public String getUnlocalizedName()
	{
		return _super_IAdaptorItemName.getUnlocalizedName();
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return _super_IAdaptorItemName.getUnlocalizedName(itemStack);
	}

	@Override
	public String getItemStackDisplayName(ItemStack itemStack)
	{
		return _super_IAdaptorItemName.getItemStackDisplayName(itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> strings, boolean shift)
	{
		_super_IAdaptorItemName.addInformation(itemStack, player, strings, shift);
	}

}
