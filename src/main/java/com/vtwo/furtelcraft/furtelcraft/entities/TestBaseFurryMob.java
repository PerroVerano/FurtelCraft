package com.vtwo.furtelcraft.furtelcraft.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class TestBaseFurryMob extends PathAwareEntity implements IAnimatable {
    private final AnimationFactory animationFactory = GeckoLibUtil.createFactory(this);

    public TestBaseFurryMob(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        experiencePoints = 0;
        this.ignoreCameraFrustum = true;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2, false) {
            @Override
            protected void attack(LivingEntity target, double squaredDistance) {
                super.attack(target, squaredDistance);
            }
        });
        this.goalSelector.add(2, new SwimGoal(this));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.goalSelector.add(3, new WanderAroundGoal(this, 0.4));
    }

    @Override
    public EntityType<?> getType() {
        return super.getType();
    }

    public static DefaultAttributeContainer.Builder getAttribute() {
        DefaultAttributeContainer.Builder builder = LivingEntity.createLivingAttributes();
        builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16);
        builder.add(EntityAttributes.GENERIC_ARMOR, 0);

        return builder;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.walk", ILoopType.EDefaultLoopTypes.LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.animationFactory;
    }
}
