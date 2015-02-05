package mirrg.mir50.item;

import java.util.List;

import mirrg.p.virtualclass.IVirtualClass;
import mirrg.p.virtualclass.IVirtualImplementationAccessor;
import mirrg.p.virtualclass.VirtualClass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMir50 extends Item implements IVirtualClass
{

	public final VirtualClass virtualClass = new VirtualClass(this);

	public ItemMir50()
	{

		virtualClass.register(IAdaptorItemSubItems.class, new AdaptorItemSubItems(this));
		accessor_IAdaptorItemSubItems = virtualClass.cast(IAdaptorItemSubItems.class);
		virtualClass.register(IAdaptorItemIcon.class, new AdaptorItemIcon(this));
		accessor_IAdaptorItemIcon = virtualClass.cast(IAdaptorItemIcon.class);
		virtualClass.register(IAdaptorItemName.class, new AdaptorItemName(this));
		accessor_IAdaptorItemName = virtualClass.cast(IAdaptorItemName.class);
		virtualClass.register(IAdaptorItemContainerItem.class, new AdaptorItemContainerItem(this));
		accessor_IAdaptorItemContainerItem = virtualClass.cast(IAdaptorItemContainerItem.class);

	}

	@Override
	public final VirtualClass getVirtualClass()
	{
		return virtualClass;
	}

	/////////////////////////////////////////////////////////////////////

	public final IVirtualImplementationAccessor<IAdaptorItemSubItems> accessor_IAdaptorItemSubItems;

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTab, List itemStacks)
	{
		accessor_IAdaptorItemSubItems.get().getSubItems(item, creativeTab, itemStacks);
	}

	/////////////////////////////////////////////////////////////////////

	public final IVirtualImplementationAccessor<IAdaptorItemIcon> accessor_IAdaptorItemIcon;

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
		return accessor_IAdaptorItemIcon.get().getIcon(itemStack, pass, player, usingItem, useRemaining);
	}

	@Override
	public IIcon getIcon(ItemStack itemStack, int pass)
	{
		return accessor_IAdaptorItemIcon.get().getIcon(itemStack, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass)
	{
		return accessor_IAdaptorItemIcon.get().getIconFromDamageForRenderPass(meta, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return accessor_IAdaptorItemIcon.get().getIconFromDamage(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		accessor_IAdaptorItemIcon.get().registerIcons(iconRegister);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getIconString()
	{
		return accessor_IAdaptorItemIcon.get().getIconString();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return accessor_IAdaptorItemIcon.get().requiresMultipleRenderPasses();
	}

	@Override
	public int getRenderPasses(int meta)
	{
		return accessor_IAdaptorItemIcon.get().getRenderPasses(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int pass)
	{
		return accessor_IAdaptorItemIcon.get().getColorFromItemStack(itemStack, pass);
	}

	/////////////////////////////////////////////////////////////////////

	public final IVirtualImplementationAccessor<IAdaptorItemName> accessor_IAdaptorItemName;

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
		return accessor_IAdaptorItemName.get().setUnlocalizedName(unlocalizedName);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack itemStack)
	{
		return accessor_IAdaptorItemName.get().getUnlocalizedNameInefficiently(itemStack);
	}

	@Override
	public String getUnlocalizedName()
	{
		return accessor_IAdaptorItemName.get().getUnlocalizedName();
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return accessor_IAdaptorItemName.get().getUnlocalizedName(itemStack);
	}

	/////////////////////////////////////////////////////////////////////

	public final IVirtualImplementationAccessor<IAdaptorItemContainerItem> accessor_IAdaptorItemContainerItem;

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
		return accessor_IAdaptorItemContainerItem.get().hasContainerItem(itemStack);
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
	{
		return accessor_IAdaptorItemContainerItem.get().doesContainerItemLeaveCraftingGrid(itemStack);
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		return accessor_IAdaptorItemContainerItem.get().getContainerItem(itemStack);
	}

}
