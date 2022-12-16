package com.vtwo.furtelcraft.furtelcraft.clientinit;

import com.vtwo.furtelcraft.furtelcraft.init.ScreenInit;
import com.vtwo.furtelcraft.furtelcraft.screens.CentrifugeScreen;
import com.vtwo.furtelcraft.furtelcraft.screens.DNAMixerScreen;
import com.vtwo.furtelcraft.furtelcraft.screens.MagneticParticleProcessorScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ScreenClientInit {
    //屏幕注册——仅在客户端
    public static void init(){
        HandledScreens.register(ScreenInit.MAGNETIC_PARTICLE_PROCESSOR_SCREEN_HANDLER, MagneticParticleProcessorScreen::new);
        HandledScreens.register(ScreenInit.CENTRIFUGE_SCREEN_HANDLER, CentrifugeScreen::new);
        HandledScreens.register(ScreenInit.DNA_MIXER_SCREEN_HANDLER, DNAMixerScreen::new);
    }
}
