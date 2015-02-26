package mirrg_miragecrops5.material.matrix;

import java.util.ArrayList;
import java.util.function.Supplier;

import mirrg.h.struct.Tuple;
import mirrg.mir50.block.BlockMir50;
import mirrg.mir51.loaders.LoaderBlock;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.item.ItemStack;

public abstract class ShapeBlockMirageCrops5 extends ShapeMirageCrops5
{

	public LoaderBlock loaderBlock;

	public ShapeBlockMirageCrops5(String name, LoaderBlock loaderBlock)
	{
		super(name);
		this.loaderBlock = loaderBlock;
	}

	public abstract void onAppliedMetaBlock(
		MaterialMirageCrops5 material,
		BlockMir50 owner,
		IVirtualClass superObject,
		int metaId,
		ArrayList<Tuple<String, Supplier<ItemStack>>> scheduleRegisterOreDictionary,
		ArrayList<Runnable> scheduleRegisterRecipe);

}
