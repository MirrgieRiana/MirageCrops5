package mirrg.mir50.block;

import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AdaptorBlockEventsOverriding extends AdaptorBlockEvents
{

	protected final IAdaptorBlockEvents _super_IAdaptorBlockEvents;

	public AdaptorBlockEventsOverriding(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner);
		this._super_IAdaptorBlockEvents = superObject.getVirtualClass().getCurrentImplementation(IAdaptorBlockEvents.class);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float x2, float y2, float z2)
	{
		return _super_IAdaptorBlockEvents.onBlockActivated(world, x, y, z, player, side, x2, y2, z2);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		_super_IAdaptorBlockEvents.onBlockAdded(world, x, y, z);
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		_super_IAdaptorBlockEvents.onBlockClicked(world, x, y, z, player);
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
	{
		_super_IAdaptorBlockEvents.onBlockDestroyedByExplosion(world, x, y, z, explosion);
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metadata)
	{
		_super_IAdaptorBlockEvents.onBlockDestroyedByPlayer(world, x, y, z, metadata);
	}

	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int arg1, int arg2)
	{
		return _super_IAdaptorBlockEvents.onBlockEventReceived(world, x, y, z, arg1, arg2);
	}

	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
	{
		_super_IAdaptorBlockEvents.onBlockExploded(world, x, y, z, explosion);
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player)
	{
		_super_IAdaptorBlockEvents.onBlockHarvested(world, x, y, z, metadata, player);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float x2, float y2, float z2, int metadata)
	{
		return _super_IAdaptorBlockEvents.onBlockPlaced(world, x, y, z, side, x2, y2, z2, metadata);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
		_super_IAdaptorBlockEvents.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
	}

	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int metadata)
	{
		_super_IAdaptorBlockEvents.onBlockPreDestroy(world, x, y, z, metadata);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		_super_IAdaptorBlockEvents.onEntityCollidedWithBlock(world, x, y, z, entity);
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{
		_super_IAdaptorBlockEvents.onEntityWalking(world, x, y, z, entity);
	}

	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance)
	{
		_super_IAdaptorBlockEvents.onFallenUpon(world, x, y, z, entity, fallDistance);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		_super_IAdaptorBlockEvents.onNeighborBlockChange(world, x, y, z, block);
	}

	@Override
	public void onNeighborChange(IBlockAccess blockAccess, int x, int y, int z, int tileX, int tileY, int tileZ)
	{
		_super_IAdaptorBlockEvents.onNeighborChange(blockAccess, x, y, z, tileX, tileY, tileZ);
	}

	@Override
	public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
	{
		_super_IAdaptorBlockEvents.onPlantGrow(world, x, y, z, sourceX, sourceY, sourceZ);
	}

	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int metadata)
	{
		_super_IAdaptorBlockEvents.onPostBlockPlaced(world, x, y, z, metadata);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		_super_IAdaptorBlockEvents.breakBlock(world, x, y, z, block, metadata);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata)
	{
		_super_IAdaptorBlockEvents.harvestBlock(world, player, x, y, z, metadata);
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest)
	{
		return _super_IAdaptorBlockEvents.removedByPlayer(world, player, x, y, z, willHarvest);
	}

	@SuppressWarnings("deprecation")
	@Override
	@Deprecated
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z)
	{
		return _super_IAdaptorBlockEvents.removedByPlayer(world, player, x, y, z);
	}

}
