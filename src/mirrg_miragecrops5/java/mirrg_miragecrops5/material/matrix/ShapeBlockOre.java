package mirrg_miragecrops5.material.matrix;

import java.util.ArrayList;
import java.util.function.Supplier;

import mirrg.h.struct.Tuple;
import mirrg.he.math.HelpersString;
import mirrg.mir50.block.AdaptorBlockHarvestOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRenderingAutonomy;
import mirrg.mir51.render.block.multiple.HelpersBlockMultipleRendering;
import mirrg.p.virtualclass.IVirtualClass;
import mirrg_miragecrops5.material.HelpersModuleMaterial;
import mirrg_miragecrops5.material.HelpersModuleMaterial.RecipesOreBreaking.IResultOreBreaking;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ShapeBlockOre extends ShapeBlockMirageCrops5
{

	public ShapeBlockOre(String name, LoaderBlock loaderBlock)
	{
		super(name, loaderBlock);
	}

	@Override
	public void onAppliedMetaBlock(
		MaterialMirageCrops5 material,
		BlockMir50 owner,
		IVirtualClass superObject,
		int metaId,
		ArrayList<Tuple<String, Supplier<ItemStack>>> scheduleRegisterOreDictionary,
		ArrayList<Runnable> scheduleRegisterRecipe)
	{
		// テクスチャの設定
		AdaptorBlockMultipleRenderingAutonomy a = HelpersBlockMultipleRendering.makeAutonomy(superObject, owner);
		a.appendIcon("minecraft:stone");
		{
			String unlocalizedName = getName() + HelpersString.toUpperCaseHead(material.getName());
			a.appendIcon("miragecrops5:" + unlocalizedName);
		}

		// ドロップアイテム設定
		superObject.getVirtualClass().override(new AdaptorBlockHarvestOverriding(owner, superObject) {

			@Override
			public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
			{
				IResultOreBreaking result = HelpersModuleMaterial.recipesOreBreaking.match(
					getName() + HelpersString.toUpperCaseHead(material.getName()));
				if (result == null) return super.getDrops(world, x, y, z, metaId, fortune);
				return result.get(fortune, world.rand);
			}

		});
	}
}
