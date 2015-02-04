package mirrg.mir51.render.block.multiple.multi;

import mirrg.mir50.block.BlockMir50;
import mirrg.mir50.block.multi.ContainerMetaBlock;
import mirrg.mir50.block.multi.MetaBlock;
import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRenderingOverriding;
import mirrg.mir51.render.block.multiple.IBlockMultipleRendering;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockMultipleRenderingMulti extends AdaptorBlockMultipleRenderingOverriding
{

	protected ContainerMetaBlock containerMetaBlock;

	public AdaptorBlockMultipleRenderingMulti(BlockMir50 owner, ContainerMetaBlock containerMetaBlock)
	{
		super(owner);
		this.containerMetaBlock = containerMetaBlock;
	}

	///////////////////////////////////////////////////////////

	@Override
	@SideOnly(Side.CLIENT)
	public void setMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, boolean isInMultipleRendering)
	{
		MetaBlock metaBlock = containerMetaBlock.get(blockAccess, x, y, z);
		if (metaBlock == null) {
			super.setMultipleRendering(blockAccess, x, y, z, isInMultipleRendering);
			return;
		}
		metaBlock.virtualClass.cast(IBlockMultipleRendering.class).get().setMultipleRendering(blockAccess, x, y, z, isInMultipleRendering);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setMultipleRendering(int metadata, boolean isInMultipleRendering)
	{
		MetaBlock metaBlock = containerMetaBlock.get(metadata);
		if (metaBlock == null) {
			super.setMultipleRendering(metadata, isInMultipleRendering);
			return;
		}
		metaBlock.virtualClass.cast(IBlockMultipleRendering.class).get().setMultipleRendering(metadata, isInMultipleRendering);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setMultipleRenderPass(IBlockAccess blockAccess, int x, int y, int z, int pass)
	{
		MetaBlock metaBlock = containerMetaBlock.get(blockAccess, x, y, z);
		if (metaBlock == null) {
			super.setMultipleRenderPass(blockAccess, x, y, z, pass);
			return;
		}
		metaBlock.virtualClass.cast(IBlockMultipleRendering.class).get().setMultipleRenderPass(blockAccess, x, y, z, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setMultipleRenderPass(int metadata, int pass)
	{
		MetaBlock metaBlock = containerMetaBlock.get(metadata);
		if (metaBlock == null) {
			super.setMultipleRenderPass(metadata, pass);
			return;
		}
		metaBlock.virtualClass.cast(IBlockMultipleRendering.class).get().setMultipleRenderPass(metadata, pass);
	}

	///////////////////////////////////////////////////////////

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(IBlockAccess blockAccess, int x, int y, int z)
	{
		MetaBlock metaBlock = containerMetaBlock.get(blockAccess, x, y, z);
		if (metaBlock == null) return super.getMultipleRenderPasses(blockAccess, x, y, z);
		return metaBlock.virtualClass.cast(IBlockMultipleRendering.class).get().getMultipleRenderPasses(blockAccess, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(int metadata)
	{
		MetaBlock metaBlock = containerMetaBlock.get(metadata);
		if (metaBlock == null) return super.getMultipleRenderPasses(metadata);
		return metaBlock.virtualClass.cast(IBlockMultipleRendering.class).get().getMultipleRenderPasses(metadata);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getMultipleRenderIcon(IBlockAccess blockAccess, int x, int y, int z, int side, int pass)
	{
		MetaBlock metaBlock = containerMetaBlock.get(blockAccess, x, y, z);
		if (metaBlock == null) return super.getMultipleRenderIcon(blockAccess, x, y, z, side, pass);
		return metaBlock.virtualClass.cast(IBlockMultipleRendering.class).get().getMultipleRenderIcon(blockAccess, x, y, z, side, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getMultipleRenderIcon(int metadata, int side, int pass)
	{
		MetaBlock metaBlock = containerMetaBlock.get(metadata);
		if (metaBlock == null) return super.getMultipleRenderIcon(metadata, side, pass);
		return metaBlock.virtualClass.cast(IBlockMultipleRendering.class).get().getMultipleRenderIcon(metadata, side, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderColor(IBlockAccess blockAccess, int x, int y, int z, int pass)
	{
		MetaBlock metaBlock = containerMetaBlock.get(blockAccess, x, y, z);
		if (metaBlock == null) return super.getMultipleRenderColor(blockAccess, x, y, z, pass);
		return metaBlock.virtualClass.cast(IBlockMultipleRendering.class).get().getMultipleRenderColor(blockAccess, x, y, z, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderColor(int metadata, int pass)
	{
		MetaBlock metaBlock = containerMetaBlock.get(metadata);
		if (metaBlock == null) return super.getMultipleRenderColor(metadata, pass);
		return metaBlock.virtualClass.cast(IBlockMultipleRendering.class).get().getMultipleRenderColor(metadata, pass);
	}

}
