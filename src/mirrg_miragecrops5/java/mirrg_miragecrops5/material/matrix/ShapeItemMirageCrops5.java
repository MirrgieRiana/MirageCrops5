package mirrg_miragecrops5.material.matrix;

import java.util.ArrayList;
import java.util.function.Supplier;

import mirrg.h.struct.Tuple;
import mirrg.mir50.item.ItemMir50;
import mirrg.mir51.icon.multi.MultipleIconShape;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.item.ItemStack;

public abstract class ShapeItemMirageCrops5 extends ShapeMirageCrops5
{

	protected MultipleIconShape multipleIconShape;

	public ShapeItemMirageCrops5(String name, MultipleIconShape multipleIconShape)
	{
		super(name);
		this.multipleIconShape = multipleIconShape;
	}

	public abstract void onAppliedMetaItem(
		MaterialMirageCrops5 material,
		ItemMir50 owner,
		IVirtualClass superObject,
		int metaId,
		ArrayList<Tuple<String, Supplier<ItemStack>>> scheduleRegisterOreDictionary,
		ArrayList<Runnable> scheduleRegisterRecipe);

}
