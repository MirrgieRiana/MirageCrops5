package mirrg.mir51.block.multi;

import java.util.ArrayList;
import java.util.Random;

import mirrg.mir50.block.AdaptorBlockHarvestOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockHarvestMulti extends AdaptorBlockHarvestOverriding
{

	protected ContainerMetaBlock containerMetaBlock;

	public AdaptorBlockHarvestMulti(BlockMir50 owner, IVirtualClass superObject, ContainerMetaBlock containerMetaBlock)
	{
		super(owner, superObject);
		this.containerMetaBlock = containerMetaBlock;
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(meta);
		if (metaBlock == null) return super.canSilkHarvest(world, player, x, y, z, meta);
		return metaBlock.accessor_IAdaptorBlockHarvest.get().canSilkHarvest(world, player, x, y, z, meta);
	}

	@Override
	public ItemStack createStackedBlock(int meta)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(meta);
		if (metaBlock == null) return super.createStackedBlock(meta);
		return metaBlock.accessor_IAdaptorBlockHarvest.get().createStackedBlock(meta);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(meta);
		if (metaBlock == null) return super.getDrops(world, x, y, z, meta, fortune);
		return metaBlock.accessor_IAdaptorBlockHarvest.get().getDrops(world, x, y, z, meta, fortune);
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(meta);
		if (metaBlock == null) return super.quantityDropped(meta, fortune, random);
		return metaBlock.accessor_IAdaptorBlockHarvest.get().quantityDropped(meta, fortune, random);
	}

	@Override
	@Deprecated
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		return super.quantityDroppedWithBonus(fortune, random);
	}

	@Override
	@Deprecated
	public int quantityDropped(Random random)
	{
		return super.quantityDropped(random);
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(meta);
		if (metaBlock == null) return super.getItemDropped(meta, random, fortune);
		return metaBlock.accessor_IAdaptorBlockHarvest.get().getItemDropped(meta, random, fortune);
	}

	@Override
	public int damageDropped(int damage)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(damage);
		if (metaBlock == null) return super.damageDropped(damage);
		return metaBlock.accessor_IAdaptorBlockHarvest.get().damageDropped(damage);
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) return super.getDamageValue(world, x, y, z);
		return metaBlock.accessor_IAdaptorBlockHarvest.get().getDamageValue(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(world, x, y, z);
		if (metaBlock == null) return super.getItem(world, x, y, z);
		return metaBlock.accessor_IAdaptorBlockHarvest.get().getItem(world, x, y, z);
	}

	@Override
	public int getExpDrop(IBlockAccess world, int meta, int fortune)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(meta);
		if (metaBlock == null) return super.getExpDrop(world, meta, fortune);
		return metaBlock.accessor_IAdaptorBlockHarvest.get().getExpDrop(world, meta, fortune);
	}

}
