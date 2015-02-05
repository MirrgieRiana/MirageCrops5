package mirrg.mir50.item;

import java.util.List;

import mirrg.p.virtualclass.IVirtualClass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdaptorItemSubItemsOverriding extends AdaptorItemSubItems
{

	protected final IAdaptorItemSubItems _super_IAdaptorItemSubItems;

	public AdaptorItemSubItemsOverriding(ItemMir50 owner, IVirtualClass superObject)
	{
		super(owner);
		this._super_IAdaptorItemSubItems = superObject.getVirtualClass().getCurrentImplementation(IAdaptorItemSubItems.class);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTab, List<ItemStack> itemStacks)
	{
		_super_IAdaptorItemSubItems.getSubItems(item, creativeTab, itemStacks);
	}

}
