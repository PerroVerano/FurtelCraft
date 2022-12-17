package com.vtwo.furtelcraft.furtelcraft.utils;

import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

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

    public static NbtList setSequenceNbtList(ItemStack stack) {
        NbtList baseNbt = new NbtList();
        NbtCompound nbt = new NbtCompound();
        if (stack.isOf(ItemInit.WOLF_MEAT_PIECE)) {
            nbt.putString("Sequence","WWF7");
            baseNbt.add(nbt);
        }
        return baseNbt;
    }
}
