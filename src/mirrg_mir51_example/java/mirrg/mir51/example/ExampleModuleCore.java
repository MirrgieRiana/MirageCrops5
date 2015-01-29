package mirrg.mir51.example;

import java.util.List;

import mirrg.mir50.modding.ModuleAbstract;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.mir51.loaders.LoaderCreativeTab;
import mirrg.mir51.loaders.LoaderItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExampleModuleCore extends ModuleAbstract
{

	public static LoaderCreativeTab loaderCreativeTab = new LoaderCreativeTab();
	public static LoaderItem loaderItemSample = new LoaderItem();
	public static LoaderBlock loaderBlockSample = new LoaderBlock();

	public ExampleModuleCore()
	{

		loaderCreativeTab.init(() -> loaderItemSample.get(), "Sample");
		ExampleApiModuleCore.loaderCreativeTab = loaderCreativeTab;
		add(loaderCreativeTab);

		loaderItemSample.init(() -> new ItemSample(), "sampleItem", ExampleMod.MODID);
		loaderItemSample.setCreativeTab(loaderCreativeTab);
		ExampleApiModuleCore.loaderItemSample = loaderItemSample;
		add(loaderItemSample);

		loaderBlockSample.init(() -> new BlockSample(), ItemBlock.class, "sampleBlock");
		loaderBlockSample.setCreativeTab(loaderCreativeTab);
		ExampleApiModuleCore.loaderBlockSample = loaderBlockSample;
		add(loaderBlockSample);

	}

	private static class ItemSample extends Item
	{

		public ItemSample()
		{
			setTextureName("minecraft:apple");
			setHasSubtypes(true);
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
		{
			p_150895_3_.add(new ItemStack(p_150895_1_, 1, 0));
			p_150895_3_.add(new ItemStack(p_150895_1_, 1, 1));
			p_150895_3_.add(new ItemStack(p_150895_1_, 1, 2));
		}

	}

	private static class BlockSample extends Block
	{

		private BlockSample()
		{
			super(Material.rock);
			setBlockTextureName("minecraft:grass_top");
		}

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

	}

}
