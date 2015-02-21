package mirrg_miragecrops5;

import static net.minecraft.util.EnumChatFormatting.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

import mirrg.h.struct.Tuple;
import mirrg.mir50.item.AdaptorItemEventsOverriding;
import mirrg.mir50.item.AdaptorItemIconOverriding;
import mirrg.mir50.item.AdaptorItemNameOverriding;
import mirrg.mir50.item.AdaptorItemSubItemsOverriding;
import mirrg.mir50.item.adaptors.AdaptorItemContainerItemCraftingTool;
import mirrg.mir50.item.adaptors.AdaptorItemIconAutonomy;
import mirrg.mir51.item.multi.MetaItem;
import mirrg.mir51.loaders.LoaderCreativeTab;
import mirrg.mir51.loaders.LoaderItem;
import mirrg.mir51.loaders.LoaderOreDictionary;
import mirrg.mir51.loaders.LoaderRecipe;
import mirrg_miragecrops5.fairytype.FairyType;
import mirrg_miragecrops5.fairytype.HelpersFairyType;
import mirrg_miragecrops5.fairytype.IFairySkill;
import mirrg_miragecrops5.fairytype.RegistryFairyType;
import mirrg_miragecrops5.recipefairy.OreMatcher;
import mirrg_miragecrops5.recipefairy.RecipeFairy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import api.mirrg.mir50.net.NBTTypes;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModuleCore extends ModuleMirageCropsAbstract
{

	public static LoaderCreativeTab loaderCreativeTab = new LoaderCreativeTab();
	//public static LoaderBlock loaderBlock_blockTest = new LoaderBlock();
	public static LoaderItem loaderItem_craftingToolHardHammerSpinachium = new LoaderItem();
	public static LoaderItem loaderItem_craftingTool = new LoaderItem();
	public static LoaderItem loaderItem_craftingMirageFairy = new LoaderItem();
	public static LoaderItem loaderItem_craftingSpiritFairy = new LoaderItem();

	public ModuleCore()
	{

		loaderCreativeTab.init(() -> loaderItem_craftingMirageFairy.get(), "miragecrops5_core");
		add(loaderCreativeTab);

		/*
		loaderBlock_blockTest.init(() -> {
			BlockMir50 block = new BlockMir50(Material.rock);

			block.adaptorBlockMultipleRendering.appendIcon("minecraft:stone");
			block.adaptorBlockMultipleRendering.appendIcon("miragecrops5:oreCalcite", color(255, 205, 59));

			return block;
		}, ItemBlock.class, "blockTest");
		loaderBlock_blockTest.setCreativeTab(loaderCreativeTab);
		add(loaderBlock_blockTest);
		*/

		process_loaderItem(loaderItem_craftingMirageFairy, loaderCreativeTab, "craftingMirageFairy", (itemMir50) -> {
			itemMir50.virtualClass.override(new AdaptorItemSubItemsOverriding(itemMir50, itemMir50) {
				@Override
				@SideOnly(Side.CLIENT)
				public void getSubItems(Item item, CreativeTabs creativeTab, List<ItemStack> itemStacks)
				{
					for (FairyType fairyType : RegistryFairyType.getFairyTypes()) {
						for (int tier = 1; tier <= 5; tier++) {
							ItemStack itemStack = new ItemStack(owner, 1, 0);
							{
								NBTTagCompound nbt = new NBTTagCompound();

								nbt.setString("type", fairyType.typeName);
								nbt.setInteger("tier", tier);

								itemStack.setTagCompound(nbt);
							}
							itemStacks.add(itemStack);
						}
					}
				}
			});
			itemMir50.virtualClass.override(new AdaptorItemNameOverriding(itemMir50, itemMir50) {
				@Override
				public String getItemStackDisplayName(ItemStack itemStack)
				{
					if (itemStack.getTagCompound() != null
						&& itemStack.getTagCompound().hasKey("type", NBTTypes.STRING)) {
						String typeName = itemStack.getTagCompound().getString("type");
						int tier = 1;
						if (itemStack.getTagCompound().hasKey("tier", NBTTypes.INT)) {
							tier = itemStack.getTagCompound().getInteger("tier");
						}

						FairyType fairyType = RegistryFairyType.get(typeName);
						if (fairyType != null) {

							String unlocalizedName = owner.getUnlocalizedNameInefficiently(itemStack) + "Tier" + tier + ".format";
							String format = StatCollector.translateToLocal(unlocalizedName).trim();
							String fairyTypeLocalizedName = HelpersFairyType.getLocalizedName(fairyType);

							return String.format(format, fairyTypeLocalizedName).trim();
						}
					}

					return super.getItemStackDisplayName(itemStack);
				}

				@Override
				@SideOnly(Side.CLIENT)
				public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> strings, boolean shift)
				{
					if (itemStack.getTagCompound() != null
						&& itemStack.getTagCompound().hasKey("type", NBTTypes.STRING)) {
						String type = itemStack.getTagCompound().getString("type");
						int tier = 1;
						if (itemStack.getTagCompound().hasKey("tier", NBTTypes.INT)) {
							tier = itemStack.getTagCompound().getInteger("tier");
						}

						FairyType fairyType = RegistryFairyType.get(type);

						if (fairyType != null) {
							strings.add("Type: " + AQUA + HelpersFairyType.getLocalizedName(fairyType) + "(" + fairyType.typeName + ")");
							strings.add("Tier: " + tier);

							strings.add("Values:");
							HelpersFairyType.addInformation(strings, HelpersFairyType.getValues(fairyType.getIncreaser(tier)));

							strings.add("Skills:");
							for (Tuple<IFairySkill, Double> skill : fairyType.getSkills(tier)) {
								strings.add(String.format("    %s%s %.2f",
									skill.getX().isPositive() ? AQUA : YELLOW,
									HelpersFairyType.getLocalizedName(skill.getX()),
									skill.getY()));
							}

							/*
							strings.add("");
							strings.add(GRAY + "Heat " + makeGauge.apply(41));
							strings.add("");
							strings.add("Effects:");
							strings.add("    Right: 攻撃力増加");
							strings.add("    Left : 防御力増加");
							strings.add("    Chest: 自動整列");
							strings.add("    植物成長効率増加");
							*/
						} else {
							strings.add("Type: " + RED + type);
						}

					} else {
						strings.add("Type: " + RED + "Undefined!!");
					}
				}
			});

			AdaptorItemIconAutonomy icon = new AdaptorItemIconAutonomy(itemMir50, itemMir50);
			itemMir50.virtualClass.override(icon);
			icon.appendIcon("miragecrops5:" + "craftingMirageFairy" + "_" + 0, 0xFFC9D1);
			icon.appendIcon("miragecrops5:" + "craftingMirageFairy" + "_" + 1);
			icon.appendIcon("miragecrops5:" + "craftingMirageFairy" + "_" + 2);
			icon.appendIcon("miragecrops5:" + "craftingMirageFairy" + "_" + 3);
			icon.appendIcon("miragecrops5:" + "craftingMirageFairy" + "_" + 4);

			itemMir50.virtualClass.override(new AdaptorItemIconOverriding(itemMir50, itemMir50) {
				@Override
				@SideOnly(Side.CLIENT)
				public int getColorFromItemStack(ItemStack itemStack, int pass)
				{
					FairyType fairyType = RegistryFairyType.get("apatite");
					if (fairyType == null) return super.getColorFromItemStack(itemStack, pass);
					if (pass == 0) return fairyType.colorS;
					if (pass == 1) return getColorOfTier(1);
					if (pass == 2) return fairyType.colorA;
					if (pass == 3) return fairyType.colorB;
					if (pass == 4) return fairyType.colorC;

					return super.getColorFromItemStack(itemStack, pass);
				}
			});
			itemMir50.virtualClass.override(new AdaptorItemIconOverriding(itemMir50, itemMir50) {
				@Override
				@SideOnly(Side.CLIENT)
				public int getColorFromItemStack(ItemStack itemStack, int pass)
				{
					if (itemStack.getTagCompound() != null
						&& itemStack.getTagCompound().hasKey("type", NBTTypes.STRING)) {
						String type = itemStack.getTagCompound().getString("type");
						int tier = 1;
						if (itemStack.getTagCompound().hasKey("tier", NBTTypes.INT)) {
							tier = itemStack.getTagCompound().getInteger("tier");
						}

						FairyType fairyType = RegistryFairyType.get(type);
						if (fairyType == null) return super.getColorFromItemStack(itemStack, pass);
						if (pass == 0) return fairyType.colorS;
						if (pass == 1) return getColorOfTier(tier);
						if (pass == 2) return fairyType.colorA;
						if (pass == 3) return fairyType.colorB;
						if (pass == 4) return fairyType.colorC;
					}

					return super.getColorFromItemStack(itemStack, pass);
				}
			});
		});

		process_loaderItem(loaderItem_craftingSpiritFairy, loaderCreativeTab, "craftingSpiritFairy", (itemMir50) -> {
			itemMir50.virtualClass.override(new AdaptorItemSubItemsOverriding(itemMir50, itemMir50) {
				@Override
				@SideOnly(Side.CLIENT)
				public void getSubItems(Item item, CreativeTabs creativeTab, List<ItemStack> itemStacks)
				{
					for (FairyType fairyType : RegistryFairyType.getFairyTypes()) {
						ItemStack itemStack = new ItemStack(owner, 1, 0);
						{
							NBTTagCompound nbt = new NBTTagCompound();

							nbt.setString("type", fairyType.typeName);

							itemStack.setTagCompound(nbt);
						}
						itemStacks.add(itemStack);
					}
				}
			});
			itemMir50.virtualClass.override(new AdaptorItemNameOverriding(itemMir50, itemMir50) {
				@Override
				public String getItemStackDisplayName(ItemStack itemStack)
				{
					if (itemStack.getTagCompound() != null
						&& itemStack.getTagCompound().hasKey("type", NBTTypes.STRING)) {
						String typeName = itemStack.getTagCompound().getString("type");

						FairyType fairyType = RegistryFairyType.get(typeName);
						if (fairyType != null) {

							String unlocalizedName = owner.getUnlocalizedNameInefficiently(itemStack) + ".format";
							String format = StatCollector.translateToLocal(unlocalizedName).trim();
							String fairyTypeLocalizedName = HelpersFairyType.getLocalizedName(fairyType);

							return String.format(format, fairyTypeLocalizedName).trim();
						}
					}

					return super.getItemStackDisplayName(itemStack);
				}

				@Override
				@SideOnly(Side.CLIENT)
				public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> strings, boolean shift)
				{
					if (itemStack.getTagCompound() != null
						&& itemStack.getTagCompound().hasKey("type", NBTTypes.STRING)) {
						String type = itemStack.getTagCompound().getString("type");
						strings.add("Type: " + AQUA + type);
					} else {
						strings.add("Type: " + RED + "Undefined!!");
					}
				}
			});

			AdaptorItemIconAutonomy icon = new AdaptorItemIconAutonomy(itemMir50, itemMir50);
			itemMir50.virtualClass.override(icon);
			icon.appendIcon("miragecrops5:" + "craftingSpiritFairy" + "_" + 0);
			icon.appendIcon("miragecrops5:" + "craftingSpiritFairy" + "_" + 1);
			icon.appendIcon("miragecrops5:" + "craftingSpiritFairy" + "_" + 2);
			icon.appendIcon("miragecrops5:" + "craftingSpiritFairy" + "_" + 3);

			itemMir50.virtualClass.override(new AdaptorItemIconOverriding(itemMir50, itemMir50) {
				@Override
				@SideOnly(Side.CLIENT)
				public int getColorFromItemStack(ItemStack itemStack, int pass)
				{
					if (itemStack.getTagCompound() != null
						&& itemStack.getTagCompound().hasKey("type", NBTTypes.STRING)) {
						String type = itemStack.getTagCompound().getString("type");

						FairyType fairyType = RegistryFairyType.get(type);
						if (fairyType == null) return super.getColorFromItemStack(itemStack, pass);
						if (pass == 0) return fairyType.colorB;
						if (pass == 1) return fairyType.colorS;
						if (pass == 2) return fairyType.colorA;
						if (pass == 3) return fairyType.colorC;
					}

					return super.getColorFromItemStack(itemStack, pass);
				}
			});
		});

		process_loaderItem_multi(loaderItem_craftingTool, loaderCreativeTab, "craftingTool", (itemMir50, metaItemContainer) -> {
			itemMir50.setTextureName("minecraft:apple");
			{
				IntFunction<BiConsumer<MetaItem, AdaptorItemIconAutonomy>> b = color -> (metaItem, a) -> {
					a.appendIcon("miragecrops5:" + "craftingDallFairy" + "_" + 0);
					a.appendIcon("miragecrops5:" + "craftingDallFairy" + "_" + 1, color);
				};

				setMetaItem(itemMir50, metaItemContainer, 0, "craftingDallFairyTier1", b.apply(getColorOfTier(1)), true);
				setMetaItem(itemMir50, metaItemContainer, 6, "craftingDallFairyTier2", b.apply(getColorOfTier(2)), true);
				setMetaItem(itemMir50, metaItemContainer, 7, "craftingDallFairyTier3", b.apply(getColorOfTier(3)), true);
				setMetaItem(itemMir50, metaItemContainer, 8, "craftingDallFairyTier4", b.apply(getColorOfTier(4)), true);
				setMetaItem(itemMir50, metaItemContainer, 9, "craftingDallFairyTier5", b.apply(getColorOfTier(5)), true);
			}
			setMetaItem(itemMir50, metaItemContainer, 1, "craftingSpinachiumMold", null, false);
			setMetaItem(itemMir50, metaItemContainer, 2, "craftingSpinachiumMoldBaked", (metaItem, a) -> {
				metaItem.virtualClass.override(new AdaptorItemEventsOverriding(itemMir50, metaItem) {
					@Override
					public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
					{
						return HelpersItemUse.craft(itemStack, world, player, () -> {
							world.playSoundEffect(
								player.posX + 0.5,
								player.posY + 0.5,
								player.posZ + 0.5,
								"random.anvil_land",
								0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
						}, () -> 1, (outputs) -> {
							outputs.add(HelpersOreDictionary.copyOrThrow("craftingSpinachiumMold"));
							outputs.add(HelpersOreDictionary.copyOrThrow("craftingDallFairy"));
						}, true);
					}
				});
			}, false);
			setMetaItem(itemMir50, metaItemContainer, 3, "craftingSpinachiumMoldFilled", null, false);
			setMetaItem(itemMir50, metaItemContainer, 4, "dustMirage", (metaItem, a) -> {
				metaItem.virtualClass.override(new AdaptorItemEventsOverriding(itemMir50, metaItem) {
					@Override
					public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world,
						int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						boolean[] crafted = new boolean[2];

						HelpersItemUse.craft(itemStack, world, player, () -> {
							crafted[0] = true;
						}, () -> 1, (outputs) -> {
							ItemStack output = HelpersOreDictionary.copyOrThrow("craftingSpiritFairy");
							{
								NBTTagCompound nbt = new NBTTagCompound();

								ArrayList<ItemStack> drops = world.getBlock(x, y, z).getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
								if (drops == null || drops.size() == 0) return;
								ItemStack drop = drops.get(world.rand.nextInt(drops.size()));

								FairyType fairyType = RegistryFairyType.get(drop);
								if (fairyType == null) return;
								nbt.setString("type", fairyType.typeName);

								output.setTagCompound(nbt);
							}
							crafted[1] = true;
							outputs.add(output);
						}, false);

						if (crafted[1]) {
							world.playSoundEffect(
								player.posX + 0.5,
								player.posY + 0.5,
								player.posZ + 0.5,
								"ambient.cave.cave",
								0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
						}

						return crafted[1];
					}
				});
			}, false);
			setMetaItem(itemMir50, metaItemContainer, 5, "ingotSpinachium", (metaItem, a) -> {
				a.appendIcon("minecraft:iron_ingot", 0x22cc00);
			}, true);
		});

		process_loaderItem(loaderItem_craftingToolHardHammerSpinachium, loaderCreativeTab, "craftingToolHardHammerSpinachium", (itemMir50) -> {
			itemMir50.setTextureName("miragecrops5:craftingToolHardHammerSpinachium");
			itemMir50.setFull3D();

			itemMir50.setMaxStackSize(1);
			itemMir50.setMaxDamage(20 - 1);
			itemMir50.virtualClass.override(new AdaptorItemContainerItemCraftingTool(itemMir50, itemMir50));

		});

		add(new LoaderOreDictionary(() -> {
			OreDictionary.registerOre("craftingToolHardHammer", loaderItem_craftingToolHardHammerSpinachium.get());
		}).dependsOn(loaderItem_craftingToolHardHammerSpinachium));

		add(new LoaderRecipe(() -> {

			GameRegistry.addRecipe(new ShapedOreRecipe(
				HelpersOreDictionary.getOrThrow("ingotIron"),
				"X",
				"Y",
				'X', "craftingToolHardHammer",
				'Y', "oreIron"));

			GameRegistry.addRecipe(new ShapedOreRecipe(
				HelpersOreDictionary.getOrThrow("craftingToolHardHammerSpinachium"),
				"XX ",
				"XXY",
				"XX ",
				'X', "ingotSpinachium",
				'Y', "stickWood"));

			GameRegistry.addRecipe(new ShapedOreRecipe(
				HelpersOreDictionary.getOrThrow("craftingSpinachiumMold"),
				"X X",
				"XXX",
				'X', "ingotSpinachium"));

			GameRegistry.addRecipe(new ShapelessOreRecipe(
				HelpersOreDictionary.getOrThrow("craftingSpinachiumMoldFilled"),
				"craftingSpinachiumMold",
				Items.rotten_flesh,
				Items.bone,
				Items.leather,
				Items.string,
				"dustRedstone",
				"treeLeaves"));

			GameRegistry.addSmelting(
				HelpersOreDictionary.getOrThrow("craftingSpinachiumMoldFilled"),
				HelpersOreDictionary.getOrThrow("craftingSpinachiumMoldBaked"), 5);

			{
				IntConsumer a = tier -> {
					GameRegistry.addRecipe(new RecipeFairy((inventoryCrafting, slotIndexes) -> {
						ItemStack craftingSpiritFairy = inventoryCrafting.getStackInSlot(slotIndexes[0]);
						ItemStack craftingMirageFairy = HelpersOreDictionary.copyOrThrow("craftingMirageFairy");

						if (craftingSpiritFairy.hasTagCompound()) {
							NBTTagCompound nbt = (NBTTagCompound) craftingSpiritFairy.getTagCompound().copy();
							if (nbt == null) {
								nbt = new NBTTagCompound();
							}
							nbt.setInteger("tier", tier);
							craftingMirageFairy.setTagCompound(nbt);
							return craftingMirageFairy;
						} else {
							return null;
						}
					},
						new OreMatcher("craftingSpiritFairy"),
						new OreMatcher("craftingDallFairyTier" + tier)));
				};

				for (int tier = 1; tier <= 5; tier++) {
					a.accept(tier);
				}
			}

		}));

	}

	int getColorOfTier(int tier)
	{
		if (tier == 1) return 0xff4444;
		if (tier == 2) return 0x4444ff;
		if (tier == 3) return 0x009900;
		if (tier == 4) return 0xbbbb00;
		if (tier == 5) return 0x00cccc;
		return 0xffffff;
	}

}
