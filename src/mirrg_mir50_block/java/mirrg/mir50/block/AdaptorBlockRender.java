package mirrg.mir50.block;

import mirrg.p.adaptor.Adaptor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockRender extends Adaptor<BlockMir50> implements IAdaptorBlockRender
{

	public AdaptorBlockRender(BlockMir50 owner)
	{
		super(owner);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return 0;
	}

}
