package mirrg.mir50.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IAdaptorBlockHarvest
{

	public boolean canSilkHarvest();

	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int meta);

	public ItemStack createStackedBlock(int meta);

	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune);

	public int quantityDropped(int meta, int fortune, Random random);

	public int quantityDroppedWithBonus(int fortune, Random random);

	public int quantityDropped(Random random);

	public Item getItemDropped(int meta, Random random, int fortune);

	public int damageDropped(int damage);

	public int getDamageValue(World world, int x, int y, int z);

	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z);

	public int getExpDrop(IBlockAccess world, int meta, int fortune);

}
