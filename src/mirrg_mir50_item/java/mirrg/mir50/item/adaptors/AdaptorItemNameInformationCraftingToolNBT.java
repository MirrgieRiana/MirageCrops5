package mirrg.mir50.item.adaptors;

import java.util.List;

import mirrg.mir50.item.AdaptorItemNameOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import api.mirrg.mir50.net.NBTTypes;

public class AdaptorItemNameInformationCraftingToolNBT extends AdaptorItemNameOverriding
{

	private int maxDamage;
	private String tagName;

	public AdaptorItemNameInformationCraftingToolNBT(ItemMir50 owner, IVirtualClass superObject, int maxDamage, String tagName)
	{
		super(owner, superObject);
		this.maxDamage = maxDamage;
		this.tagName = tagName;
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> strings, boolean shift)
	{
		super.addInformation(itemStack, player, strings, shift);

		int damage;
		if (itemStack.hasTagCompound()) {
			if (itemStack.getTagCompound().hasKey(tagName, NBTTypes.INT)) {
				damage = itemStack.getTagCompound().getInteger(tagName);
			} else {
				damage = 0;
			}
		} else {
			damage = 0;
		}

		strings.add("Durability: " + (maxDamage - damage + 1) + " / " + (maxDamage + 1));
	}

}
