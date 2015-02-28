package api.mirrg.mir50.registry.recipe;

import java.util.stream.Stream;

public interface IRegistryRecipeMir50<H extends IHandlerRecipeMir50<R, M, I, O>, R, M extends IMatcherMir50<I, O>, I, O>
	extends IHandlerRecipeMir50<R, M, I, O>
{

	public Stream<H> getHandlers();

	public void addHandler(H handler);

}
