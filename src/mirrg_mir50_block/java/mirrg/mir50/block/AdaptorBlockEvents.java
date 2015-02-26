package mirrg.mir50.block;

import java.util.ArrayList;

import mirrg.p.adaptor.Adaptor;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class AdaptorBlockEvents extends Adaptor<BlockMir50> implements IAdaptorBlockEvents
{

	public AdaptorBlockEvents(BlockMir50 owner)
	{
		super(owner);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float x2, float y2, float z2)
	{
		return false;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{

	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{

	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
	{

	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metadata)
	{

	}

	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int arg1, int arg2)
	{
		return false;
	}

	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
	{
		world.setBlockToAir(x, y, z);
		owner.onBlockDestroyedByExplosion(world, x, y, z, explosion);
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player)
	{

	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float x2, float y2, float z2, int metadata)
	{
		return metadata;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{

	}

	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int metadata)
	{

	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{

	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{

	}

	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance)
	{

	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{

	}

	@Override
	public void onNeighborChange(IBlockAccess blockAccess, int x, int y, int z, int tileX, int tileY, int tileZ)
	{

	}

	@Override
	public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
	{
		/*
		if (this == Blocks.grass || this == Blocks.farmland) {
			world.setBlock(x, y, z, Blocks.dirt, 0, 2);
		}
		*/
	}

	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int metadata)
	{

	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		if (owner.hasTileEntity(metadata)) {// && !(this instanceof BlockContainer)) {
			world.removeTileEntity(x, y, z);
		}
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata)
	{
		player.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(owner)], 1);
		player.addExhaustion(0.025F);

		if (owner.canSilkHarvest(world, player, x, y, z, metadata) && EnchantmentHelper.getSilkTouchModifier(player)) {
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			ItemStack itemstack = owner.createStackedBlock(metadata);

			if (itemstack != null) items.add(itemstack);

			ForgeEventFactory.fireBlockHarvesting(items, world, owner, x, y, z, metadata, 0, 1.0f, true, player);
			for (ItemStack is : items) {
				owner.dropBlockAsItem(world, x, y, z, is);
			}
		} else {
			owner.getField_harvesters().set(player);
			int i1 = EnchantmentHelper.getFortuneModifier(player);
			owner.dropBlockAsItem(world, x, y, z, metadata, i1);
			owner.getField_harvesters().set(null);
		}
	}

}
