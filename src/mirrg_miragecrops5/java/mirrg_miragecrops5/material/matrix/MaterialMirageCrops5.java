package mirrg_miragecrops5.material.matrix;

import mirrg.he.math.HelpersString;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class MaterialMirageCrops5
{

	private String name;

	public MaterialMirageCrops5(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public Runnable isExistAndGetRecipeSetter(ShapeMirageCrops5 shape, Block block, int metaId)
	{
		return null;
	}

	/**
	 * @return null: この形状は存在しない。
	 */
	public Runnable isExistAndGetRecipeSetter(ShapeMirageCrops5 shape, Item item, int metaId)
	{
		return null;
	}

	protected class Shape
	{

		private String shapeName;

		public Shape(String shapeName)
		{
			this.shapeName = shapeName;
		}

		public boolean is(ShapeMirageCrops5 shape)
		{
			return shape.getName().equals(shapeName);
		}

		public String getOreName()
		{
			return shapeName + HelpersString.toUpperCaseHead(getName());
		}

	}

}
