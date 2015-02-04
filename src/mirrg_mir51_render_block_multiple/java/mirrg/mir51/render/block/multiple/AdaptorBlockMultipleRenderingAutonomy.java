package mirrg.mir51.render.block.multiple;

import java.util.ArrayList;

import mirrg.mir50.block.AdaptorBlockIconOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import mirrg.p.virtualclass.IVirtualImplementationAccessor;
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

	public AdaptorBlockMultipleRenderingAutonomy(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
		accessor_IBlockMultipleRendering = superObject.getVirtualClass().cast(IBlockMultipleRendering.class);
	}

	public void appendIcon(String iconString)
	{
		this.appendIcon(iconString, 0xffffff);
	}

	public void appendIcon(String iconString, int iconColor)
	{
		this.iconStrings.add(iconString);
		this.iconColors.add(iconColor);
	}

	///////////////////////////////////////////////////////////

	protected final IVirtualImplementationAccessor<IBlockMultipleRendering> accessor_IBlockMultipleRendering;

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
		return this.blockIcons.size();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderPasses(int metadata)
	{
		return this.blockIcons.size();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getMultipleRenderIcon(IBlockAccess blockAccess, int x, int y, int z, int side, int pass)
	{
		accessor_IBlockMultipleRendering.get().setMultipleRenderPass(blockAccess, x, y, z, pass);
		return this.owner.getIcon(blockAccess, x, y, z, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getMultipleRenderIcon(int metadata, int side, int pass)
	{
		accessor_IBlockMultipleRendering.get().setMultipleRenderPass(metadata, pass);
		return this.owner.getIcon(side, metadata);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderColor(IBlockAccess blockAccess, int x, int y, int z, int pass)
	{
		accessor_IBlockMultipleRendering.get().setMultipleRenderPass(blockAccess, x, y, z, pass);
		return this.owner.colorMultiplier(blockAccess, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getMultipleRenderColor(int metadata, int pass)
	{
		accessor_IBlockMultipleRendering.get().setMultipleRenderPass(metadata, pass);
		return this.owner.getRenderColor(metadata);
	}

	///////////////////////////////////////////////////////////

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		if (!this.isInMultipleRendering) return this.blockIcons.get(0);
		return this.blockIcons.get(this.pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (!this.isInMultipleRendering) return this.blockIcons.get(0);
		return this.blockIcons.get(this.pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
	{
		if (!this.isInMultipleRendering) return this.iconColors.get(0);
		return this.iconColors.get(this.pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta)
	{
		if (!this.isInMultipleRendering) return this.iconColors.get(0);
		return this.iconColors.get(this.pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		if (this.iconStrings.size() == 0) this.appendIcon("MISSING_ICON_BLOCK_" + this);

		while (this.blockIcons.size() < this.iconStrings.size()) {
			this.blockIcons.add(null);
		}
		for (int i = 0; i < this.iconStrings.size(); i++) {
			this.blockIcons.set(i, iconRegister.registerIcon(this.iconStrings.get(i)));
		}
	}

}
