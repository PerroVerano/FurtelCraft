package com.vtwo.furtelcraft.furtelcraft;

import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import com.vtwo.furtelcraft.furtelcraft.tabs.ItemsTab;
import net.fabricmc.api.ModInitializer;

public class Furtelcraft implements ModInitializer {
    @Override
    public void onInitialize() {
        new ItemsTab().init();
        ItemInit.init();
    }
}
