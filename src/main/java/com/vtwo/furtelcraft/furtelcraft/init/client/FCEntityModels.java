package com.vtwo.furtelcraft.furtelcraft.init.client;

import com.vtwo.furtelcraft.furtelcraft.entities.renderer.TestBaseFurryMobRenderer;
import com.vtwo.furtelcraft.furtelcraft.init.FCEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class FCEntityModels {
    public static void init() {
        EntityRendererRegistry.register(FCEntities.TEST_BASE_FURRY_MOB, TestBaseFurryMobRenderer::new);
    }
}
