package api.mirrg.mir50.loader;

import java.util.function.Supplier;

@FunctionalInterface
public interface ILoader<T> extends Supplier<T>
{

}
