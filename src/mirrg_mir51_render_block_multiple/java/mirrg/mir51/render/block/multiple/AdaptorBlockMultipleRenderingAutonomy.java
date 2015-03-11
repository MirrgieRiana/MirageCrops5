package mirrg.mir51.render.block.multiple;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

import mirrg.mir50.block.AdaptorBlockIconOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
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

	@Override
	@SideOnly(Side.CLIENT)
	public Consumer<ObjIntConsumer<IIcon>> getMultipleRendering(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		return consumer -> {
			for (int i = 0; i < blockIcons.size(); i++) {
				consumer.accept(blockIcons.get(i), iconColors.get(i));
			}
		};
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Consumer<ObjIntConsumer<IIcon>> getMultipleRendering(int metadata, int side)
	{
		return consumer -> {
			for (int i = 0; i < blockIcons.size(); i++) {
				consumer.accept(blockIcons.get(i), iconColors.get(i));
			}
		};
	}

	///////////////////////////////////////////////////////////

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		if (!HelpersBlockMultipleRendering.enabled) return Blocks.stone.getIcon(blockAccess, x, y, z, side);
		return HelpersBlockMultipleRendering.icons[side];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (!HelpersBlockMultipleRendering.enabled) return Blocks.stone.getIcon(side, meta);
		return HelpersBlockMultipleRendering.icons[side];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
	{
		if (!HelpersBlockMultipleRendering.enabled) return 0xffffff;
		return HelpersBlockMultipleRendering.color;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta)
	{
		if (!HelpersBlockMultipleRendering.enabled) return 0xffffff;
		return HelpersBlockMultipleRendering.color;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		if (side == 0 && owner.getField_minY() > 0.0D) return true;
		if (side == 1 && owner.getField_maxY() < 1.0D) return true;
		if (side == 2 && owner.getField_minZ() > 0.0D) return true;
		if (side == 3 && owner.getField_maxZ() < 1.0D) return true;
		if (side == 4 && owner.getField_minX() > 0.0D) return true;
		if (side == 5 && owner.getField_maxX() < 1.0D) return true;
		if (blockAccess.getBlock(x, y, z).isOpaqueCube()) return false;

		if (!HelpersBlockMultipleRendering.enabled) return true;
		return HelpersBlockMultipleRendering.visibles[side];
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
