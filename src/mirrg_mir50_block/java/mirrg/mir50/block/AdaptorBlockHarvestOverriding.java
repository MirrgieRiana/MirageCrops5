package mirrg.mir50.block;

import java.util.ArrayList;
import java.util.Random;

import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockHarvestOverriding extends AdaptorBlockHarvest
{

	protected final IAdaptorBlockHarvest _super_IAdaptorBlockHarvest;

	public AdaptorBlockHarvestOverriding(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner);
		this._super_IAdaptorBlockHarvest = superObject.getVirtualClass().getCurrentImplementation(IAdaptorBlockHarvest.class);
	}

	@Override
	public boolean canSilkHarvest()
	{
		return this._super_IAdaptorBlockHarvest.canSilkHarvest();
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		return this._super_IAdaptorBlockHarvest.canSilkHarvest(world, player, x, y, z, meta);
	}

	@Override
	public ItemStack createStackedBlock(int meta)
	{
		return this._super_IAdaptorBlockHarvest.createStackedBlock(meta);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
	{
		return this._super_IAdaptorBlockHarvest.getDrops(world, x, y, z, meta, fortune);
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return this._super_IAdaptorBlockHarvest.quantityDropped(meta, fortune, random);
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		return this._super_IAdaptorBlockHarvest.quantityDroppedWithBonus(fortune, random);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return this._super_IAdaptorBlockHarvest.quantityDropped(random);
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return this._super_IAdaptorBlockHarvest.getItemDropped(meta, random, fortune);
	}

	@Override
	public int damageDropped(int damage)
	{
		return this._super_IAdaptorBlockHarvest.damageDropped(damage);
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return this._super_IAdaptorBlockHarvest.getDamageValue(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		return this._super_IAdaptorBlockHarvest.getItem(world, x, y, z);
	}

	@Override
	public int getExpDrop(IBlockAccess world, int meta, int fortune)
	{
		return this._super_IAdaptorBlockHarvest.getExpDrop(world, meta, fortune);
	}

}
