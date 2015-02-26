package mirrg_miragecrops5.material.matrix;

import java.util.ArrayList;
import java.util.function.Supplier;

import mirrg.h.struct.Tuple;
import mirrg.mir50.item.ItemMir50;
import mirrg.mir50.item.adaptors.AdaptorItemContainerItemCraftingToolNBT;
import mirrg.mir50.item.adaptors.AdaptorItemNameInformationCraftingToolNBT;
import mirrg.mir51.icon.multi.MultipleIconShape;
import mirrg.p.virtualclass.IVirtualClass;
import mirrg_miragecrops5.material.HelpersModuleMaterial;
import net.minecraft.item.ItemStack;

public class ShapeCraftingTool extends ShapeItemMirageCrops5
{

	private double besicDurability;
	private double weightHardness;
	private double weightStrength;

	public ShapeCraftingTool(String name, MultipleIconShape multipleIconShape,
		double besicDurability, double weightHardness, double weightStrength)
	{
		super(name, multipleIconShape);
		this.besicDurability = besicDurability;
		this.weightHardness = weightHardness;
		this.weightStrength = weightStrength;
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
		// アイコンの設定
		HelpersModuleMaterial.applyMultipleIcon(owner, superObject,
			HelpersModuleMaterial.createMultipleIcon(multipleIconShape, material.getName()));

		// クラフティング耐久値の設定
		{
			int durability = 1;
			if (material instanceof MaterialSolid) {
				durability = Math.max(1, (int) (besicDurability
					* Math.pow(weightHardness, ((MaterialSolid) material).tierHardness - 1)
					* Math.pow(weightStrength, ((MaterialSolid) material).tierStrength - 1)
					) - 1);
			}

			superObject.getVirtualClass().override(new AdaptorItemContainerItemCraftingToolNBT(owner, superObject, durability, "damage"));
			superObject.getVirtualClass().override(new AdaptorItemNameInformationCraftingToolNBT(owner, superObject, durability, "damage"));
		}

		// craftingTool用汎用鉱石辞書名
		scheduleRegisterOreDictionary.add(new Tuple<>(name, () -> new ItemStack(owner, 1, metaId)));
	}

}
