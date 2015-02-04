package mirrg.mir50.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMir50 extends Item
{

	public AdaptorItemSubItems adaptorItemSubItems = new AdaptorItemSubItems(this);

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTab, List itemStacks)
	{
		adaptorItemSubItems.getSubItems(item, creativeTab, itemStacks);
	}

	//

	public AdaptorItemIcon adaptorItemIcon = new AdaptorItemIcon(this);

	@SideOnly(Side.CLIENT)
	public final IIcon getField_itemIcon()
	{
		return itemIcon;
	}

	@SideOnly(Side.CLIENT)
	public final void setField_itemIcon(IIcon itemIcon)
	{
		this.itemIcon = itemIcon;
	}

	public final String getField_iconString()
	{
		return iconString;
	}

	public final void setField_iconString(String iconString)
	{
		this.iconString = iconString;
	}

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		return adaptorItemIcon.getIcon(itemStack, pass, player, usingItem, useRemaining);
	}

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass)
	{
		return adaptorItemIcon.getIcon(itemStack, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		return adaptorItemIcon.getIconFromDamageForRenderPass(meta, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return adaptorItemIcon.getIconFromDamage(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		adaptorItemIcon.registerIcons(iconRegister);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getIconString()
	{
		return adaptorItemIcon.getIconString();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return adaptorItemIcon.requiresMultipleRenderPasses();
	}

	@Override
	public int getRenderPasses(int meta)
	{
		return adaptorItemIcon.getRenderPasses(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int pass)
	{
		return adaptorItemIcon.getColorFromItemStack(itemStack, pass);
	}

	//

	public AdaptorItemName adaptorItemName = new AdaptorItemName(this);

	public final void setField_unlocalizedName(String unlocalizedName)
	{
		super.setUnlocalizedName(unlocalizedName);
	}

	public final String getField_unlocalizedName()
	{
		return super.getUnlocalizedName().substring(5);
	}

	@Override
	public Item setUnlocalizedName(String unlocalizedName)
	{
		return adaptorItemName.setUnlocalizedName(unlocalizedName);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack itemStack)
	{
		return adaptorItemName.getUnlocalizedNameInefficiently(itemStack);
	}

	@Override
	public String getUnlocalizedName()
	{
		return adaptorItemName.getUnlocalizedName();
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return adaptorItemName.getUnlocalizedName(itemStack);
	}

	//

	public AdaptorItemContainerItem adaptorItemContainerItem = new AdaptorItemContainerItem(this);

	public final Item getField_containerItem()
	{
		return getContainerItem();
	}

	public final void setField_containerItem(Item containerItem)
	{
		setContainerItem(containerItem);
	}

	@Override
	public boolean hasContainerItem(ItemStack itemStack)
	{
		return adaptorItemContainerItem.hasContainerItem(itemStack);
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
	{
		return adaptorItemContainerItem.doesContainerItemLeaveCraftingGrid(itemStack);
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		return adaptorItemContainerItem.getContainerItem(itemStack);
	}

}
