package api.mirrg.mir50.worldgen.ore;

import java.util.Objects;

@FunctionalInterface
public interface IConsumerXYZ
{

	public void accept(int x, int y, int z);

	public default IConsumerXYZ andThen(IConsumerXYZ after)
	{
		Objects.requireNonNull(after);
		return (x, y, z) -> {
			accept(x, y, z);
			after.accept(x, y, z);
		};
	}

}
