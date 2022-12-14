package com.vtwo.furtelcraft.furtelcraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SpecimenTube extends Item {
    public SpecimenTube(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack itemStack = new ItemStack(this);
        itemStack.getOrCreateNbt().putString("Squence","NULL");
        return itemStack;
    }


}
