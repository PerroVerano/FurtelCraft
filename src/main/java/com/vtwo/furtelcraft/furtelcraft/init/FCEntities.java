package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.entities.TestBaseFurryMob;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class FCEntities {
    private static final Identifier TEST_BASE_FURRY_MOB_ID = new Identifier(MOD_ID, "test_base_furry_mob");

    public static final EntityType<TestBaseFurryMob> TEST_BASE_FURRY_MOB = Registry.register(
            Registry.ENTITY_TYPE,
            TEST_BASE_FURRY_MOB_ID,
            FabricEntityTypeBuilder.createLiving().spawnGroup(SpawnGroup.CREATURE)
                    .entityFactory(TestBaseFurryMob::new)
                    .defaultAttributes(TestBaseFurryMob::getAttribute)
                    .dimensions(EntityDimensions.fixed(0.6f, 2.1f))
                    .trackRangeBlocks(64)
                    .forceTrackedVelocityUpdates(true)
                    .trackedUpdateRate(3)
                    .build()
    );


    public static void init() {
    }
}