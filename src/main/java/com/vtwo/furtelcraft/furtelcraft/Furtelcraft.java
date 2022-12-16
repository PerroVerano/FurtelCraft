package com.vtwo.furtelcraft.furtelcraft;

import com.vtwo.furtelcraft.furtelcraft.init.*;
import net.fabricmc.api.ModInitializer;

public class Furtelcraft implements ModInitializer {
    public static final String MOD_ID = "furtelcraft";
    @Override
    public void onInitialize() {
        ItemInit.init();
        BlockInit.init();
        ScreenInit.init();
        RecipeInit.init();
        TagInit.init();
    }
}
