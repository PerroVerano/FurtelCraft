package com.vtwo.furtelcraft.furtelcraft;

import com.vtwo.furtelcraft.furtelcraft.events.loots.LootTableInject;
import com.vtwo.furtelcraft.furtelcraft.init.*;
import net.fabricmc.api.ModInitializer;

public class Furtelcraft implements ModInitializer {
    public static final String MOD_ID = "furtelcraft";
    @Override
    public void onInitialize() {
        FCItems.init();
        FCBlocks.init();
        FCScreens.init();
        FCRecipes.init();
        FCTags.init();
        LootTableInject.init();
        FCEntities.init();
        FCNetPacks.init();

//        GeckoLib.initialize();
    }
}
