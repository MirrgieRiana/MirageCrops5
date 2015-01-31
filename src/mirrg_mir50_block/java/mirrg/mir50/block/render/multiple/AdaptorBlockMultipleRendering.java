package mirrg.mir50.block.render.multiple;

import java.util.ArrayList;

import mirrg.mir50.block.AdaptorBlockIcon;
import mirrg.mir50.block.BlockMir50;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockMultipleRendering extends AdaptorBlockIcon implements IBlockMultipleRendering
{

	@SideOnly(Side.CLIENT)
	public ArrayList<IIcon> blockIcons = new ArrayList<>();

	public ArrayList<String> iconStrings = new ArrayList<>();

	@SideOnly(Side.CLIENT)
	public ArrayList<Integer> iconColors = new ArrayList<>();

	public AdaptorBlockMultipleRendering(BlockMir50 blockMir50)
	{
		super(blockMir50);
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

	@SideOnly(Side.CLIENT)
	public void setMultipleRendering(boolean isInMultipleRendering)
	{
		this.isInMultipleRendering = isInMultipleRendering;
	}

	@SideOnly(Side.CLIENT)
	public void setMultipleRenderPass(int pass)
	{
		this.pass = pass;
	}

	//

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(IBlockAccess blockAccess, int x, int y, int z)
	{
		return blockIcons.size();
	}

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(int metadata)
	{
		return blockIcons.size();
	}

	@SideOnly(Side.CLIENT)
	public IIcon getMultipleRenderIcon(IBlockAccess blockAccess, int x, int y, int z, int side, int pass)
	{
		setMultipleRendering(true);
		setMultipleRenderPass(pass);
		IIcon icon = blockMir50.getIcon(blockAccess, x, y, z, side);
		setMultipleRendering(true);
		return icon;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getMultipleRenderIcon(int side, int metadata, int pass)
	{
		setMultipleRendering(true);
		setMultipleRenderPass(pass);
		IIcon icon = blockMir50.getIcon(side, metadata);
		setMultipleRendering(true);
		return icon;
	}

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderColor(IBlockAccess blockAccess, int x, int y, int z, int pass)
	{
		setMultipleRendering(true);
		setMultipleRenderPass(pass);
		int color = blockMir50.colorMultiplier(blockAccess, x, y, z);
		setMultipleRendering(true);
		return color;
	}

	@SideOnly(Side.CLIENT)
	public int getMultipleRenderColor(int metadata, int pass)
	{
		setMultipleRendering(true);
		setMultipleRenderPass(pass);
		int color = blockMir50.getRenderColor(metadata);
		setMultipleRendering(true);
		return color;
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
	public IIcon getIcon(int side, int metaId)
	{
		if (!isInMultipleRendering) return blockIcons.get(0);
		return blockIcons.get(pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
	{
		if (!isInMultipleRendering) return 0xFFFFFF;
		return iconColors.get(pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int metaId)
	{
		if (!isInMultipleRendering) return 0xFFFFFF;
		return iconColors.get(pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		while (blockIcons.size() < iconStrings.size()) {
			blockIcons.add(null);
		}
		for (int i = 0; i < iconStrings.size(); i++) {
			blockIcons.set(i, iconRegister.registerIcon(iconStrings.get(i)));
		}
	}

}
