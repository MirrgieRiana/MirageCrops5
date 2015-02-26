package mirrg_miragecrops5.material.matrix;

import java.util.ArrayList;
import java.util.function.Supplier;

import mirrg.h.struct.Tuple;
import mirrg.he.math.HelpersString;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRenderingAutonomy;
import mirrg.mir51.render.block.multiple.HelpersBlockMultipleRendering;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.item.ItemStack;

public class ShapeBlockBlock extends ShapeBlockMirageCrops5
{
	public ShapeBlockBlock(String name, LoaderBlock loaderBlock)
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
		{
			String unlocalizedName = getName() + HelpersString.toUpperCaseHead(material.getName());
			a.appendIcon("miragecrops5:" + unlocalizedName);
		}
	}

}
