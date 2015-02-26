package mirrg.mir50.block;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public interface IAdaptorBlockEvents
{

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float x2, float y2, float z2);

	public void onBlockAdded(World world, int x, int y, int z);

	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player);

	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion);

	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metadata);

	public boolean onBlockEventReceived(World world, int x, int y, int z, int arg1, int arg2);

	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion);

	public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player);

	public int onBlockPlaced(World world, int x, int y, int z, int side, float x2, float y2, float z2, int metadata);

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack);

	public void onBlockPreDestroy(World world, int x, int y, int z, int metadata);

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity);

	public void onEntityWalking(World world, int x, int y, int z, Entity entity);

	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance);

	public void onNeighborBlockChange(World world, int x, int y, int z, Block block);

	public void onNeighborChange(IBlockAccess blockAccess, int x, int y, int z, int tileX, int tileY, int tileZ);

	public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ);

	public void onPostBlockPlaced(World world, int x, int y, int z, int metadata);

	public void breakBlock(World world, int x, int y, int z, Block block, int metadata);

	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata);

}
