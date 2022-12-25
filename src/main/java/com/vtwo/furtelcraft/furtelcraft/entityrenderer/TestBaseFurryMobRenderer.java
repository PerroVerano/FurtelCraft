package com.vtwo.furtelcraft.furtelcraft.entityrenderer;

import com.vtwo.furtelcraft.furtelcraft.entities.TestBaseFurryMob;
import com.vtwo.furtelcraft.furtelcraft.entitymodel.TestBaseFurryMobModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;
import static com.vtwo.furtelcraft.furtelcraft.clientinit.EntityModelInit.TEST_BASE_FURRY_MOB_MODEL;

public class TestBaseFurryMobRenderer extends MobEntityRenderer<TestBaseFurryMob, TestBaseFurryMobModel> {



    public TestBaseFurryMobRenderer(EntityRendererFactory.Context context) {
        super(context, new TestBaseFurryMobModel(context.getPart(TEST_BASE_FURRY_MOB_MODEL)), 0.5f);
    }

    @Override
    public Identifier getTexture(TestBaseFurryMob entity) {
        return new Identifier(MOD_ID, "textures/entity/testbasefurrymob/texture.png");
    }
}
