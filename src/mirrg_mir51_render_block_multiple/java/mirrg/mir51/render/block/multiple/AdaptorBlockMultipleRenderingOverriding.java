package mirrg.mir51.render.block.multiple;

import java.util.function.Consumer;

import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockMultipleRenderingOverriding extends AdaptorBlockMultipleRendering
{

	protected final IBlockMultipleRendering _super_IBlockMultipleRendering;

	public AdaptorBlockMultipleRenderingOverriding(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner);
		this._super_IBlockMultipleRendering = superObject.getVirtualClass().getCurrentImplementation(IBlockMultipleRendering.class);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Consumer<IConsumerMultipleRendering> getMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		return this._super_IBlockMultipleRendering.getMultipleRendering(blockAccess, x, y, z, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Consumer<IConsumerMultipleRendering> getMultipleRendering(int metadata, int side)
	{
		return this._super_IBlockMultipleRendering.getMultipleRendering(metadata, side);
	}

}
