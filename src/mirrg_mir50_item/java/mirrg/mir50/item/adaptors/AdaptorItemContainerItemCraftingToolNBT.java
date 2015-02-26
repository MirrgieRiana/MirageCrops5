package mirrg.mir50.item.adaptors;

import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import api.mirrg.mir50.net.NBTTypes;

public class AdaptorItemContainerItemCraftingToolNBT extends AdaptorItemContainerItemCraftingTool
{

	public int maxDamage;
	public int damageCost = 1;
	public String tagName;

	public AdaptorItemContainerItemCraftingToolNBT(ItemMir50 owner, IVirtualClass superObject, int maxDamage, String tagName)
	{
		super(owner, superObject);
		this.maxDamage = maxDamage;
		this.tagName = tagName;
	}

	protected int getDamageAmount(ItemStack itemStack)
	{
		return damageCost;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		ItemStack itemStack2 = itemStack.copy();

		int damage;
		if (itemStack2.hasTagCompound()) {
			if (itemStack2.getTagCompound().hasKey(tagName, NBTTypes.INT)) {
				damage = itemStack2.getTagCompound().getInteger(tagName);
			} else {
				damage = 0;
			}
		} else {
			damage = 0;
		}

		damage += getDamageAmount(itemStack);

		if (damage > maxDamage) {
			return null;
		} else {
			if (itemStack2.hasTagCompound()) {
				NBTTagCompound nbt = itemStack2.getTagCompound();
				nbt.setInteger(tagName, damage);
				itemStack2.setTagCompound(nbt);
				return itemStack2;
			} else {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger(tagName, damage);
				itemStack2.setTagCompound(nbt);
				return itemStack2;
			}
		}
	}

	@Override
	public double getDurabilityForDisplay(ItemStack itemStack)
	{
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

		return (double) damage / (double) maxDamage;
	}

	@Override
	public boolean showDurabilityBar(ItemStack itemStack)
	{
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

		return damage > 0;
	}

}
