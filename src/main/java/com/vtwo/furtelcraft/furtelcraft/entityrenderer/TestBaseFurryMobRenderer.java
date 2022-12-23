package com.vtwo.furtelcraft.furtelcraft.entityrenderer;

import com.vtwo.furtelcraft.furtelcraft.entities.TestBaseFurryMob;
import com.vtwo.furtelcraft.furtelcraft.entitymodel.TestBaseFurryMobModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class TestBaseFurryMobRenderer extends MobEntityRenderer<TestBaseFurryMob, TestBaseFurryMobModel> {


    public TestBaseFurryMobRenderer(EntityRendererFactory.Context context, TestBaseFurryMobModel entityModel, float f) {
        super(context, entityModel, f);
    }

    @Override
    public Identifier getTexture(TestBaseFurryMob entity) {
        return new Identifier(MOD_ID, "textures/entity/testbasefurrymob/texture.png");
    }
}
