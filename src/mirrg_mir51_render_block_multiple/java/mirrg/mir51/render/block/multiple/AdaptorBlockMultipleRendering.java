package mirrg.mir51.render.block.multiple;

import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.IAdaptorBlockIcon;
import mirrg.p.adaptor.Adaptor;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class AdaptorBlockMultipleRendering extends Adaptor<BlockMir50> implements IBlockMultipleRendering
{

	protected final IAdaptorBlockIcon _super_IAdaptorBlockIcon;

	public AdaptorBlockMultipleRendering(BlockMir50 owner)
	{
		super(owner);
		this._super_IAdaptorBlockIcon = owner.virtualClass.getCurrentImplementation(IAdaptorBlockIcon.class);
	}

	@Override
	public void setMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, boolean isInMultipleRendering)
	{

	}

	@Override
	public void setMultipleRendering(int metadata, boolean isInMultipleRendering)
	{

	}

	@Override
	public void setMultipleRenderPass(IBlockAccess blockAccess, int x, int y, int z, int pass)
	{

	}

	@Override
	public void setMultipleRenderPass(int metadata, int pass)
	{

	}

	@Override
	public int getMultipleRenderPasses(IBlockAccess blockAccess, int x, int y, int z)
	{
		return 1;
	}

	@Override
	public int getMultipleRenderPasses(int metadata)
	{
		return 1;
	}

	@Override
	public IIcon getMultipleRenderIcon(IBlockAccess blockAccess, int x, int y, int z, int side, int pass)
	{
		return this._super_IAdaptorBlockIcon.getIcon(blockAccess, x, y, z, side);
	}

	@Override
	public IIcon getMultipleRenderIcon(int metadata, int side, int pass)
	{
		return this._super_IAdaptorBlockIcon.getIcon(side, metadata);
	}

	@Override
	public int getMultipleRenderColor(IBlockAccess blockAccess, int x, int y, int z, int pass)
	{
		return 0xffffff;
	}

	@Override
	public int getMultipleRenderColor(int metadata, int pass)
	{
		return 0xffffff;
	}

}
