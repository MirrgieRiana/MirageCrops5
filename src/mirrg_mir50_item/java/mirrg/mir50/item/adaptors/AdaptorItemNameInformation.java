package mirrg.mir50.item.adaptors;

import java.util.List;

import mirrg.mir50.item.AdaptorItemNameOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class AdaptorItemNameInformation extends AdaptorItemNameOverriding
{

	public IAdderInformation adderInformation = null;

	public AdaptorItemNameInformation(ItemMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	public AdaptorItemNameInformation(ItemMir50 owner, IVirtualClass superObject, AdaptorItemNameInformation.IAdderInformation adderInformation)
	{
		super(owner, superObject);
		this.adderInformation = adderInformation;
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> strings, boolean shift)
	{
		super.addInformation(itemStack, player, strings, shift);

		if (adderInformation != null) adderInformation.addInformation(itemStack, player, strings, shift);
	}

	@FunctionalInterface
	public static interface IAdderInformation
	{

		public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> strings, boolean shift);

	}

}
