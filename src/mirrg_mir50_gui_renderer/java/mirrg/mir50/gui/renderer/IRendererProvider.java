package mirrg.mir50.gui.renderer;

public interface IRendererProvider<T>
{

	/**
	 * @return
	 *         null: 規定のレンダラーを使う
	 */
	public IRenderer<T> getRenderer();

}
