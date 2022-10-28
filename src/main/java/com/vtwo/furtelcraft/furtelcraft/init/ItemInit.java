package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.items.*;
import com.vtwo.furtelcraft.furtelcraft.tabs.ItemsTab;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit {
    public static final FoodComponent WATERTUBEFOOD = new FoodComponent.Builder().hunger(0).saturationModifier(0f).alwaysEdible().build();

    public static final Item WATERTUBEREMAIN = ItemInit.EMPTY_TUBE;

    public static final Item GUIDE_BOOK = new GuideBook(new FabricItemSettings().group(ItemsTab.ITEM_TAB).maxCount(1));
    public static final Item KIT_KNIFE = new KitKnife(new FabricItemSettings().group(ItemsTab.ITEM_TAB).maxCount(1).maxDamage(64));
    public static final Item DIRT_BRUSH = new DirtBrush(new FabricItemSettings().group(ItemsTab.ITEM_TAB).maxCount(1).maxDamage(64));
    public static final Item IRON_TRAP = new IronTrap(new FabricItemSettings().group(ItemsTab.ITEM_TAB).maxDamage(16));
    public static final Item EMPTY_TUBE = new EmptyTube(new FabricItemSettings().group(ItemsTab.ITEM_TAB));
    public static final Item WATER_TUBE = new WaterTube(new FabricItemSettings().group(ItemsTab.ITEM_TAB).food(WATERTUBEFOOD).recipeRemainder(WATERTUBEREMAIN));


    public static void init(){
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","guide_book"),GUIDE_BOOK);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","kit_knife"),KIT_KNIFE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","dirt_brush"),DIRT_BRUSH);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","iron_trap"),IRON_TRAP);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","empty_tube"),EMPTY_TUBE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","water_tube"),WATER_TUBE);
    }
}
