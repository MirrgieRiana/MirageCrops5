package mirrg.mir50.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockRenderOverriding extends AdaptorBlockRender
{

	protected final IAdaptorBlockRender _super_IAdaptorBlockRender;

	public AdaptorBlockRenderOverriding(BlockMir50 owner)
	{
		super(owner);
		_super_IAdaptorBlockRender = owner.getVirtualClass().getCurrentImplementation(IAdaptorBlockRender.class);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return _super_IAdaptorBlockRender.getRenderType();
	}

}
