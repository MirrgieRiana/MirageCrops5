package mirrg_miragecrops5.fairytype;

import java.util.function.Consumer;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;

public class LoaderFairyType extends Loader<FairyType>
{

	public String typeName;
	public Consumer<FairyType> setter;

	public LoaderFairyType()
	{

	}

	public LoaderFairyType(String typeName, Consumer<FairyType> setter)
	{
		this.typeName = typeName;
		this.setter = setter;
	}

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.Created) {
			FairyType fairyType = RegistryFairyType.register(typeName);
			if (setter != null) setter.accept(fairyType);
			set(fairyType);
		}
	}
}
