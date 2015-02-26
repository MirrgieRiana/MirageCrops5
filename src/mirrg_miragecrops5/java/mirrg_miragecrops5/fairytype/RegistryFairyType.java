package mirrg_miragecrops5.fairytype;

import java.util.ArrayList;

import mirrg.mir50.instanceregistry.IInstanceRegistry;
import mirrg.mir50.instanceregistry.InstanceRegistryArray;
import net.minecraft.item.ItemStack;

public class RegistryFairyType
{

	/**
	 * 有効添え字：　0～3199<br>
	 * 予約済み：　0～1499
	 */
	public static IInstanceRegistry<FairyType> registry = new InstanceRegistryArray<>(new FairyType[3200]);

	public static ArrayList<FairyType> getFromItemStack(ItemStack itemStack)
	{
		ArrayList<FairyType> fairyTypes = new ArrayList<>();

		registry.forEach((index, name, fairyType) -> {
			if (fairyType.matches(itemStack)) fairyTypes.add(fairyType);
		});

		return fairyTypes;
	}

	/*
		public static FairyType register(String typeName)
		{
			FairyType value = new FairyType(typeName);
			registry.put(typeName, value);
			return value;
		}

		public static FairyType get(String typeName)
		{
			return registry.get(typeName);
		}

		public static FairyType get(ItemStack itemStack)
		{
		}

		public static boolean matches(String typeName, ItemStack itemStack)
		{
			return get(typeName).matches(itemStack);
		}

		public static Collection<FairyType> getFairyTypes()
		{
			return registry.values();
		}
	*/

}
