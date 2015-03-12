package mirrg.mir51.render.block.multiple;

import java.util.function.Consumer;

import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.IAdaptorBlockIcon;
import mirrg.p.adaptor.Adaptor;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockMultipleRendering extends Adaptor<BlockMir50> implements IBlockMultipleRendering
{

	protected final IAdaptorBlockIcon _super_IAdaptorBlockIcon;

	public AdaptorBlockMultipleRendering(BlockMir50 owner)
	{
		super(owner);
		this._super_IAdaptorBlockIcon = owner.virtualClass.getCurrentImplementation(IAdaptorBlockIcon.class);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Consumer<IConsumerMultipleRendering> getMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		return consumer -> consumer.accept(_super_IAdaptorBlockIcon.getIcon(blockAccess, x, y, z, side), 0xffffff);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Consumer<IConsumerMultipleRendering> getMultipleRendering(int metadata, int side)
	{
		return consumer -> consumer.accept(_super_IAdaptorBlockIcon.getIcon(side, metadata), 0xffffff);
	}

}
