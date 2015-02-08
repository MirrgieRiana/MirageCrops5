package mirrg.mir50.item;

import mirrg.p.adaptor.Adaptor;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AdaptorItemEvents extends Adaptor<ItemMir50> implements IAdaptorItemEvents
{

	public AdaptorItemEvents(ItemMir50 owner)
	{
		super(owner);
	}

	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{

	}

	public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving)
	{
		return false;
	}

	public boolean onBlockStartBreak(ItemStack itemStack, int x, int y, int z, EntityPlayer player)
	{
		return false;
	}

	public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
	{

	}

	public boolean onDroppedByPlayer(ItemStack itemStack, EntityPlayer player)
	{
		return true;
	}

	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
	{
		return itemStack;
	}

	public boolean onEntityItemUpdate(EntityItem entityItem)
	{
		return false;
	}

	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack itemStack)
	{
		return false;
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		return itemStack;
	}

	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		return false;
	}

	public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		return false;
	}

	public boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer player, Entity entity)
	{
		return false;
	}

	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int itemInUseCount)
	{

	}

	public void onUpdate(ItemStack itemStack, World world, Entity entity, int positionInInventory, boolean having)
	{

	}

	public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
	{

	}

}
