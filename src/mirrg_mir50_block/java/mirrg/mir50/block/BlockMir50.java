package mirrg.mir50.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMir50 extends Block
{

	public BlockMir50(Material material)
	{
		super(material);
	}

	/*
	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor()
	{
		double d0 = 0.5D;
		double d1 = 1.0D;
		return ColorizerGrass.getGrassColor(d0, d1);
	}

		@Override
		@SideOnly(Side.CLIENT)
		public int getRenderColor(int p_149741_1_)
		{
			return this.getBlockColor();
		}

		@Override
		@SideOnly(Side.CLIENT)
		public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
		{
			int l = 0;
			int i1 = 0;
			int j1 = 0;

			for (int k1 = -1; k1 <= 1; ++k1)
			{
				for (int l1 = -1; l1 <= 1; ++l1)
				{
					int i2 = p_149720_1_.getBiomeGenForCoords(p_149720_2_ + l1, p_149720_4_ + k1).getBiomeGrassColor(p_149720_2_ + l1, p_149720_3_, p_149720_4_ + k1);
					l += (i2 & 16711680) >> 16;
					i1 += (i2 & 65280) >> 8;
					j1 += i2 & 255;
				}
			}

			return 0xffffff - ((l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255);
		}
	*/

	//

	public AdaptorBlockSubBlocks adaptorBlockSubBlocks = new AdaptorBlockSubBlocks(this);

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List itemStacks)
	{
		adaptorBlockSubBlocks.getSubBlocks(item, creativeTabs, itemStacks);
	}

	//

	public AdaptorBlockIcon adaptorBlockIcon = new AdaptorBlockIcon(this);

	@SideOnly(Side.CLIENT)
	public void setField_blockIcon(IIcon blockIcon)
	{
		this.blockIcon = blockIcon;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getField_blockIcon()
	{
		return blockIcon;
	}

	public void setField_textureName(String textureName)
	{
		this.textureName = textureName;
	}

	public String getField_textureName()
	{
		return textureName;
	}

	public void setField_unlocalizedName(String unlocalizedName)
	{
		super.setBlockName(unlocalizedName);
	}

	public String getField_unlocalizedName()
	{
		return super.getUnlocalizedName().substring(5);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		return adaptorBlockIcon.getIcon(blockAccess, x, y, z, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return adaptorBlockIcon.getIcon(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon func_149735_b(int side, int meta)
	{
		return adaptorBlockIcon.func_149735_b(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		adaptorBlockIcon.registerBlockIcons(iconRegister);
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected String getTextureName()
	{
		return adaptorBlockIcon.getTextureName();
	}

	@Override
	public Block setBlockTextureName(String textureName)
	{
		return adaptorBlockIcon.setBlockTextureName(textureName);
	}

	//

	public AdaptorBlockHarvest adaptorBlockHarvest = new AdaptorBlockHarvest(this);

	@Override
	public boolean canSilkHarvest()
	{
		return adaptorBlockHarvest.canSilkHarvest();
	}

	/**
	 * シルクタッチで回収可能か。その場合、{@link #createStackedBlock(int)}で入手アイテムを制御する。
	 */
	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		return adaptorBlockHarvest.canSilkHarvest(world, player, x, y, z, meta);
	}

	/**
	 * シルクタッチでONの赤石鉱石を採取してもOFF状態のが手に入る処理に使う。
	 */
	@Override
	public ItemStack createStackedBlock(int meta)
	{
		return adaptorBlockHarvest.createStackedBlock(meta);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
	{
		return adaptorBlockHarvest.getDrops(world, x, y, z, meta, fortune);
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return adaptorBlockHarvest.quantityDropped(meta, fortune, random);
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		return adaptorBlockHarvest.quantityDroppedWithBonus(fortune, random);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return adaptorBlockHarvest.quantityDropped(random);
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return adaptorBlockHarvest.getItemDropped(meta, random, fortune);
	}

	@Override
	public int damageDropped(int damage)
	{
		return adaptorBlockHarvest.damageDropped(damage);
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return adaptorBlockHarvest.getDamageValue(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		return adaptorBlockHarvest.getItem(world, x, y, z);
	}

	@Override
	public int getExpDrop(IBlockAccess world, int meta, int fortune)
	{
		return adaptorBlockHarvest.getExpDrop(world, meta, fortune);
	}

	/*
	@SideOnly(Side.CLIENT)
	public int getBlockColor()
	{
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int p_149741_1_)
	{
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
	{
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 0;
	}

	@Override
	public boolean canRenderInPass(int pass)
	{
		return pass == getRenderBlockPass();
	}
	*/

}
