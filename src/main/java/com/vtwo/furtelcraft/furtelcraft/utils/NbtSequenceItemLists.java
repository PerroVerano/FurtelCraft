package com.vtwo.furtelcraft.furtelcraft.utils;

import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import com.vtwo.furtelcraft.furtelcraft.items.SpecimenTube;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class NbtSequenceItemLists {
    public static ItemStack getSequenceNbtItemStack(ItemStack stack) {
        NbtCompound nbt = new NbtCompound();
        if (stack.isOf(ItemInit.WOLF_MEAT_PIECE)) {
            ItemStack specimentube = ItemInit.SPECIMEN_TUBE.getDefaultStack();
            nbt.putString("Sequence","WWF7");
            specimentube.setNbt(nbt);
            return specimentube;
        }
        return ItemStack.EMPTY;
    }
}
