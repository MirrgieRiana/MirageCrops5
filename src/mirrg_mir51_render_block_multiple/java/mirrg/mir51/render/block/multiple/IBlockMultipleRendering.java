package mirrg.mir51.render.block.multiple;

import java.util.function.Consumer;

import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IBlockMultipleRendering
{

	@SideOnly(Side.CLIENT)
	public Consumer<IConsumerMultipleRendering> getMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, int side);

	@SideOnly(Side.CLIENT)
	public Consumer<IConsumerMultipleRendering> getMultipleRendering(int metadata, int side);

}
