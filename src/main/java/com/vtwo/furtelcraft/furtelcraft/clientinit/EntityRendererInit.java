package com.vtwo.furtelcraft.furtelcraft.clientinit;

import com.vtwo.furtelcraft.furtelcraft.entitymodel.TestBaseFurryMobModel;
import com.vtwo.furtelcraft.furtelcraft.entityrenderer.TestBaseFurryMobRenderer;
import com.vtwo.furtelcraft.furtelcraft.init.EntityInit;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EntityRendererInit {
    public static void init() {
        EntityRendererRegistry.register(EntityInit.TEST_BASE_FURRY_MOB, (ctx -> new TestBaseFurryMobRenderer(ctx,new TestBaseFurryMobModel(), 0.5f)));
    }
}
