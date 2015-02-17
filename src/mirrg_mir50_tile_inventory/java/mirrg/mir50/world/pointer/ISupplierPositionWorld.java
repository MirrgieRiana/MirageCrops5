package mirrg.mir50.world.pointer;

import net.minecraft.world.World;

public interface ISupplierPositionWorld
{

	public World getWorld();

	public double getX();

	public double getY();

	public double getZ();

	public int getXAsInt();

	public int getYAsInt();

	public int getZAsInt();

}
