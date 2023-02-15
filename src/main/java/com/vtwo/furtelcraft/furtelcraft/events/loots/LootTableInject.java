package com.vtwo.furtelcraft.furtelcraft.events.loots;

import com.vtwo.furtelcraft.furtelcraft.init.FCItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootTableInject {
    private static final Identifier MC_WOLF_LOOT_TABLE_ID = EntityType.WOLF.getLootTableId();
    private static final Identifier MC_FOX_LOOT_TABLE_ID = EntityType.FOX.getLootTableId();
    private static final Identifier MC_ENDER_DRAGON_LOOT_TABLE_ID = EntityType.ENDER_DRAGON.getLootTableId();
    private static final Identifier MC_SPAWN_BONUS_CHEST_ID = LootTables.SPAWN_BONUS_CHEST;
    private static final Identifier MC_BURIED_TREASURE_CHEST = LootTables.BURIED_TREASURE_CHEST;

    public static void init() {
        LootTableEvents.MODIFY.register((resourceManager, manager, id, supplier, setter) -> {
            if (MC_FOX_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(FCItems.FOX_MEAT));
                supplier.pool(poolBuilder);
            }
        });
        LootTableEvents.MODIFY.register((resourceManager, manager, id, supplier, setter) -> {
            if (MC_ENDER_DRAGON_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(14))
                        .with(ItemEntry.builder(FCItems.ENDER_DRAGON_MEAT));
                supplier.pool(poolBuilder);
            }
        });
        LootTableEvents.MODIFY.register((resourceManager, manager, id, supplier, setter) -> {
            if (MC_SPAWN_BONUS_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .with(ItemEntry.builder(FCItems.BUFFER_TUBE))
                        .rolls(ConstantLootNumberProvider.create(2))
                        .with(ItemEntry.builder(FCItems.PROTEINASE_K_TUBE))
                        .rolls(ConstantLootNumberProvider.create(2))
                        .with(ItemEntry.builder(FCItems.ABSOLUTE_ALCOHOL_TUBE));
                supplier.pool(poolBuilder);
            }
        });
        LootTableEvents.MODIFY.register((resourceManager, manager, id, supplier, setter) -> {
            if (MC_BURIED_TREASURE_CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .with(ItemEntry.builder(FCItems.BUFFER_TUBE))
                        .rolls(ConstantLootNumberProvider.create(2))
                        .with(ItemEntry.builder(FCItems.PROTEINASE_K_TUBE))
                        .rolls(ConstantLootNumberProvider.create(2))
                        .with(ItemEntry.builder(FCItems.ABSOLUTE_ALCOHOL_TUBE));
                supplier.pool(poolBuilder);
            }
        });
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (MC_WOLF_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder builder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(FCItems.WOLF_MEAT));
                tableBuilder.pool(builder);
            }
        });
    }
}
