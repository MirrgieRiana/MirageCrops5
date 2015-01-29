package api.mirrg.mir50.loader;

public class LoadingException extends RuntimeException
{

	private ILoader<?> loader;

	public LoadingException(ILoader<?> loader)
	{
		this.loader = loader;
	}

	@Override
	public String getMessage()
	{
		return String.format("Loader: %s", loader.toString());
	}

}
