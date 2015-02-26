package mirrg_miragecrops5.fairytype;

import java.util.function.Consumer;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;

public class LoaderFairyType extends Loader<FairyType>
{

	public int index;
	public String typeName;
	public Consumer<FairyType> setter;

	public LoaderFairyType()
	{

	}

	public LoaderFairyType(int index, String typeName, Consumer<FairyType> setter)
	{
		this.index = index;
		this.typeName = typeName;
		this.setter = setter;
	}

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.Created) {
			FairyType fairyType = new FairyType(typeName);
			RegistryFairyType.registry.register(index, typeName, fairyType);
			if (setter != null) setter.accept(fairyType);
			set(fairyType);
		}
	}
}
