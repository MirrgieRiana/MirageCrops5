package mirrg.mir50.item.multi;

import mirrg.mir50.item.AdaptorItemEventsOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AdaptorItemEventsMulti extends AdaptorItemEventsOverriding
{

	protected ContainerMetaItem containerMetaItem;

	public AdaptorItemEventsMulti(ItemMir50 itemSample, IVirtualClass virtualClass, ContainerMetaItem containerMetaItem)
	{
		super(itemSample, virtualClass);
		this.containerMetaItem = containerMetaItem;
	}

	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) {
			super.onArmorTick(world, player, itemStack);
			return;
		}
		metaItem.accessor_IAdaptorItemEvents.get().onArmorTick(world, player, itemStack);
	}

	public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.onBlockDestroyed(itemStack, world, block, x, y, z, entityLiving);
		return metaItem.accessor_IAdaptorItemEvents.get().onBlockDestroyed(itemStack, world, block, x, y, z, entityLiving);
	}

	public boolean onBlockStartBreak(ItemStack itemStack, int x, int y, int z, EntityPlayer player)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.onBlockStartBreak(itemStack, x, y, z, player);
		return metaItem.accessor_IAdaptorItemEvents.get().onBlockStartBreak(itemStack, x, y, z, player);
	}

	public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) {
			super.onCreated(itemStack, world, player);
			return;
		}
		metaItem.accessor_IAdaptorItemEvents.get().onCreated(itemStack, world, player);
	}

	public boolean onDroppedByPlayer(ItemStack itemStack, EntityPlayer player)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.onDroppedByPlayer(itemStack, player);
		return metaItem.accessor_IAdaptorItemEvents.get().onDroppedByPlayer(itemStack, player);
	}

	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.onEaten(itemStack, world, player);
		return metaItem.accessor_IAdaptorItemEvents.get().onEaten(itemStack, world, player);
	}

	public boolean onEntityItemUpdate(EntityItem entityItem)
	{
		MetaItem metaItem = containerMetaItem.get(entityItem.getEntityItem());
		if (metaItem == null) return super.onEntityItemUpdate(entityItem);
		return metaItem.accessor_IAdaptorItemEvents.get().onEntityItemUpdate(entityItem);
	}

	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack itemStack)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.onEntitySwing(entityLiving, itemStack);
		return metaItem.accessor_IAdaptorItemEvents.get().onEntitySwing(entityLiving, itemStack);
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.onItemRightClick(itemStack, world, player);
		return metaItem.accessor_IAdaptorItemEvents.get().onItemRightClick(itemStack, world, player);
	}

	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
		return metaItem.accessor_IAdaptorItemEvents.get().onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}

	public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.onItemUseFirst(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
		return metaItem.accessor_IAdaptorItemEvents.get().onItemUseFirst(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}

	public boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer player, Entity entity)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) return super.onLeftClickEntity(itemStack, player, entity);
		return metaItem.accessor_IAdaptorItemEvents.get().onLeftClickEntity(itemStack, player, entity);
	}

	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int itemInUseCount)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) {
			super.onPlayerStoppedUsing(itemStack, world, player, itemInUseCount);
			return;
		}
		metaItem.accessor_IAdaptorItemEvents.get().onPlayerStoppedUsing(itemStack, world, player, itemInUseCount);
	}

	public void onUpdate(ItemStack itemStack, World world, Entity entity, int positionInInventory, boolean having)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) {
			super.onUpdate(itemStack, world, entity, positionInInventory, having);
			return;
		}
		metaItem.accessor_IAdaptorItemEvents.get().onUpdate(itemStack, world, entity, positionInInventory, having);
	}

	public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
	{
		MetaItem metaItem = containerMetaItem.get(itemStack);
		if (metaItem == null) {
			super.onUsingTick(itemStack, player, count);
			return;
		}
		metaItem.accessor_IAdaptorItemEvents.get().onUsingTick(itemStack, player, count);
	}
}
