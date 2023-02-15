package com.vtwo.furtelcraft.furtelcraft.entities.renderer;

import com.vtwo.furtelcraft.furtelcraft.entities.TestBaseFurryMob;
import com.vtwo.furtelcraft.furtelcraft.entities.model.TestBaseFurryMobModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TestBaseFurryMobRenderer extends GeoEntityRenderer<TestBaseFurryMob> {
    public TestBaseFurryMobRenderer(EntityRendererFactory.Context context) {
        super(context, new TestBaseFurryMobModel());
    }
}
