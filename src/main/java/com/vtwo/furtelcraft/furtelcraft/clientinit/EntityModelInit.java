package com.vtwo.furtelcraft.furtelcraft.clientinit;

import com.vtwo.furtelcraft.furtelcraft.entitymodel.TestBaseFurryMobModel;
import com.vtwo.furtelcraft.furtelcraft.entityrenderer.TestBaseFurryMobRenderer;
import com.vtwo.furtelcraft.furtelcraft.init.EntityInit;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class EntityModelInit {
    public static final String MAIN = "main";
    public static final Identifier TEST_BASE_FURRY_MOB_ID = new Identifier(MOD_ID,"test_base_furry_mob");
    public static final EntityModelLayer TEST_BASE_FURRY_MOB_MODEL = new EntityModelLayer(TEST_BASE_FURRY_MOB_ID,MAIN);
    public static void init() {
        EntityRendererRegistry.register(EntityInit.TEST_BASE_FURRY_MOB, TestBaseFurryMobRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(TEST_BASE_FURRY_MOB_MODEL, TestBaseFurryMobModel::getTexturedModelData);
    }
}
