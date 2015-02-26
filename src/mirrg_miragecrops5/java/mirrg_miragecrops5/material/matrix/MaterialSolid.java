package mirrg_miragecrops5.material.matrix;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class MaterialSolid extends MaterialMirageCrops5
{

	protected Shape ore = new Shape("ore");
	protected Shape block = new Shape("block");

	protected Shape dust = new Shape("dust");
	protected Shape dustSmall = new Shape("dustSmall");
	protected Shape dustTiny = new Shape("dustTiny");

	/**
	 * 0: 無力　硬度0から　他<br>
	 * 1: 初級　硬度3から　方解石<br>
	 * 2: 下級　硬度5から　燐灰石<br>
	 * 3: 中級　硬度7から　石英<br>
	 * 4: 上級　硬度9から　ルビー<br>
	 * 5: 超級　硬度10から　ダイヤモンド
	 */
	protected int tierHardness;

	/**
	 * 0: 無力　他<br>
	 * 1: 初級　銅級<br>
	 * 2: 下級　鉄・アルミ・青銅級<br>
	 * 3: 中級　鋼鉄・ステンレス級<br>
	 * 4: 上級　タングステンスチール・チタン・クロム級<br>
	 * 5: 超級　イリジウム・オスミウム級
	 */
	protected int tierStrength;

	public MaterialSolid(String name, int tierHardness, int tierStrength)
	{
		super(name);
		this.tierHardness = tierHardness;
		this.tierStrength = tierStrength;
	}

	@Override
	public Runnable isExistAndGetRecipeSetter(ShapeMirageCrops5 shape, Block block, int metaId)
	{
		if (ore.is(shape)) return () -> {

		};
		if (this.block.is(shape)) return () -> {

		};

		return super.isExistAndGetRecipeSetter(shape, block, metaId);
	}

	@Override
	public Runnable isExistAndGetRecipeSetter(
		ShapeMirageCrops5 shape,
		Item item,
		int metaId)
	{
		if (dust.is(shape)) return () -> {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(item, 1, metaId),
				dustSmall.getOreName(),
				dustSmall.getOreName(),
				dustSmall.getOreName(),
				dustSmall.getOreName()));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 1, metaId),
				"AAA",
				"AAA",
				"AAA",
				'A', dustTiny.getOreName()));
		};
		if (dustSmall.is(shape)) return () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 4, metaId),
				false,
				" A",
				'A', dust.getOreName()));
		};
		if (dustTiny.is(shape)) return () -> {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, 9, metaId),
				false,
				"A ",
				'A', dust.getOreName()));
		};

		return super.isExistAndGetRecipeSetter(shape, item, metaId);
	}

}
