package mirrg.mir51.tile.inventory;

public interface IInventoryName
{

	/**
	 * Returns the name of the inventory
	 */
	public String getInventoryName();

	/**
	 * Returns if the inventory is named
	 */
	public boolean hasCustomInventoryName();

	public String getDefaultName();

	public String getLocalizedName();

	public void setCustomInventoryName(String customInventoryName);

}
