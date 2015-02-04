package mirrg.mir51.render.block.multiple;

import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class AdaptorBlockMultipleRenderingOverriding extends AdaptorBlockMultipleRendering
{

	protected final IBlockMultipleRendering _super_IBlockMultipleRendering;

	public AdaptorBlockMultipleRenderingOverriding(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner);
		this._super_IBlockMultipleRendering = superObject.getVirtualClass().getCurrentImplementation(IBlockMultipleRendering.class);
	}

	@Override
	public void setMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, boolean isInMultipleRendering)
	{
		this._super_IBlockMultipleRendering.setMultipleRendering(blockAccess, x, y, z, isInMultipleRendering);
	}

	@Override
	public void setMultipleRendering(int metadata, boolean isInMultipleRendering)
	{
		this._super_IBlockMultipleRendering.setMultipleRendering(metadata, isInMultipleRendering);
	}

	@Override
	public void setMultipleRenderPass(IBlockAccess blockAccess, int x, int y, int z, int pass)
	{
		this._super_IBlockMultipleRendering.setMultipleRenderPass(blockAccess, x, y, z, pass);
	}

	@Override
	public void setMultipleRenderPass(int metadata, int pass)
	{
		this._super_IBlockMultipleRendering.setMultipleRenderPass(metadata, pass);
	}

	@Override
	public int getMultipleRenderPasses(IBlockAccess blockAccess, int x, int y, int z)
	{
		return this._super_IBlockMultipleRendering.getMultipleRenderPasses(blockAccess, x, y, z);
	}

	@Override
	public int getMultipleRenderPasses(int metadata)
	{
		return this._super_IBlockMultipleRendering.getMultipleRenderPasses(metadata);
	}

	@Override
	public IIcon getMultipleRenderIcon(IBlockAccess blockAccess, int x, int y, int z, int side, int pass)
	{
		return this._super_IBlockMultipleRendering.getMultipleRenderIcon(blockAccess, x, y, z, side, pass);
	}

	@Override
	public IIcon getMultipleRenderIcon(int metadata, int side, int pass)
	{
		return this._super_IBlockMultipleRendering.getMultipleRenderIcon(metadata, side, pass);
	}

	@Override
	public int getMultipleRenderColor(IBlockAccess blockAccess, int x, int y, int z, int pass)
	{
		return this._super_IBlockMultipleRendering.getMultipleRenderColor(blockAccess, x, y, z, pass);
	}

	@Override
	public int getMultipleRenderColor(int metadata, int pass)
	{
		return this._super_IBlockMultipleRendering.getMultipleRenderColor(metadata, pass);
	}

}
