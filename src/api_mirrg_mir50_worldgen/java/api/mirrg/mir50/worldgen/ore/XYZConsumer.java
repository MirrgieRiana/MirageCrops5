package api.mirrg.mir50.worldgen.ore;

import java.util.Objects;

@FunctionalInterface
public interface XYZConsumer
{

	public void accept(int x, int y, int z);

	public default XYZConsumer andThen(XYZConsumer after)
	{
		Objects.requireNonNull(after);
		return (x, y, z) -> {
			accept(x, y, z);
			after.accept(x, y, z);
		};
	}

}
