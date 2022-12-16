package com.vtwo.furtelcraft.furtelcraft.init;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TagInit {
    //=========ITEM TAGS=========//
    public static final TagKey<Item> TUBE_ITEM = TagKey.of(Registry.ITEM_KEY,new Identifier("furtelcraft","tube_item"));

    //=========BLOCK TAGS=========//


    public static void init() {

    }
}
