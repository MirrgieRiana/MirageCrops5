package mirrg.mir50.item;

import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AdaptorItemEventsOverriding extends AdaptorItemEvents
{

	protected final IAdaptorItemEvents _super_IAdaptorItemEvents;

	public AdaptorItemEventsOverriding(ItemMir50 owner, IVirtualClass superObject)
	{
		super(owner);
		this._super_IAdaptorItemEvents = superObject.getVirtualClass().getCurrentImplementation(IAdaptorItemEvents.class);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		_super_IAdaptorItemEvents.onArmorTick(world, player, itemStack);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving)
	{
		return _super_IAdaptorItemEvents.onBlockDestroyed(itemStack, world, block, x, y, z, entityLiving);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemStack, int x, int y, int z, EntityPlayer player)
	{
		return _super_IAdaptorItemEvents.onBlockStartBreak(itemStack, x, y, z, player);
	}

	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
	{
		_super_IAdaptorItemEvents.onCreated(itemStack, world, player);
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack itemStack, EntityPlayer player)
	{
		return _super_IAdaptorItemEvents.onDroppedByPlayer(itemStack, player);
	}

	@Override
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
	{
		return _super_IAdaptorItemEvents.onEaten(itemStack, world, player);
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem)
	{
		return _super_IAdaptorItemEvents.onEntityItemUpdate(entityItem);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack itemStack)
	{
		return _super_IAdaptorItemEvents.onEntitySwing(entityLiving, itemStack);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		return _super_IAdaptorItemEvents.onItemRightClick(itemStack, world, player);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		return _super_IAdaptorItemEvents.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}

	@Override
	public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		return _super_IAdaptorItemEvents.onItemUseFirst(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer player, Entity entity)
	{
		return _super_IAdaptorItemEvents.onLeftClickEntity(itemStack, player, entity);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int itemInUseCount)
	{
		_super_IAdaptorItemEvents.onPlayerStoppedUsing(itemStack, world, player, itemInUseCount);
	}

	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int positionInInventory, boolean having)
	{
		_super_IAdaptorItemEvents.onUpdate(itemStack, world, entity, positionInInventory, having);
	}

	@Override
	public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
	{
		_super_IAdaptorItemEvents.onUsingTick(itemStack, player, count);
	}

}
