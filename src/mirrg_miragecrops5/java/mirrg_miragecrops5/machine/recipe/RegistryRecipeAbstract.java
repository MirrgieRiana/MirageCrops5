package mirrg_miragecrops5.machine.recipe;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import api.mirrg.mir50.registry.recipe.IHandlerRecipeMir50;
import api.mirrg.mir50.registry.recipe.IMatcherMir50;
import api.mirrg.mir50.registry.recipe.IRegistryRecipeMir50;

public abstract class RegistryRecipeAbstract<H extends IHandlerRecipeMir50<R, M, I, O>, R, M extends IMatcherMir50<I, O>, I, O>
	implements IRegistryRecipeMir50<H, R, M, I, O>
{

	private ArrayList<H> handlers = new ArrayList<>();

	@Override
	public Stream<H> getHandlers()
	{
		return handlers.stream();
	}

	@Override
	public void addHandler(H handler)
	{
		handlers.add(handler);
	}

	protected <T extends H> T add(T handler)
	{
		addHandler(handler);
		return handler;
	}

	@Override
	public Optional<M> matcher(I input)
	{
		return getHandlers()
			.map(handler -> handler.matcher(input))
			.filter(Optional::isPresent)
			.map(Optional::get)
			.findFirst();
	}

	@Override
	public Stream<R> getRecipesToShow()
	{
		return getHandlers().flatMap(handler -> handler.getRecipesToShow());
	}

}
