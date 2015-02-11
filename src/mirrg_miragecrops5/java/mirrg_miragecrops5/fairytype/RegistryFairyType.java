package mirrg_miragecrops5.fairytype;

import java.util.Collection;
import java.util.Hashtable;

import net.minecraft.item.ItemStack;

public class RegistryFairyType
{

	protected static Hashtable<String, FairyType> hash = new Hashtable<>();

	public static FairyType register(String typeName)
	{
		FairyType value = new FairyType(typeName);
		hash.put(typeName, value);
		return value;
	}

	public static FairyType get(String typeName)
	{
		return hash.get(typeName);
	}

	public static FairyType get(ItemStack itemStack)
	{
		for (FairyType fairyType : hash.values()) {
			if (fairyType.matches(itemStack)) return fairyType;
		}

		return null;
	}

	public static boolean matches(String typeName, ItemStack itemStack)
	{
		return get(typeName).matches(itemStack);
	}

	public static Collection<FairyType> getFairyTypes()
	{
		return hash.values();
	}

}
