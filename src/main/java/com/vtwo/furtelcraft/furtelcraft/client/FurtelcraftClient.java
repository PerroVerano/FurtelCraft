package com.vtwo.furtelcraft.furtelcraft.client;

import com.vtwo.furtelcraft.furtelcraft.clientinit.BlockTransInit;
import com.vtwo.furtelcraft.furtelcraft.clientinit.ScreenClientInit;
import com.vtwo.furtelcraft.furtelcraft.clientinit.KeyboardInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class FurtelcraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockTransInit.init();
        ScreenClientInit.init();
        KeyboardInit.init();
    }
}
