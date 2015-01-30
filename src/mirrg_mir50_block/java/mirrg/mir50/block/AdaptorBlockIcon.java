package mirrg.mir50.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockIcon extends AdaptorBlock
{

	public AdaptorBlockIcon(BlockMir50 blockMir50)
	{
		super(blockMir50);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		//return getMeta(blockAccess, x, y, z).getIcon(blockAccess, x, y, z, side);
		return getIcon(side, blockAccess.getBlockMetadata(x, y, z));
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return blockMir50.getField_blockIcon();
		//return getMeta(metaId).getIcon(side, meta);
	}

	@SideOnly(Side.CLIENT)
	public IIcon func_149735_b(int side, int p_149735_2_)
	{
		return getIcon(side, p_149735_2_);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockMir50.setField_blockIcon(iconRegister.registerIcon(getTextureName()));
	}

	/*
	for (META meta : multi) {
		if (meta != null) {
			meta.registerBlockIcons(iconRegister);
		}
	}
	*/

	@SideOnly(Side.CLIENT)
	protected String getTextureName()
	{
		return blockMir50.getField_textureName() == null
			? "MISSING_ICON_BLOCK_" + Block.getIdFromBlock(blockMir50) + "_" + blockMir50.getField_unlocalizedName()
			: blockMir50.getField_textureName();
	}

	public Block setBlockTextureName(String textureName)
	{
		blockMir50.setField_textureName(textureName);
		return blockMir50;
	}

	/*
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 0;
	}

	public boolean canRenderInPass(int pass)
	{
		return pass == getRenderBlockPass();
	}
	*/

}
