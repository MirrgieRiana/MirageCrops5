package mirrg.mir50.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockIconOverriding extends AdaptorBlockIcon
{

	protected final IAdaptorBlockIcon _super_IAdaptorBlockIcon;

	public AdaptorBlockIconOverriding(BlockMir50 owner)
	{
		super(owner);
		_super_IAdaptorBlockIcon = owner.getVirtualClass().getCurrentImplementation(IAdaptorBlockIcon.class);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		return _super_IAdaptorBlockIcon.getIcon(blockAccess, x, y, z, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return _super_IAdaptorBlockIcon.getIcon(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon func_149735_b(int side, int meta)
	{
		return _super_IAdaptorBlockIcon.func_149735_b(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
	{
		return _super_IAdaptorBlockIcon.colorMultiplier(blockAccess, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta)
	{
		return _super_IAdaptorBlockIcon.getRenderColor(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		_super_IAdaptorBlockIcon.registerBlockIcons(iconRegister);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTextureName()
	{
		return _super_IAdaptorBlockIcon.getTextureName();
	}

	@Override
	public Block setBlockTextureName(String textureName)
	{
		return _super_IAdaptorBlockIcon.setBlockTextureName(textureName);
	}

}
