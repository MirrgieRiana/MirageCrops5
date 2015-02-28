package api.mirrg.mir50.registry.recipe;

import java.util.Optional;
import java.util.stream.Stream;

public interface IHandlerRecipeMir50<R, M extends IMatcherMir50<I, O>, I, O>
{

	/**
	 * @param input
	 *            このインスタンスは返却されるマッチャーによって破壊されます。
	 */
	public Optional<M> matcher(I input);

	public Stream<R> getRecipesToShow();

}
