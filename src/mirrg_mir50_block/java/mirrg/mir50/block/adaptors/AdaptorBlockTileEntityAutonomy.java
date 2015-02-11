package mirrg.mir50.block.adaptors;

import java.util.function.BiFunction;

import mirrg.mir50.block.AdaptorBlockTileEntityOverriding;
import mirrg.mir50.block.BlockMir50;
import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AdaptorBlockTileEntityAutonomy extends AdaptorBlockTileEntityOverriding
{

	public BiFunction<World, Integer, TileEntity> supplierTileEntity = null;

	public AdaptorBlockTileEntityAutonomy(BlockMir50 owner, IVirtualClass superObject)
	{
		super(owner, superObject);
	}

	public AdaptorBlockTileEntityAutonomy(BlockMir50 owner, IVirtualClass superObject,
		BiFunction<World, Integer, TileEntity> supplierTileEntity)
	{
		this(owner, superObject);
		this.supplierTileEntity = supplierTileEntity;
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
		return supplierTileEntity != null;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		if (supplierTileEntity != null) return supplierTileEntity.apply(world, metadata);
		return null;
	}

}
