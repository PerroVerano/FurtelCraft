package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.items.*;
import com.vtwo.furtelcraft.furtelcraft.items.tools.SpecimenKnife;
import com.vtwo.furtelcraft.furtelcraft.items.tools.materials.SpecimenKnifeMaterial;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class FCItems {
    public static final FoodComponent WATERTUBEFOOD = new FoodComponent.Builder().hunger(0).saturationModifier(0f).alwaysEdible().build();
    public static final FoodComponent WOLFMEATFOOD = new FoodComponent.Builder().hunger(2).saturationModifier(1.0F).meat().build();
    public static final FoodComponent WOLFMEATSPECIMENFOOD = new FoodComponent.Builder().hunger(1).saturationModifier(1.0F).meat().build();
    public static final FoodComponent COOKEDWOLFMEATFOOD = new FoodComponent.Builder().hunger(6).saturationModifier(5.0F).meat().build();
    public static final FoodComponent FOXMEATFOOD = new FoodComponent.Builder().hunger(2).saturationModifier(1.0F).meat().build();
    public static final FoodComponent FOXMEATSPECIMENFOOD = new FoodComponent.Builder().hunger(1).saturationModifier(1.0F).meat().build();
    public static final FoodComponent COOKEDFOXMEATFOOD = new FoodComponent.Builder().hunger(6).saturationModifier(5.0F).meat().build();
    public static final FoodComponent ENDERDRAGONMEATFOOD = new FoodComponent.Builder().hunger(7).saturationModifier(2.0F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 3), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 500, 3), 1.0F).meat().build();
    public static final FoodComponent ENDERDRAGONMEATSPECIMENFOOD = new FoodComponent.Builder().hunger(2).saturationModifier(1.0F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 150, 2), 1.0F).meat().build();
    public static final FoodComponent COOKEDENDERDRAGONMEATFOOD = new FoodComponent.Builder().hunger(12).saturationModifier(15.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 10, 1), 1.0F).meat().build();


    public static final Item WATERTUBEREMAIN = FCItems.EMPTY_TUBE;

    public static final GuideBook GUIDE_BOOK = new GuideBook(new FabricItemSettings().maxCount(1));
    public static final SpecimenKnife SPECIMEN_KNIFE = new SpecimenKnife(SpecimenKnifeMaterial.INSTANCE, 1, 1.5F, new Item.Settings());
    public static final DirtBrush DIRT_BRUSH = new DirtBrush(new FabricItemSettings().maxCount(1).maxDamage(64));
    public static final IronTrap IRON_TRAP = new IronTrap(new FabricItemSettings().maxDamage(16));
    public static final EmptyTube EMPTY_TUBE = new EmptyTube(new FabricItemSettings());
    public static final WaterTube WATER_TUBE = new WaterTube(new FabricItemSettings().food(WATERTUBEFOOD).recipeRemainder(WATERTUBEREMAIN));
    public static final WolfMeat WOLF_MEAT = new WolfMeat(new FabricItemSettings().food(WOLFMEATFOOD));
    public static final WolfMeatSpecimen WOLF_MEAT_SPECIMEN = new WolfMeatSpecimen(new FabricItemSettings().food(WOLFMEATSPECIMENFOOD));
    public static final CookedWolfMeat COOKED_WOLF_MEAT = new CookedWolfMeat(new FabricItemSettings().food(COOKEDWOLFMEATFOOD));
    public static final FoxMeat FOX_MEAT = new FoxMeat(new FabricItemSettings().food(FOXMEATFOOD));
    public static final FoxMeatSpecimen FOX_MEAT_SPECIMEN = new FoxMeatSpecimen(new FabricItemSettings().food(FOXMEATSPECIMENFOOD));
    public static final CookedFoxMeat COOKED_FOX_MEAT = new CookedFoxMeat(new FabricItemSettings().food(COOKEDFOXMEATFOOD));
    public static final EnderDragonMeat ENDER_DRAGON_MEAT = new EnderDragonMeat(new FabricItemSettings().food(ENDERDRAGONMEATFOOD));
    public static final EnderDragonMeatSpecimen ENDER_DRAGON_MEAT_SPECIMEN = new EnderDragonMeatSpecimen(new FabricItemSettings().food(ENDERDRAGONMEATSPECIMENFOOD));
    public static final CookedEnderDragonMeat COOKED_ENDER_DRAGON_MEAT = new CookedEnderDragonMeat(new FabricItemSettings().food(COOKEDENDERDRAGONMEATFOOD));
    public static final SpecimenTube SPECIMEN_TUBE = new SpecimenTube(new FabricItemSettings());
    public static final ProteinaseKTube PROTEINASE_K_TUBE = new ProteinaseKTube(new FabricItemSettings());
    public static final SplittingDNATube SPLITTING_DNA_TUBE = new SplittingDNATube(new FabricItemSettings());
    public static final BufferTube BUFFER_TUBE = new BufferTube(new FabricItemSettings());
    public static final CombineDNATube COMBINE_DNA_TUBE = new CombineDNATube(new FabricItemSettings());
    public static final AbsoluteAlcoholTube ABSOLUTE_ALCOHOL_TUBE = new AbsoluteAlcoholTube(new FabricItemSettings());
    public static final SedimentDNATube SEDIMENT_DNA_TUBE = new SedimentDNATube(new FabricItemSettings());
    public static final DragonDNATube DRAGON_DNA_TUBE = new DragonDNATube(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC));
    public static final FoxDNATube FOX_DNA_TUBE = new FoxDNATube(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC));
    public static final WhiteWolfDNATube WHITE_WOLF_DNA_TUBE = new WhiteWolfDNATube(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC));
    public static final GrayWolfDNATube GRAY_WOLF_DNA_TUBE = new GrayWolfDNATube(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC));
    public static final Listener LISTENER = new Listener(new FabricItemSettings());
    public static final Handler HANDLER = new Handler(new FabricItemSettings());
    public static final CPU CPU = new CPU(new FabricItemSettings().rarity(Rarity.RARE));
    public static final StorageCard STORAGE_CARD = new StorageCard(new FabricItemSettings());
    public static final TestServerBook TEST_SERVER_BOOK = new TestServerBook(new FabricItemSettings().maxCount(1));
    public static final CreativeEditVNStick CREATIVE_EDIT_VN_STICK = new CreativeEditVNStick(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE));


    public static final ItemGroup FC_ITEM_GROUP = FabricItemGroupBuilder.create(
                    new Identifier("furtelcraft", "fc_item_group"))
            .icon(() -> new ItemStack(FCItems.GUIDE_BOOK))
            .appendItems(itemStacks -> {
                itemStacks.add(new ItemStack(FCItems.GUIDE_BOOK));
                itemStacks.add(new ItemStack(FCItems.TEST_SERVER_BOOK));
                itemStacks.add(new ItemStack(FCItems.DIRT_BRUSH));
                itemStacks.add(new ItemStack(FCItems.SPECIMEN_KNIFE));
                itemStacks.add(new ItemStack(FCItems.IRON_TRAP));
                itemStacks.add(new ItemStack(FCItems.WOLF_MEAT));
                itemStacks.add(new ItemStack(FCItems.WOLF_MEAT_SPECIMEN));
                itemStacks.add(new ItemStack(FCItems.COOKED_WOLF_MEAT));
                itemStacks.add(new ItemStack(FCItems.FOX_MEAT));
                itemStacks.add(new ItemStack(FCItems.FOX_MEAT_SPECIMEN));
                itemStacks.add(new ItemStack(FCItems.COOKED_FOX_MEAT));
                itemStacks.add(new ItemStack(FCItems.ENDER_DRAGON_MEAT));
                itemStacks.add(new ItemStack(FCItems.ENDER_DRAGON_MEAT_SPECIMEN));
                itemStacks.add(new ItemStack(FCItems.COOKED_ENDER_DRAGON_MEAT));
                itemStacks.add(new ItemStack(FCItems.EMPTY_TUBE));
                itemStacks.add(new ItemStack(FCItems.WATER_TUBE));
                itemStacks.add(new ItemStack(FCItems.SPECIMEN_TUBE));
                itemStacks.add(new ItemStack(FCItems.PROTEINASE_K_TUBE));
                itemStacks.add(new ItemStack(FCItems.SPLITTING_DNA_TUBE));
                itemStacks.add(new ItemStack(FCItems.BUFFER_TUBE));
                itemStacks.add(new ItemStack(FCItems.COMBINE_DNA_TUBE));
                itemStacks.add(new ItemStack(FCItems.ABSOLUTE_ALCOHOL_TUBE));
                itemStacks.add(new ItemStack(FCItems.SEDIMENT_DNA_TUBE));
                itemStacks.add(new ItemStack(FCItems.DRAGON_DNA_TUBE));
                itemStacks.add(new ItemStack(FCItems.FOX_DNA_TUBE));
                itemStacks.add(new ItemStack(FCItems.WHITE_WOLF_DNA_TUBE));
                itemStacks.add(new ItemStack(FCItems.GRAY_WOLF_DNA_TUBE));
                itemStacks.add(new ItemStack(FCItems.LISTENER));
                itemStacks.add(new ItemStack(FCItems.HANDLER));
                itemStacks.add(new ItemStack(FCItems.CPU));
                itemStacks.add(new ItemStack(FCItems.STORAGE_CARD));
                itemStacks.add(new ItemStack(FCItems.CREATIVE_EDIT_VN_STICK));
            })
            .build();

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "guide_book"), GUIDE_BOOK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "specimen_knife"), SPECIMEN_KNIFE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dirt_brush"), DIRT_BRUSH);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "iron_trap"), IRON_TRAP);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "empty_tube"), EMPTY_TUBE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "water_tube"), WATER_TUBE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "wolf_meat"), WOLF_MEAT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "wolf_meat_specimen"), WOLF_MEAT_SPECIMEN);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cooked_wolf_meat"), COOKED_WOLF_MEAT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fox_meat"), FOX_MEAT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fox_meat_specimen"), FOX_MEAT_SPECIMEN);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cooked_fox_meat"), COOKED_FOX_MEAT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ender_dragon_meat"), ENDER_DRAGON_MEAT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ender_dragon_meat_specimen"), ENDER_DRAGON_MEAT_SPECIMEN);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cooked_ender_dragon_meat"), COOKED_ENDER_DRAGON_MEAT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "specimen_tube"), SPECIMEN_TUBE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "proteinase_k_tube"), PROTEINASE_K_TUBE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "splitting_dna_tube"), SPLITTING_DNA_TUBE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "buffer_tube"), BUFFER_TUBE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "combine_dna_tube"), COMBINE_DNA_TUBE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "absolute_alcohol_tube"), ABSOLUTE_ALCOHOL_TUBE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "sediment_dna_tube"), SEDIMENT_DNA_TUBE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dragon_dna_tube"), DRAGON_DNA_TUBE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fox_dna_tube"), FOX_DNA_TUBE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "white_wolf_dna_tube"), WHITE_WOLF_DNA_TUBE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "gray_wolf_dna_tube"), GRAY_WOLF_DNA_TUBE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "listener"), LISTENER);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "handler"), HANDLER);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cpu"), CPU);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "storage_card"), STORAGE_CARD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "test_server_book"), TEST_SERVER_BOOK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "creative_edit_vn_stick"), CREATIVE_EDIT_VN_STICK);
    }
}
