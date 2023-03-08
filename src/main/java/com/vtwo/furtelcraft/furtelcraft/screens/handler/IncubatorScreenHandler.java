package com.vtwo.furtelcraft.furtelcraft.screens.handler;

import com.vtwo.furtelcraft.furtelcraft.init.FCScreens;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.screens.handler
 * @NAME: IncubatorScreenHandler
 * @USER: Perano
 * @DATE: 2023/3/8
 * @TIME: 21:50
 * @YEAR: 2023
 * @MONTH: 03
 * @MONTH_NAME_SHORT: 3月
 * @MONTH_NAME_FULL: 三月
 * @DAY: 08
 * @DAY_NAME_SHORT: 周三
 * @DAY_NAME_FULL: 星期三
 * @HOUR: 21
 * @MINUTE: 50
 * @PROJECT_NAME: furtelcraft
 */
public class IncubatorScreenHandler extends ScreenHandler {
    public IncubatorScreenHandler(int syncId, PlayerInventory inventory) {
        super(FCScreens.INCUBATOR_SCREEN_HANDLER, syncId);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
