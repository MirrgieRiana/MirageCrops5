package mirrg.mir50.loaders;

import java.util.function.Consumer;
import java.util.function.Supplier;

import mirrg.mir50.loader.EnumLoadEventTiming;
import mirrg.mir50.loader.Loader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class LoaderBlock extends Loader<Block>
{

	protected Supplier<Block> supplierBlock;
	protected Class<? extends ItemBlock> itemclass;
	protected String name;
	protected Object[] itemConstructorArguments;
	protected LoaderCreativeTab loaderCreativeTab;
	protected Consumer<Item> itemBlockIniter;

	public void init(Supplier<Block> supplierBlock, Class<? extends ItemBlock> itemclass, String name, Object... itemConstructorArguments)
	{
		this.supplierBlock = supplierBlock;
		this.itemclass = itemclass;
		this.name = name;
		this.itemConstructorArguments = itemConstructorArguments;
	}

	public void setItemBlockIniter(Consumer<Item> itemBlockIniter)
	{
		this.itemBlockIniter = itemBlockIniter;
	}

	public void setCreativeTab(LoaderCreativeTab loaderCreativeTab)
	{
		this.loaderCreativeTab = loaderCreativeTab;
	}

	@Override
	protected void loadDependancies(EnumLoadEventTiming loadEvent)
	{
		super.loadDependancies(loadEvent);
		if (loaderCreativeTab != null) loaderCreativeTab.load(loadEvent);
	}

	@Override
	protected void loadThisLoader(EnumLoadEventTiming loadEvent)
	{
		if (loadEvent == EnumLoadEventTiming.PreInit) {
			Block block = supplierBlock.get();
			if (loaderCreativeTab != null) block.setCreativeTab(loaderCreativeTab.get());
			GameRegistry.registerBlock(block, itemclass, name, itemConstructorArguments);
			if (itemBlockIniter != null) itemBlockIniter.accept(Item.getItemFromBlock(block));
			set(block);
		}
	}

}
