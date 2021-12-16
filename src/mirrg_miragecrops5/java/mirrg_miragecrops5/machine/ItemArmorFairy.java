package mirrg_miragecrops5.machine;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArmorFairy extends ItemArmor
{

	public ItemArmorFairy(ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_)
	{
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return "miragecrops5:textures/models/armor/fairy_layer_" + (slot == 2 ? 2 : 1) + ".png";
	}

	@Override
	public int getColor(ItemStack p_82814_1_)
	{
		return 0xffffff;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return false;
	}

}
