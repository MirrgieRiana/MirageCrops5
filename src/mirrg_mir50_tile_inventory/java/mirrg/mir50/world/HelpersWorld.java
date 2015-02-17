package mirrg.mir50.world;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class HelpersWorld
{

	public static void dropItemStack(World world, double x, double y, double z, ItemStack itemStack)
	{
		if (itemStack == null) return;

		double x2 = world.rand.nextFloat() * 0.8 + 0.1;
		double y2 = world.rand.nextFloat() * 0.8 + 0.1;
		double z2 = world.rand.nextFloat() * 0.8 + 0.1;

		while (itemStack.stackSize > 0) {
			int amount = world.rand.nextInt(21) + 10;
			if (amount > itemStack.stackSize) amount = itemStack.stackSize;
			itemStack.stackSize -= amount;

			EntityItem entityItem = new EntityItem(world, x + x2, y + y2, z + z2,
				new ItemStack(itemStack.getItem(), amount, itemStack.getItemDamage()));

			if (itemStack.hasTagCompound()) {
				entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
			}

			double speed = 0.05;
			entityItem.motionX = world.rand.nextGaussian() * speed;
			entityItem.motionY = world.rand.nextGaussian() * speed + 0.2;
			entityItem.motionZ = world.rand.nextGaussian() * speed;
			world.spawnEntityInWorld(entityItem);
		}

	}

}
