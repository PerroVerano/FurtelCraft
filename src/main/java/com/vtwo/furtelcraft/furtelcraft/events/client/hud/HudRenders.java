package com.vtwo.furtelcraft.furtelcraft.events.client.hud;

import com.vtwo.furtelcraft.furtelcraft.screens.hud.TubeHolderHud;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.events.client.hud
 * @NAME: HudRenders
 * @USER: Perano
 * @DATE: 2023/2/1
 * @TIME: 12:57
 * @YEAR: 2023
 * @MONTH: 02
 * @MONTH_NAME_SHORT: 2月
 * @MONTH_NAME_FULL: 二月
 * @DAY: 01
 * @DAY_NAME_SHORT: 周三
 * @DAY_NAME_FULL: 星期三
 * @HOUR: 12
 * @MINUTE: 57
 * @PROJECT_NAME: furtelcraft
 */
public class HudRenders {
    public static void init() {
        HudRenderCallback.EVENT.register(new TubeHolderHud());
    }
}
