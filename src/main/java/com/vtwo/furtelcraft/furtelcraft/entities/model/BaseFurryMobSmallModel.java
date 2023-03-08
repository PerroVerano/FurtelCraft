package com.vtwo.furtelcraft.furtelcraft.entities.model;

import com.vtwo.furtelcraft.furtelcraft.entities.BaseFurryMobSmall;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.entities.model
 * @NAME: BaseFurryMobSmallModel
 * @USER: Perano
 * @DATE: 2023/3/8
 * @TIME: 17:37
 * @YEAR: 2023
 * @MONTH: 03
 * @MONTH_NAME_SHORT: 3月
 * @MONTH_NAME_FULL: 三月
 * @DAY: 08
 * @DAY_NAME_SHORT: 周三
 * @DAY_NAME_FULL: 星期三
 * @HOUR: 17
 * @MINUTE: 37
 * @PROJECT_NAME: furtelcraft
 */
public class BaseFurryMobSmallModel extends AnimatedGeoModel<BaseFurryMobSmall> {
    @Override
    public Identifier getModelLocation(BaseFurryMobSmall object) {
        return new Identifier(MOD_ID, "geo/base_furry_mob_small.geo.json");
    }

    @Override
    public Identifier getTextureLocation(BaseFurryMobSmall object) {
        return new Identifier(MOD_ID, "textures/entity/base_furry_mob_small.png");
    }

    @Override
    public Identifier getAnimationFileLocation(BaseFurryMobSmall animatable) {
        return new Identifier(MOD_ID, "animations/base_furry_mob_small.animation.json");
    }
}
