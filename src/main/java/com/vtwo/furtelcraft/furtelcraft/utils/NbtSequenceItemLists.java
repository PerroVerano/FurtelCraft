package com.vtwo.furtelcraft.furtelcraft.utils;

import com.vtwo.furtelcraft.furtelcraft.init.FCItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

public class NbtSequenceItemLists {
    //返回带有序列的样本试管
    public static ItemStack getSequenceNbtItemStack(ItemStack stack) {
        NbtCompound nbt = new NbtCompound();
        ItemStack specimentube = FCItems.SPECIMEN_TUBE.getDefaultStack();
        if (stack.isOf(FCItems.WOLF_MEAT_SPECIMEN)) {
            nbt.putString("Sequence", MammaliaLists.getWolfRandomSequence());
            specimentube.setNbt(nbt);
            return specimentube;
        } else if (stack.isOf(FCItems.FOX_MEAT_SPECIMEN)) {
            nbt.putString("Sequence", MammaliaLists.getFoxRandomSequence());
            specimentube.setNbt(nbt);
            return specimentube;
        } else if (stack.isOf(FCItems.ENDER_DRAGON_MEAT_SPECIMEN)) {
            nbt.putString("Sequence", SauropsidaLists.getDragonRandomSequence());
            specimentube.setNbt(nbt);
            return specimentube;
        }
        return ItemStack.EMPTY;
    }
    //返回样本序列NbtList
    public static NbtList setSequenceNbtList(ItemStack stack) {
        NbtList baseNbt = new NbtList();
        NbtCompound nbt = new NbtCompound();
        if (stack.isOf(FCItems.WOLF_MEAT_SPECIMEN)) {
            nbt.putString("Sequence", "WWF7");
            baseNbt.add(nbt);
        }
        return baseNbt;
    }
    //返回带有序列Nbt的最终DNA试剂
    public static ItemStack getSequenceNbtResult(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        assert nbtCompound != null;
        String nbt = nbtCompound.getString("Sequence");
        if (nbt.contains("WWFG")) {
            ItemStack wolf = FCItems.GRAY_WOLF_DNA_TUBE.getDefaultStack();
            wolf.setNbt(nbtCompound);
            return wolf;
        } else if (nbt.contains("WFF")) {
            ItemStack fox = FCItems.FOX_DNA_TUBE.getDefaultStack();
            fox.setNbt(nbtCompound);
            return fox;
        } else if (nbt.contains("GD")) {
            ItemStack dragon = FCItems.DRAGON_DNA_TUBE.getDefaultStack();
            dragon.setNbt(nbtCompound);
            return dragon;
        } else if (nbt.contains("WWFW")) {
            ItemStack wolf = FCItems.WHITE_WOLF_DNA_TUBE.getDefaultStack();
            wolf.setNbt(nbtCompound);
            return wolf;
        }
        return ItemStack.EMPTY;
    }
}
