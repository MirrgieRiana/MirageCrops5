package mirrg_miragecrops5.machine;

import mirrg.mir50.block.AdaptorBlockEventsOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import mirrg_miragecrops5.machine.tile.TileEntityMMF;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AdaptorBlockEventsTileEntityMMF extends AdaptorBlockEventsOverriding
{

	public AdaptorBlockEventsTileEntityMMF(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float x2, float y2, float z2)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null) return false;
		if (tileEntity instanceof TileEntityMMF) {
			return ((TileEntityMMF) tileEntity).onActivated(world, x, y, z, player, side, x2, y2, z2);
		}
		return false;
	}

}
