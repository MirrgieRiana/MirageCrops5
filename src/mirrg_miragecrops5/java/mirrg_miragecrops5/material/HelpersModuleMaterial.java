package mirrg_miragecrops5.material;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import mirrg.mir50.icon.multi.IMultipleIcon;
import mirrg.mir50.instanceregistry.IInstanceRegistry;
import mirrg.mir50.item.ItemMir50;
import mirrg.mir50.item.adaptors.AdaptorItemIconAutonomy;
import mirrg.mir50.material.DynamicRegistryMaterialProperty;
import mirrg.mir51.icon.multi.MultipleIcon;
import mirrg.mir51.icon.multi.MultipleIconShape;
import mirrg.p.virtualclass.IVirtualClass;
import mirrg_miragecrops5.material.matrix.MaterialMirageCrops5;
import mirrg_miragecrops5.material.matrix.ShapeItemMirageCrops5;
import net.minecraft.item.ItemStack;

public class HelpersModuleMaterial
{

	public static DynamicRegistryMaterialProperty registryMaterialProperty = new DynamicRegistryMaterialProperty();

	public static Hashtable<String, MaterialMirageCrops5> registryMaterial = new Hashtable<>();

	public static IInstanceRegistry<MaterialMirageCrops5> registryMaterialItem;
	public static IInstanceRegistry<MaterialMirageCrops5> registryMaterialBlock;
	public static IInstanceRegistry<ShapeItemMirageCrops5> registryShapeTool;
	public static IInstanceRegistry<ShapeItemMirageCrops5> registryShapeMaterial;

	public static void applyMultipleIcon(ItemMir50 itemMir50, IVirtualClass superObject, IMultipleIcon multipleIcon)
	{
		AdaptorItemIconAutonomy a = new AdaptorItemIconAutonomy(itemMir50, superObject);
		for (int i = 0; i < multipleIcon.getLength(); i++) {
			a.appendIcon(multipleIcon.getIconString(i), multipleIcon.getColor(i));
		}
		superObject.getVirtualClass().override(a);
	}

	public static IMultipleIcon createMultipleIcon(MultipleIconShape multipleIconShape, String nameMaterial)
	{
		if (multipleIconShape.getChannelsUpperBound() == 1) {
			return new MultipleIcon(multipleIconShape, registryMaterialProperty.getColor(nameMaterial), 0x896727);
		} else {
			return new MultipleIcon(multipleIconShape, registryMaterialProperty.getColor(nameMaterial));
		}
	}

	public static RecipesOreBreaking recipesOreBreaking = new RecipesOreBreaking();

	public static class RecipesOreBreaking
	{

		public Hashtable<String, IResultOreBreaking> hash = new Hashtable<>();

		public IResultOreBreaking match(String dictionaryName)
		{
			return hash.get(dictionaryName);
		}

		public void add(String dictionaryName, IResultOreBreaking result)
		{
			hash.put(dictionaryName, result);
		}

		@FunctionalInterface
		public static interface IResultOreBreaking
		{

			public ArrayList<ItemStack> get(int fortune, Random random);

		}

	}

}
