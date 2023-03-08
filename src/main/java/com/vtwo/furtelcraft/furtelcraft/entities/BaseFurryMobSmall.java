package com.vtwo.furtelcraft.furtelcraft.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.entities
 * @NAME: BaseFurryMobSmall
 * @USER: Perano
 * @DATE: 2023/3/8
 * @TIME: 16:41
 * @YEAR: 2023
 * @MONTH: 03
 * @MONTH_NAME_SHORT: 3月
 * @MONTH_NAME_FULL: 三月
 * @DAY: 08
 * @DAY_NAME_SHORT: 周三
 * @DAY_NAME_FULL: 星期三
 * @HOUR: 16
 * @MINUTE: 41
 * @PROJECT_NAME: furtelcraft
 */
public class BaseFurryMobSmall extends PathAwareEntity implements IAnimatable, IPlayState {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public BaseFurryMobSmall(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
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

    @Override
    public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.base_furry_mob_small.walk", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.base_furry_mob_small.stay", ILoopType.EDefaultLoopTypes.LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
