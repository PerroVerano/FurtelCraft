package com.vtwo.furtelcraft.furtelcraft.tabs;

import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ItemsTab {
    public static ItemGroup ITEM_TAB;
    public static void init(){
        ITEM_TAB = FabricItemGroupBuilder.build(new Identifier("furtelcraft","item_tab"),()->new ItemStack(ItemInit.GUIDE_BOOK));
    }
}
