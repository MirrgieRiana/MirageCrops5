package mirrg.mir50.tile.inventory;

 interface IInventoryName
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

}
