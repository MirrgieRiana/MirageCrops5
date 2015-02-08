package mirrg.mir50.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IAdaptorItemEvents
{

	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack);

	public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving);

	public boolean onBlockStartBreak(ItemStack itemStack, int x, int y, int z, EntityPlayer player);

	public void onCreated(ItemStack itemStack, World world, EntityPlayer player);

	public boolean onDroppedByPlayer(ItemStack itemStack, EntityPlayer player);

	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player);

	public boolean onEntityItemUpdate(EntityItem entityItem);

	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack itemStack);

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player);

	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ);

	public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ);

	public boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer player, Entity entity);

	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int itemInUseCount);

	public void onUpdate(ItemStack itemStack, World world, Entity entity, int positionInInventory, boolean having);

	public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count);

}
