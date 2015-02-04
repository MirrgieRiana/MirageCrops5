package mirrg.mir50.block;

import mirrg.p.adaptor.Adaptor;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockIcon extends Adaptor<BlockMir50> implements IAdaptorBlockIcon
{

	public AdaptorBlockIcon(BlockMir50 owner)
	{
		super(owner);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		return owner.accessor_IAdaptorBlockIcon.get().getIcon(side, blockAccess.getBlockMetadata(x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.owner.getField_blockIcon();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon func_149735_b(int side, int meta)
	{
		return owner.accessor_IAdaptorBlockIcon.get().getIcon(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
	{
		return 0xffffff;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta)
	{
		return 0xffffff;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.owner.setField_blockIcon(iconRegister.registerIcon(owner.accessor_IAdaptorBlockIcon.get().getTextureName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTextureName()
	{
		return this.owner.getField_textureName() == null
			? "MISSING_ICON_BLOCK_" + Block.getIdFromBlock(this.owner) + "_" + this.owner.getField_unlocalizedName()
			: this.owner.getField_textureName();
	}

	@Override
	public Block setBlockTextureName(String textureName)
	{
		this.owner.setField_textureName(textureName);
		return this.owner;
	}

}
