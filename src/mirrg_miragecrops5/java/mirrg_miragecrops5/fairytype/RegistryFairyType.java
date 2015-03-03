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

	public static ArrayList<FairyType> getFromMaterial(ItemStack itemStack)
	{
		ArrayList<FairyType> fairyTypes = new ArrayList<>();

		registry.forEach((index, name, fairyType) -> {
			if (fairyType.matches(itemStack)) fairyTypes.add(fairyType);
		});

		return fairyTypes;
	}

}
