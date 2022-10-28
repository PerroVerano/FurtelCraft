package com.vtwo.furtelcraft.furtelcraft;

import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import net.fabricmc.api.ModInitializer;

public class Furtelcraft implements ModInitializer {
    @Override
    public void onInitialize() {
        ItemInit.init();
    }
}
