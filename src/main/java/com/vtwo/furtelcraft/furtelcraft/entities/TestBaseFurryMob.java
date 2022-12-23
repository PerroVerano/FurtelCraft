package com.vtwo.furtelcraft.furtelcraft.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class TestBaseFurryMob extends PathAwareEntity {
    public TestBaseFurryMob(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
}
