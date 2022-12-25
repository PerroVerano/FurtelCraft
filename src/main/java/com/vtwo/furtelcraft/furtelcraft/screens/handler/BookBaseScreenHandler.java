package com.vtwo.furtelcraft.furtelcraft.screens.handler;

import com.vtwo.furtelcraft.furtelcraft.init.ScreenInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;

public class BookBaseScreenHandler extends ScreenHandler {

    public BookBaseScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(ScreenInit.BOOK_BASE_SCREEN_HANDLER, syncId);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
