package mirrg.mir50.block.multi;

import java.util.ArrayList;
import java.util.Random;

import mirrg.mir50.block.AdaptorBlockHarvest;
import mirrg.mir50.block.BlockMir50;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockHarvestMulti extends AdaptorBlockHarvest
{

	protected ContainerMetaBlock containerMetaBlock;

	public AdaptorBlockHarvestMulti(BlockMir50 blockMir50, ContainerMetaBlock containerMetaBlock)
	{
		super(blockMir50);
		this.containerMetaBlock = containerMetaBlock;
	}

	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		MetaBlock metaBlock = containerMetaBlock.get(meta);
		if (metaBlock == null || metaBlock.adaptorBlockHarvest == null) return super.canSilkHarvest(world, player, x, y, z, meta);
		return metaBlock.adaptorBlockHarvest.canSilkHarvest(world, player, x, y, z, meta);
	}

	public ItemStack createStackedBlock(int meta)
	{
		MetaBlock metaBlock = containerMetaBlock.get(meta);
		if (metaBlock == null || metaBlock.adaptorBlockHarvest == null) return super.createStackedBlock(meta);
		return metaBlock.adaptorBlockHarvest.createStackedBlock(meta);
	}

	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
	{
		MetaBlock metaBlock = containerMetaBlock.get(meta);
		if (metaBlock == null || metaBlock.adaptorBlockHarvest == null) return super.getDrops(world, x, y, z, meta, fortune);
		return metaBlock.adaptorBlockHarvest.getDrops(world, x, y, z, meta, fortune);
	}

	public int quantityDropped(int meta, int fortune, Random random)
	{
		MetaBlock metaBlock = containerMetaBlock.get(meta);
		if (metaBlock == null || metaBlock.adaptorBlockHarvest == null) return super.quantityDropped(meta, fortune, random);
		return metaBlock.adaptorBlockHarvest.quantityDropped(meta, fortune, random);
	}

	@Deprecated
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		return super.quantityDroppedWithBonus(fortune, random);
	}

	@Deprecated
	public int quantityDropped(Random random)
	{
		return super.quantityDropped(random);
	}

	public Item getItemDropped(int meta, Random random, int fortune)
	{
		MetaBlock metaBlock = containerMetaBlock.get(meta);
		if (metaBlock == null || metaBlock.adaptorBlockHarvest == null) return super.getItemDropped(meta, random, fortune);
		return metaBlock.adaptorBlockHarvest.getItemDropped(meta, random, fortune);
	}

	public int damageDropped(int damage)
	{
		MetaBlock metaBlock = containerMetaBlock.get(damage);
		if (metaBlock == null || metaBlock.adaptorBlockHarvest == null) return damage;
		return metaBlock.adaptorBlockHarvest.damageDropped(damage);
	}

	public int getDamageValue(World world, int x, int y, int z)
	{
		MetaBlock metaBlock = containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null || metaBlock.adaptorBlockHarvest == null) return super.getDamageValue(world, x, y, z);
		return metaBlock.adaptorBlockHarvest.getDamageValue(world, x, y, z);
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		MetaBlock metaBlock = containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null || metaBlock.adaptorBlockHarvest == null) return super.getItem(world, x, y, z);
		return metaBlock.adaptorBlockHarvest.getItem(world, x, y, z);
	}

	public int getExpDrop(IBlockAccess world, int meta, int fortune)
	{
		MetaBlock metaBlock = containerMetaBlock.get(meta);
		if (metaBlock == null || metaBlock.adaptorBlockHarvest == null) return super.getExpDrop(world, meta, fortune);
		return metaBlock.adaptorBlockHarvest.getExpDrop(world, meta, fortune);
	}

}
