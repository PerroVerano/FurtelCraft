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

public class TestBaseFurryMob extends PathAwareEntity {
    public TestBaseFurryMob(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        experiencePoints = 0;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1,new MeleeAttackGoal(this,1.2,false){
            @Override
            protected void attack(LivingEntity target, double squaredDistance) {
                super.attack(target, squaredDistance);
            }
        });
        this.goalSelector.add(2,new SwimGoal(this));
        this.goalSelector.add(3,new LookAroundGoal(this));
        this.goalSelector.add(3,new WanderAroundGoal(this,0.4));
    }

    @Override
    public EntityType<?> getType() {
        return super.getType();
    }

    public static DefaultAttributeContainer.Builder getAttribute() {
        DefaultAttributeContainer.Builder builder = LivingEntity.createLivingAttributes();
        builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE,16);
        builder.add(EntityAttributes.GENERIC_ARMOR,0);

        return builder;
    }
}
