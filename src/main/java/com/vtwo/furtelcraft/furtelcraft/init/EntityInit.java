package com.vtwo.furtelcraft.furtelcraft.init;

import com.vtwo.furtelcraft.furtelcraft.entities.TestBaseFurryMob;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.vtwo.furtelcraft.furtelcraft.Furtelcraft.MOD_ID;

public class EntityInit {
    private static final Identifier TEST_BASE_FURRY_MOB_ID = new Identifier(MOD_ID,"test_base_furry_mob");

    public static final EntityType<TestBaseFurryMob> TEST_BASE_FURRY_MOB = Registry.register(
            Registry.ENTITY_TYPE,
            TEST_BASE_FURRY_MOB_ID,
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,TestBaseFurryMob::new).dimensions(EntityDimensions.fixed(1.75F,1F)).build()
    );


    public static void init() {
        FabricDefaultAttributeRegistry.register(TEST_BASE_FURRY_MOB, TestBaseFurryMob.createLivingAttributes());
    }
}
