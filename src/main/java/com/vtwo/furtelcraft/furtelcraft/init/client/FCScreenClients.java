package com.vtwo.furtelcraft.furtelcraft.init.client;

import com.vtwo.furtelcraft.furtelcraft.init.FCScreens;
import com.vtwo.furtelcraft.furtelcraft.screens.*;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class FCScreenClients {
    //屏幕注册——仅在客户端
    public static void init() {
        HandledScreens.register(FCScreens.MAGNETIC_PARTICLE_PROCESSOR_SCREEN_HANDLER, MagneticParticleProcessorScreen::new);
        HandledScreens.register(FCScreens.CENTRIFUGE_SCREEN_HANDLER, CentrifugeScreen::new);
        HandledScreens.register(FCScreens.DNA_MIXER_SCREEN_HANDLER, DNAMixerScreen::new);
        HandledScreens.register(FCScreens.TUBE_HOLDER_SCREEN_HANDLER, TubeHolderScreen::new);
        HandledScreens.register(FCScreens.RACK_SCREEN_HANDLER, RackScreen::new);
        HandledScreens.register(FCScreens.INCUBATOR_SCREEN_HANDLER, IncubatorScreen::new);
    }
}
