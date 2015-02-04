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

public class AdaptorBlockHarvest extends AdaptorBlock implements IAdaptorBlockHarvest
{

	public AdaptorBlockHarvest(BlockMir50 blockMir50)
	{
		super(blockMir50);
	}

	public ThreadLocal<Integer> silk_check_meta = new ThreadLocal();

	@Override
	public boolean canSilkHarvest()
	{
		Integer meta = silk_check_meta.get();
		return blockMir50.renderAsNormalBlock() && !blockMir50.hasTileEntity(meta == null ? 0 : meta);
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		silk_check_meta.set(meta);
		boolean ret = this.canSilkHarvest();
		silk_check_meta.set(null);
		return ret;
	}

	@Override
	public ItemStack createStackedBlock(int meta)
	{
		int j = 0;
		Item item = Item.getItemFromBlock(blockMir50);

		if (item != null && item.getHasSubtypes())
		{
			j = meta;
		}

		return new ItemStack(item, 1, j);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		int count = quantityDropped(meta, fortune, world.rand);
		for (int i = 0; i < count; i++)
		{
			Item item = getItemDropped(meta, world.rand, fortune);
			if (item != null)
			{
				ret.add(new ItemStack(item, 1, damageDropped(meta)));
			}
		}
		return ret;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return quantityDroppedWithBonus(fortune, random);
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		return this.quantityDropped(random);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return Item.getItemFromBlock(blockMir50);
	}

	@Override
	public int damageDropped(int damage)
	{
		return 0;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return this.damageDropped(world.getBlockMetadata(x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		return Item.getItemFromBlock(blockMir50);
	}

	@Override
	public int getExpDrop(IBlockAccess world, int meta, int fortune)
	{
		return 0;
	}

}
