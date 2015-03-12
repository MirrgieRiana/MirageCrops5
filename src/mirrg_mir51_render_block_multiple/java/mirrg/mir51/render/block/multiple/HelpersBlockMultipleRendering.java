package mirrg.mir51.render.block.multiple;

import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HelpersBlockMultipleRendering
{

	public static LoaderSimpleBlockRenderingHandler loader =
		new LoaderSimpleBlockRenderingHandler(RenderBlockMultipleRendering::new);

	public static void make(IVirtualClass virtualClass, BlockMir50 blockMir50)
	{
		virtualClass.getVirtualClass().override(new AdaptorBlockRenderMultipleRendering(blockMir50, virtualClass));
		virtualClass.getVirtualClass().register(IBlockMultipleRendering.class, new AdaptorBlockMultipleRendering(blockMir50));
	}

	public static AdaptorBlockMultipleRenderingAutonomy makeAutonomy(IVirtualClass virtualClass, BlockMir50 blockMir50)
	{
		make(virtualClass, blockMir50);

		AdaptorBlockMultipleRenderingAutonomy a = new AdaptorBlockMultipleRenderingAutonomy(blockMir50, virtualClass);
		virtualClass.getVirtualClass().override(a);
		return a;
	}

	@SideOnly(Side.CLIENT)
	public static boolean enabled;

	@SideOnly(Side.CLIENT)
	public static boolean[] visibles = new boolean[6];

	@SideOnly(Side.CLIENT)
	public static IIcon[] icons = new IIcon[6];

	@SideOnly(Side.CLIENT)
	public static int[] rotates = new int[6];

	@SideOnly(Side.CLIENT)
	public static int color;

	@SideOnly(Side.CLIENT)
	public static boolean flip;

}
