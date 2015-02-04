package mirrg.mir50.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mirrg.p.virtualclass.IVirtualClass;
import mirrg.p.virtualclass.IVirtualImplementationAccessor;
import mirrg.p.virtualclass.VirtualClass;
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

public class BlockMir50 extends Block implements IVirtualClass
{

	public final VirtualClass virtualClass = new VirtualClass(this);

	public BlockMir50(Material material)
	{
		super(material);

		virtualClass.register(IAdaptorBlockSubBlocks.class, new AdaptorBlockSubBlocks(this));
		accessor_IAdaptorBlockSubBlocks = virtualClass.cast(IAdaptorBlockSubBlocks.class);
		virtualClass.register(IAdaptorBlockIcon.class, new AdaptorBlockIcon(this));
		accessor_IAdaptorBlockIcon = virtualClass.cast(IAdaptorBlockIcon.class);
		virtualClass.register(IAdaptorBlockHarvest.class, new AdaptorBlockHarvest(this));
		accessor_IAdaptorBlockHarvest = virtualClass.cast(IAdaptorBlockHarvest.class);
		virtualClass.register(IAdaptorBlockName.class, new AdaptorBlockName(this));
		accessor_IAdaptorBlockName = virtualClass.cast(IAdaptorBlockName.class);
		virtualClass.register(IAdaptorBlockRender.class, new AdaptorBlockRender(this));
		accessor_IAdaptorBlockRender = virtualClass.cast(IAdaptorBlockRender.class);

	}

	@Override
	public final VirtualClass getVirtualClass()
	{
		return virtualClass;
	}

	/////////////////////////////////////////////////////////////////////

	//TODO
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

	/////////////////////////////////////////////////////////////////////

	public final IVirtualImplementationAccessor<IAdaptorBlockSubBlocks> accessor_IAdaptorBlockSubBlocks;

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List itemStacks)
	{
		accessor_IAdaptorBlockSubBlocks.get().getSubBlocks(item, creativeTabs, itemStacks);
	}

	/////////////////////////////////////////////////////////////////////

	public final IVirtualImplementationAccessor<IAdaptorBlockIcon> accessor_IAdaptorBlockIcon;

	@SideOnly(Side.CLIENT)
	public final void setField_blockIcon(IIcon blockIcon)
	{
		this.blockIcon = blockIcon;
	}

	@SideOnly(Side.CLIENT)
	public final IIcon getField_blockIcon()
	{
		return blockIcon;
	}

	public final void setField_textureName(String textureName)
	{
		this.textureName = textureName;
	}

	public final String getField_textureName()
	{
		return textureName;
	}

	public final void setField_unlocalizedName(String unlocalizedName)
	{
		super.setBlockName(unlocalizedName);
	}

	public final String getField_unlocalizedName()
	{
		return super.getUnlocalizedName().substring(5);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		return accessor_IAdaptorBlockIcon.get().getIcon(blockAccess, x, y, z, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return accessor_IAdaptorBlockIcon.get().getIcon(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon func_149735_b(int side, int meta)
	{
		return accessor_IAdaptorBlockIcon.get().func_149735_b(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
	{
		return accessor_IAdaptorBlockIcon.get().colorMultiplier(blockAccess, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta)
	{
		return accessor_IAdaptorBlockIcon.get().getRenderColor(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		accessor_IAdaptorBlockIcon.get().registerBlockIcons(iconRegister);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTextureName()
	{
		return accessor_IAdaptorBlockIcon.get().getTextureName();
	}

	@Override
	public Block setBlockTextureName(String textureName)
	{
		return accessor_IAdaptorBlockIcon.get().setBlockTextureName(textureName);
	}

	/////////////////////////////////////////////////////////////////////

	public final IVirtualImplementationAccessor<IAdaptorBlockHarvest> accessor_IAdaptorBlockHarvest;

	@Override
	public boolean canSilkHarvest()
	{
		return accessor_IAdaptorBlockHarvest.get().canSilkHarvest();
	}

	/**
	 * シルクタッチで回収可能か。その場合、{@link #createStackedBlock(int)}で入手アイテムを制御する。
	 */
	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		return accessor_IAdaptorBlockHarvest.get().canSilkHarvest(world, player, x, y, z, meta);
	}

	/**
	 * シルクタッチでONの赤石鉱石を採取してもOFF状態のが手に入る処理に使う。
	 */
	@Override
	public ItemStack createStackedBlock(int meta)
	{
		return accessor_IAdaptorBlockHarvest.get().createStackedBlock(meta);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
	{
		return accessor_IAdaptorBlockHarvest.get().getDrops(world, x, y, z, meta, fortune);
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return accessor_IAdaptorBlockHarvest.get().quantityDropped(meta, fortune, random);
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		return accessor_IAdaptorBlockHarvest.get().quantityDroppedWithBonus(fortune, random);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return accessor_IAdaptorBlockHarvest.get().quantityDropped(random);
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return accessor_IAdaptorBlockHarvest.get().getItemDropped(meta, random, fortune);
	}

	@Override
	public int damageDropped(int damage)
	{
		return accessor_IAdaptorBlockHarvest.get().damageDropped(damage);
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return accessor_IAdaptorBlockHarvest.get().getDamageValue(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		return accessor_IAdaptorBlockHarvest.get().getItem(world, x, y, z);
	}

	@Override
	public int getExpDrop(IBlockAccess world, int meta, int fortune)
	{
		return accessor_IAdaptorBlockHarvest.get().getExpDrop(world, meta, fortune);
	}

	//TODO
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

	/////////////////////////////////////////////////////////////////////

	public final IVirtualImplementationAccessor<IAdaptorBlockName> accessor_IAdaptorBlockName;

	@Override
	public Block setBlockName(String unlocalizedName)
	{
		return accessor_IAdaptorBlockName.get().setBlockName(unlocalizedName);
	}

	@Override
	public String getLocalizedName()
	{
		return accessor_IAdaptorBlockName.get().getLocalizedName();
	}

	@Override
	public String getUnlocalizedName()
	{
		return accessor_IAdaptorBlockName.get().getUnlocalizedName();
	}

	/////////////////////////////////////////////////////////////////////

	public final IVirtualImplementationAccessor<IAdaptorBlockRender> accessor_IAdaptorBlockRender;

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return accessor_IAdaptorBlockRender.get().getRenderType();
	}

}
