package mirrg.mir51.render.block.multiple;

import mirrg.mir50.block.BlockMir50;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMultipleRendering extends BlockMir50 implements IBlockMultipleRendering
{

	public BlockMultipleRendering(Material material)
	{
		super(material);
		adaptorBlockIcon = adaptorBlockMultipleRendering;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return RenderBlockMultipleRendering.loader.get().getRenderId();
	}

	////////////////////////////////////////

	public AdaptorBlockMultipleRendering adaptorBlockMultipleRendering = new AdaptorBlockMultipleRendering(this);

	public void setAdaptorBlockMultipleRendering(AdaptorBlockMultipleRendering adaptorBlockMultipleRendering)
	{
		this.adaptorBlockMultipleRendering = adaptorBlockMultipleRendering;
		adaptorBlockIcon = adaptorBlockMultipleRendering;
	}

	public void setMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, boolean isInMultipleRendering)
	{
		adaptorBlockMultipleRendering.setMultipleRendering(blockAccess, x, y, z, isInMultipleRendering);
	}

	public void setMultipleRendering(int metadata, boolean isInMultipleRendering)
	{
		adaptorBlockMultipleRendering.setMultipleRendering(metadata, isInMultipleRendering);
	}

	public void setMultipleRenderPass(IBlockAccess blockAccess, int x, int y, int z, int pass)
	{
		adaptorBlockMultipleRendering.setMultipleRenderPass(blockAccess, x, y, z, pass);
	}

	public void setMultipleRenderPass(int metadata, int pass)
	{
		adaptorBlockMultipleRendering.setMultipleRenderPass(metadata, pass);
	}

	public int getMultipleRenderPasses(IBlockAccess blockAccess, int x, int y, int z)
	{
		return adaptorBlockMultipleRendering.getMultipleRenderPasses(blockAccess, x, y, z);
	}

	public int getMultipleRenderPasses(int metadata)
	{
		return adaptorBlockMultipleRendering.getMultipleRenderPasses(metadata);
	}

	public IIcon getMultipleRenderIcon(IBlockAccess blockAccess, int x, int y, int z, int side, int pass)
	{
		return adaptorBlockMultipleRendering.getMultipleRenderIcon(blockAccess, x, y, z, side, pass);
	}

	public IIcon getMultipleRenderIcon(int side, int metadata, int pass)
	{
		return adaptorBlockMultipleRendering.getMultipleRenderIcon(side, metadata, pass);
	}

	public int getMultipleRenderColor(IBlockAccess blockAccess, int x, int y, int z, int pass)
	{
		return adaptorBlockMultipleRendering.getMultipleRenderColor(blockAccess, x, y, z, pass);
	}

	public int getMultipleRenderColor(int metadata, int pass)
	{
		return adaptorBlockMultipleRendering.getMultipleRenderColor(metadata, pass);
	}

}
