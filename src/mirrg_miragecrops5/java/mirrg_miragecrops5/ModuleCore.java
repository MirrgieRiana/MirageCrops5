package mirrg_miragecrops5;

import static net.minecraft.util.EnumChatFormatting.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;

import mirrg.h.struct.Tuple;
import mirrg.he.math.HelpersColor;
import mirrg.he.math.HelpersString;
import mirrg.mir50.item.AdaptorItemEventsOverriding;
import mirrg.mir50.item.AdaptorItemIconOverriding;
import mirrg.mir50.item.AdaptorItemNameOverriding;
import mirrg.mir50.item.AdaptorItemSubItemsOverriding;
import mirrg.mir50.item.adaptors.AdaptorItemContainerItemAutonomy;
import mirrg.mir50.item.adaptors.AdaptorItemContainerItemCraftingToolNBT;
import mirrg.mir50.item.adaptors.AdaptorItemContainerItemMaxStackSize;
import mirrg.mir50.item.adaptors.AdaptorItemIconAutonomy;
import mirrg.mir50.item.adaptors.AdaptorItemNameInformation;
import mirrg.mir50.item.adaptors.AdaptorItemNameInformationCraftingToolNBT;
import mirrg.mir50.oredictionary.HelpersOreDictionary;
import mirrg.mir51.icon.multi.MultipleIconShapes;
import mirrg.mir51.item.multi.MetaItem;
import mirrg.mir51.loaders.LoaderCreativeTab;
import mirrg.mir51.loaders.LoaderItem;
import mirrg.mir51.loaders.LoaderOreDictionary;
import mirrg.mir51.loaders.LoaderRecipe;
import mirrg_miragecrops5.fairytype.FairyType;
import mirrg_miragecrops5.fairytype.HelpersFairyType;
import mirrg_miragecrops5.fairytype.IFairySkill;
import mirrg_miragecrops5.fairytype.IItemFairy;
import mirrg_miragecrops5.fairytype.RegistryFairyType;
import mirrg_miragecrops5.material.HelpersModuleMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModuleCore extends ModuleMirageCropsAbstract
{

	public static LoaderCreativeTab loaderCreativeTab = new LoaderCreativeTab();
	public static LoaderCreativeTab loaderCreativeTabFairy = new LoaderCreativeTab();
	//public static LoaderBlock loaderBlock_blockTest = new LoaderBlock();
	public static LoaderItem loaderItem_craftingTool = new LoaderItem();
	public static LoaderItem loaderItem_craftingMirageFairy = new LoaderItem();
	public static LoaderItem loaderItem_craftingSpiritFairy = new LoaderItem();
	public static LoaderItem loaderItem_craftingPickaxeApatite = new LoaderItem();

	public ModuleCore()
	{

		loaderCreativeTab.init(() -> loaderItem_craftingTool.get(), "miragecrops5_core");
		add(loaderCreativeTab);

		loaderCreativeTabFairy.init(() -> loaderItem_craftingMirageFairy.get(), "miragecrops5_fairy");
		add(loaderCreativeTabFairy);

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

		loaderItem_craftingPickaxeApatite.init(() -> {
			ItemCraftingPickaxeApatite item = new ItemCraftingPickaxeApatite();
			item.setCreativeTab(loaderCreativeTab.get());
			item.setTextureName("miragecrops5:craftingPickaxeApatite");
			item.setUnlocalizedName("craftingPickaxeApatite");
			return item;
		}, "craftingPickaxeApatite", ModMirageCrops.MODID);
		add(loaderItem_craftingPickaxeApatite);

		process_loaderItem(loaderItem_craftingMirageFairy, loaderCreativeTabFairy, "craftingMirageFairy", (itemMir50) -> {
			itemMir50.setHasSubtypes(true);

			itemMir50.virtualClass.register(IItemFairy.class, itemStack -> null);
			itemMir50.virtualClass.override((IItemFairy) itemStack -> {
				int damage = itemStack.getItemDamage();
				int indexFairyType = damage / 10;
				int tier = (damage % 10) + 1;
				if (RegistryFairyType.registry.get(indexFairyType) == null) return null;
				return new Tuple<>(RegistryFairyType.registry.get(indexFairyType), tier);
			});

			itemMir50.virtualClass.override(new AdaptorItemSubItemsOverriding(itemMir50, itemMir50) {
				@Override
				@SideOnly(Side.CLIENT)
				public void getSubItems(Item item, CreativeTabs creativeTab, List<ItemStack> itemStacks)
				{
					RegistryFairyType.registry.forEach((index, name, fairyType) -> {
						for (int tier = 1; tier <= Math.max(fairyType.getMaxTier(), 5); tier++) {
							itemStacks.add(new ItemStack(owner, 1, index * 10 + (tier - 1)));
						}
					});
				}
			});
			itemMir50.virtualClass.override(new AdaptorItemNameOverriding(itemMir50, itemMir50) {
				@Override
				public String getItemStackDisplayName(ItemStack itemStack)
				{
					Tuple<FairyType, Integer> fairyType = HelpersFairyType.getFairyType(itemStack);

					if (fairyType != null) {
						String unlocalizedName = owner.getUnlocalizedNameInefficiently(itemStack) + "Tier" + fairyType.getY() + ".format";
						String format = StatCollector.translateToLocal(unlocalizedName).trim();
						String fairyTypeLocalizedName = HelpersFairyType.getLocalizedName(fairyType.getX());
						return String.format(format, fairyTypeLocalizedName).trim();
					}

					return super.getItemStackDisplayName(itemStack);
				}

				@Override
				@SideOnly(Side.CLIENT)
				public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> strings, boolean shift)
				{
					Tuple<FairyType, Integer> fairyType = HelpersFairyType.getFairyType(itemStack);

					if (fairyType != null) {
						strings.add("Type: " + AQUA + HelpersFairyType.getLocalizedName(fairyType.getX()) + "(" + fairyType.getX().typeName + ")");
						strings.add("Tier: " + fairyType.getY());

						strings.add("Values:");
						HelpersFairyType.addInformation(strings, HelpersFairyType.getValues(fairyType.getX().getIncreaser(fairyType.getY())));

						strings.add(String.format("Rate: x %.2f", fairyType.getX().getTransportAttenuation(fairyType.getY())));

						strings.add("Skills:");
						for (Tuple<IFairySkill, Double> skill : fairyType.getX().getSkills(fairyType.getY())) {
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
					FairyType fairyType = RegistryFairyType.registry.get("apatite");
					if (fairyType == null) return super.getColorFromItemStack(itemStack, pass);
					if (pass == 0) return FairyType.DEFAULT_SKIN_COLOR;
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
					Tuple<FairyType, Integer> fairyType = HelpersFairyType.getFairyType(itemStack);

					if (fairyType != null) {
						if (pass == 0) return fairyType.getX().colorS;
						if (pass == 1) return getColorOfTier(fairyType.getY());
						if (pass == 2) return fairyType.getX().colorA;
						if (pass == 3) return fairyType.getX().colorB;
						if (pass == 4) return fairyType.getX().colorC;
					}

					return super.getColorFromItemStack(itemStack, pass);
				}
			});
		});

		process_loaderItem(loaderItem_craftingSpiritFairy, loaderCreativeTabFairy, "craftingSpiritFairy", (itemMir50) -> {
			itemMir50.setHasSubtypes(true);

			itemMir50.virtualClass.override(new AdaptorItemSubItemsOverriding(itemMir50, itemMir50) {
				@Override
				@SideOnly(Side.CLIENT)
				public void getSubItems(Item item, CreativeTabs creativeTab, List<ItemStack> itemStacks)
				{
					RegistryFairyType.registry.forEach((index, name, fairyType) -> {
						itemStacks.add(new ItemStack(owner, 1, index * 10));
					});
				}
			});
			itemMir50.virtualClass.override(new AdaptorItemNameOverriding(itemMir50, itemMir50) {
				@Override
				public String getItemStackDisplayName(ItemStack itemStack)
				{
					int damage = itemStack.getItemDamage();
					int indexFairyType = damage / 10;
					FairyType fairyType = RegistryFairyType.registry.get(indexFairyType);

					if (fairyType != null) {

						String unlocalizedName = owner.getUnlocalizedNameInefficiently(itemStack) + ".format";
						String format = StatCollector.translateToLocal(unlocalizedName).trim();
						String fairyTypeLocalizedName = HelpersFairyType.getLocalizedName(fairyType);

						return String.format(format, fairyTypeLocalizedName);
					}

					return super.getItemStackDisplayName(itemStack);
				}

				@Override
				@SideOnly(Side.CLIENT)
				public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> strings, boolean shift)
				{
					int damage = itemStack.getItemDamage();
					int indexFairyType = damage / 10;
					FairyType fairyType = RegistryFairyType.registry.get(indexFairyType);

					if (fairyType != null) {
						strings.add("Type: " + AQUA + fairyType.typeName);
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
					int damage = itemStack.getItemDamage();
					int indexFairyType = damage / 10;
					FairyType fairyType = RegistryFairyType.registry.get(indexFairyType);

					if (fairyType != null) {
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
							outputs.add(HelpersOreDictionary.copyOrThrow("craftingDallFairyTier1"));
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
						boolean[] crafted = new boolean[1];

						HelpersItemUse.craft(itemStack, world, player, () -> {
							crafted[0] = true;
							world.playSoundEffect(
								player.posX + 0.5,
								player.posY + 0.5,
								player.posZ + 0.5,
								"ambient.cave.cave",
								1.0F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
						}, () -> 1, (outputs) -> {
							ItemStack output = HelpersOreDictionary.copyOrThrow("craftingSpiritFairy");

							{
								ArrayList<ItemStack> drops = world.getBlock(x, y, z).getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
								if (drops == null || drops.size() == 0) return;
								ItemStack drop = drops.get(world.rand.nextInt(drops.size()));

								ArrayList<FairyType> fairyTypes = RegistryFairyType.getFromMaterial(drop);
								if (fairyTypes.size() == 0) return;
								FairyType fairyType = fairyTypes.get(world.rand.nextInt(fairyTypes.size()));

								output.setItemDamage(RegistryFairyType.registry.getIndexFromItem(fairyType) * 10);
							}

							outputs.add(output);
						}, false);

						return crafted[0];
					}
				});
			}, false);

			setMetaItem(itemMir50, metaItemContainer, 10, "craftingCoke", null, false);

			setMetaItem(itemMir50, metaItemContainer, 11, "craftingFairyWastesTier1", (metaItem, a) -> {
				a.appendIcon("miragecrops5:" + "craftingFairyWastes");
			}, true);

			setMetaItem(itemMir50, metaItemContainer, 12, "craftingBookMirage", (metaItem, a) -> {
				a.appendIcon("miragecrops5:" + "craftingBookMirage", HelpersColor.multiplicate(0x654B17, 255.0 / 138));
				metaItem.virtualClass.override(new AdaptorItemContainerItemCraftingToolNBT(itemMir50, metaItem, 4000 - 1, "damage"));
				metaItem.virtualClass.override(new AdaptorItemNameInformationCraftingToolNBT(itemMir50, metaItem, 4000 - 1, "damage"));
				metaItem.virtualClass.override(new AdaptorItemContainerItemMaxStackSize(itemMir50, metaItem, 1));
			}, true);

			setMetaItem(itemMir50, metaItemContainer, 13, "craftingMonolithMirage", (metaItem, a) -> {
				a.appendIcon("miragecrops5:" + "craftingMonolithMirage", 0xAADDFF);
			}, true);

			setMetaItem(itemMir50, metaItemContainer, 14, "craftingStoneMirageFairy", (metaItem, a) -> {
				{
					AdaptorItemContainerItemAutonomy b = new AdaptorItemContainerItemAutonomy(itemMir50, metaItem);
					b.functionContainerItem = itemStack -> itemStack;
					b.doesContainerItemLeaveCraftingGrid = false;
					b.maxStackSize = 1;
					metaItem.virtualClass.override(b);
				}
				metaItem.virtualClass.override(new AdaptorItemNameInformation(itemMir50, metaItem,
					(itemStack, player, strings, shift) -> {
						strings.add(StatCollector.translateToLocal("item.craftingStoneMirageFairy.information"));
					}));
			}, false);

			setMetaItem(itemMir50, metaItemContainer, 15, "craftingBookMirageToolIndustrial", (metaItem, a) -> {
				a.appendIcon("miragecrops5:" + "craftingBookMirage", 0x3232FF);
				metaItem.virtualClass.override(new AdaptorItemContainerItemCraftingToolNBT(itemMir50, metaItem, 4000 - 1, "damage"));
				metaItem.virtualClass.override(new AdaptorItemNameInformationCraftingToolNBT(itemMir50, metaItem, 4000 - 1, "damage"));
				metaItem.virtualClass.override(new AdaptorItemContainerItemMaxStackSize(itemMir50, metaItem, 1));
			}, true);

			setMetaItem(itemMir50, metaItemContainer, 16, "craftingClayMold", null, false);

			setMetaItem(itemMir50, metaItemContainer, 17, "craftingBrickMold", null, false);
			setMetaItem(itemMir50, metaItemContainer, 18, "craftingBrickMoldBaked", (metaItem, a) -> {
				metaItem.virtualClass.override(new AdaptorItemEventsOverriding(itemMir50, metaItem) {
					@Override
					public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
					{
						return HelpersItemUse.craft(itemStack, world, player, () -> {
							world.playSoundEffect(
								player.posX + 0.5,
								player.posY + 0.5,
								player.posZ + 0.5,
								"random.break",
								1.0F, 1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F);
						}, () -> 1, (outputs) -> {
							outputs.add(HelpersOreDictionary.copyOrThrow("craftingDallFairyTier1"));
						}, true);
					}
				});
			}, false);
			setMetaItem(itemMir50, metaItemContainer, 19, "craftingBrickMoldFilled", null, false);

			setMetaItem(itemMir50, metaItemContainer, 20, "craftingBookMirageFairy", (metaItem, a) -> {
				a.appendIcon("miragecrops5:" + "craftingBookMirage", 0x26A1FF);
				metaItem.virtualClass.override(new AdaptorItemContainerItemCraftingToolNBT(itemMir50, metaItem, 4000 - 1, "damage"));
				metaItem.virtualClass.override(new AdaptorItemNameInformationCraftingToolNBT(itemMir50, metaItem, 4000 - 1, "damage"));
				metaItem.virtualClass.override(new AdaptorItemContainerItemMaxStackSize(itemMir50, metaItem, 1));
			}, true);

			setMetaItem(itemMir50, metaItemContainer, 21, "craftingStoneMirageFairyBaked", null, false);

			setMetaItem(itemMir50, metaItemContainer, 5, "craftingMachineHullStoneTier1", (metaItem, a) -> {
				a.appendIcon("minecraft:iron_ingot", 0x22cc00);

				HelpersModuleMaterial.applyMultipleIcon(itemMir50, metaItem,
					HelpersModuleMaterial.createMultipleIcon(MultipleIconShapes.MACHINE_HULL, "calcite"));

				metaItem.virtualClass.override(new AdaptorItemNameInformationCraftingToolNBT(itemMir50, metaItem, 4000 - 1, "damage"));

			}, true);

		});

		add(new LoaderOreDictionary(() -> {

			RegistryFairyType.registry.forEach((index, name, fairyType) -> {
				OreDictionary.registerOre("craftingSpiritFairy" + HelpersString.toUpperCaseHead(name),
					new ItemStack(loaderItem_craftingSpiritFairy.get(), 1, index * 10));
				for (int tier = 1; tier <= fairyType.getMaxTier(); tier++) {
					OreDictionary.registerOre("craftingMirageFairy" + HelpersString.toUpperCaseHead(name) + "Tier" + tier,
						new ItemStack(loaderItem_craftingMirageFairy.get(), 1, index * 10 + (tier - 1)));
					OreDictionary.registerOre("craftingMirageFairy" + HelpersString.toUpperCaseHead(name),
						new ItemStack(loaderItem_craftingMirageFairy.get(), 1, index * 10 + (tier - 1)));
				}
			});

		}));

		add(new LoaderRecipe(() -> {

			GameRegistry.addRecipe(new ShapedOreRecipe(
				HelpersOreDictionary.getOrThrow("mmfFurnace"),
				" P ",
				"PHP",
				" F ",
				'H', "craftingMachineHullStoneTier1",
				'F', Blocks.furnace,
				'P', "plateApatite"));

			GameRegistry.addRecipe(new ShapedOreRecipe(
				HelpersOreDictionary.getOrThrow("craftingMachineHullStoneTier1"),
				"RPR",
				"PBP",
				"RPR",
				'B', "craftingBookMirageToolIndustrial",
				'R', "rodIron",
				'P', "plateCalcite"));

			GameRegistry.addRecipe(new ShapedOreRecipe(
				HelpersOreDictionary.getOrThrow("writingDesk"),
				"BST",
				"HHH",
				"P P",
				'B', "craftingBookMirageFairy",
				'S', Blocks.bookshelf,
				'T', Blocks.torch,
				'P', "plankWood",
				'H', "slabWood"));

			GameRegistry.addSmelting(
				HelpersOreDictionary.getOrThrow("craftingStoneMirageFairy"),
				HelpersOreDictionary.getOrThrow("craftingStoneMirageFairyBaked"), 5);

			GameRegistry.addRecipe(new ShapelessOreRecipe(
				HelpersOreDictionary.getOrThrow("craftingMirageFairyCobblestoneTier1"),
				"craftingStoneMirageFairyBaked",
				"craftingDallFairyTier1"));

			GameRegistry.addSmelting(
				HelpersOreDictionary.getOrThrow("craftingBrickMoldFilled"),
				HelpersOreDictionary.getOrThrow("craftingBrickMoldBaked"), 5);

			GameRegistry.addSmelting(
				HelpersOreDictionary.getOrThrow("craftingClayMold"),
				HelpersOreDictionary.getOrThrow("craftingBrickMold"), 5);

			GameRegistry.addRecipe(new ShapedOreRecipe(
				HelpersOreDictionary.getOrThrow("craftingClayMold"),
				"B  ",
				"C C",
				"CCC",
				'B', "craftingBookMirageFairy",
				'C', Items.clay_ball));

			GameRegistry.addRecipe(new ShapelessOreRecipe(
				HelpersOreDictionary.getOrThrow("craftingBookMirageFairy"),
				"craftingStoneMirageFairy",
				Items.writable_book));

			GameRegistry.addRecipe(new ShapelessOreRecipe(
				HelpersOreDictionary.getOrThrow("craftingBookMirage"),
				"craftingMirageFairyRedstone",
				Items.writable_book));

			GameRegistry.addRecipe(new ShapedOreRecipe(
				HelpersOreDictionary.getOrThrow("craftingSpinachiumMold"),
				"B  ",
				"XCX",
				"XXX",
				'B', "craftingBookMirageToolIndustrial",
				'C', "craftingToolChisel",
				'X', "ingotSpinachium"));

			{
				ItemStack itemStack = new ItemStack(loaderItem_craftingPickaxeApatite.get());
				{
					Hashtable<Integer, Integer> hash = new Hashtable<>();
					hash.put(Enchantment.fortune.effectId, 1);
					EnchantmentHelper.setEnchantments(hash, itemStack);
				}
				GameRegistry.addRecipe(new ShapedOreRecipe(
					itemStack,
					"FFF",
					" A ",
					" A ",
					'A', "gemApatite",
					'F', "gemFluorite"));
			}

			GameRegistry.addRecipe(new ShapelessOreRecipe(
				HelpersOreDictionary.getOrThrow("craftingBrickMoldFilled"),
				"craftingBookMirageFairy",
				"craftingBrickMold",
				Items.rotten_flesh,
				Items.bone,
				Items.leather,
				"dustRedstone",
				"treeLeaves",
				"gemApatite",
				"ingotSpinachium"));

			GameRegistry.addRecipe(new ShapelessOreRecipe(
				HelpersOreDictionary.getOrThrow("craftingSpinachiumMoldFilled"),
				"craftingBookMirageFairy",
				"craftingSpinachiumMold",
				Items.rotten_flesh,
				Items.bone,
				Items.leather,
				"dustRedstone",
				"treeLeaves",
				"gemApatite",
				"nuggetSpinachium"));

			GameRegistry.addSmelting(
				HelpersOreDictionary.getOrThrow("craftingSpinachiumMoldFilled"),
				HelpersOreDictionary.getOrThrow("craftingSpinachiumMoldBaked"), 5);

			GameRegistry.addRecipe(new ShapelessOreRecipe(
				HelpersOreDictionary.copyOrThrow("dustMirage", 3),
				"dustMiragium",
				"dustApatite",
				"dustFluorite"));

			GameRegistry.addRecipe(new ShapelessOreRecipe(
				HelpersOreDictionary.getOrThrow("dustMirage"),
				"dustTinyMiragium",
				"dustTinyMiragium",
				"dustTinyMiragium",
				"dustTinyApatite",
				"dustTinyApatite",
				"dustTinyApatite",
				"dustTinyFluorite",
				"dustTinyFluorite",
				"dustTinyFluorite"));

			RegistryFairyType.registry.forEach((index, name, fairyType) -> {
				GameRegistry.addRecipe(new ShapelessOreRecipe(
					HelpersOreDictionary.copyOrThrow(
						"craftingMirageFairy" + HelpersString.toUpperCaseHead(name) + "Tier1"),
					"craftingSpiritFairy" + HelpersString.toUpperCaseHead(name),
					"craftingDallFairyTier1"));
			});

			GameRegistry.registerFuelHandler(
				itemStack -> OreDictionary.itemMatches(
					new ItemStack(loaderItem_craftingTool.get(), 1, 10), itemStack, false) ? 1600 * 2 : 0);

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
