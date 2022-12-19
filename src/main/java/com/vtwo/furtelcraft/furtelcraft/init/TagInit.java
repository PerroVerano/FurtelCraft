package com.vtwo.furtelcraft.furtelcraft.init;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class TagInit {
    //=========ITEM TAGS=========//
    public static final TagKey<Item> TUBE_ITEM = TagKey.of(Registry.ITEM_KEY,new Identifier(MOD_ID,"tube_item"));
    public static final TagKey<Item> IS_SPECIMEN_MEAT_ITEM = TagKey.of(Registry.ITEM_KEY,new Identifier(MOD_ID,"is_specimen_meat_item"));

    //=========BLOCK TAGS=========//


    public static void init() {

    }
}
