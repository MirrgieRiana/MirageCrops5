package mirrg.mir52.tile;

import mirrg.mir50.guihandler.IGuiProvider;
import mirrg.mir52.gui.GuiMir53;
import mirrg.mir53.gui.container.ContainerMir52;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import api.mirrg.mir50.net.NBTTypes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityMir53 extends TileEntity implements IGuiProvider
{

	@Override
	public void writeToNBT(NBTTagCompound p_145841_1_)
	{
		super.writeToNBT(p_145841_1_);

		if (customInventoryName != null) {
			p_145841_1_.setString("CustomName", customInventoryName);
		}

	}

	@Override
	public void readFromNBT(NBTTagCompound p_145839_1_)
	{
		super.readFromNBT(p_145839_1_);

		//dirty = true;

		if (p_145839_1_.hasKey("CustomName", NBTTypes.STRING)) {
			customInventoryName = p_145839_1_.getString("CustomName");
		} else {
			customInventoryName = null;
		}

	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		writeToNBT(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.func_148857_g());
	}

	///////////////////////// IGuiProvider /////////////////////////

	@Override
	@SideOnly(Side.CLIENT)
	public GuiMir53 createGui(EntityPlayer player, World world, int x, int y, int z)
	{
		if (!hasGui()) return null;
		ContainerMir52 container = createContainer(player, world, x, y, z);
		return new GuiMir53(container, getGuiTexture(container));
	}

	@Override
	public ContainerMir52 createContainer(EntityPlayer player, World world, int x, int y, int z)
	{
		if (!hasGui()) return null;
		ContainerMir52 container = new ContainerMir52(player, (player2) -> {
			TileEntity tileEntity = world.getTileEntity(xCoord, yCoord, zCoord);
			if (tileEntity != this) return false;
			return player2.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
		});
		prepareContainerSlots(container);
		return container;
	}

	protected ResourceLocation getGuiTexture(ContainerMir52 container)
	{
		return null;
	}

	protected boolean hasGui()
	{
		return false;
	}

	protected void prepareContainerSlots(ContainerMir52 container)
	{

	}

	///////////////////////// IMarkDirty /////////////////////////

	//public boolean dirty = true;

	@Override
	public void markDirty()
	{
		super.markDirty();
		//dirty = true;
	}

	@Override
	public void updateEntity()
	{
		tick();

		//if (dirty) {
		//dirty = false;

		//this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		//this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);
		//if ((!HelpersSide.helper(this).isSimulating()) || HelpersSide.helper(this).isRendering()) return;
		//}
	}

	protected void tick()
	{

	}

	///////////////////////// IInventoryName /////////////////////////

	protected String customInventoryName;

	public void setCustomInventoryName(String customInventoryName)
	{
		this.customInventoryName = customInventoryName;
	}

	public String getDefaultName()
	{
		return "container.mirageMachine";
	}

	public boolean hasCustomInventoryName()
	{
		return customInventoryName != null;
	}

	public String getInventoryName()
	{
		return hasCustomInventoryName() ? customInventoryName : getDefaultName();
	}

	public String getLocalizedName()
	{
		return hasCustomInventoryName() ? getInventoryName() : StatCollector.translateToLocal(getInventoryName());
	}

	public String getCustomInventoryName()
	{
		return customInventoryName;
	}

	/*
		///////////////////////// ITileEntityMirageMachine /////////////////////////

		@Override
		public TileEntity getTileEntity()
		{
			return this;
		}
	*/
	public void onBroken()
	{

	}

}
