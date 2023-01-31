package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.screens.handler.*;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class ScreenInit {
    public static final ScreenHandlerType<MagneticParticleProcessorScreenHandler> MAGNETIC_PARTICLE_PROCESSOR_SCREEN_HANDLER = new ScreenHandlerType<>(MagneticParticleProcessorScreenHandler::new);
    public static final ScreenHandlerType<CentrifugeScreenHandler> CENTRIFUGE_SCREEN_HANDLER = new ScreenHandlerType<>(CentrifugeScreenHandler::new);
    public static final ScreenHandlerType<DNAMixerScreenHandler> DNA_MIXER_SCREEN_HANDLER = new ScreenHandlerType<>(DNAMixerScreenHandler::new);
    public static final ScreenHandlerType<TubeHolderScreenHandler> TUBE_HOLDER_SCREEN_HANDLER = new ScreenHandlerType<>(TubeHolderScreenHandler::new);
    public static final ScreenHandlerType<RackScreenHandler> RACK_SCREEN_HANDLER = new ScreenHandlerType<>(RackScreenHandler::new);


    public static void init() {
        Registry.register(Registry.SCREEN_HANDLER, new Identifier(MOD_ID, "magnetic_particle_processor_screen_handler"), MAGNETIC_PARTICLE_PROCESSOR_SCREEN_HANDLER);
        Registry.register(Registry.SCREEN_HANDLER, new Identifier(MOD_ID, "centrifuge_screen_handler"), CENTRIFUGE_SCREEN_HANDLER);
        Registry.register(Registry.SCREEN_HANDLER, new Identifier(MOD_ID, "dna_mixer_screen_handler"), DNA_MIXER_SCREEN_HANDLER);
        Registry.register(Registry.SCREEN_HANDLER, new Identifier(MOD_ID, "tube_holder_screen_handler"), TUBE_HOLDER_SCREEN_HANDLER);
        Registry.register(Registry.SCREEN_HANDLER, new Identifier(MOD_ID, "rack_screen_handler"), RACK_SCREEN_HANDLER);
    }
}
