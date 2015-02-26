package mirrg.mir51.block.multi;

import mirrg.mir50.block.AdaptorBlockEventsOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AdaptorBlockEventsMulti extends AdaptorBlockEventsOverriding
{

	protected ContainerMetaBlock containerMetaBlock;

	public AdaptorBlockEventsMulti(BlockMir50 owner, IVirtualClass superObject, ContainerMetaBlock containerMetaBlock)
	{
		super(owner, superObject);
		this.containerMetaBlock = containerMetaBlock;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float x2, float y2, float z2)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) return super.onBlockActivated(world, x, y, z, player, side, x2, y2, z2);
		return metaBlock.accessor_IAdaptorBlockEvents.get().onBlockActivated(world, x, y, z, player, side, x2, y2, z2);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) {
			super.onBlockAdded(world, x, y, z);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onBlockAdded(world, x, y, z);
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) {
			super.onBlockClicked(world, x, y, z, player);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onBlockClicked(world, x, y, z, player);
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) {
			super.onBlockDestroyedByExplosion(world, x, y, z, explosion);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onBlockDestroyedByExplosion(world, x, y, z, explosion);
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metadata)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(metadata);
		if (metaBlock == null) {
			super.onBlockDestroyedByPlayer(world, x, y, z, metadata);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onBlockDestroyedByPlayer(world, x, y, z, metadata);
	}

	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int arg1, int arg2)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) return super.onBlockEventReceived(world, x, y, z, arg1, arg2);
		return metaBlock.accessor_IAdaptorBlockEvents.get().onBlockEventReceived(world, x, y, z, arg1, arg2);
	}

	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) {
			super.onBlockExploded(world, x, y, z, explosion);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onBlockExploded(world, x, y, z, explosion);
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(metadata);
		if (metaBlock == null) {
			super.onBlockHarvested(world, x, y, z, metadata, player);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onBlockHarvested(world, x, y, z, metadata, player);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float x2, float y2, float z2, int metadata)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(metadata);
		if (metaBlock == null) return super.onBlockPlaced(world, x, y, z, side, x2, y2, z2, metadata);
		return metaBlock.accessor_IAdaptorBlockEvents.get().onBlockPlaced(world, x, y, z, side, x2, y2, z2, metadata);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) {
			super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
	}

	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int metadata)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(metadata);
		if (metaBlock == null) {
			super.onBlockPreDestroy(world, x, y, z, metadata);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onBlockPreDestroy(world, x, y, z, metadata);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) {
			super.onEntityCollidedWithBlock(world, x, y, z, entity);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onEntityCollidedWithBlock(world, x, y, z, entity);
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) {
			super.onEntityWalking(world, x, y, z, entity);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onEntityWalking(world, x, y, z, entity);
	}

	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) {
			super.onFallenUpon(world, x, y, z, entity, fallDistance);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onFallenUpon(world, x, y, z, entity, fallDistance);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) {
			super.onNeighborBlockChange(world, x, y, z, block);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onNeighborBlockChange(world, x, y, z, block);
	}

	@Override
	public void onNeighborChange(IBlockAccess blockAccess, int x, int y, int z, int tileX, int tileY, int tileZ)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(blockAccess, x, y, z);
		if (metaBlock == null) {
			super.onNeighborChange(blockAccess, x, y, z, tileX, tileY, tileZ);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onNeighborChange(blockAccess, x, y, z, tileX, tileY, tileZ);
	}

	@Override
	public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) {
			super.onPlantGrow(world, x, y, z, sourceX, sourceY, sourceZ);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onPlantGrow(world, x, y, z, sourceX, sourceY, sourceZ);
	}

	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int metadata)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(metadata);
		if (metaBlock == null) {
			super.onPostBlockPlaced(world, x, y, z, metadata);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().onPostBlockPlaced(world, x, y, z, metadata);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(metadata);
		if (metaBlock == null) {
			super.breakBlock(world, x, y, z, block, metadata);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().breakBlock(world, x, y, z, block, metadata);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(metadata);
		if (metaBlock == null) {
			super.harvestBlock(world, player, x, y, z, metadata);
			return;
		}
		metaBlock.accessor_IAdaptorBlockEvents.get().harvestBlock(world, player, x, y, z, metadata);
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) return super.removedByPlayer(world, player, x, y, z, willHarvest);
		return metaBlock.accessor_IAdaptorBlockEvents.get().removedByPlayer(world, player, x, y, z, willHarvest);
	}

	@SuppressWarnings("deprecation")
	@Override
	@Deprecated
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) return super.removedByPlayer(world, player, x, y, z);
		return metaBlock.accessor_IAdaptorBlockEvents.get().removedByPlayer(world, player, x, y, z);
	}

}
