package mirrg_miragecrops5;

import java.util.Hashtable;

public class RegistryFairyType
{

	protected static Hashtable<String, FairyType> hash = new Hashtable<>();

	public static FairyType register(String typeName)
	{
		return hash.put(typeName, new FairyType(typeName));
	}

}
