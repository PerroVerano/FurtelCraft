package com.vtwo.furtelcraft.furtelcraft.entities.model;

import com.vtwo.furtelcraft.furtelcraft.entities.TestBaseFurryMob;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class TestBaseFurryMobModel extends AnimatedGeoModel<TestBaseFurryMob> {

	@Override
	public Identifier getModelLocation(TestBaseFurryMob object) {
		return new Identifier(MOD_ID, "geo/test_base_furry_mob.geo.json");
	}

	@Override
	public Identifier getTextureLocation(TestBaseFurryMob object) {
		return new Identifier(MOD_ID, "textures/entity/test_base_furry_mob.png");
	}

	@Override
	public Identifier getAnimationFileLocation(TestBaseFurryMob animatable) {
		return new Identifier(MOD_ID, "animations/test_base_furry_mob.animation.json");
	}
}