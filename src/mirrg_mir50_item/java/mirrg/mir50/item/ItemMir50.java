package mirrg.mir50.item;

import java.util.List;

import mirrg.p.virtualclass.IVirtualClass;
import mirrg.p.virtualclass.IVirtualImplementationAccessor;
import mirrg.p.virtualclass.VirtualClass;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
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
		virtualClass.register(IAdaptorItemEvents.class, new AdaptorItemEvents(this));
		accessor_IAdaptorItemEvents = virtualClass.cast(IAdaptorItemEvents.class);

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

	@Override
	public String getItemStackDisplayName(ItemStack itemStack)
	{
		return accessor_IAdaptorItemName.get().getItemStackDisplayName(itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List strings, boolean shift)
	{
		accessor_IAdaptorItemName.get().addInformation(itemStack, player, strings, shift);
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

	/////////////////////////////////////////////////////////////////////

	public final IVirtualImplementationAccessor<IAdaptorItemEvents> accessor_IAdaptorItemEvents;

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		accessor_IAdaptorItemEvents.get().onArmorTick(world, player, itemStack);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving)
	{
		return accessor_IAdaptorItemEvents.get().onBlockDestroyed(itemStack, world, block, x, y, z, entityLiving);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemStack, int x, int y, int z, EntityPlayer player)
	{
		return accessor_IAdaptorItemEvents.get().onBlockStartBreak(itemStack, x, y, z, player);
	}

	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
	{
		accessor_IAdaptorItemEvents.get().onCreated(itemStack, world, player);
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack itemStack, EntityPlayer player)
	{
		return accessor_IAdaptorItemEvents.get().onDroppedByPlayer(itemStack, player);
	}

	@Override
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
	{
		return accessor_IAdaptorItemEvents.get().onEaten(itemStack, world, player);
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem)
	{
		return accessor_IAdaptorItemEvents.get().onEntityItemUpdate(entityItem);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack itemStack)
	{
		return accessor_IAdaptorItemEvents.get().onEntitySwing(entityLiving, itemStack);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		return accessor_IAdaptorItemEvents.get().onItemRightClick(itemStack, world, player);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		return accessor_IAdaptorItemEvents.get().onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}

	@Override
	public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		return accessor_IAdaptorItemEvents.get().onItemUseFirst(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer player, Entity entity)
	{
		return accessor_IAdaptorItemEvents.get().onLeftClickEntity(itemStack, player, entity);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int itemInUseCount)
	{
		accessor_IAdaptorItemEvents.get().onPlayerStoppedUsing(itemStack, world, player, itemInUseCount);
	}

	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int positionInInventory, boolean having)
	{
		accessor_IAdaptorItemEvents.get().onUpdate(itemStack, world, entity, positionInInventory, having);
	}

	@Override
	public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
	{
		accessor_IAdaptorItemEvents.get().onUsingTick(itemStack, player, count);
	}

}
