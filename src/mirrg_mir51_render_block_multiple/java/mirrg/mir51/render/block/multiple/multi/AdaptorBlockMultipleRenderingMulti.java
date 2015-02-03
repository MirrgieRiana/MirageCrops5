package mirrg.mir51.render.block.multiple.multi;

import mirrg.mir51.render.block.multiple.AdaptorBlockMultipleRendering;
import mirrg.mir51.render.block.multiple.BlockMultipleRendering;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockMultipleRenderingMulti extends AdaptorBlockMultipleRendering
{

	protected BlockMultipleRendering blockMultipleRendering;

	protected ContainerMetaBlockMultipleRendering containerMetaBlockMultipleRendering;

	public AdaptorBlockMultipleRenderingMulti(BlockMultipleRendering blockMultipleRendering, ContainerMetaBlockMultipleRendering containerMetaBlockMultipleRendering)
	{
		super(blockMultipleRendering);
		this.blockMultipleRendering = blockMultipleRendering;
		this.containerMetaBlockMultipleRendering = containerMetaBlockMultipleRendering;
	}

	///////////////////////////////////////////////////////////

	@SideOnly(Side.CLIENT)
	public void setMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, boolean isInMultipleRendering)
	{
		MetaBlockMultipleRendering metaBlockMultipleRendering = containerMetaBlockMultipleRendering.get(blockAccess, x, y, z);
		if (metaBlockMultipleRendering == null || metaBlockMultipleRendering.adaptorBlockIcon == null) {
			super.setMultipleRendering(blockAccess, x, y, z, isInMultipleRendering);
			return;
		}
		metaBlockMultipleRendering.adaptorBlockMultipleRendering.setMultipleRendering(blockAccess, x, y, z, isInMultipleRendering);
	}

	@SideOnly(Side.CLIENT)
	public void setMultipleRendering(int metadata, boolean isInMultipleRendering)
	{
		MetaBlockMultipleRendering metaBlockMultipleRendering = containerMetaBlockMultipleRendering.get(metadata);
		if (metaBlockMultipleRendering == null || metaBlockMultipleRendering.adaptorBlockIcon == null) {
			super.setMultipleRendering(metadata, isInMultipleRendering);
			return;
		}
		metaBlockMultipleRendering.adaptorBlockMultipleRendering.setMultipleRendering(metadata, isInMultipleRendering);
	}

	@SideOnly(Side.CLIENT)
	public void setMultipleRenderPass(IBlockAccess blockAccess, int x, int y, int z, int pass)
	{
		MetaBlockMultipleRendering metaBlockMultipleRendering = containerMetaBlockMultipleRendering.get(blockAccess, x, y, z);
		if (metaBlockMultipleRendering == null || metaBlockMultipleRendering.adaptorBlockIcon == null) {
			super.setMultipleRenderPass(blockAccess, x, y, z, pass);
			;
			return;
		}
		metaBlockMultipleRendering.adaptorBlockMultipleRendering.setMultipleRenderPass(blockAccess, x, y, z, pass);
	}

	@SideOnly(Side.CLIENT)
	public void setMultipleRenderPass(int metadata, int pass)
	{
		MetaBlockMultipleRendering metaBlockMultipleRendering = containerMetaBlockMultipleRendering.get(metadata);
		if (metaBlockMultipleRendering == null || metaBlockMultipleRendering.adaptorBlockIcon == null) {
			super.setMultipleRenderPass(metadata, pass);
			return;
		}
		metaBlockMultipleRendering.adaptorBlockMultipleRendering.setMultipleRenderPass(metadata, pass);
	}

	///////////////////////////////////////////////////////////

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(IBlockAccess blockAccess, int x, int y, int z)
	{
		MetaBlockMultipleRendering metaBlockMultipleRendering = containerMetaBlockMultipleRendering.get(blockAccess, x, y, z);
		if (metaBlockMultipleRendering == null || metaBlockMultipleRendering.adaptorBlockIcon == null) return super.getMultipleRenderPasses(blockAccess, x, y, z);
		return metaBlockMultipleRendering.adaptorBlockMultipleRendering.getMultipleRenderPasses(blockAccess, x, y, z);
	}

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(int metadata)
	{
		MetaBlockMultipleRendering metaBlockMultipleRendering = containerMetaBlockMultipleRendering.get(metadata);
		if (metaBlockMultipleRendering == null || metaBlockMultipleRendering.adaptorBlockIcon == null) return super.getMultipleRenderPasses(metadata);
		return metaBlockMultipleRendering.adaptorBlockMultipleRendering.getMultipleRenderPasses(metadata);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getMultipleRenderIcon(IBlockAccess blockAccess, int x, int y, int z, int side, int pass)
	{
		MetaBlockMultipleRendering metaBlockMultipleRendering = containerMetaBlockMultipleRendering.get(blockAccess, x, y, z);
		if (metaBlockMultipleRendering == null || metaBlockMultipleRendering.adaptorBlockIcon == null) return super.getMultipleRenderIcon(blockAccess, x, y, z, side, pass);
		return metaBlockMultipleRendering.adaptorBlockMultipleRendering.getMultipleRenderIcon(blockAccess, x, y, z, side, pass);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getMultipleRenderIcon(int metadata, int side, int pass)
	{
		MetaBlockMultipleRendering metaBlockMultipleRendering = containerMetaBlockMultipleRendering.get(metadata);
		if (metaBlockMultipleRendering == null || metaBlockMultipleRendering.adaptorBlockIcon == null) return super.getMultipleRenderIcon(metadata, side, pass);
		return metaBlockMultipleRendering.adaptorBlockMultipleRendering.getMultipleRenderIcon(metadata, side, pass);
	}

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderColor(IBlockAccess blockAccess, int x, int y, int z, int pass)
	{
		MetaBlockMultipleRendering metaBlockMultipleRendering = containerMetaBlockMultipleRendering.get(blockAccess, x, y, z);
		if (metaBlockMultipleRendering == null || metaBlockMultipleRendering.adaptorBlockIcon == null) return super.getMultipleRenderColor(blockAccess, x, y, z, pass);
		return metaBlockMultipleRendering.adaptorBlockMultipleRendering.getMultipleRenderColor(blockAccess, x, y, z, pass);
	}

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderColor(int metadata, int pass)
	{
		MetaBlockMultipleRendering metaBlockMultipleRendering = containerMetaBlockMultipleRendering.get(metadata);
		if (metaBlockMultipleRendering == null || metaBlockMultipleRendering.adaptorBlockIcon == null) return super.getMultipleRenderColor(metadata, pass);
		return metaBlockMultipleRendering.adaptorBlockMultipleRendering.getMultipleRenderColor(metadata, pass);
	}

	///////////////////////////////////////////////////////////

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		MetaBlockMultipleRendering metaBlockMultipleRendering = containerMetaBlockMultipleRendering.get(blockAccess, x, y, z);
		if (metaBlockMultipleRendering == null || metaBlockMultipleRendering.adaptorBlockIcon == null) return super.getIcon(blockAccess, x, y, z, side);
		return metaBlockMultipleRendering.adaptorBlockIcon.getIcon(blockAccess, x, y, z, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		MetaBlockMultipleRendering metaBlockMultipleRendering = containerMetaBlockMultipleRendering.get(meta);
		if (metaBlockMultipleRendering == null || metaBlockMultipleRendering.adaptorBlockIcon == null) return super.getIcon(side, meta);
		return metaBlockMultipleRendering.adaptorBlockIcon.getIcon(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
	{
		MetaBlockMultipleRendering metaBlockMultipleRendering = containerMetaBlockMultipleRendering.get(blockAccess, x, y, z);
		if (metaBlockMultipleRendering == null || metaBlockMultipleRendering.adaptorBlockIcon == null) return super.colorMultiplier(blockAccess, x, y, z);
		return metaBlockMultipleRendering.adaptorBlockIcon.colorMultiplier(blockAccess, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta)
	{
		MetaBlockMultipleRendering metaBlockMultipleRendering = containerMetaBlockMultipleRendering.get(meta);
		if (metaBlockMultipleRendering == null || metaBlockMultipleRendering.adaptorBlockIcon == null) return super.getRenderColor(meta);
		return metaBlockMultipleRendering.adaptorBlockIcon.getRenderColor(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		super.registerBlockIcons(iconRegister);

		containerMetaBlockMultipleRendering.forEach(metaBlockMultipleRendering -> {
			if (metaBlockMultipleRendering != null && metaBlockMultipleRendering.adaptorBlockIcon != null) metaBlockMultipleRendering.adaptorBlockIcon.registerBlockIcons(iconRegister);
		});

	}

}
