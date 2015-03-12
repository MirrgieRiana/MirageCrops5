package mirrg.mir51.render.block.multiple;

import net.minecraft.util.IIcon;

public interface IConsumerMultipleRendering
{

	public default void accept(IIcon icon)
	{
		accept(icon, 0xFFFFFF);
	}

	public default void accept(IIcon icon, int color)
	{
		accept(icon, color, 0, false);
	}

	/**
	 * @param flip
	 *            TODO RenderBlocksが上下面の描画時にflipTextureを参照していないので上面・下面には使えない！
	 */
	public void accept(IIcon icon, int color, int rotate, boolean flip);

}
