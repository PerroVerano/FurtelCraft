package com.vtwo.furtelcraft.furtelcraft.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.AttributeContainer;
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
    }

    @Override
    public EntityType<?> getType() {
        return super.getType();
    }

    @Override
    public AttributeContainer getAttributes() {
        return super.getAttributes();
    }
}
