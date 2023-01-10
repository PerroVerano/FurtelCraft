package com.vtwo.furtelcraft.furtelcraft.utils;

import com.vtwo.furtelcraft.furtelcraft.stories.missions.BiomeMission;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeKeys;

import java.util.List;

/**
 * @PACKAGE_NAME: com.vtwo.furtelcraft.furtelcraft.utils
 * @NAME: Test
 * @USER: Perano
 * @DATE: 2023/1/9
 * @TIME: 21:19
 * @YEAR: 2023
 * @MONTH: 01
 * @MONTH_NAME_SHORT: 1月
 * @MONTH_NAME_FULL: 一月
 * @DAY: 09
 * @DAY_NAME_SHORT: 周一
 * @DAY_NAME_FULL: 星期一
 * @HOUR: 21
 * @MINUTE: 19
 * @PROJECT_NAME: furtelcraft
 */
public class Missions {
    static World world;
    static PlayerEntity player;

    private static final List<WWidget> MISSIONS;

    static {
        MISSIONS = List.of(
                Mission1()
        );
    }

    public Missions(World world, PlayerEntity player) {
        Missions.world = world;
        Missions.player = player;
    }

    public static int getMissionsCount() {
        return MISSIONS.size();
    }

    public static WWidget getAssignMission(int index) {
        return MISSIONS.get(index);
    }

    private static WGridPanel Mission1() {
        WGridPanel root = PageUtils.getDefaultGridPanel();
        BiomeMission mission = new BiomeMission(BiomeKeys.PLAINS, Missions.world, Missions.player, false);
        return root;
    }
}
