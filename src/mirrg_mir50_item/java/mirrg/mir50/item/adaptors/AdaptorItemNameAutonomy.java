package mirrg.mir50.item.adaptors;

import mirrg.mir50.item.AdaptorItemNameOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AdaptorItemNameAutonomy extends AdaptorItemNameOverriding
{

	public String unlocalizedName;

	public AdaptorItemNameAutonomy(ItemMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	public AdaptorItemNameAutonomy(ItemMir50 owner, IVirtualClass superObject, String unlocalizedName)
	{
		super(owner, superObject);
		this.unlocalizedName = unlocalizedName;
	}

	@Override
	public Item setUnlocalizedName(String unlocalizedName)
	{
		this.unlocalizedName = unlocalizedName;
		return this.owner;
	}

	@Override
	public String getUnlocalizedName()
	{
		return "item." + this.unlocalizedName;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return "item." + this.unlocalizedName;
	}

}
