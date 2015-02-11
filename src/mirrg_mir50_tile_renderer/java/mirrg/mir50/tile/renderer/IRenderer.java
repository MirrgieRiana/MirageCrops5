package mirrg.mir50.tile.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IRenderer<T>
{

	@SideOnly(Side.CLIENT)
	public void drawBackgroundLayer(IGuiRenderHelper gui, T t, int mouseX, int mouseY);

	@SideOnly(Side.CLIENT)
	public void drawForegroundLayer(IGuiRenderHelper gui, T t, int mouseX, int mouseY);

	/**
	 * @return trueのとき、後続のレンダラーのToolTipの描画をキャンセルする。
	 */
	@SideOnly(Side.CLIENT)
	public boolean drawToolTip(IGuiRenderHelper gui, T t, int mouseX, int mouseY);

}
