package com.vtwo.furtelcraft.furtelcraft.init;

import net.minecraft.util.Identifier;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class FCEntities {
    private static final Identifier TEST_BASE_FURRY_MOB_ID = new Identifier(MOD_ID, "test_base_furry_mob");
    private static final Identifier BASE_FURRY_MOB_SMALL_ID = new Identifier(MOD_ID, "base_furry_mob_small");

//    public static final EntityType<TestBaseFurryMob> TEST_BASE_FURRY_MOB = Registry.register(
//            Registry.ENTITY_TYPE,
//            TEST_BASE_FURRY_MOB_ID,
//            FabricEntityTypeBuilder.createLiving().spawnGroup(SpawnGroup.CREATURE)
//                    .entityFactory(TestBaseFurryMob::new)
//                    .defaultAttributes(TestBaseFurryMob::getAttribute)
//                    .dimensions(EntityDimensions.fixed(0.6f, 2.1f))
//                    .trackRangeBlocks(64)
//                    .forceTrackedVelocityUpdates(true)
//                    .trackedUpdateRate(3)
//                    .build()
//    );
//
//    public static final EntityType<BaseFurryMobSmall> BASE_FURRY_MOB_SMALL = Registry.register(
//            Registry.ENTITY_TYPE,
//            BASE_FURRY_MOB_SMALL_ID,
//            FabricEntityTypeBuilder.createLiving().spawnGroup(SpawnGroup.CREATURE)
//                    .entityFactory(BaseFurryMobSmall::new)
//                    .defaultAttributes(BaseFurryMobSmall::getAttribute)
//                    .dimensions(EntityDimensions.fixed(0.5f, 1.0f))
//                    .trackRangeBlocks(64)
//                    .forceTrackedVelocityUpdates(true)
//                    .trackedUpdateRate(3)
//                    .build()
//    );


    public static void init() {
    }
}
