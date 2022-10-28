package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.items.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ItemInit {
    public static final FoodComponent WATERTUBEFOOD = new FoodComponent.Builder().hunger(0).saturationModifier(0f).alwaysEdible().build();
    public static final FoodComponent WOLFMEATFOOD = new FoodComponent.Builder().hunger(3).saturationModifier(2.0F).build();
    public static final FoodComponent WOLFMEATPIECEFOOD = new FoodComponent.Builder().hunger(1).saturationModifier(1.0F).build();
    public static final FoodComponent COOKEDWOLFMEATFOOD = new FoodComponent.Builder().hunger(8).saturationModifier(5.0F).build();


    public static final Item WATERTUBEREMAIN = ItemInit.EMPTY_TUBE;

    public static final Item GUIDE_BOOK = new GuideBook(new FabricItemSettings().maxCount(1));
    public static final Item KIT_KNIFE = new KitKnife(new FabricItemSettings().maxCount(1).maxDamage(64));
    public static final Item DIRT_BRUSH = new DirtBrush(new FabricItemSettings().maxCount(1).maxDamage(64));
    public static final Item IRON_TRAP = new IronTrap(new FabricItemSettings().maxDamage(16));
    public static final Item EMPTY_TUBE = new EmptyTube(new FabricItemSettings());
    public static final Item WATER_TUBE = new WaterTube(new FabricItemSettings().food(WATERTUBEFOOD).recipeRemainder(WATERTUBEREMAIN));
    public static final Item WOLF_MEAT = new WolfMeat(new FabricItemSettings().food(WOLFMEATFOOD));
    public static final Item WOLF_MEAT_PIECE = new WolfMeatPiece(new FabricItemSettings().food(WOLFMEATPIECEFOOD));
    public static final Item COOKED_WOLF_MEAT = new CookedWolfMeat(new FabricItemSettings().food(COOKEDWOLFMEATFOOD));
    public static final Item SPECIMEN_TUBE = new SpecimenTube(new FabricItemSettings());
    public static final Item PROTEINASE_K_TUBE = new ProteinaseKTube(new FabricItemSettings());
    public static final Item SPLITTING_DNA_TUBE = new SplittingDNATube(new FabricItemSettings());
    public static final Item BUFFER_TUBE = new BufferTube(new FabricItemSettings());
    public static final Item COMBINE_DNA_TUBE = new CombineDNATube(new FabricItemSettings());
    public static final Item ABSOLUTE_ALCOHOL_TUBE = new AbsoluteAlcoholTube(new FabricItemSettings());
    public static final Item SEDIMENT_DNA_TUBE = new SedimentDNATube(new FabricItemSettings());
    public static final Item DRAGON_DNA_TUBE = new DragonDNATube(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC));
    public static final Item FOX_DNA_TUBE = new FoxDNATube(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC));
    public static final Item WHITE_WOLF_DNA_TUBE = new WhiteWolfDNATube(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC));
    public static final Item GRAY_WOLF_DNA_TUBE = new GrayWolfDNATube(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC));


    public static final ItemGroup FC_ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier("furtelcraft","fc_item_group"))
            .icon(() -> new ItemStack(ItemInit.GUIDE_BOOK))
            .appendItems(itemStacks -> {
                itemStacks.add(new ItemStack(ItemInit.GUIDE_BOOK));
                itemStacks.add(new ItemStack(ItemInit.DIRT_BRUSH));
                itemStacks.add(new ItemStack(ItemInit.KIT_KNIFE));
                itemStacks.add(new ItemStack(ItemInit.IRON_TRAP));
                itemStacks.add(new ItemStack(ItemInit.WOLF_MEAT));
                itemStacks.add(new ItemStack(ItemInit.WOLF_MEAT_PIECE));
                itemStacks.add(new ItemStack(ItemInit.COOKED_WOLF_MEAT));
                itemStacks.add(new ItemStack(ItemInit.EMPTY_TUBE));
                itemStacks.add(new ItemStack(ItemInit.WATER_TUBE));
                itemStacks.add(new ItemStack(ItemInit.SPECIMEN_TUBE));
                itemStacks.add(new ItemStack(ItemInit.PROTEINASE_K_TUBE));
                itemStacks.add(new ItemStack(ItemInit.SPLITTING_DNA_TUBE));
                itemStacks.add(new ItemStack(ItemInit.BUFFER_TUBE));
                itemStacks.add(new ItemStack(ItemInit.COMBINE_DNA_TUBE));
                itemStacks.add(new ItemStack(ItemInit.ABSOLUTE_ALCOHOL_TUBE));
                itemStacks.add(new ItemStack(ItemInit.SEDIMENT_DNA_TUBE));
                itemStacks.add(new ItemStack(ItemInit.DRAGON_DNA_TUBE));
                itemStacks.add(new ItemStack(ItemInit.FOX_DNA_TUBE));
                itemStacks.add(new ItemStack(ItemInit.WHITE_WOLF_DNA_TUBE));
                itemStacks.add(new ItemStack(ItemInit.GRAY_WOLF_DNA_TUBE));
            })
            .build();

    public static void init(){
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","guide_book"),GUIDE_BOOK);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","kit_knife"),KIT_KNIFE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","dirt_brush"),DIRT_BRUSH);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","iron_trap"),IRON_TRAP);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","empty_tube"),EMPTY_TUBE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","water_tube"),WATER_TUBE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","wolf_meat"),WOLF_MEAT);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","wolf_meat_piece"),WOLF_MEAT_PIECE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","cooked_wolf_meat"),COOKED_WOLF_MEAT);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","specimen_tube"),SPECIMEN_TUBE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","proteinase_k_tube"),PROTEINASE_K_TUBE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","splitting_dna_tube"),SPLITTING_DNA_TUBE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","buffer_tube"),BUFFER_TUBE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","combine_dna_tube"),COMBINE_DNA_TUBE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","absolute_alcohol_tube"),ABSOLUTE_ALCOHOL_TUBE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","sediment_dna_tube"),SEDIMENT_DNA_TUBE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","dragon_dna_tube"),DRAGON_DNA_TUBE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","fox_dna_tube"),FOX_DNA_TUBE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","white_wolf_dna_tube"),WHITE_WOLF_DNA_TUBE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","gray_wolf_dna_tube"),GRAY_WOLF_DNA_TUBE);
    }
}
