package mirrg.mir50.block.multi;

import mirrg.mir50.block.AdaptorBlockIcon;
import mirrg.mir50.block.BlockMir50;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockIconMulti extends AdaptorBlockIcon
{

	protected ContainerMetaBlock containerMetaBlock;

	public AdaptorBlockIconMulti(BlockMir50 blockMir50, ContainerMetaBlock containerMetaBlock)
	{
		super(blockMir50);
		this.containerMetaBlock = containerMetaBlock;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		MetaBlock metaBlock = containerMetaBlock.get(blockAccess, x, y, z);
		if (metaBlock == null || metaBlock.adaptorBlockIcon == null) return super.getIcon(blockAccess, x, y, z, side);
		return metaBlock.adaptorBlockIcon.getIcon(blockAccess, x, y, z, side);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		MetaBlock metaBlock = containerMetaBlock.get(meta);
		if (metaBlock == null || metaBlock.adaptorBlockIcon == null) return super.getIcon(side, meta);
		return metaBlock.adaptorBlockIcon.getIcon(side, meta);
	}

	@SideOnly(Side.CLIENT)
	public IIcon func_149735_b(int side, int meta)
	{
		MetaBlock metaBlock = containerMetaBlock.get(meta);
		if (metaBlock == null || metaBlock.adaptorBlockIcon == null) return super.func_149735_b(side, meta);
		return metaBlock.adaptorBlockIcon.func_149735_b(side, meta);
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
	{
		MetaBlock metaBlock = containerMetaBlock.get(blockAccess, x, y, z);
		if (metaBlock == null || metaBlock.adaptorBlockIcon == null) return super.colorMultiplier(blockAccess, x, y, z);
		return metaBlock.adaptorBlockIcon.colorMultiplier(blockAccess, x, y, z);
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta)
	{
		MetaBlock metaBlock = containerMetaBlock.get(meta);
		if (metaBlock == null || metaBlock.adaptorBlockIcon == null) return super.getRenderColor(meta);
		return metaBlock.adaptorBlockIcon.getRenderColor(meta);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		super.registerBlockIcons(iconRegister);

		containerMetaBlock.forEach(metaBlock -> {
			if (metaBlock != null && metaBlock.adaptorBlockIcon != null) metaBlock.adaptorBlockIcon.registerBlockIcons(iconRegister);
		});
	}

	@Deprecated
	@SideOnly(Side.CLIENT)
	public String getTextureName()
	{
		return super.getTextureName();
	}

	@Deprecated
	public Block setBlockTextureName(String textureName)
	{
		return super.setBlockTextureName(textureName);
	}

}
