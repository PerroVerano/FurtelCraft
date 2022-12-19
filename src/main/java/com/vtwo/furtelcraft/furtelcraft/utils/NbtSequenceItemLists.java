package com.vtwo.furtelcraft.furtelcraft.utils;

import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

import java.util.Objects;

public class NbtSequenceItemLists {
    //返回带有序列的样本试管
    public static ItemStack getSequenceNbtItemStack(ItemStack stack) {
        NbtCompound nbt = new NbtCompound();
        if (stack.isOf(ItemInit.WOLF_MEAT_SPECIMEN)) {
            ItemStack specimentube = ItemInit.SPECIMEN_TUBE.getDefaultStack();
            nbt.putString("Sequence","WWF7");
            specimentube.setNbt(nbt);
            return specimentube;
        }
        return ItemStack.EMPTY;
    }
    //返回样本序列NbtList
    public static NbtList setSequenceNbtList(ItemStack stack) {
        NbtList baseNbt = new NbtList();
        NbtCompound nbt = new NbtCompound();
        if (stack.isOf(ItemInit.WOLF_MEAT_SPECIMEN)) {
            nbt.putString("Sequence","WWF7");
            baseNbt.add(nbt);
        }
        return baseNbt;
    }
    //返回带有序列Nbt的最终DNA试剂
    public static ItemStack getSequenceNbtResult(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        assert nbtCompound != null;
        if (Objects.equals(nbtCompound.getString("Sequence"), "WWF7")) {
            ItemStack wolf = ItemInit.GRAY_WOLF_DNA_TUBE.getDefaultStack();
            wolf.setNbt(nbtCompound);
            return wolf;
        }
        return ItemStack.EMPTY;
    }
}
