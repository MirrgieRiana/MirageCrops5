package mirrg.mir51.render.block.multiple;

import java.util.ArrayList;

import mirrg.mir50.block.AdaptorBlockIconOverriding;
import mirrg.mir50.block.BlockMir50;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockMultipleRenderingAutonomy extends AdaptorBlockIconOverriding implements IBlockMultipleRendering
{

	@SideOnly(Side.CLIENT)
	public ArrayList<IIcon> blockIcons = new ArrayList<>();

	public ArrayList<String> iconStrings = new ArrayList<>();

	@SideOnly(Side.CLIENT)
	public ArrayList<Integer> iconColors = new ArrayList<>();

	public AdaptorBlockMultipleRenderingAutonomy(BlockMir50 owner)
	{
		super(owner);
	}

	public void appendIcon(String iconString)
	{
		appendIcon(iconString, 0xffffff);
	}

	public void appendIcon(String iconString, int iconColor)
	{
		iconStrings.add(iconString);
		iconColors.add(iconColor);
	}

	///////////////////////////////////////////////////////////

	@SideOnly(Side.CLIENT)
	public int pass;

	@SideOnly(Side.CLIENT)
	public boolean isInMultipleRendering;

	@Override
	@SideOnly(Side.CLIENT)
	public void setMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, boolean isInMultipleRendering)
	{
		this.isInMultipleRendering = isInMultipleRendering;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setMultipleRendering(int metadata, boolean isInMultipleRendering)
	{
		this.isInMultipleRendering = isInMultipleRendering;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setMultipleRenderPass(IBlockAccess blockAccess, int x, int y, int z, int pass)
	{
		this.pass = pass;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setMultipleRenderPass(int metadata, int pass)
	{
		this.pass = pass;
	}

	//

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(IBlockAccess blockAccess, int x, int y, int z)
	{
		return blockIcons.size();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(int metadata)
	{
		return blockIcons.size();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getMultipleRenderIcon(IBlockAccess blockAccess, int x, int y, int z, int side, int pass)
	{
		setMultipleRenderPass(blockAccess, x, y, z, pass);
		return owner.getIcon(blockAccess, x, y, z, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getMultipleRenderIcon(int metadata, int side, int pass)
	{
		setMultipleRenderPass(metadata, pass);
		return owner.getIcon(side, metadata);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderColor(IBlockAccess blockAccess, int x, int y, int z, int pass)
	{
		setMultipleRenderPass(blockAccess, x, y, z, pass);
		return owner.colorMultiplier(blockAccess, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderColor(int metadata, int pass)
	{
		setMultipleRenderPass(metadata, pass);
		return owner.getRenderColor(metadata);
	}

	///////////////////////////////////////////////////////////

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		if (!isInMultipleRendering) return blockIcons.get(0);
		return blockIcons.get(pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (!isInMultipleRendering) return blockIcons.get(0);
		return blockIcons.get(pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
	{
		if (!isInMultipleRendering) return iconColors.get(0);
		return iconColors.get(pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta)
	{
		if (!isInMultipleRendering) return iconColors.get(0);
		return iconColors.get(pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		if (iconStrings.size() == 0) appendIcon("MISSING_ICON_BLOCK_" + this);

		while (blockIcons.size() < iconStrings.size()) {
			blockIcons.add(null);
		}
		for (int i = 0; i < iconStrings.size(); i++) {
			blockIcons.set(i, iconRegister.registerIcon(iconStrings.get(i)));
		}
	}

}
