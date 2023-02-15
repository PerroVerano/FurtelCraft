package com.vtwo.furtelcraft.furtelcraft.client;

import com.vtwo.furtelcraft.furtelcraft.events.client.hud.HudRenders;
import com.vtwo.furtelcraft.furtelcraft.init.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class FurtelcraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FCBlockTrans.init();
        FCScreenClients.init();
        FCKeyboards.init();
        FCEntityModels.init();
        FCClientNetPacks.init();
        HudRenders.init();
    }
}
