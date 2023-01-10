package com.vtwo.furtelcraft.furtelcraft.stories.missions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.stories.missions
 * @NAME: BiomeMission
 * @USER: Perano
 * @DATE: 2023/1/9
 * @TIME: 21:31
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 09
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 21
 * @MINUTE: 31
 * @PROJECT_NAME: furtelcraft
 */
public class BiomeMission {
    static RegistryKey<Biome> biome;
    static World world;
    static PlayerEntity player;
    static boolean team;

    public BiomeMission(RegistryKey<Biome> biome, World world, PlayerEntity player, boolean team) {
        BiomeMission.biome = biome;
        BiomeMission.world = world;
        BiomeMission.player = player;
        BiomeMission.team = team;
    }

    public boolean isMatches() {
        return world.getBiome(player.getBlockPos()).matchesKey(biome);
    }
}
