package com.vtwo.furtelcraft.furtelcraft.events.loots;

import com.vtwo.furtelcraft.furtelcraft.init.ItemInit;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootTableInject {
    private static final Identifier MC_WOLF_LOOT_TABLE_ID = EntityType.WOLF.getLootTableId();
    private static final Identifier MC_FOX_LOOT_TABLE_ID = EntityType.FOX.getLootTableId();
    private static final Identifier MC_ENDER_DRAGON_LOOT_TABLE_ID = EntityType.ENDER_DRAGON.getLootTableId();

    public static void init() {
        LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
            if (MC_WOLF_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder =  FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(ItemInit.WOLF_MEAT));
                supplier.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
            if (MC_FOX_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(ItemInit.FOX_MEAT));
                supplier.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
            if (MC_ENDER_DRAGON_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(14))
                        .with(ItemEntry.builder(ItemInit.ENDER_DRAGON_MEAT));
                supplier.pool(poolBuilder);
            }
        });
    }
}