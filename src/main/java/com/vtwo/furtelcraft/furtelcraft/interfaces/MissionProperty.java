package com.vtwo.furtelcraft.furtelcraft.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface MissionProperty {
    World getWorld();

    PlayerEntity getPlayer();

    default Item getItem() {
        return ItemStack.EMPTY.getItem();
    }

    default boolean isCompleted() {
        return false;
    }

    default int score() {
        return 0;
    }
}
