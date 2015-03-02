package mirrg.mir51.block.multi;

import mirrg.mir50.block.AdaptorBlockIconOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockIconMulti extends AdaptorBlockIconOverriding
{

	protected ContainerMetaBlock containerMetaBlock;

	public AdaptorBlockIconMulti(BlockMir50 owner, IVirtualClass superObject, ContainerMetaBlock containerMetaBlock)
	{
		super(owner, superObject);
		this.containerMetaBlock = containerMetaBlock;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(blockAccess, x, y, z);
		if (metaBlock == null) return super.getIcon(blockAccess, x, y, z, side);
		return metaBlock.accessor_IAdaptorBlockIcon.get().getIcon(blockAccess, x, y, z, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(meta);
		if (metaBlock == null) return super.getIcon(side, meta);
		return metaBlock.accessor_IAdaptorBlockIcon.get().getIcon(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon func_149735_b(int side, int meta)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(meta);
		if (metaBlock == null) return super.func_149735_b(side, meta);
		return metaBlock.accessor_IAdaptorBlockIcon.get().func_149735_b(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(blockAccess, x, y, z);
		if (metaBlock == null) return super.colorMultiplier(blockAccess, x, y, z);
		return metaBlock.accessor_IAdaptorBlockIcon.get().colorMultiplier(blockAccess, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(meta);
		if (metaBlock == null) return super.getRenderColor(meta);
		return metaBlock.accessor_IAdaptorBlockIcon.get().getRenderColor(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		super.registerBlockIcons(iconRegister);

		this.containerMetaBlock.forEach(metaBlock -> {
			if (metaBlock != null) metaBlock.accessor_IAdaptorBlockIcon.get().registerBlockIcons(iconRegister);
		});
	}

	@Override
	@Deprecated
	@SideOnly(Side.CLIENT)
	public String getTextureName()
	{
		return super.getTextureName();
	}

	@Override
	@Deprecated
	public Block setBlockTextureName(String textureName)
	{
		return super.setBlockTextureName(textureName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		MetaBlock metaBlock = this.containerMetaBlock.get(blockAccess, x, y, z);
		if (metaBlock == null) return super.shouldSideBeRendered(blockAccess, x, y, z, side);
		return metaBlock.accessor_IAdaptorBlockIcon.get().shouldSideBeRendered(blockAccess, x, y, z, side);
	}

}
