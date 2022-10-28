package com.vtwo.furtelcraft.furtelcraft.client;

import com.vtwo.furtelcraft.furtelcraft.blocktrans.BlockTransInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class FurtelcraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockTransInit.init();
    }
}
