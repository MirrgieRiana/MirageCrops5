package mirrg.mir50.render.block.switcher;

import mirrg.mir50.block.AdaptorBlockRenderOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockRenderRenderingSwitcher extends AdaptorBlockRenderOverriding
{

	public boolean shouldRender3DInInventory = true;
	public boolean isOpaqueCube = true;
	public boolean renderAsNormalBlock = true;

	public AdaptorBlockRenderRenderingSwitcher(
		BlockMir50 owner,
		IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	public AdaptorBlockRenderRenderingSwitcher(
		BlockMir50 owner,
		IVirtualClass superObject,
		boolean shouldRender3DInInventory)
	{
		super(owner, superObject);
		this.shouldRender3DInInventory = shouldRender3DInInventory;
	}

	public AdaptorBlockRenderRenderingSwitcher(
		BlockMir50 owner,
		IVirtualClass superObject,
		boolean shouldRender3DInInventory,
		boolean isOpaqueCube,
		boolean renderAsNormalBlock)
	{
		super(owner, superObject);
		this.shouldRender3DInInventory = shouldRender3DInInventory;
		this.isOpaqueCube = isOpaqueCube;
		this.renderAsNormalBlock = renderAsNormalBlock;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return HelpersRenderBlockRenderingSwitcher.getHandler(shouldRender3DInInventory).getRenderId();
	}

	@Override
	public boolean isOpaqueCube()
	{
		return isOpaqueCube;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return renderAsNormalBlock;
	}

}
