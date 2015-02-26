package mirrg_miragecrops5.material;

import mirrg.mir50.item.AdaptorItemNameOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class AdaptorItemNameMaterial extends AdaptorItemNameOverriding
{

	private String nameShape;
	private String nameMaterial;

	public AdaptorItemNameMaterial(ItemMir50 owner, IVirtualClass superObject, String nameShape, String nameMaterial)
	{
		super(owner, superObject);
		this.nameShape = nameShape;
		this.nameMaterial = nameMaterial;
	}

	@Override
	public String getItemStackDisplayName(ItemStack itemStack)
	{
		String s = owner.getUnlocalizedNameInefficiently(itemStack) + ".name";
		if (s != null) {
			if (!s.equals("")) {
				if (StatCollector.canTranslate(s)) {
					return StatCollector.translateToLocal(s).trim();
				}
			}
		}

		String format = StatCollector.translateToLocal("item." + nameShape + ".format").trim();
		String materialLocalizedName = StatCollector.translateToLocal("material." + nameMaterial + ".name").trim();
		return String.format(format, materialLocalizedName);
	}

}
