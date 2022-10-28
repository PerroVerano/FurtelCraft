package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.items.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit {
    public static final FoodComponent WATERTUBEFOOD = new FoodComponent.Builder().hunger(0).saturationModifier(0f).alwaysEdible().build();

    public static final Item WATERTUBEREMAIN = ItemInit.EMPTY_TUBE;

    public static final Item GUIDE_BOOK = new GuideBook(new FabricItemSettings().maxCount(1));
    public static final Item KIT_KNIFE = new KitKnife(new FabricItemSettings().maxCount(1).maxDamage(64));
    public static final Item DIRT_BRUSH = new DirtBrush(new FabricItemSettings().maxCount(1).maxDamage(64));
    public static final Item IRON_TRAP = new IronTrap(new FabricItemSettings().maxDamage(16));
    public static final Item EMPTY_TUBE = new EmptyTube(new FabricItemSettings());
    public static final Item WATER_TUBE = new WaterTube(new FabricItemSettings().food(WATERTUBEFOOD).recipeRemainder(WATERTUBEREMAIN));

    public static final ItemGroup FC_ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier("furtelcraft","fc_item_group"))
            .icon(() -> new ItemStack(ItemInit.GUIDE_BOOK))
            .appendItems(itemStacks -> {
                itemStacks.add(new ItemStack(ItemInit.GUIDE_BOOK));
                itemStacks.add(new ItemStack(ItemInit.DIRT_BRUSH));
                itemStacks.add(new ItemStack(ItemInit.KIT_KNIFE));
                itemStacks.add(new ItemStack(ItemInit.IRON_TRAP));
                itemStacks.add(new ItemStack(ItemInit.EMPTY_TUBE));
                itemStacks.add(new ItemStack(ItemInit.WATER_TUBE));
            })
            .build();

    public static void init(){
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","guide_book"),GUIDE_BOOK);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","kit_knife"),KIT_KNIFE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","dirt_brush"),DIRT_BRUSH);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","iron_trap"),IRON_TRAP);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","empty_tube"),EMPTY_TUBE);
        Registry.register(Registry.ITEM,new Identifier("furtelcraft","water_tube"),WATER_TUBE);
    }
}
