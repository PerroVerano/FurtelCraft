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

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class ItemInit {
    public static final FoodComponent WATERTUBEFOOD = new FoodComponent.Builder().hunger(0).saturationModifier(0f).alwaysEdible().build();
    public static final FoodComponent WOLFMEATFOOD = new FoodComponent.Builder().hunger(3).saturationModifier(2.0F).build();
    public static final FoodComponent WOLFMEATPIECEFOOD = new FoodComponent.Builder().hunger(1).saturationModifier(1.0F).build();
    public static final FoodComponent COOKEDWOLFMEATFOOD = new FoodComponent.Builder().hunger(8).saturationModifier(5.0F).build();


    public static final Item WATERTUBEREMAIN = ItemInit.EMPTY_TUBE;

    public static final GuideBook GUIDE_BOOK = new GuideBook(new FabricItemSettings().maxCount(1));
    public static final KitKnife KIT_KNIFE = new KitKnife(new FabricItemSettings().maxCount(1).maxDamage(64));
    public static final DirtBrush DIRT_BRUSH = new DirtBrush(new FabricItemSettings().maxCount(1).maxDamage(64));
    public static final IronTrap IRON_TRAP = new IronTrap(new FabricItemSettings().maxDamage(16));
    public static final EmptyTube EMPTY_TUBE = new EmptyTube(new FabricItemSettings());
    public static final WaterTube WATER_TUBE = new WaterTube(new FabricItemSettings().food(WATERTUBEFOOD).recipeRemainder(WATERTUBEREMAIN));
    public static final WolfMeat WOLF_MEAT = new WolfMeat(new FabricItemSettings().food(WOLFMEATFOOD));
    public static final WolfMeatPiece WOLF_MEAT_PIECE = new WolfMeatPiece(new FabricItemSettings().food(WOLFMEATPIECEFOOD));
    public static final CookedWolfMeat COOKED_WOLF_MEAT = new CookedWolfMeat(new FabricItemSettings().food(COOKEDWOLFMEATFOOD));
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
                itemStacks.add(new ItemStack(ItemInit.LISTENER));
                itemStacks.add(new ItemStack(ItemInit.HANDLER));
                itemStacks.add(new ItemStack(ItemInit.CPU));
                itemStacks.add(new ItemStack(ItemInit.STORAGE_CARD));
            })
            .build();

    public static void init(){
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"guide_book"),GUIDE_BOOK);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"kit_knife"),KIT_KNIFE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"dirt_brush"),DIRT_BRUSH);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"iron_trap"),IRON_TRAP);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"empty_tube"),EMPTY_TUBE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"water_tube"),WATER_TUBE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"wolf_meat"),WOLF_MEAT);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"wolf_meat_piece"),WOLF_MEAT_PIECE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"cooked_wolf_meat"),COOKED_WOLF_MEAT);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"specimen_tube"),SPECIMEN_TUBE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"proteinase_k_tube"),PROTEINASE_K_TUBE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"splitting_dna_tube"),SPLITTING_DNA_TUBE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"buffer_tube"),BUFFER_TUBE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"combine_dna_tube"),COMBINE_DNA_TUBE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"absolute_alcohol_tube"),ABSOLUTE_ALCOHOL_TUBE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"sediment_dna_tube"),SEDIMENT_DNA_TUBE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"dragon_dna_tube"),DRAGON_DNA_TUBE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"fox_dna_tube"),FOX_DNA_TUBE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"white_wolf_dna_tube"),WHITE_WOLF_DNA_TUBE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"gray_wolf_dna_tube"),GRAY_WOLF_DNA_TUBE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"listener"),LISTENER);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"handler"),HANDLER);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"cpu"),CPU);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"storage_card"),STORAGE_CARD);
    }
}
