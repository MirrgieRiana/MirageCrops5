package mirrg.mir50.modding;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

public class HelpersSide
{

	public final static HelpersSide REMOTE = new HelpersSide(true);
	public final static HelpersSide SIMULATING = new HelpersSide(false);

	public static HelpersSide helper(Entity entity)
	{
		return helper(entity.worldObj);
	}

	public static HelpersSide helper(TileEntity tileEntity)
	{
		return helper(tileEntity.getWorldObj());
	}

	public static HelpersSide helper(World world)
	{
		return helper(world.isRemote);
	}

	public static HelpersSide helper()
	{
		return helper(FMLCommonHandler.instance().getEffectiveSide().isClient());
	}

	public static HelpersSide helper(boolean isRemote)
	{
		return isRemote ? REMOTE : SIMULATING;
	}

	public final boolean isRemote;

	public HelpersSide(boolean isRemote)
	{
		this.isRemote = isRemote;
	}

	public boolean isRemote()
	{
		return isRemote;
	}

	public boolean isSimulating()
	{
		return !isRemote;
	}

	public boolean isRendering()
	{
		return isRemote;
	}

}
