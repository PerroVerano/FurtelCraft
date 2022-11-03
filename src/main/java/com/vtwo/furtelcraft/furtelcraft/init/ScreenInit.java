package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.screens.handler.CentrifugeScreenHandler;
import com.vtwo.furtelcraft.furtelcraft.screens.handler.MagneticParticleProcessorScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class ScreenInit {
    public static final ScreenHandlerType<MagneticParticleProcessorScreenHandler> MAGNETIC_PARTICLE_PROCESSOR_SCREEN_HANDLER = new ScreenHandlerType<>(MagneticParticleProcessorScreenHandler::new);
    public static final ScreenHandlerType<CentrifugeScreenHandler> CENTRIFUGE_SCREEN_HANDLER = new ScreenHandlerType<>(CentrifugeScreenHandler::new);

    public static void init() {
        Registry.register(Registry.SCREEN_HANDLER,new Identifier(MOD_ID,"magnetic_particle_processor_screen_handler"),MAGNETIC_PARTICLE_PROCESSOR_SCREEN_HANDLER);
        Registry.register(Registry.SCREEN_HANDLER,new Identifier(MOD_ID,"centrifuge_screen_handler"),CENTRIFUGE_SCREEN_HANDLER);
    }
}
