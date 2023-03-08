package com.vtwo.furtelcraft.furtelcraft.screens;

import com.vtwo.furtelcraft.furtelcraft.screens.handler.IncubatorScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.screens
 * @NAME: IncubatorScreen
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
public class IncubatorScreen extends HandledScreen<IncubatorScreenHandler> {
    public IncubatorScreen(IncubatorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {

    }
}
