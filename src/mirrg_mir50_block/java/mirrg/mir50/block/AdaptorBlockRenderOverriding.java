package mirrg.mir50.block;

import mirrg.p.virtualclass.IVirtualClass;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockRenderOverriding extends AdaptorBlockRender
{

	protected final IAdaptorBlockRender _super_IAdaptorBlockRender;

	public AdaptorBlockRenderOverriding(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner);
		this._super_IAdaptorBlockRender = superObject.getVirtualClass().getCurrentImplementation(IAdaptorBlockRender.class);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return this._super_IAdaptorBlockRender.getRenderType();
	}

}
