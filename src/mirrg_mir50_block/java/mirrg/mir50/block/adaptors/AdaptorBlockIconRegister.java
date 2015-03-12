package mirrg.mir50.block.adaptors;

import java.util.function.Consumer;

import mirrg.mir50.block.AdaptorBlockIconOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorBlockIconRegister extends AdaptorBlockIconOverriding
{

	public Consumer<IIconRegister> consumer;
	public boolean callSuper;

	public AdaptorBlockIconRegister(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	public AdaptorBlockIconRegister(BlockMir50 owner, IVirtualClass superObject, Consumer<IIconRegister> consumer, boolean callSuper)
	{
		super(owner, superObject);
		this.consumer = consumer;
		this.callSuper = callSuper;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		if (callSuper) super.registerBlockIcons(iconRegister);
		if (consumer != null) consumer.accept(iconRegister);
	}

}
