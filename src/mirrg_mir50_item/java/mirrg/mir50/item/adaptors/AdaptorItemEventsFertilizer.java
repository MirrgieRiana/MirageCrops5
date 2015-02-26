package mirrg.mir50.item.adaptors;

import mirrg.mir50.item.AdaptorItemEventsOverriding;
import mirrg.mir50.item.ItemMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AdaptorItemEventsFertilizer extends AdaptorItemEventsOverriding
{

	public AdaptorItemEventsFertilizer(ItemMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (!player.canPlayerEdit(x, y, z, side, itemStack)) return false;

		if (ItemDye.applyBonemeal(itemStack, world, x, y, z, player)) {
			if (!world.isRemote) {
				world.playAuxSFX(2005, x, y, z, 0);
			}
			return true;
		} else {
			return false;
		}
	}

}
