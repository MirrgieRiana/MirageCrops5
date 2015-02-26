package mirrg_miragecrops5.material.matrix;

import java.util.ArrayList;
import java.util.function.Supplier;

import mirrg.h.struct.Tuple;
import mirrg.mir50.item.ItemMir50;
import mirrg.mir51.icon.multi.MultipleIconShape;
import mirrg.p.virtualclass.IVirtualClass;
import mirrg_miragecrops5.material.HelpersModuleMaterial;
import net.minecraft.item.ItemStack;

public class ShapeMaterial extends ShapeItemMirageCrops5
{

	public ShapeMaterial(String name, MultipleIconShape multipleIconShape)
	{
		super(name, multipleIconShape);
	}

	@Override
	public void onAppliedMetaItem(
		MaterialMirageCrops5 material,
		ItemMir50 owner,
		IVirtualClass superObject,
		int metaId,
		ArrayList<Tuple<String, Supplier<ItemStack>>> scheduleRegisterOreDictionary,
		ArrayList<Runnable> scheduleRegisterRecipe)
	{
		HelpersModuleMaterial.applyMultipleIcon(owner, superObject,
			HelpersModuleMaterial.createMultipleIcon(multipleIconShape, material.getName()));
	}

}
