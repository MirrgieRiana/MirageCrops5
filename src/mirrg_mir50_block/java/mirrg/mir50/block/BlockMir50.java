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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
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
		virtualClass.register(IAdaptorBlockTileEntity.class, new AdaptorBlockTileEntity(this));
		accessor_IAdaptorBlockTileEntity = virtualClass.cast(IAdaptorBlockTileEntity.class);
		virtualClass.register(IAdaptorBlockEvents.class, new AdaptorBlockEvents(this));
		accessor_IAdaptorBlockEvents = virtualClass.cast(IAdaptorBlockEvents.class);

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

	/////////////////////////////////////////////////////////////////////

	public final IVirtualImplementationAccessor<IAdaptorBlockTileEntity> accessor_IAdaptorBlockTileEntity;

	@SuppressWarnings("deprecation")
	@Deprecated
	@Override
	public boolean hasTileEntity()
	{
		return accessor_IAdaptorBlockTileEntity.get().hasTileEntity();
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
		return accessor_IAdaptorBlockTileEntity.get().hasTileEntity(metadata);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return accessor_IAdaptorBlockTileEntity.get().createTileEntity(world, metadata);
	}

	/////////////////////////////////////////////////////////////////////

	public final IVirtualImplementationAccessor<IAdaptorBlockEvents> accessor_IAdaptorBlockEvents;

	public final void setField_harvesters(ThreadLocal<EntityPlayer> harvesters)
	{
		this.harvesters = harvesters;
	}

	public final ThreadLocal<EntityPlayer> getField_harvesters()
	{
		return harvesters;
	}

	@Override
	public void dropBlockAsItem(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_)
	{
		super.dropBlockAsItem(p_149642_1_, p_149642_2_, p_149642_3_, p_149642_4_, p_149642_5_);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float x2, float y2, float z2)
	{
		return accessor_IAdaptorBlockEvents.get().onBlockActivated(world, x, y, z, player, side, x2, y2, z2);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		accessor_IAdaptorBlockEvents.get().onBlockAdded(world, x, y, z);
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		accessor_IAdaptorBlockEvents.get().onBlockClicked(world, x, y, z, player);
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
	{
		accessor_IAdaptorBlockEvents.get().onBlockDestroyedByExplosion(world, x, y, z, explosion);
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metadata)
	{
		accessor_IAdaptorBlockEvents.get().onBlockDestroyedByPlayer(world, x, y, z, metadata);
	}

	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int arg1, int arg2)
	{
		return accessor_IAdaptorBlockEvents.get().onBlockEventReceived(world, x, y, z, arg1, arg2);
	}

	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
	{
		accessor_IAdaptorBlockEvents.get().onBlockExploded(world, x, y, z, explosion);
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player)
	{
		accessor_IAdaptorBlockEvents.get().onBlockHarvested(world, x, y, z, metadata, player);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float x2, float y2, float z2, int metadata)
	{
		return accessor_IAdaptorBlockEvents.get().onBlockPlaced(world, x, y, z, side, x2, y2, z2, metadata);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
		accessor_IAdaptorBlockEvents.get().onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
	}

	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int metadata)
	{
		accessor_IAdaptorBlockEvents.get().onBlockPreDestroy(world, x, y, z, metadata);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		accessor_IAdaptorBlockEvents.get().onEntityCollidedWithBlock(world, x, y, z, entity);
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{
		accessor_IAdaptorBlockEvents.get().onEntityWalking(world, x, y, z, entity);
	}

	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance)
	{
		accessor_IAdaptorBlockEvents.get().onFallenUpon(world, x, y, z, entity, fallDistance);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		accessor_IAdaptorBlockEvents.get().onNeighborBlockChange(world, x, y, z, block);
	}

	@Override
	public void onNeighborChange(IBlockAccess blockAccess, int x, int y, int z, int tileX, int tileY, int tileZ)
	{
		accessor_IAdaptorBlockEvents.get().onNeighborChange(blockAccess, x, y, z, tileX, tileY, tileZ);
	}

	@Override
	public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
	{
		accessor_IAdaptorBlockEvents.get().onPlantGrow(world, x, y, z, sourceX, sourceY, sourceZ);
	}

	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int metadata)
	{
		accessor_IAdaptorBlockEvents.get().onPostBlockPlaced(world, x, y, z, metadata);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		accessor_IAdaptorBlockEvents.get().breakBlock(world, x, y, z, block, metadata);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata)
	{
		accessor_IAdaptorBlockEvents.get().harvestBlock(world, player, x, y, z, metadata);
	}

}
