package com.vtwo.furtelcraft.furtelcraft.entities.renderer;

import com.vtwo.furtelcraft.furtelcraft.entities.BaseFurryMobSmall;
import com.vtwo.furtelcraft.furtelcraft.entities.model.BaseFurryMobSmallModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.entities.renderer
 * @NAME: BaseFurryMobSmallRender
 * @USER: Perano
 * @DATE: 2023/3/8
 * @TIME: 17:39
 * @YEAR: 2023
 * @MONTH: 03
 * @MONTH_NAME_SHORT: 3月
 * @MONTH_NAME_FULL: 三月
 * @DAY: 08
 * @DAY_NAME_SHORT: 周三
 * @DAY_NAME_FULL: 星期三
 * @HOUR: 17
 * @MINUTE: 39
 * @PROJECT_NAME: furtelcraft
 */
public class BaseFurryMobSmallRender extends GeoEntityRenderer<BaseFurryMobSmall> {
    public BaseFurryMobSmallRender(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BaseFurryMobSmallModel());
    }
}
